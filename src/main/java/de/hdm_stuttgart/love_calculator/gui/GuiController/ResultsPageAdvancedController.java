package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.calculator.NameCalculation;
import de.hdm_stuttgart.love_calculator.game.Session;
import de.hdm_stuttgart.love_calculator.gui.Navigatable;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResultsPageAdvancedController implements Navigatable {

    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);

    //@FXML private Button backToMainMenuButton;
    @FXML private Label percentageLabel;
    @FXML private Label userNamesLabel;
    @FXML private Label descriptionLabel;
    @FXML private Label heading;
    @FXML private Label nameRingLabel;

    @FXML ProgressIndicator nameRing = new ProgressIndicator();
    @FXML ProgressIndicator drinkRing = new ProgressIndicator();
    @FXML ProgressIndicator studiumRing = new ProgressIndicator();
    @FXML ProgressIndicator instagramRing = new ProgressIndicator();
    @FXML ProgressIndicator custom1Ring = new ProgressIndicator();
    @FXML ProgressIndicator custom2Ring = new ProgressIndicator();
    @FXML ProgressIndicator custom3Ring = new ProgressIndicator();

    private int studiumPercentage;
    private int partyPercentage;

    private int countToPercentage = 0;

    private Session session;

    Timeline timeline = new Timeline();

    @Override
    public void onShow(Object argument) {

        if (argument instanceof Session) {
            this.session = (Session) argument;

            if(checkAnswers(1)) {
                studiumPercentage += 65;
            }

            heading.setText("ERGEBNIS");
            heading.getStyleClass().add("mouseFont");
            userNamesLabel.setText(session.getUserAnswer(true, 0).get(0) + " und " + session.getUserAnswer(false, 0).get(0));
            userNamesLabel.getStyleClass().add("userNameLabel");
            userNamesLabel.setWrapText(true);
            descriptionLabel.setWrapText(true);

            generatePercentage();

            int nameCalculation = NameCalculation.calculate(session.getUserAnswer(true, 0).get(0), session.getUserAnswer(false, 0).get(0));
            nameRing.setProgress(Double.parseDouble(0 + "." + nameCalculation));
            nameRingLabel.setText(nameCalculation + " %");
        }
    }

    private boolean checkAnswers(int questionIndex) {
        if(session.getUserAnswer(true, questionIndex).get(0) == session.getUserAnswer(false, questionIndex).get(0)) {
            return true;
        }
        return false;
    }


    private void generatePercentage() {


        timeline = new Timeline(
                new KeyFrame(Duration.millis(0),
                        e -> generateCounter()),
                new KeyFrame(Duration.millis(70)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void generateCounter() {

        int percentage = NameCalculation.calculate(session.getUserAnswer(true, 0).get(0), session.getUserAnswer(false, 0).get(0));


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
            descriptionLabel.setText(resultPageClassicController.generateDescription(session));
            descriptionLabel.getStyleClass().add("creativeText");
        }
    }
}
