package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.calculator.NameCalculation;
import de.hdm_stuttgart.love_calculator.game.Session;
import de.hdm_stuttgart.love_calculator.gui.Navigatable;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
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



    private int countToPercentage = 0;

    private Session session;

    Timeline timeline = new Timeline();

    @Override
    public void onShow(Object argument) {

        if (argument instanceof Session) {
            this.session = (Session) argument;

            heading.setText("ERGEBNIS");
            heading.getStyleClass().add("mouseFont");
            userNamesLabel.setText(session.getUserAnswer(true, 0).get(0) + " und " + session.getUserAnswer(false, 0).get(0));
            userNamesLabel.getStyleClass().add("userNameLabel");
            userNamesLabel.setWrapText(true);
            descriptionLabel.setWrapText(true);

            //generatePercentage();

            int nameCalculation = NameCalculation.calculate(session.getUserAnswer(true, 0).get(0), session.getUserAnswer(false, 0).get(0));
            nameRing.setProgress(Double.parseDouble(0 + "." + nameCalculation));
            nameRingLabel.setText(nameCalculation + " %");
        }
    }

    private int checkAnswers(int questionIndex) {
        if(session.getUserAnswer(true, questionIndex).get(0) == session.getUserAnswer(false, questionIndex).get(0)) {
           // if(session.)
        }
        return 0;
    }
}
