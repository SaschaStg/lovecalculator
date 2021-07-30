package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.gui.AlertDialogue;
import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.sql.SqlParameter;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;



public class LoginFactory {

    //create logger for every class
    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);

    private static String loggedInUser;

    public static void checkLogin(String username, String password) {

        if(checkLoginInput(username, password)) {

            if (!sqlLogin(username, password)) {
                AlertDialogue.showInfoAlert("Benutzername oder Passwort falsch.", "Bist du schon registriert?");
            }
        } else {
            AlertDialogue.showInfoAlert("Fehler", "Bitte fülle alle Felder aus.");
        }
    }

    public static void setLoggedInUser(String setLoggedInUser) {
        loggedInUser = setLoggedInUser;
    }

    public static String getLoggedInUser() {
        return loggedInUser;
    }

    public static boolean checkLoginInput(String username, String password) {
        if(username.isEmpty() || password.isEmpty()) {
            LOGGER.error("No Username and/or password found.");
            return false;
        }
        return true;
    }

    public static boolean sqlLogin(String username, String password) {
        try {
            // Verbindung aufbauen
            Connection con = DriverManager.getConnection(SqlParameter.URL, SqlParameter.USER, SqlParameter.PASSW);
            LOGGER.info("Connection to database successful.");

            String searchInDB = "SELECT * FROM userdata WHERE username = ? and password = ?;";

            PreparedStatement preparedStatement =
                    con.prepareStatement(searchInDB);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){

                //Safe Username in String
                setLoggedInUser(rs.getString(2));
                FxmlGuiDriver.setScene("/fxml/loggedInScene.fxml");
                LOGGER.info("User " + username + " is now logged in.");
                return true;
            }else{
                LOGGER.info("User entered a wrong username or password.");
            }


            con.close();

            return false;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return false;
    }





}
