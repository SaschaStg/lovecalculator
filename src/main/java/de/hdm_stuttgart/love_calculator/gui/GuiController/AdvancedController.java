package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.user.User1;
import de.hdm_stuttgart.love_calculator.user.User2;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.WindowEvent;

import java.util.Arrays;

public class AdvancedController {

    @FXML
    public static void startAdvancedQuestions() throws Exception {
        QuestionsController.isClassic = false;
            QuestionsController.questionStage.setTitle("Advanced Mode");

            Button next = new Button();
            next.setText("->");
            next.setTranslateY(300);
            next.setTranslateX(400);

            QuestionsController.loadQuestionAndAnswers(QuestionsController.layout);

            //generateCheckboxes(0, layout);

            QuestionsController.layout.getChildren().addAll(QuestionsController.label, next);


            //generateCheckboxes(1, layout);
            //generateTextField(1, layout);

            next.setOnAction(e -> {
                try {
                    QuestionsController.nextButton(QuestionsController.layout);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });


            QuestionsController.questionStage.setScene(QuestionsController.scene);
            QuestionsController.questionStage.show();




    }

    private static void showResults(StackPane pane) {

        Label label;
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
        if(QuestionsController.checkEqualAnswers(1)) {
            studiengang.setText("Studienpartner! Ihr Studiert beide " + Arrays.deepToString(User2.result.get(1)));
        } else {
            studiengang.setText("Hm.. vom Studium passt ihr leider nicht zusammen!");
        }

        studiengang.setTranslateY(-100);

        QuestionsController.layout.getChildren().addAll(label, schwarm, studiengang);

        QuestionsController.questionStage.setScene(QuestionsController.scene);
        QuestionsController.questionStage.show();
    }


}