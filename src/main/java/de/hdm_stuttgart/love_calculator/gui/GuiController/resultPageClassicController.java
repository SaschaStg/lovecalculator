package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.calculator.CalculationAnimation;
import de.hdm_stuttgart.love_calculator.calculator.Calculator;
import de.hdm_stuttgart.love_calculator.calculator.NameCalculation;
import de.hdm_stuttgart.love_calculator.game.Session;
import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.gui.Navigatable;
import de.hdm_stuttgart.love_calculator.sql.ResultUpdater;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.concurrent.atomic.AtomicInteger;

public class resultPageClassicController implements Navigatable {

    @FXML
    private Button backToMainMenuButton;
    @FXML
    private Label percentageLabel;
    @FXML
    private Label userNamesLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label courseLabel;
    @FXML
    private Label heading;

    private int countToPercentage = 0;

    private Session session;


    Timeline timeline = new Timeline();

    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);

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

        if (argument instanceof Session) {
            this.session = (Session) argument;

            int namePercentage = (NameCalculation.calculate(session.getUserAnswer(true, 0).get(0), session.getUserAnswer(false, 0).get(0)) * 2);
            int studiumPercentage = Calculator.calculate(session, 1);
            int finalPercentage = (namePercentage + studiumPercentage) / 3;

            ResultUpdater.updateResult(session, finalPercentage);

            heading.setText("ERGEBNIS");
            heading.getStyleClass().add("mouseFont");
            userNamesLabel.setText(session.getUserAnswer(true, 0).get(0) + " und " + session.getUserAnswer(false, 0).get(0));
            userNamesLabel.getStyleClass().add("userNameLabel");
            courseLabel.setText("Du Studierst " + session.getUserAnswer(true, 1).get(0) + " und dein Schwarm " + session.getUserAnswer(false, 1).get(0));
            courseLabel.getStyleClass().add("courseResult");
            courseLabel.setWrapText(true);
            userNamesLabel.setWrapText(true);
            descriptionLabel.setWrapText(true);
            generatePercentage(finalPercentage);

            LOGGER.info("Name percentage is: " + namePercentage/2 + "%");
            LOGGER.info("Studium percentage is: " + studiumPercentage + "%");
            LOGGER.info("Final percentage is: " + finalPercentage + "%");

        }
    }

    public static String generateDescription(Session sessionDescription) {
        int percentage = NameCalculation.calculate(sessionDescription.getUserAnswer(true, 0).get(0), sessionDescription.getUserAnswer(false, 0).get(0));
        if (percentage <= 10) {
            return "Leider passt ihr nicht zusammen :(";
        } else if (percentage > 10 && percentage <= 20) {
            return "Naja.. das wird glaub nix.";
        } else if (percentage > 20 && percentage <= 30) {
            return "Eventuell könnt ihr ja Freunde sein..";
        } else if (percentage > 30 && percentage <= 40) {
            return "Also gute Freunde könntet ihr ja schon werden";
        } else if (percentage > 40 && percentage <= 50) {
            return "Könnte schwierig werden.. aber nicht unmöglich!";
        } else if (percentage > 50 && percentage <= 60) {
            return "Zwar keine Liebe auf den ersten Blick aber auch nicht hoffnungslos!";
        } else if (percentage > 60 && percentage <= 70) {
            return "Es könnte öfter Stress in der Beziehung geben aber ihr passt schon ein wenig zusammen!";
        } else if (percentage > 70 && percentage <= 80) {
            return "Zwischen euch funkt es.. da ist was!";
        } else if (percentage > 80 && percentage <= 90) {
            return "Seid ihr schon zusammen? Nein? Dann wirds höchste Zeit! Das passt super!";
        } else if (percentage > 90 && percentage <= 100) {
            return "Besser gehts nicht! Das nennt man Liebe auf den ersten Blick!";
        }
        return "Eure Liebe überfordert sogar das System.. das ist wohl was ganz besonderes?";
    }


    private void generatePercentage(int finalPercentage) {


        timeline = new Timeline(
                new KeyFrame(Duration.millis(0),
                        e -> generateCounter(finalPercentage)),
                new KeyFrame(Duration.millis(70)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void generateCounter(int finalPercentage) {

        int percentage = finalPercentage;


        if (countToPercentage <= percentage - (percentage * 0.1)) {


            percentageLabel.setText(String.valueOf(countToPercentage++) + "%");
            percentageLabel.getStyleClass().add("mouseFontPercentage");

        } else {
            timeline.stop();
            timeline = new Timeline(
                    new KeyFrame(Duration.millis(0),
                            e -> generateCounterSlow(percentage)),
                    new KeyFrame(Duration.millis(500)));

            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
    }

    private void generateCounterSlow(int percentage) {
        if (countToPercentage <= percentage) {

            percentageLabel.setText(String.valueOf(countToPercentage++) + "%");
            percentageLabel.getStyleClass().add("mouseFontPercentage");
        } else {
            timeline.stop();
            descriptionLabel.setText(generateDescription(session));
            descriptionLabel.getStyleClass().add("creativeText");
        }
    }
}




