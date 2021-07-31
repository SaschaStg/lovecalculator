package de.hdm_stuttgart.love_calculator.Gui.GuiController;

import de.hdm_stuttgart.love_calculator.Calculator.Calculator;
import de.hdm_stuttgart.love_calculator.Calculator.Description;
import de.hdm_stuttgart.love_calculator.Calculator.NameCalculation;
import de.hdm_stuttgart.love_calculator.Game.Session;
import de.hdm_stuttgart.love_calculator.Gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.Gui.GuiFactory.QuestionsFactory;
import de.hdm_stuttgart.love_calculator.Gui.Navigatable;
import de.hdm_stuttgart.love_calculator.Sql.ResultUpdater;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResultPageClassicController implements Navigatable {

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

    /**
     * Integer to know how high the counter should go in the generateCounter method
     */
    private int countToPercentage = 0;

    /**
     * Timeline for the percentage counter
     */
    private Timeline timeline = new Timeline();

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);

    /**
     * Fires when the scene is opened. Loads all information for the result page in the resultPageClassic.fxml labels.
     * Uses Calculator threads for the percentage number.
     * @param argument must be a session in order to know what session to work with
     */
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

            LOGGER.debug("Name percentage is: " + namePercentage / 2 + "%");
            LOGGER.debug("Studium percentage is: " + finalStudiumResult + "%");
            LOGGER.debug("Final percentage is: " + finalPercentage + "%");

        }
    }

    /**
     * Generates a visible counter which updates every 70ms up to the final percentage
     * @param finalPercentage the final percentage to what this method should count
     */
    private void generatePercentage(int finalPercentage) {

        timeline = new Timeline(
                new KeyFrame(Duration.millis(0),
                        e -> generateCounter(finalPercentage)),
                new KeyFrame(Duration.millis(70)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    /**
     * For the last 10% of the final percentage the counter is slowed down to 500ms every number to give a more
     * intense experience for the user
     * @param finalPercentage the final percentage to what this method should count
     */
    private void generateCounter(int finalPercentage) {

        if (countToPercentage <= finalPercentage - (finalPercentage * 0.1)) {


            percentageLabel.setText(countToPercentage++ + "%");
            percentageLabel.getStyleClass().add("mouseFontPercentage");

        } else {
            timeline.stop();
            timeline = new Timeline(
                    new KeyFrame(Duration.millis(0),
                            e -> generateCounterSlow(finalPercentage)),
                    new KeyFrame(Duration.millis(500)));

            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
    }

    /**
     * Sets the labels for the counter to the percentage which is calculated in the method generatePercentage
     * and generateCounter
     * @param finalPercentage the final percentage to what this method should count
     */
    private void generateCounterSlow(int finalPercentage) {
        if (countToPercentage <= finalPercentage) {

            percentageLabel.setText(countToPercentage++ + "%");
            percentageLabel.getStyleClass().add("mouseFontPercentage");
        } else {
            timeline.stop();
            descriptionLabel.setText(Description.generateDescription(finalPercentage));
            descriptionLabel.getStyleClass().add("creativeText");
        }
    }

    /**
     * Switches the scene to the startScene.fxml
     */
    @FXML
    public void backToMainMenu() {

        FxmlGuiDriver.setScene("/fxml/startScene.fxml");
    }
}




