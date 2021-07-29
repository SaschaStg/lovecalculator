package de.hdm_stuttgart.love_calculator.gui.GuiController;


import de.hdm_stuttgart.love_calculator.game.Session;
import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.gui.Navigatable;
import de.hdm_stuttgart.love_calculator.sql.SqlParameter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.*;

public class LoggedInController implements Navigatable {

    @FXML private Label username = new Label();
    @FXML private Label userInformation = new Label();
    @FXML private Label counter = new Label();
    @FXML private Label highestMatch = new Label();
    @FXML private Label highestMatchNumber = new Label();
    @FXML private ImageView profilePicture = new ImageView();


    private Session session;

    @Override
    public void onShow(Object argument) {

            try {
                // Verbindung aufbauen
                Connection con = DriverManager.getConnection(SqlParameter.URL, SqlParameter.USER, SqlParameter.PASSW);
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
                    showProfilePicture(rs.getInt(9));

                }


                con.close();



            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }





        }

        @FXML
        private void logout() {
        LoginFactory.setLoggedInUser(null);
        FxmlGuiDriver.setScene("/fxml/profileScene.fxml");
        }

        private void showProfilePicture(int profilePictureIndex) {

        switch(profilePictureIndex) {
            case 1: profilePicture.setImage(new Image(FxmlGuiDriver.class.getResource("/images/mike.jpg").toExternalForm()));
                break;
            case 2: profilePicture.setImage(new Image(FxmlGuiDriver.class.getResource("/images/amogus.jpg").toExternalForm()));
                //PB 2
                break;
            case 3: profilePicture.setImage(new Image(FxmlGuiDriver.class.getResource("/images/daniel_jung.jpg").toExternalForm()));
                //PB 2
                break;
            case 4: profilePicture.setImage(new Image(FxmlGuiDriver.class.getResource("/images/fish_meme.jpg").toExternalForm()));
                break;
            case 5: profilePicture.setImage(new Image(FxmlGuiDriver.class.getResource("/images/harold.jpg").toExternalForm()));
                break;
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
