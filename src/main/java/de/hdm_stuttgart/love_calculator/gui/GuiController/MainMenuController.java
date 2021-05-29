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

    @FXML
    public void startScene() {
        FxmlGuiDriver.sceneSwitcher("/fxml/startScene.fxml", startButton);

    }

    @FXML
    public void playScene() {
        //FxmlGuiDriver.sceneSwitcher("/fxml/playScene.fxml", playButton);
        playButton.setOnAction(e -> {
            try {
                QuestionsController.startClassicQuestions();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    @FXML
    public void profileScene() {
        FxmlGuiDriver.sceneSwitcher("/fxml/profileScene.fxml", profileButton);
    }




}
