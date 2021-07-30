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
import javafx.scene.control.ProgressIndicator;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResultsPageAdvancedController implements Navigatable {

    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);

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
    ProgressIndicator nameRing = new ProgressIndicator();
    @FXML
    ProgressIndicator socialRing = new ProgressIndicator();
    @FXML
    ProgressIndicator studiumRing = new ProgressIndicator();
    @FXML
    ProgressIndicator zodiacRing = new ProgressIndicator();

    private int countToPercentage = 0;

    private Session session;

    Timeline timeline = new Timeline();

    @Override
    public void onShow(Object argument) {

        if (argument instanceof Session) {
            this.session = (Session) argument;

            //BERECHNUNG






            Calculator studiumCalculation1 = new Calculator(session, 1);
            Calculator studiumCalculation2 = new Calculator(session, 2);
            Calculator zodiacCalculator = new Calculator(session,3);
            Calculator socialCalculator1 = new Calculator(session,4);
            Calculator socialCalculator2 = new Calculator(session,5);


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

            LOGGER.info("Final Percentage for Studium: " + finalStudiumResult + "%");
            LOGGER.info("Final Percentage for Zodiac: " + finalZodiacResult + "%");
            LOGGER.info("Final Percentage for Social: " + finalSocialResult + "%");


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

    private void generatePieChar(ProgressIndicator pi, double percentage, Label label) {

        pi.setProgress(percentage / 100);

        label.setText((int)percentage + "%");
    }

    private void generatePercentage(int percentage) {


        timeline = new Timeline(
                new KeyFrame(Duration.millis(0),
                        e -> generateCounter(percentage)),
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
            descriptionLabel.setText(Description.generateDescription(session, percentage));
            descriptionLabel.getStyleClass().add("creativeText");
        }
    }

    @FXML
    private void backToMainMenu(){
        FxmlGuiDriver.setScene("/fxml/startScene.fxml");
    }


}
