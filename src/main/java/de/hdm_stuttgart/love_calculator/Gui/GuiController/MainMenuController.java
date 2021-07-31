package de.hdm_stuttgart.love_calculator.Gui.GuiController;

import de.hdm_stuttgart.love_calculator.Gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.Gui.GuiFactory.LoginFactory;
import de.hdm_stuttgart.love_calculator.Gui.GuiFactory.QuestionsFactory;
import de.hdm_stuttgart.love_calculator.Gui.GuiFactory.RegisterFactory;
import javafx.fxml.FXML;
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
    private void startScene() {
        FxmlGuiDriver.setScene("/fxml/startScene.fxml");
    }


    @FXML
    private void playClassic() {
        FxmlGuiDriver.setTitle("Classic Mode");
        //no using of .setOnAction because it causes the "button only works on second click" bug (at least with our implementation)
        FxmlGuiDriver.setScene(new ClassicMode());
        LOGGER.info("Switched scene to Classic Mode");
    }


    @FXML
    private void playAdvanced() {
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
    private void playScene() {
        //FxmlGuiDriver.sceneSwitcher("/fxml/playScene.fxml", playButton);
        FxmlGuiDriver.setScene("/fxml/playScene.fxml");
        LOGGER.info("Switched scene to playScene");
    }

    @FXML
    private void profileScene() {
        if (LoginFactory.getLoggedInUser() != null) {
            FxmlGuiDriver.setScene("/fxml/loggedInScene.fxml");
        } else {
            FxmlGuiDriver.setScene("/fxml/profileScene.fxml");
        }
    }

    @FXML
    private void loginScene() {
        FxmlGuiDriver.setScene("/fxml/loginScene.fxml");
        LOGGER.info("Switched scene to loginScene");
    }

    @FXML
    private void registerScene() {
        FxmlGuiDriver.setScene("/fxml/registerScene.fxml");
        LOGGER.info("Switched scene to registerScene");
    }

    @FXML
    private void onLogin() {
        LoginFactory.checkLogin(userNameTextField.getText(), passwordTextField.getText());
    }

    @FXML
    private void onRegister() {
        RegisterFactory.register(userNameTextField.getText(), passwordTextField.getText(), vornameTextField.getText(), nachnameTextField.getText());
    }


}
