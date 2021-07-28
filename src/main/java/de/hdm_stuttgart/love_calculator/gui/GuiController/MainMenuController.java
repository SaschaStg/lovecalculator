package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainMenuController {
    @FXML private Button startButton;
    @FXML private Button playButton;
    @FXML private Button profileButton;
    @FXML private Button classicButton;
    @FXML private Button advancedButton;
    @FXML private Button backToMainMenuButton;
    @FXML private Button iconmenubutton;
    @FXML private Button subMenuClassicButton;

    @FXML private TextField userNameTextField = new TextField();
    @FXML private TextField passwordTextField = new TextField();
    @FXML private TextField vornameTextField = new TextField();
    @FXML private TextField nachnameTextField = new TextField();
    @FXML private Label userNameLabel = new Label();
    @FXML private Label passwordLabel = new Label();

    @FXML
    public void startScene() {
        FxmlGuiDriver.setScene("/fxml/startScene.fxml");
    }



    @FXML
    public void playClassic() {
        FxmlGuiDriver.setTitle("Classic Mode");
        //no using of .setOnAction because it causes the "button only works on second click" bug (at least with our implementation)
        FxmlGuiDriver.setScene(new ClassicMode());
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
                    FxmlGuiDriver.setScene(new AdvancedMode());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
    }

    @FXML
    public void playScene() {
        //FxmlGuiDriver.sceneSwitcher("/fxml/playScene.fxml", playButton);
        FxmlGuiDriver.setScene("/fxml/playScene.fxml");
    }

    @FXML
    public void backToMainMenuScene() {
        //FxmlGuiDriver.sceneSwitcher("/fxml/playScene.fxml", playButton);
        FxmlGuiDriver.setScene("/fxml/startScene.fxml");
    }

    @FXML
    public void subMenuClassicScene() {
        //FxmlGuiDriver.sceneSwitcher("/fxml/playScene.fxml", playButton);
        FxmlGuiDriver.setScene("/fxml/submenuClassicMode.fxml");
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
    }

    @FXML
    public void registerScene() {
        FxmlGuiDriver.setScene("/fxml/registerScene.fxml");
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
