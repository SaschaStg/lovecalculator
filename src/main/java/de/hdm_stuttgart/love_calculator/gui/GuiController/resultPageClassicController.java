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
            descriptionLabel.setText(generateDescription());
            percentageLabel.setText(String.valueOf(NameCalculation.calculate(session.getUserAnswer(true, 0).get(0), session.getUserAnswer(false, 0).get(0)) + "%"));
            courseLabel.setText("Du Studierst " + session.getUserAnswer(true, 1).get(0) + " und dein Schwarm " +  session.getUserAnswer(false, 1).get(0));
        }
    }

    public String generateDescription() {
        int percentage = NameCalculation.calculate(session.getUserAnswer(true, 0).get(0), session.getUserAnswer(false, 0).get(0));
        if(percentage <= 10) {
            return "Leider passt ihr nicht zusammen :(";
        }
        else if(percentage > 10 && percentage <= 20) {
            return "Naja.. das wird glaub nix.";
        }
        else if(percentage > 20 && percentage <= 30) {
            return "Eventuell könnt ihr ja Freunde sein..";
        }
        else if(percentage > 30 && percentage <= 40) {
            return "Also gute Freunde könntet ihr ja schon werden";
        }
        else if(percentage > 40 && percentage <= 50) {
            return "Könnte schwierig werden.. aber nicht unmöglich!";
        }
        else if(percentage > 50 && percentage <= 60) {
            return "Zwar keine Liebe auf den ersten Blick aber auch nicht hoffnungslos!";
        }
        else if(percentage > 60 && percentage <= 70) {
            return "Es könnte öfter Stress in der Beziehung geben aber ihr passt schon ein wenig zusammen!";
        }
        else if(percentage > 70 && percentage <= 80) {
            return "Zwischen euch funkt es.. da ist was!";
        }
        else if(percentage > 80 && percentage <= 90) {
            return "Seid ihr schon zusammen? Nein? Dann wirds höchste Zeit! Das passt super!";
        }
        else if(percentage > 90 && percentage <= 100) {
            return "Besser gehts nicht! Das nennt man Liebe auf den ersten Blick!";
        }
        return "Eure Liebe überfordert sogar das System.. das ist wohl was ganz besonderes?";
    }
}
