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
import javafx.scene.control.ProgressIndicator;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResultsPageAdvancedController implements Navigatable {

    //@FXML private Button backToMainMenuButton;
    @FXML
    private Label percentageLabel;
    @FXML
    private Label userNamesLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label heading;
    @FXML
    private Label nameRingLabel;
    @FXML
    private Label studiumRingLabel;
    @FXML
    private Label socialRingLabel;
    @FXML
    private Label zodiacRingLabel;

    @FXML
    private ProgressIndicator nameRing = new ProgressIndicator();
    @FXML
    private ProgressIndicator socialRing = new ProgressIndicator();
    @FXML
    private ProgressIndicator studiumRing = new ProgressIndicator();
    @FXML
    private ProgressIndicator zodiacRing = new ProgressIndicator();

    private int countToPercentage = 0;

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);

    /**
     * Timeline for the percentage counter
     */
    private Timeline timeline = new Timeline();

    /**
     * Fires when the scene is opened. Loads all information for the result page in the resultPageAdvanced.fxml labels.
     * Uses Calculator threads for the percentage number.
     * @param argument must be a session in order to know what session to work with
     */
    @Override
    public void onShow(Object argument) {

        if (argument instanceof Session) {
            Session session = (Session) argument;

            //BERECHNUNG


            Calculator studiumCalculation1 = new Calculator(session, 1);
            Calculator studiumCalculation2 = new Calculator(session, 2);
            Calculator zodiacCalculator = new Calculator(session, 3);
            Calculator socialCalculator1 = new Calculator(session, 4);
            Calculator socialCalculator2 = new Calculator(session, 5);


            studiumCalculation1.start();
            studiumCalculation2.start();
            zodiacCalculator.start();
            socialCalculator1.start();
            socialCalculator2.start();
            try {
                studiumCalculation1.join();
                studiumCalculation2.join();
                zodiacCalculator.join();
                socialCalculator1.join();
                socialCalculator2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            int finalStudiumResult = (studiumCalculation1.calculationResult + studiumCalculation2.calculationResult) / 2;
            int finalZodiacResult = zodiacCalculator.calculationResult;
            int finalSocialResult = (socialCalculator1.calculationResult + socialCalculator2.calculationResult) / 2;

            LOGGER.debug("Final Percentage for Studium: " + finalStudiumResult + "%");
            LOGGER.debug("Final Percentage for Zodiac: " + finalZodiacResult + "%");
            LOGGER.debug("Final Percentage for Social: " + finalSocialResult + "%");


            generatePieChar(studiumRing, finalStudiumResult, studiumRingLabel);
            generatePieChar(zodiacRing, finalZodiacResult, zodiacRingLabel);
            generatePieChar(socialRing, finalSocialResult, socialRingLabel);


            int finalNameResult = NameCalculation.calculate(session.getUserAnswer(true, 0).get(0), session.getUserAnswer(false, 0).get(0));
            generatePieChar(nameRing, finalNameResult, nameRingLabel);


            int finalPercentage = (finalNameResult + finalStudiumResult + finalZodiacResult + finalSocialResult) / 4;

            heading.setText("ERGEBNIS");
            heading.getStyleClass().add("mouseFont");
            userNamesLabel.setText(session.getUserAnswer(true, 0).get(0) + " und " + session.getUserAnswer(false, 0).get(0));
            userNamesLabel.getStyleClass().add("userNameLabel");
            userNamesLabel.setWrapText(true);
            descriptionLabel.setWrapText(true);

            ResultUpdater.updateResult(session, finalPercentage);
            generatePercentage(finalPercentage);


        }
    }

    /**
     * Generates the "pie char" ProgressIndicator in the resultsPageAdvanced.fxml
     * @param pi id of the ProgressIndicator
     * @param percentage the percentage of the final result from this category
     * @param label id of the label
     */
    private void generatePieChar(ProgressIndicator pi, double percentage, Label label) {

        pi.setProgress(percentage / 100);

        label.setText((int) percentage + "%");
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
    private void backToMainMenu() {
        FxmlGuiDriver.setScene("/fxml/startScene.fxml");
    }


}
