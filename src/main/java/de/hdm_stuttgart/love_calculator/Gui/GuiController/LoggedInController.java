package de.hdm_stuttgart.love_calculator.Gui.GuiController;

import de.hdm_stuttgart.love_calculator.Gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.Gui.GuiFactory.LoginFactory;
import de.hdm_stuttgart.love_calculator.Gui.GuiFactory.QuestionsFactory;
import de.hdm_stuttgart.love_calculator.Gui.Navigatable;
import de.hdm_stuttgart.love_calculator.Sql.SqlParameter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Objects;

public class LoggedInController implements Navigatable {

    @FXML private Label username = new Label();
    @FXML private Label userInformation = new Label();
    @FXML private Label counter = new Label();
    @FXML private Label highestMatch = new Label();
    @FXML private Label highestMatchNumber = new Label();
    @FXML private ImageView profilePicture = new ImageView();

    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);

    @Override
    public void onShow(Object argument) {

        try {
            // Verbindung aufbauen
            Connection con = DriverManager.getConnection(SqlParameter.URL, SqlParameter.USER, SqlParameter.PASSW);
            LOGGER.info("Connection to database successful.");


            String searchInDB = "SELECT * FROM userdata WHERE username = ?;";

            PreparedStatement preparedStatement =
                    con.prepareStatement(searchInDB);

            preparedStatement.setString(1, LoginFactory.getLoggedInUser());

            ResultSet rs = preparedStatement.executeQuery();


            if (rs.next()) {

                userInformation.setText(rs.getString(7) + " " + rs.getString(8));
                username.setText(rs.getString(2));
                counter.setText(rs.getString(4));
                highestMatch.setText(rs.getString(5));
                highestMatchNumber.setText(rs.getString(6) + "%");
                showProfilePicture(rs.getInt(9));

                LOGGER.debug("User " + username.getText() + " logged in.");

            }
            con.close();

        } catch (SQLException e) {
            LOGGER.fatal(e.getMessage());
        }
    }

    @FXML
    private void logout() {
        LOGGER.debug("User " + LoginFactory.getLoggedInUser() + " logged out. Set LoggedInUser to null");
        LoginFactory.setLoggedInUser(null);
        FxmlGuiDriver.setScene("/fxml/profileScene.fxml");

    }

    //Switch case to get the right profile picture out of the database and load it into the profile
    private void showProfilePicture(int profilePictureIndex) {

        switch (profilePictureIndex) {
            case 1:
                profilePicture.setImage(new Image(Objects.requireNonNull(FxmlGuiDriver.class.getResource("/images/mike.jpg")).toExternalForm()));
                break;
            case 2:
                profilePicture.setImage(new Image(Objects.requireNonNull(FxmlGuiDriver.class.getResource("/images/amogus.jpg")).toExternalForm()));
                break;
            case 3:
                profilePicture.setImage(new Image(Objects.requireNonNull(FxmlGuiDriver.class.getResource("/images/daniel_jung.jpg")).toExternalForm()));
                break;
            case 4:
                profilePicture.setImage(new Image(Objects.requireNonNull(FxmlGuiDriver.class.getResource("/images/fish_meme.jpg")).toExternalForm()));
                break;
            case 5:
                profilePicture.setImage(new Image(Objects.requireNonNull(FxmlGuiDriver.class.getResource("/images/harold.jpg")).toExternalForm()));
                break;
            default: LOGGER.error("No profile picture found!");
        }
        LOGGER.debug("Profilepicture loaded for " + LoginFactory.getLoggedInUser());
    }

    @FXML
    private void profileScene() {
        if (LoginFactory.getLoggedInUser() != null) {
            FxmlGuiDriver.setScene("/fxml/loggedInScene.fxml");
            LOGGER.debug("User " + LoginFactory.getLoggedInUser() + " is logged in. Showing loggedInScene");
        } else {
            FxmlGuiDriver.setScene("/fxml/profileScene.fxml");
            LOGGER.debug("User is not logged in. Showing profileScene");
        }
    }

    @FXML
    private void playScene() {
        //FxmlGuiDriver.sceneSwitcher("/fxml/playScene.fxml", playButton);
        FxmlGuiDriver.setScene("/fxml/playScene.fxml");
        LOGGER.debug("Switched scene to playScene");
    }

    @FXML
    private void openLeaderBoard() {
        FxmlGuiDriver.setScene("/fxml/leaderBoardScene.fxml");
        LOGGER.debug("Switched scene to leaderBoardScene");
    }

    @FXML
    private void startScene() {
        FxmlGuiDriver.setScene("/fxml/startScene.fxml");
        LOGGER.debug("Switched scene to startScene");
    }

}
