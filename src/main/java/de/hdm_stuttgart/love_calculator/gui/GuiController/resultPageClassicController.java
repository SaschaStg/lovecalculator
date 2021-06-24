package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.game.Answers;
import de.hdm_stuttgart.love_calculator.game.Catalog;
import de.hdm_stuttgart.love_calculator.game.Session;
import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class resultPageClassicController {

    @FXML private Button backToMainMenuButton;
    @FXML private Label percentageLabel;
    @FXML private Label userNamesLabel;
    @FXML private Label descriptionLabel;


    @FXML
    public void showName(Session session) {
        userNamesLabel.setText("Hallo");
        System.out.println("It works! showName active");
    }


}
