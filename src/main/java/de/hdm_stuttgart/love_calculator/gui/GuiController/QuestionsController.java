package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.catalog.Catalog;
import de.hdm_stuttgart.love_calculator.user.User;
import de.hdm_stuttgart.love_calculator.user.User2;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Arrays;

public class QuestionsController {

    private static Button button;



    private static Label label;
    private static Stage questionStage = new Stage();

    private static CheckBox[] checkBoxesClone;
    private static RadioButton[] radioButtonClone;




    @FXML
    public static void startClassicQuestions() throws Exception {

        questionStage.setTitle("Classic Mode");

        Button next = new Button();
        next.setText("->");
        next.setTranslateY(300);
        next.setTranslateX(400);




        StackPane layout = new StackPane();

        loadQuestionAndAnswers(0, layout);

        //generateCheckboxes(0, layout);

        layout.getChildren().addAll(label, next);


        //generateCheckboxes(1, layout);
        //generateTextField(1, layout);

        next.setOnAction(e -> {
            try {
                nextButton(1, layout);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });


        Scene scene = new Scene(layout, 1065, 670);
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


    private static void loadQuestionAndAnswers(final int index, StackPane pane){

        //Load question
        label = new Label();
        label.setText(Catalog.getQuestionList().get(index).questionContent);
        label.setTranslateY(-100);

        //check type
        switch (Catalog.getAnswerList().get(index).inputType) {

            case "checkbox":
                generateCheckboxes(index, pane);
                break;
            case "radiobutton":
                generateRadiobuttons(index, pane);
                break;
            case "textfield":
                generateTextField(index, pane);
                break;
            case "slider":
                break;

            default:
                System.out.println("ERROR INPUTTYPE IS " + Catalog.getAnswerList().get(index).inputType);

        }


        System.out.println("Inputtype for question " + index + " is " + Catalog.getAnswerList().get(index).inputType);


    }

    private static void generateTextField(final int index, StackPane pane) {
        TextField textfield = new TextField();
        pane.getChildren().add(textfield);
    }

    private static void generateRadiobuttons(final int index, StackPane pane){

        //Button[] button1 = new Button[Catalog.getAnswerList().get(index).answerOptions.size()];
        RadioButton[] radioButtons = new RadioButton[Catalog.getAnswerList().get(index).answerOptions.size()];
        ToggleGroup radioGroup = new ToggleGroup();

        for(int i = 0; i < radioButtons.length; i++){

            /*button1[i] = new Button();
            button1[i].setText(Catalog.getAnswerList().get(index).answerOptions.get(i));      // you have twice this line
            button1[i].setTranslateY(i*30);

            System.out.println(button1[i].getText());
            pane.getChildren().addAll(button1[i]);*/

            radioButtons[i] = new RadioButton();
            radioButtons[i].setText(Catalog.getAnswerList().get(index).answerOptions.get(i));      // you have twice this line
            radioButtons[i].setTranslateY(i*30);
            radioButtons[i].setToggleGroup(radioGroup);
            //System.out.println(radioButtons[i].getText());
            pane.getChildren().addAll(radioButtons[i]);

        }

        radioButtonClone = radioButtons;

    }


    private static void generateCheckboxes(final int index, StackPane pane){

        //Button[] button1 = new Button[Catalog.getAnswerList().get(index).answerOptions.size()];
        CheckBox[] checkBoxes = new CheckBox[Catalog.getAnswerList().get(index).answerOptions.size()];

        for(int i = 0; i < checkBoxes.length; i++){

            /*button1[i] = new Button();
            button1[i].setText(Catalog.getAnswerList().get(index).answerOptions.get(i));      // you have twice this line
            button1[i].setTranslateY(i*30);

            System.out.println(button1[i].getText());
            pane.getChildren().addAll(button1[i]);*/

            checkBoxes[i] = new CheckBox();
            checkBoxes[i].setText(Catalog.getAnswerList().get(index).answerOptions.get(i));      // you have twice this line
            checkBoxes[i].setTranslateY(i*30);
            //System.out.println(checkBoxes[i].getText());
            pane.getChildren().addAll(checkBoxes[i]);

        }

        checkBoxesClone = checkBoxes;

    }





    private static void nextButton(final int index, StackPane pane){

        switch (Catalog.getAnswerList().get(index).inputType) {

            case "checkbox":
                getCheckboxInput(index);
                break;
            case "radiobutton":
                getRadioButtonInput(index);
                break;
            case "textfield":
                generateTextField(index, pane);
                break;
            case "slider":
                break;

            default:
                System.out.println("ERROR INPUTTYPE IS " + Catalog.getAnswerList().get(index).inputType);

        }
}

    private static void getCheckboxInput(int index) {


        int sumSelectedFields = 0;

        for(int i = 0; i < checkBoxesClone.length; i++){

            if(checkBoxesClone[i].isSelected()){

                sumSelectedFields++;

            }

        }

        String[] tickedCheckboxes = new String[sumSelectedFields];
        int indexTickedCheckbox = 0;

        for(int i = 0; i < checkBoxesClone.length; i++){

            if(checkBoxesClone[i].isSelected()){
                tickedCheckboxes[indexTickedCheckbox] = String.valueOf(checkBoxesClone[i].getText());
                indexTickedCheckbox++;
            }
        }

        User2.result.add(tickedCheckboxes);
        //System.out.println(User2.result.get(0));

        System.out.println("Gecheckte Checkboxen:");
        System.out.println(Arrays.deepToString(User2.result.get(index)));
        //System.out.println(User2.result.get(0).toString());

    }


    private static void getRadioButtonInput(int index) {

        final String[] tickedRadioButton = new String[1];

        for(int i = 0; i < radioButtonClone.length; i++){

            if(radioButtonClone[i].isSelected()) {

                    tickedRadioButton[0] = String.valueOf(radioButtonClone[i].getText());
                    User2.result.add(tickedRadioButton);

                    System.out.println("Gecheckte RadioButton:");
                    System.out.println(Arrays.deepToString(User2.result.get(index)));

                    break;
            }
        }


    }
}
