package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.calculator.NameCalculation;
import de.hdm_stuttgart.love_calculator.game.Answers;
import de.hdm_stuttgart.love_calculator.game.Catalog;
import de.hdm_stuttgart.love_calculator.game.Session;
import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.gui.Navigatable;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class resultPageClassicController implements Navigatable {

    @FXML private Button backToMainMenuButton;
    @FXML private Label percentageLabel;
    @FXML private Label userNamesLabel;
    @FXML private Label descriptionLabel;
    @FXML private Label courseLabel;

    private Session session;

    @FXML
    public void showName() {
        //userNamesLabel.setText("Hallo");
        System.out.println("It works! showName active");
    }

    @FXML
    public void backToMainMenu() {
        //userNamesLabel.setText("Hallo");
        FxmlGuiDriver.setScene("/fxml/startScene.fxml");
    }


    @Override
    public void onShow(Object argument) {
        if(argument instanceof Session) {
            this.session = (Session) argument;
            userNamesLabel.setText(session.getUserAnswer(true, 0).get(0) + " und " + session.getUserAnswer(false, 0).get(0));
            descriptionLabel.setText("Hier kommt eine Beschreibung");
            percentageLabel.setText(String.valueOf(NameCalculation.calculate(session.getUserAnswer(true, 0).get(0), session.getUserAnswer(false, 0).get(0)) + " %"));
            courseLabel.setText("Du Studierst " + session.getUserAnswer(true, 1).get(0) + " und dein Schwarm " +  session.getUserAnswer(false, 1).get(0));
        }
    }
}
