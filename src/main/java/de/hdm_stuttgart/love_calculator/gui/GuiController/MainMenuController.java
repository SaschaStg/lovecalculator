package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainMenuController {

    @FXML private TextField userNameTextField = new TextField();
    @FXML private TextField passwordTextField = new TextField();
    @FXML private TextField vornameTextField = new TextField();
    @FXML private TextField nachnameTextField = new TextField();

    //create logger for every class
    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);

    @FXML
    public void startScene() {
        FxmlGuiDriver.setScene("/fxml/startScene.fxml");
    }



    @FXML
    public void playClassic() {
        FxmlGuiDriver.setTitle("Classic Mode");
        //no using of .setOnAction because it causes the "button only works on second click" bug (at least with our implementation)
        FxmlGuiDriver.setScene(new ClassicMode());
        LOGGER.info("Switched scene to Classic Mode");
    }


    @FXML
    public void playAdvanced() {
        //no using of .setOnAction because it causes the "button only works on second click" bug (at least with our implementation)
            try {
                    FxmlGuiDriver.setScene(new AdvancedMode());
                LOGGER.info("Switched scene to Advanced Mode");
            } catch (Exception exception) {
                LOGGER.info("Error at switching Scene to Advanced Mode:");
                exception.printStackTrace();
            }
    }

    @FXML
    public void playScene() {
        //FxmlGuiDriver.sceneSwitcher("/fxml/playScene.fxml", playButton);
        FxmlGuiDriver.setScene("/fxml/playScene.fxml");
        LOGGER.info("Switched scene to playScene");
    }

    @FXML
    public void backToMainMenuScene() {
        //FxmlGuiDriver.sceneSwitcher("/fxml/playScene.fxml", playButton);
        FxmlGuiDriver.setScene("/fxml/startScene.fxml");
        LOGGER.info("Switched scene to startScene");
    }

    @FXML
    public void profileScene() {
        if (LoginFactory.getLoggedInUser() != null) {
            FxmlGuiDriver.setScene("/fxml/loggedInScene.fxml");
        } else {
            FxmlGuiDriver.setScene("/fxml/profileScene.fxml");
        }
    }

    @FXML
    public void loginScene() {
        FxmlGuiDriver.setScene("/fxml/loginScene.fxml");
        LOGGER.info("Switched scene to loginScene");
    }

    @FXML
    public void registerScene() {
        FxmlGuiDriver.setScene("/fxml/registerScene.fxml");
        LOGGER.info("Switched scene to registerScene");
    }

    @FXML
    public void onLogin() {
        LoginFactory.checkLogin(userNameTextField, passwordTextField);
    }

    @FXML
    public void onRegister(){
        RegisterFactory.register(userNameTextField, passwordTextField, vornameTextField, nachnameTextField);
    }






}
