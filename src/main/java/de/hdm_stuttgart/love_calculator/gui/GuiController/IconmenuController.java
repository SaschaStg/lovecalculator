package de.hdm_stuttgart.love_calculator.gui.GuiController;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class IconmenuController {

    public static Stage testStage = new Stage();
    public static StackPane layout = new StackPane();
    public static Scene scene = new Scene(layout, 1065, 670);

    @FXML
    public static void openIconmenu() throws Exception {
        testStage.setTitle("Icon Menu");

            Button test = new Button();
            test.setText("Test!");
            test.setTranslateY(0);
            test.setTranslateX(0);

            layout.getChildren().addAll(test);


            testStage.setScene(scene);
            testStage.show();


    }


}
