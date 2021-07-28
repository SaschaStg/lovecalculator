package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.calculator.NameCalculation;
import de.hdm_stuttgart.love_calculator.game.Session;
import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.gui.Navigatable;
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


            heading.setText("ERGEBNIS");
            heading.getStyleClass().add("mouseFont");
            userNamesLabel.setText(session.getUserAnswer(true, 0).get(0) + " und " + session.getUserAnswer(false, 0).get(0));
            userNamesLabel.getStyleClass().add("userNameLabel");
            courseLabel.setText("Du Studierst " + session.getUserAnswer(true, 1).get(0) + " und dein Schwarm " + session.getUserAnswer(false, 1).get(0));
            courseLabel.getStyleClass().add("courseResult");
            courseLabel.setWrapText(true);
            userNamesLabel.setWrapText(true);
            descriptionLabel.setWrapText(true);
            generatePercentage();


            if(LoginFactory.getLoggedInUser() != null) {

                // Datenbankadresse und Anmeldedaten
                String url = "jdbc:mysql://s230.goserver.host:3306/web21_db5";
                String user = "web21_5";
                String pass = "MLQCZRdX8DUIsxEk";


                try {
                    // Verbindung aufbauen
                    Connection con = DriverManager.getConnection(url, user, pass);
                    System.out.println("Verbindung erfolgreich hergestellt");


                    String searchInDB = "UPDATE userdata SET gamecount = gamecount + 1 WHERE username = ?";

                    PreparedStatement prepareInsertStatement =
                            con.prepareStatement(searchInDB);

                    prepareInsertStatement.setString(1, LoginFactory.getLoggedInUser());

                    int rs_insert = prepareInsertStatement.executeUpdate();

                    LOGGER.info("User " + LoginFactory.getLoggedInUser() + " is logged in! Incremented gamecount by 1.");


                    String checkPercentage = "SELECT * FROM userdata WHERE username = ?";
                    PreparedStatement prepareCheckStatement = con.prepareStatement(checkPercentage);

                    prepareCheckStatement.setString(1, LoginFactory.getLoggedInUser());

                    ResultSet percentageResult = prepareCheckStatement.executeQuery();

                    if(percentageResult.next()){

                        int highestMatch = percentageResult.getInt(6);
                        int currentHighestMatch = NameCalculation.calculate(session.getUserAnswer(true, 0).get(0), session.getUserAnswer(false, 0).get(0));


                        if(currentHighestMatch > highestMatch){
                            LOGGER.info("User " + LoginFactory.getLoggedInUser() + " got a new highest match: " + currentHighestMatch);

                            String setHighestmatch = "UPDATE userdata SET highestmatch = ?, highestmatchNumber = ? WHERE username = ?";

                            PreparedStatement prepareUpdateStatement =
                                    con.prepareStatement(setHighestmatch);

                            prepareUpdateStatement.setString(1, session.getUserAnswer(false, 0).get(0));
                            prepareUpdateStatement.setInt(2, currentHighestMatch);
                            prepareUpdateStatement.setString(3, LoginFactory.getLoggedInUser());


                            int rs_update = prepareUpdateStatement.executeUpdate();
                        }

                    }





                    con.close();


                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            }

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
            descriptionLabel.setText(generateDescription(session));
            descriptionLabel.getStyleClass().add("creativeText");
        }
    }
}




