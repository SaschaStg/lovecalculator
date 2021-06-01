package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.catalog.Catalog;
import de.hdm_stuttgart.love_calculator.user.User;
import de.hdm_stuttgart.love_calculator.user.User2;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class QuestionsController {

    private static Button button;



    private static Label label;
    private static Stage questionStage = new Stage();

    private static CheckBox[] checkBoxesClone;




    @FXML
    public static void startClassicQuestions() throws Exception {

        Button next = new Button();
        //warning: unicode used
        next.setText("->");
        next.setTranslateY(300);
        next.setTranslateX(400);





        label = new Label();
        label.setText(Catalog.getQuestionList().get(1).questionContent);
        questionStage.setTitle("Classic Mode");

        StackPane layout = new StackPane();
        layout.getChildren().addAll(label, next);
        generateCheckboxes(1, layout);

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


    private static void displayQuestionsAndAnswers(final int index){

        //check type
        switch(Catalog.getAnswerList().get(index).inputType){

            case"checkbox":

                break;
            case"radiobutton":
            case"textfield":
            case"slider":

        }


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

    }


}
