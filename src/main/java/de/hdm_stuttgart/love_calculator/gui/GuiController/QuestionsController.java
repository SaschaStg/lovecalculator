package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.catalog.Catalog;
import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.user.User2;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class QuestionsController {

    private static Button button;

    private static final Logger log = LogManager.getLogger(FxmlGuiDriver.class);

    private static Label label;
    private static Stage questionStage = new Stage();

    private static CheckBox[] checkBoxesClone;
    private static RadioButton[] radioButtonClone;
    private static int index = 0;
    private static boolean isSelected = false;
    private static StackPane layout = new StackPane();
    private static Scene scene = new Scene(layout, 1065, 670);




    @FXML
    public static void startClassicQuestions() throws Exception {

        questionStage.setTitle("Classic Mode");

        Button next = new Button();
        next.setText("->");
        next.setTranslateY(300);
        next.setTranslateX(400);

        loadQuestionAndAnswers(layout);

        //generateCheckboxes(0, layout);

        layout.getChildren().addAll(label, next);


        //generateCheckboxes(1, layout);
        //generateTextField(1, layout);

        next.setOnAction(e -> {
            try {
                nextButton(layout);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });


        questionStage.setScene(scene);
        questionStage.show();






        /*Stage window = new Stage();

        window.setTitle("Classic Mode");
        window.setWidth(1065);
        window.setHeight(720);



        VBox layout = new VBox(10);
        layout.getChildren().addAll(question, vorname);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();*/



    }


    private static void loadQuestionAndAnswers(StackPane pane) {

            //Load question
            label = new Label();
            label.setText(Catalog.getQuestionList().get(index).questionContent);
            label.setTranslateY(-100);

            //check type
            InputType test = Enum.valueOf(InputType.class, Catalog.getAnswerList().get(index).inputType.trim().toUpperCase());
            switch (test) {

                case CHECKBOX:
                    generateCheckboxes(pane);
                    break;
                case RADIOBUTTON:
                    generateRadiobuttons(pane);
                    break;
                case TEXTFIELD:
                    generateTextField(pane);
                    break;
                case SLIDER:
                    break;

                default:
                    log.error("ERROR INPUTTYPE IS " + test);

            }


            log.info("Inputtype for question " + index + " is " + Catalog.getAnswerList().get(index).inputType);
    }

    private static void generateTextField(StackPane pane) {
        TextField textfield = new TextField();
        pane.getChildren().add(textfield);
    }

    private static void generateRadiobuttons(StackPane pane){

        //Button[] button1 = new Button[Catalog.getAnswerList().get(index).answerOptions.size()];
        RadioButton[] radioButtons = new RadioButton[Catalog.getAnswerList().get(index).answerOptions.size()];
        ToggleGroup radioGroup = new ToggleGroup();

        for(int i = 0; i < radioButtons.length; i++){

            /*button1[i] = new Button();
            button1[i].setText(Catalog.getAnswerList().get(index).answerOptions.get(i));
            button1[i].setTranslateY(i*30);

            System.out.println(button1[i].getText());
            pane.getChildren().addAll(button1[i]);*/

            radioButtons[i] = new RadioButton();
            radioButtons[i].setText(Catalog.getAnswerList().get(index).answerOptions.get(i));
            radioButtons[i].setTranslateY(i*30);
            radioButtons[i].setToggleGroup(radioGroup);
            //System.out.println(radioButtons[i].getText());
            pane.getChildren().addAll(radioButtons[i]);

        }

        radioButtonClone = radioButtons;

    }


    private static void generateCheckboxes(StackPane pane){

        //Button[] button1 = new Button[Catalog.getAnswerList().get(index).answerOptions.size()];
        CheckBox[] checkBoxes = new CheckBox[Catalog.getAnswerList().get(index).answerOptions.size()];

        for(int i = 0; i < checkBoxes.length; i++){

            /*button1[i] = new Button();
            button1[i].setText(Catalog.getAnswerList().get(index).answerOptions.get(i));
            button1[i].setTranslateY(i*30);

            System.out.println(button1[i].getText());
            pane.getChildren().addAll(button1[i]);*/

            checkBoxes[i] = new CheckBox();
            checkBoxes[i].setText(Catalog.getAnswerList().get(index).answerOptions.get(i));
            checkBoxes[i].setTranslateY(i*30);
            //System.out.println(checkBoxes[i].getText());
            pane.getChildren().addAll(checkBoxes[i]);

        }

        checkBoxesClone = checkBoxes;

    }





    private static void nextButton(StackPane pane) throws Exception {

            switch (Catalog.getAnswerList().get(index).inputType) {

                case "checkbox":
                    getCheckboxInput();
                    break;
                case "radiobutton":
                    getRadioButtonInput();
                    break;
                case "textfield":
                    generateTextField(pane);
                    break;
                case "slider":
                    break;

                default:
                    log.error("ERROR INPUTTYPE IS " + Catalog.getAnswerList().get(index).inputType);

            }

            if(isSelected) {
                log.info("Felder Input:");
                log.info(Arrays.deepToString(User2.result.get(index)));
                log.info("");
                log.info("Gesamter User Result Array:");
                log.info(Arrays.deepToString(User2.result.toArray()));

                //Reset User Input Check
                isSelected = false;
                pane.getChildren().clear();

                //IMPORTANT: Size of answerlist is -1 from index! If 10 answers -> AnswerList size is 9!
                if (Catalog.getAnswerList().size() - 1 > index) {
                    index++;
                    startClassicQuestions();
                } else {
                    //Show results after all questions are finished
                    showResults(pane);
                }
            } else {
            showAlertBox("Fehler!", "Bitte wähle eine Antwort aus!");
            log.info("Es wurde keine Antwort ausgewählt!");
            }
    }

    private static void getCheckboxInput() {

        int sumSelectedFields = 0;

        for (int i = 0; i < checkBoxesClone.length; i++) {

            if (checkBoxesClone[i].isSelected()) {

                sumSelectedFields++;

            }

        }

        String[] tickedCheckboxes = new String[sumSelectedFields];
        int indexTickedCheckbox = 0;

        for (int i = 0; i < checkBoxesClone.length; i++) {

            if (checkBoxesClone[i].isSelected()) {
                tickedCheckboxes[indexTickedCheckbox] = String.valueOf(checkBoxesClone[i].getText());
                indexTickedCheckbox++;
            }
        }

        //CHECK IF INPUT IS EMPTY
        if (tickedCheckboxes.length > 0) {
            isSelected = true;

            User2.result.add(tickedCheckboxes);
        }
    }



    private static void getRadioButtonInput() {

        final String[] tickedRadioButton = new String[1];

        for(int i = 0; i < radioButtonClone.length; i++){

            if(radioButtonClone[i].isSelected()) {

                    tickedRadioButton[0] = String.valueOf(radioButtonClone[i].getText());
                    User2.result.add(tickedRadioButton);
                    isSelected = true;

                    break;
            }
        }


    }

    private static void showResults(StackPane pane) {

        label = new Label();
        label.setText("Du hast es geschafft! Hier deine Ergebnisse: " + Arrays.deepToString(User2.result.toArray()));
        label.setTranslateY(-100);

        layout.getChildren().add(label);

        questionStage.setScene(scene);
        questionStage.show();

        System.out.println("Ergebnisse: " + Arrays.deepToString(User2.result.toArray()));
    }


    private static void showAlertBox(String title, String header) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}
