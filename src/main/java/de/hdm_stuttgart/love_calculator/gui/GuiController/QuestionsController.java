package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.catalog.Catalog;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QuestionsController {

    private static Button button;
    private static Label label;
    private static Stage questionStage = new Stage();

    @FXML
    public static void startClassicQuestions() throws Exception {
        label = new Label();
        label.setText(Catalog.getQuestionList().get(1).questionContent);
        questionStage.setTitle("Classic Mode");
        button = new Button();
        button.setText(Catalog.getAnswerList().get(1).getOneAnswer(1));
        button.setTranslateY(100);

        StackPane layout = new StackPane();
        layout.getChildren().addAll(label, button);

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


}
