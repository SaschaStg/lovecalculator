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

    @FXML
    public void startScene() {
        FxmlGuiDriver.sceneSwitcher("/fxml/startScene.fxml", startButton);

    }

    @FXML
    public void playClassic() {
        classicButton.setOnAction(e -> {
            try {
                //Prevent index bug
                if(QuestionsController.index > 1) {
                    QuestionsController.index = 0;
                }
                ClassicController.startClassicQuestions();
                QuestionsController.safeClose();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    @FXML
    public void playAdvanced() {
        advancedButton.setOnAction(e -> {
            try {
                AdvancedController.startAdvancedQuestions();
                QuestionsController.safeClose();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }


    @FXML
    public void playScene() {
        //FxmlGuiDriver.sceneSwitcher("/fxml/playScene.fxml", playButton);
        playButton.setOnAction(e -> {
            try {
                FxmlGuiDriver.sceneSwitcher("/fxml/playScene.fxml", playButton);
                //ClassicController.startClassicQuestions();
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
