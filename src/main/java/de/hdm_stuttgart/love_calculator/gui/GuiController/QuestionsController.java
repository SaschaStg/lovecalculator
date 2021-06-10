package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.catalog.Catalog;
import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.user.User1;
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

    public static Button button;

    public static Label label;
    public static Stage questionStage = new Stage();

    public static CheckBox[] checkBoxesClone;
    public static RadioButton[] radioButtonClone;

    public static TextField textfield = new TextField();

    public static int index = 0;
    public static boolean isSelected = false;
    public static StackPane layout = new StackPane();
    public static Scene scene = new Scene(layout, 1065, 670);
    public static boolean isUser1 = true;
    public static boolean isClassic = true;







    public static void loadQuestionAndAnswers(StackPane pane) {

            //Load question
            label = new Label();
            label.setTranslateY(-100);

            if(isUser1) {
                label.setText(Catalog.getQuestionList().get(index).questionContent);
            } else {
                label.setText(Catalog.getQuestionList().get(index).questionContentUser2);
            }

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
                    FxmlGuiDriver.log.error("ERROR INPUTTYPE IS " + test);

            }


            FxmlGuiDriver.log.info("Inputtype for question " + index + " is " + Catalog.getAnswerList().get(index).inputType);
    }

    private static void generateTextField(StackPane pane) {
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





    public static void nextButton(StackPane pane) throws Exception {

            switch (Catalog.getAnswerList().get(index).inputType) {

                case "checkbox":
                    getCheckboxInput();
                    break;
                case "radiobutton":
                    getRadioButtonInput();
                    break;
                case "textfield":
                    getTextFieldInput();
                    break;
                case "slider":
                    break;

                default:
                    FxmlGuiDriver.log.error("ERROR INPUTTYPE IS " + Catalog.getAnswerList().get(index).inputType);

            }

            if(isSelected) {

                //Reset User Input Check
                isSelected = false;
                pane.getChildren().clear();

                //IMPORTANT: Size of answerlist is -1 from index! If 10. answer -> AnswerList index 9!
                if (Catalog.getAnswerList().size() - 1 > index) {
                    if(isUser1) {
                        isUser1 = false;

                        FxmlGuiDriver.log.info("Felder User 1 Input:");
                        FxmlGuiDriver.log.info(Arrays.deepToString(User1.result.get(index)));
                        FxmlGuiDriver.log.info("");
                        FxmlGuiDriver.log.info("Gesamter User 1 Result Array:");
                        FxmlGuiDriver.log.info(Arrays.deepToString(User1.result.toArray()));

                        FxmlGuiDriver.log.info("USER 2 IST JETZT DRAN");
                    } else {

                        FxmlGuiDriver.log.info("Felder User 2 Input:");
                        FxmlGuiDriver.log.info(Arrays.deepToString(User2.result.get(index)));
                        FxmlGuiDriver.log.info("");
                        FxmlGuiDriver.log.info("Gesamter User 2 Result Array:");
                        FxmlGuiDriver.log.info(Arrays.deepToString(User2.result.toArray()));

                        isUser1 = true;
                        index++;

                        FxmlGuiDriver.log.info("USER 1 IST JETZT DRAN, INDEX: " + index);
                    }

                    //Check which mode
                    if(isClassic) {
                        ClassicController.startClassicQuestions();
                    } else {

                        System.out.println("Advanced modus activated");
                        //METHOD START ADVANCED QUESTIONS
                    }
                } else {
                showResults(pane);
                    }
            } else {
            showAlertBox("Fehler!", "Bitte wähle eine Antwort aus!");
                FxmlGuiDriver.log.info("Es wurde keine Antwort ausgewählt!");
            }
    }


    private static void getTextFieldInput() {
        if (!textfield.getText().isEmpty()) {
            String[] userInput = new String[1];
            userInput[0] = textfield.getText();
            isSelected = true;

            if(isUser1) {
                User1.result.add(userInput);
            } else {
                User2.result.add(userInput);
            }
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

            if(isUser1) {
                User1.result.add(tickedCheckboxes);
            } else {
                User2.result.add(tickedCheckboxes);
            }
        }
    }



    private static void getRadioButtonInput() {

        final String[] tickedRadioButton = new String[1];

        for(int i = 0; i < radioButtonClone.length; i++){

            if(radioButtonClone[i].isSelected()) {

                    tickedRadioButton[0] = String.valueOf(radioButtonClone[i].getText());
                    isSelected = true;

                if(isUser1) {
                    User1.result.add(tickedRadioButton);
                } else {
                    User2.result.add(tickedRadioButton);
                }

                    break;
            }
        }


    }

    private static void showResults(StackPane pane) {

        label = new Label();
        label.setText("Du hast es geschafft! Hier deine Ergebnisse: " + Arrays.deepToString(User1.result.toArray()));
        label.setTranslateY(-300);

        Label schwarm;
        schwarm = new Label();
        schwarm.setText("Und hier die Ergebnisse deines Schwarms: " + Arrays.deepToString(User2.result.toArray()));
        schwarm.setTranslateY(-200);

        System.out.println("Ergebnisse: " + Arrays.deepToString(User1.result.toArray()));

        Label studiengang;
        studiengang = new Label();

        //Check if answers are the same
        if(checkEqualAnswers(1)) {
            studiengang.setText("Studienpartner! Ihr Studiert beide " + Arrays.deepToString(User2.result.get(1)));
        } else {
            studiengang.setText("Hm.. vom Studium passt ihr leider nicht zusammen!");
        }

        studiengang.setTranslateY(-100);

        layout.getChildren().addAll(label, schwarm, studiengang);

        questionStage.setScene(scene);
        questionStage.show();
    }


    private static void showAlertBox(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public static boolean checkEqualAnswers(int index) {
        if(Arrays.deepToString(User1.result.get(index)).equals(Arrays.deepToString(User2.result.get(index)))) {
            FxmlGuiDriver.log.info("Ergebnisse für Antwort " + index + " sind gleich!");
            FxmlGuiDriver.log.info("User 1 input: " + Arrays.deepToString(User1.result.get(index)));
            FxmlGuiDriver.log.info("User 2 input: " + Arrays.deepToString(User2.result.get(index)));
            return true;
        }
        FxmlGuiDriver.log.info("Ergebnisse für Antwort " + index + " sind NICHT gleich!");
        FxmlGuiDriver.log.info("User 1 input: " + Arrays.deepToString(User1.result.get(index)));
        FxmlGuiDriver.log.info("User 2 input: " + Arrays.deepToString(User2.result.get(index)));
        return false;
    }
}
