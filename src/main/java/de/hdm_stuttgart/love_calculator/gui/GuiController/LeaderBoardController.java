package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.game.Session;
import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.gui.Navigatable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.*;

public class LeaderBoardController implements Navigatable {

    @FXML private Label username1Label = new Label();
    @FXML private Label username2Label = new Label();
    @FXML private Label username3Label = new Label();
    @FXML private Label username4Label = new Label();
    @FXML private Label username5Label = new Label();

    @FXML private Label gamecount1Label = new Label();
    @FXML private Label gamecount2Label = new Label();
    @FXML private Label gamecount3Label = new Label();
    @FXML private Label gamecount4Label = new Label();
    @FXML private Label gamecount5Label = new Label();

    @Override
    public void onShow(Object argument) {

        // Datenbankadresse und Anmeldedaten
        String url = "jdbc:mysql://s230.goserver.host:3306/web21_db5";
        String user = "web21_5";
        String pass = "MLQCZRdX8DUIsxEk";

        System.out.println("SQL for leaderboard started!");



        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement stm = con.createStatement();

            ResultSet top1 = stm.executeQuery("SELECT gamecount, username FROM userdata ORDER BY gamecount DESC LIMIT 1;");
            if(top1.next()){
                gamecount1Label.setText(top1.getString(1));
                username1Label.setText(top1.getString(2));
            }

            ResultSet top2 = stm.executeQuery("SELECT gamecount, username FROM userdata ORDER BY gamecount DESC LIMIT 1,1;");
            if(top2.next()){
                gamecount2Label.setText(top2.getString(1));
                username2Label.setText(top2.getString(2));
            }

            ResultSet top3 = stm.executeQuery("SELECT gamecount, username FROM userdata ORDER BY gamecount DESC LIMIT 2,1;");
            if(top3.next()){
                gamecount3Label.setText(top3.getString(1));
                username3Label.setText(top3.getString(2));
            }

            ResultSet top4 = stm.executeQuery("SELECT gamecount, username FROM userdata ORDER BY gamecount DESC LIMIT 3,1;");
            if(top4.next()){
                gamecount4Label.setText(top4.getString(1));
                username4Label.setText(top4.getString(2));
            }

            ResultSet top5 = stm.executeQuery("SELECT gamecount, username FROM userdata ORDER BY gamecount DESC LIMIT 4,1;");
            if(top5.next()){
                gamecount5Label.setText(top5.getString(1));
                username5Label.setText(top5.getString(2));
            }

            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @FXML
    public void profileScene() {
        if (LoginFactory.getLoggedInUser() != null) {
            FxmlGuiDriver.setScene("/fxml/loggedInScene.fxml");
        } else {
            FxmlGuiDriver.setScene("/fxml/profileScene.fxml");
        }
    }

    @FXML
    public void playScene() {
        //FxmlGuiDriver.sceneSwitcher("/fxml/playScene.fxml", playButton);
        FxmlGuiDriver.setScene("/fxml/playScene.fxml");
    }

    @FXML
    public void openLeaderBoard() {
        FxmlGuiDriver.setScene("/fxml/leaderBoardScene.fxml");
        System.out.println("Leaderboard opened!");
    }

    @FXML
    public void startScene() {
        FxmlGuiDriver.setScene("/fxml/startScene.fxml");
    }

}
