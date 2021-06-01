package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.catalog.Catalog;
import de.hdm_stuttgart.love_calculator.user.User;
import de.hdm_stuttgart.love_calculator.user.User2;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Arrays;

public class QuestionsController {

    private static Button button;



    private static Label label;
    private static Stage questionStage = new Stage();

    private static CheckBox[] checkBoxesClone;




    @FXML
    public static void startClassicQuestions() throws Exception {

        questionStage.setTitle("Classic Mode");

        Button next = new Button();
        next.setText("->");
        next.setTranslateY(300);
        next.setTranslateX(400);




        StackPane layout = new StackPane();

        loadQuestionAndAnswers(2, layout);

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
                //generateRadiobuttons(i, pane);
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

            System.out.println(checkBoxes[i].getText());
            pane.getChildren().addAll(checkBoxes[i]);

        }

        checkBoxesClone = checkBoxes;

    }





    private static void nextButton(final int index, StackPane pane){




        int sumSelectedFields = 0;

        for(int i = 0; i < checkBoxesClone.length; i++){

            if(checkBoxesClone[i].isSelected()){

                sumSelectedFields++;

            }

        }

        String[] tickedCheckboxes = new String[sumSelectedFields];


        for(int i = 0; i < checkBoxesClone.length; i++){

            if(checkBoxesClone[i].isSelected()){

                for(int e = 0; e < tickedCheckboxes.length; e++){

                    tickedCheckboxes[e] = String.valueOf(checkBoxesClone[e]);

                }
            }
        }

        User2.result.add(tickedCheckboxes);
        //System.out.println(User2.result.get(0));

        System.out.println("Gecheckte Checkboxen:");
        System.out.println(Arrays.deepToString(User2.result.toArray()));

    }


}
