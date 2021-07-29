package de.hdm_stuttgart.love_calculator.gui.GuiController;


import de.hdm_stuttgart.love_calculator.game.Session;
import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.gui.Navigatable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.*;

public class LoggedInController implements Navigatable {

    @FXML private Label username = new Label();
    @FXML private Label userInformation = new Label();
    @FXML private Label counter = new Label();
    @FXML private Label highestMatch = new Label();
    @FXML private Label highestMatchNumber = new Label();


    private Session session;

    @Override
    public void onShow(Object argument) {


            // Datenbankadresse und Anmeldedaten
            String url = "jdbc:mysql://s230.goserver.host:3306/web21_db5";
            String user = "web21_5";
            String pass = "MLQCZRdX8DUIsxEk";

            System.out.println("Working!");



            try {
                // Verbindung aufbauen
                Connection con = DriverManager.getConnection(url, user, pass);
                System.out.println("Verbindung erfolgreich hergestellt");



                String searchInDB = "SELECT * FROM userdata WHERE username = ?;";

                PreparedStatement preparedStatement =
                        con.prepareStatement(searchInDB);

                preparedStatement.setString(1, LoginFactory.getLoggedInUser());

                ResultSet rs = preparedStatement.executeQuery();



                if(rs.next()){

                    userInformation.setText(rs.getString(7) + " " + rs.getString(8));
                    username.setText(rs.getString(2));
                    counter.setText(rs.getString(4));
                    highestMatch.setText(rs.getString(5));
                    highestMatchNumber.setText(rs.getString(6));

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
