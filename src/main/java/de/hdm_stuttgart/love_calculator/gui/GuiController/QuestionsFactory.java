package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.game.Answers;
import de.hdm_stuttgart.love_calculator.game.Catalog;
import de.hdm_stuttgart.love_calculator.game.Session;
import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.game.Question;
import de.hdm_stuttgart.love_calculator.user.User1;
import de.hdm_stuttgart.love_calculator.user.User2;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.WindowEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Optional;

public class QuestionsFactory {
    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);

    public static void generateQuestionPane(Session session, StackPane pane) {
        pane.getParent().getStylesheets()
                .add(FxmlGuiDriver.class.getResource("/styles/styles.css").toExternalForm());

        //Load question
        Label label = new Label();
        label.getStyleClass().add("questionLabel");
        label.getStyleClass().add("textOutput");
        label.setTranslateY(-150);

        //gets question q from map and the answers from the current index via getQuestions / getAnswers method
        Question question = Catalog.INSTANCE.getQuestion(session.getCurrentQuestionIndex());
        Answers answers = Catalog.INSTANCE.getAnswers(question);

        //returns true if its user1's turn
        if (session.isUser1Turn()) {
            //sets question text for user1, otherwise set question text for user2
            label.setText(question.questionContent);
        } else {
            label.setText(question.questionContentUser2);
        }

        //add label to pane
        pane.getChildren().add(label);

        //check type of question, then display required inputs
        InputType input = Enum.valueOf(InputType.class, answers.inputType);
        switch (input) {
            case CHECKBOX:
                generateCheckboxes(answers, session, pane);
                break;
            case RADIOBUTTON:
                generateRadiobuttons(answers, session, pane);
                break;
            case TEXTFIELD:
                generateTextField(session, pane);
                break;
            case SLIDER:
                break;
            default:
                FxmlGuiDriver.log.error("Error: Inputtype is invalid! (" + input + ")");
                break;
        }

        LOGGER.info("Input-type for question " + session.getCurrentQuestionIndex() + " is " + input);
    }

    private static void generateTextField(Session session, StackPane pane) {
        TextField field = new TextField();

        field.focusedProperty().addListener((observableProperty, oldValue, newValue) -> {
            if (!newValue) {
                // We have lost focus, update answer
                session.clearAnswers();

                String answer = field.getText().trim();
                if (!answer.isBlank()) {
                    session.addAnswer(answer);
                }
            }
        });

        pane.getChildren().add(field);
    }

    // TODO same as generateCheckboxes
    private static void generateRadiobuttons(Session session, Answers answers, StackPane pane) {
        //Button[] button1 = new Button[Catalog.getAnswerList().get(index).answerOptions.size()];
       /* RadioButton[] radioButtons = new RadioButton[answers.getAnswersCount()];
        ToggleGroup radioGroup = new ToggleGroup();

        for (int i = 0; i < radioButtons.length; i++) {
            /*button1[i] = new Button();
            button1[i].setText(Catalog.getAnswerList().get(index).answerOptions.get(i));
            button1[i].setTranslateY(i*30);

            System.out.println(button1[i].getText());
            pane.getChildren().addAll(button1[i]);*/

           /* radioButtons[i] = new RadioButton();
            radioButtons[i].setText(answers.getAnswer(i));
            radioButtons[i].setTranslateY(i * 30);
            radioButtons[i].setToggleGroup(radioGroup);
            //System.out.println(radioButtons[i].getText());
            pane.getChildren().addAll(radioButtons[i]);

        }

        radioButtonClone = radioButtons;*/
    }

    private static void generateCheckboxes(Answers answers, Session session, StackPane pane) {

        for (int i = 0; i < answers.getAnswersCount(); i++) {

            LOGGER.debug("Generating Answers: " + answers.getAnswer(i));

            CheckBox checkBox = new CheckBox();
            checkBox.setText(answers.getAnswer(i));
            checkBox.setTranslateY(i * 30);

            checkBox.selectedProperty().addListener((observableProperty, oldValue, newValue) -> {
                if (newValue) {
                    session.addAnswer(checkBox.getText());
                } else {
                    session.removeAnswer(checkBox.getText());
                }
            });

            pane.getChildren().addAll(checkBox);
        }
    }

    // Returns true if
    public static Optional<Boolean> tryAdvanceTurn(Session session, StackPane pane) {
        if (session.hasAnswers()) {
            pane.getChildren().clear();

            if (session.nextTurn()) {
                // game continues
                return Optional.of(true);
            } else {
                // show result
                return Optional.of(false);
            }
        } else {
            LOGGER.info("Es wurde keine Antwort ausgewählt!");
            return Optional.empty();
        }
    }

    // public static boolean checkEqualAnswers(int index) {
    //     if (Arrays.deepToString(User1.result.get(index)).equals(Arrays.deepToString(User2.result.get(index)))) {
    //         FxmlGuiDriver.log.info("Ergebnisse für Antwort " + index + " sind gleich!");
    //         FxmlGuiDriver.log.info("User 1 input: " + Arrays.deepToString(User1.result.get(index)));
    //         FxmlGuiDriver.log.info("User 2 input: " + Arrays.deepToString(User2.result.get(index)));
    //         return true;
    //     }
    //     FxmlGuiDriver.log.info("Ergebnisse für Antwort " + index + " sind NICHT gleich!");
    //     FxmlGuiDriver.log.info("User 1 input: " + Arrays.deepToString(User1.result.get(index)));
    //     FxmlGuiDriver.log.info("User 2 input: " + Arrays.deepToString(User2.result.get(index)));
    //     return false;
    // }
}