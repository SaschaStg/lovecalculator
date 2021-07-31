package de.hdm_stuttgart.love_calculator.Gui.GuiFactory;

import de.hdm_stuttgart.love_calculator.Game.Answers;
import de.hdm_stuttgart.love_calculator.Game.Catalog;
import de.hdm_stuttgart.love_calculator.Game.Session;
import de.hdm_stuttgart.love_calculator.Gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.Game.Question;
import de.hdm_stuttgart.love_calculator.Gui.InputType;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Optional;

/**
 * Dynamically generate the GamePane with all the labels, radiobuttons, textfields and buttons and fill
 * it with the questions and answers
 */
public class QuestionsFactory {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);

    /**
     * Generates the labels, radiobuttons, textfield from the questions and answers objects.
     * Dynamically loads the question in a label and the answer possibilitys in a textfield or radio button
     *
     * @param session the session from the game to know what session to work with
     * @param pane    the StackPane that is opened
     */
    public static void generateQuestionPane(Session session, StackPane pane) {
        //style sheet is added to pane
        pane.getParent().getStylesheets().add(Objects.requireNonNull(FxmlGuiDriver.class.getResource("/styles/styles.css")).toExternalForm());
        LOGGER.info("Loaded styles.css");

        //Generate Progress Header on top with icons
        generateProgressHeader(session, pane);

        //Load question label
        Label label = new Label();
        label.getStyleClass().add("questionLabel");
        label.getStyleClass().add("textOutput");
        label.setTranslateY(-90);

        //gets question q from map and the answers from the current index via getQuestions / getAnswers method
        Question question = Catalog.INSTANCE.getQuestion(session.getCurrentQuestionIndex());
        Answers answers = Catalog.INSTANCE.getAnswers(question);

        //returns true if its user1's turn
        if (session.isUser1Turn()) {
            //sets question text for user1, otherwise set question text for user2
            label.setText(question.QUESTIONCONTENT);
        } else {
            label.setText(question.QUESTIONCONTENTUSER2);
        }

        //add label to pane
        pane.getChildren().addAll(label);

        //check type of question, then display required inputs
        InputType input = Enum.valueOf(InputType.class, answers.INPUTTYPE);
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
            default:
                LOGGER.error("Error: Input type is invalid! (" + input + ")");

                break;

        }

        LOGGER.info("Input-type for question " + session.getCurrentQuestionIndex() + " is " + input);
    }

    /**
     * Generates the TextField for the user input and safes that input in the answer object if the user lost focus
     *
     * @param session the session from the game to know what session to work with
     * @param pane    the StackPane that is opened
     */
    private static void generateTextField(Session session, StackPane pane) {
        TextField field = new TextField();
        field.setTranslateY(20);

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
        field.requestFocus();
    }

    /**
     * Dynamically generates the radio buttons for the answer possibilitys and displays them in a flowpane
     *
     * @param answers the answers for which the radio buttons should be generated
     * @param session the session from the game to know what session to work with
     * @param pane    the StackPane that is opened
     */
    private static void generateRadiobuttons(Answers answers, Session session, StackPane pane) {

        FlowPane flowpane = new FlowPane();
        flowpane.setMaxWidth(900);
        flowpane.setMaxHeight(500);
        flowpane.setTranslateY(220);
        flowpane.setHgap(20);
        flowpane.setVgap(20);
        pane.getChildren().add(flowpane);

        ToggleGroup radioGroup = new ToggleGroup();

        for (int i = 0; i < answers.getAnswersCount(); i++) {

            LOGGER.info("Generating Answers: " + answers.getAnswer(i));

            RadioButton radioButton = new RadioButton();
            radioButton.setText(answers.getAnswer(i));

            radioButton.setToggleGroup(radioGroup);
            radioButton.selectedProperty().addListener((observableProperty, oldValue, newValue) -> {
                session.removeAnswer(radioButton.getText());
                session.addAnswer(radioButton.getText());
                LOGGER.info("Selected radiobutton: " + radioButton.getText());
            });
            flowpane.getChildren().addAll(radioButton);
        }
    }

    /**
     * Dynamically generates the checkboxes for the answer possibilitys and displays them in a flowpane
     *
     * @param answers the answers for which the checkboxes should be generated
     * @param session the session from the game to know what session to work with
     * @param pane    the StackPane that is opened
     */
    private static void generateCheckboxes(Answers answers, Session session, StackPane pane) {

        for (int i = 0; i < answers.getAnswersCount(); i++) {

            LOGGER.info("Generating Answers: " + answers.getAnswer(i));

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

    /**
     * Decide if the next question should be displayed or if all questions are answered then show the result page
     *
     * @param session the session from the game to know what session to work with
     * @param pane    the StackPane that is opened
     * @return true if game should continue, otherwise show results if false
     */
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
            LOGGER.debug("No answer is selected!");
            return Optional.empty();
        }
    }

    /**
     * Generates the progressbar and icons on top of the fxml to display the progress of the answered questions
     *
     * @param session the session from the game to know what session to work with
     * @param pane    the StackPane that is opened
     */
    public static void generateProgressHeader(Session session, StackPane pane) {

        ImageView nameActive = new ImageView();
        ProgressBar progressBar = new ProgressBar();

        switch (session.getCurrentIndex()) {

            case 0:
                if (session.isCLASSICMODE()) {
                    setProgress(nameActive, "/images/classic-name-active.png", progressBar, 0.4, session);
                } else {
                    setProgress(nameActive, "/images/advanced-name-active.png", progressBar, 0.1, session);
                }
                break;

            case 1:
                if (session.isCLASSICMODE()) {
                    setProgress(nameActive, "/images/classic-studium-active.png", progressBar, 0.8, session);
                } else {
                    setProgress(nameActive, "/images/advanced-studium-active.png", progressBar, 0.18, session);
                }
                break;

            case 2:
                setProgress(nameActive, "/images/advanced-zukunft-active.png", progressBar, 0.36, session);
                break;
            case 3:
                setProgress(nameActive, "/images/advanced-sternzeichen-active.png", progressBar, 0.54, session);
                break;
            case 4:
                setProgress(nameActive, "/images/advanced-party-active.png", progressBar, 0.72, session);
                break;
            case 5:
                setProgress(nameActive, "/images/advanced-instagram-active.png", progressBar, 0.9, session);
                break;

            default:
                LOGGER.error("No Progress image found for this index.");
        }

        nameActive.setFitWidth(1065);
        nameActive.setFitHeight(165);
        nameActive.setTranslateY(-263);

        progressBar.setMaxWidth(1065);
        //thickness of the progress Bar
        progressBar.setMaxHeight(10);
        progressBar.setTranslateY(-330);
        progressBar.getStyleClass().add("progressBar");

        pane.getChildren().addAll(nameActive, progressBar);

    }


    //Generates icon images header on question stackpane. also generates progress bar on top of the icons.

    /**
     * Creates the progress bar for the generateProgressHeader method and adds an animation to it
     *
     * @param view        the ImageView to display the image in it
     * @param path        the path of the image
     * @param progressBar the progressbar to work with
     * @param progress    the progress of all questions answered as an double, at 100% the result page is shown
     * @param session     the session from the game to know what session to work with
     */
    private static void setProgress(ImageView view, String path, ProgressBar progressBar, double progress, Session session) {

        view.setImage(new Image(Objects.requireNonNull(FxmlGuiDriver.class.getResource(path)).toExternalForm()));

        Timeline timeline = new Timeline();

        if (session.isUser1Turn()) {
            progressBar.setProgress(progress - 0.1);
            KeyValue keyValue = new KeyValue(progressBar.progressProperty(), progress);
            KeyFrame keyFrame = new KeyFrame(new Duration(800), keyValue);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
        } else {
            progressBar.setProgress(progress);
        }
    }
}
