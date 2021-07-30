package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.calculator.Calculator;
import de.hdm_stuttgart.love_calculator.calculator.Description;
import de.hdm_stuttgart.love_calculator.calculator.NameCalculation;
import de.hdm_stuttgart.love_calculator.game.Session;
import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.gui.Navigatable;
import de.hdm_stuttgart.love_calculator.sql.ResultUpdater;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class resultPageClassicController implements Navigatable {

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


    Timeline timeline = new Timeline();

    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);


    @FXML
    public void backToMainMenu() {

        FxmlGuiDriver.setScene("/fxml/startScene.fxml");
    }


    @Override
    public void onShow(Object argument) {

        if (argument instanceof Session) {

            Session session = (Session) argument;

            int namePercentage = (NameCalculation.calculate(session.getUserAnswer(true, 0).get(0), session.getUserAnswer(false, 0).get(0)) * 2);

            Calculator studiumCalculation1 = new Calculator(session, 1);

            studiumCalculation1.start();

            try {
                studiumCalculation1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            int finalStudiumResult = studiumCalculation1.calculationResult;
            int finalPercentage = (namePercentage + finalStudiumResult) / 3;

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

            LOGGER.info("Name percentage is: " + namePercentage / 2 + "%");
            LOGGER.info("Studium percentage is: " + finalStudiumResult + "%");
            LOGGER.info("Final percentage is: " + finalPercentage + "%");

        }
    }

    private void generatePercentage(int finalPercentage) {


        timeline = new Timeline(
                new KeyFrame(Duration.millis(0),
                        e -> generateCounter(finalPercentage)),
                new KeyFrame(Duration.millis(70)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void generateCounter(int percentage) {


        if (countToPercentage <= percentage - (percentage * 0.1)) {


            percentageLabel.setText(countToPercentage++ + "%");
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

            percentageLabel.setText(countToPercentage++ + "%");
            percentageLabel.getStyleClass().add("mouseFontPercentage");
        } else {
            timeline.stop();
            descriptionLabel.setText(Description.generateDescription(percentage));
            descriptionLabel.getStyleClass().add("creativeText");
        }
    }
}




