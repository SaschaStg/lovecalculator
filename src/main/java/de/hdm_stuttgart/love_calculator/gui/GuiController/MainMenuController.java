package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML private Button startButton;
    @FXML private Button playButton;
    @FXML private Button profileButton;
    @FXML private Button classicButton;
    @FXML private Button advancedButton;
    @FXML private Button backToMainMenuButton;
    @FXML private Button iconmenubutton;
    @FXML private Button subMenuClassicButton;

    @FXML
    public void startScene() {
        FxmlGuiDriver.sceneSwitcher("/fxml/startScene.fxml", startButton);

    }

    @FXML
    public void openIconmenu() throws Exception {
        FxmlGuiDriver.sceneSwitcher("/fxml/iconMenu.fxml", iconmenubutton);
        IconmenuController.openIconmenu();

    }

    @FXML
    public void playClassic() {

        //no using of .setOnAction because it causes the "button only works on second click" bug (at least with our implementation)
            try {
                //Prevent index bug
                if(QuestionsController.index > 1) {
                    QuestionsController.index = 0;
                }
                ClassicController.startClassicQuestions();
                QuestionsController.safeClose();
                Stage stage = (Stage) classicButton.getScene().getWindow();
                stage.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

    }

    /*@FXML
    public void playClassic() {

        classicButton.setOnAction(e -> {
            try {
                //BUG man muss 2x Klicken, das liegt am Try Catch, müssen wir mal schauen morgen
                //Prevent index bug
                if(QuestionsController.index > 1) {
                    QuestionsController.index = 0;
                }
                ClassicController.startClassicQuestions();
                QuestionsController.safeClose();
                Stage stage = (Stage) classicButton.getScene().getWindow();
                stage.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }*/

    @FXML
    public void playAdvanced() {
        //no using of .setOnAction because it causes the "button only works on second click" bug (at least with our implementation)
            try {
                AdvancedController.startAdvancedQuestions();
                QuestionsController.safeClose();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
    }


    @FXML
    public void playScene() {
        //FxmlGuiDriver.sceneSwitcher("/fxml/playScene.fxml", playButton);
        FxmlGuiDriver.sceneSwitcher("/fxml/playScene.fxml", playButton);
    }

    @FXML
    public void backToMainMenuScene() {
        //FxmlGuiDriver.sceneSwitcher("/fxml/playScene.fxml", playButton);
        FxmlGuiDriver.sceneSwitcher("/fxml/startScene.fxml", backToMainMenuButton);
    }

    @FXML
    public void subMenuClassicScene() {
        //FxmlGuiDriver.sceneSwitcher("/fxml/playScene.fxml", playButton);
        FxmlGuiDriver.sceneSwitcher("/fxml/submenuClassicMode.fxml", subMenuClassicButton);
    }

    @FXML
    public void profileScene() {

        FxmlGuiDriver.sceneSwitcher("/fxml/profileScene.fxml", profileButton);
    }




}
