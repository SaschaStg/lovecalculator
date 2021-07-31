package de.hdm_stuttgart.love_calculator.Gui.GuiController;

import de.hdm_stuttgart.love_calculator.Gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.Gui.GameModes.AdvancedMode;
import de.hdm_stuttgart.love_calculator.Gui.GameModes.ClassicMode;
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

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);

    /**
     * Switches the scene to the startScene.fxml
     */
    @FXML
    private void startScene() {
        FxmlGuiDriver.setScene("/fxml/startScene.fxml");
    }


    /**
     * Sets the fxml window title to "Classic Mode", Starts the ClassicMode and switches the scene to "ClassicMode"
     */
    @FXML
    private void playClassic() {
        FxmlGuiDriver.setTitle("Classic Mode");
        //no using of .setOnAction because it causes the "button only works on second click" bug (at least with our implementation)
        FxmlGuiDriver.setScene(new ClassicMode());
        LOGGER.info("Switched scene to Classic Mode");
    }

    /**
     * Sets the fxml window title to "Advanced Mode", Starts the ClassicMode and switches the scene to "AdvancedMode"
     */
    @FXML
    private void playAdvanced() {
        //no using of .setOnAction because it causes the "button only works on second click" bug (at least with our implementation)
        try {
            FxmlGuiDriver.setTitle("Advanced Mode");
            FxmlGuiDriver.setScene(new AdvancedMode());
            LOGGER.info("Switched scene to Advanced Mode");
        } catch (Exception exception) {
            LOGGER.info("Error at switching Scene to Advanced Mode:");
            exception.printStackTrace();
        }
    }

    /**
     * Switches the scene to the playScene.fxml
     */
    @FXML
    private void playScene() {
        //FxmlGuiDriver.sceneSwitcher("/fxml/playScene.fxml", playButton);
        FxmlGuiDriver.setScene("/fxml/playScene.fxml");
        LOGGER.info("Switched scene to playScene");
    }

    /**
     * Switches the scene to the loggedInScene.fxml if the player is logged in,
     * otherwise it switches to the profileScene.fxml
     */
    @FXML
    private void profileScene() {
        if (LoginFactory.getLoggedInUser() != null) {
            FxmlGuiDriver.setScene("/fxml/loggedInScene.fxml");
        } else {
            FxmlGuiDriver.setScene("/fxml/profileScene.fxml");
        }
    }

    /**
     * Switches the scene to the loginScene.fxml
     */
    @FXML
    private void loginScene() {
        FxmlGuiDriver.setScene("/fxml/loginScene.fxml");
        LOGGER.info("Switched scene to loginScene");
    }

    /**
     * Switches the scene to the registerScene.fxml
     */
    @FXML
    private void registerScene() {
        FxmlGuiDriver.setScene("/fxml/registerScene.fxml");
        LOGGER.info("Switched scene to registerScene");
    }

    /**
     * Starts the method checkLogin in LoginFactory
     */
    @FXML
    private void onLogin() {
        LoginFactory.checkLogin(userNameTextField.getText(), passwordTextField.getText());
    }

    /**
     * Starts the method register in RegisterFactory
     */
    @FXML
    private void onRegister() {
        RegisterFactory.register(userNameTextField.getText(), passwordTextField.getText(), vornameTextField.getText(), nachnameTextField.getText());
    }


}
