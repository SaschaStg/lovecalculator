package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.game.Answers;
import de.hdm_stuttgart.love_calculator.game.Catalog;
import de.hdm_stuttgart.love_calculator.game.Session;
import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.game.Question;
import de.hdm_stuttgart.love_calculator.user.User1;
import de.hdm_stuttgart.love_calculator.user.User2;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.WindowEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Optional;

public class QuestionsFactory {

    //create logger for every class
    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);

    public static void generateQuestionPane(Session session, StackPane pane) {
        //style sheet is added to pane
        pane.getParent().getStylesheets().add(FxmlGuiDriver.class.getResource("/styles/styles.css").toExternalForm());

        generateProgressHeader(session,pane);

        //Load question label
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
        pane.getChildren().addAll(label);

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
                LOGGER.error("Error: Input type is invalid! (" + input + ")");
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
    private static void generateRadiobuttons(Answers answers, Session session, StackPane pane) {

        ToggleGroup radioGroup = new ToggleGroup();

        for (int i = 0; i < answers.getAnswersCount(); i++) {

            LOGGER.debug("Generating Answers: " + answers.getAnswer(i));

            RadioButton radioButton = new RadioButton();
            radioButton.setText(answers.getAnswer(i));
            radioButton.setTranslateY(i * 30);

            radioButton.setToggleGroup(radioGroup);
            radioButton.selectedProperty().addListener((observableProperty, oldValue, newValue) -> {
                session.removeAnswer(radioButton.getText());
                session.addAnswer(radioButton.getText());
                LOGGER.info("Selected radiobutton: " + radioButton.getText());
            });
            pane.getChildren().addAll(radioButton);
        }
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

    public static void generateProgressHeader(Session session, StackPane pane){

        ImageView nameActive = new ImageView();


        switch (session.getCurrentIndex()) {

            case 0:
                if(session.isClassicMode()){
                    setImage(nameActive, "/images/classic-name-active.jpg");
                }else{
                    setImage(nameActive, "/images/name-active.jpg");
                }
                break;
            case 1:
                if(session.isClassicMode()){
                    setImage(nameActive, "/images/classic-studium-active.jpg");
                }else{
                    setImage(nameActive, "/images/studium-active.jpg");
                }
                break;
            default:
                LOGGER.error("Index out of bonds exception aka theres no image dumb ass");
        }

        nameActive.setFitWidth(1065);
        nameActive.setFitHeight(150);
        nameActive.setTranslateY(-263);
        pane.getChildren().add(nameActive);

    }

    private static void setImage(ImageView view, String path) {
        view.setImage(new Image(FxmlGuiDriver.class.getResource(path).toExternalForm()));
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