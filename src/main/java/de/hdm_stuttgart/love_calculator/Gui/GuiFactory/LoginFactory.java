package de.hdm_stuttgart.love_calculator.Gui.GuiFactory;

import de.hdm_stuttgart.love_calculator.Gui.AlertDialogue;
import de.hdm_stuttgart.love_calculator.Gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.Sql.SqlParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;


public class LoginFactory {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);

    /**
     * String to check if the user is logged in and also get the name of the user
     */
    private static String loggedInUser;

    /**
     * Check if the user can login, otherwise show a dialog with the error
     * @param username the username of the user as a String
     * @param password the password of the user as a String
     */
    public static void checkLogin(String username, String password) {

        if (checkLoginInput(username, password)) {

            if (!sqlLogin(username, password)) {
                AlertDialogue.showInfoAlert("Benutzername oder Passwort falsch.", "Bist du schon registriert?");
            } else {
                FxmlGuiDriver.setScene("/fxml/loggedInScene.fxml");
            }
        } else {
            AlertDialogue.showInfoAlert("Fehler", "Bitte fülle alle Felder aus.");
        }
    }

    /**
     * Sets the String loggedInUser to the users username
     * @param setLoggedInUser
     */
    public static void setLoggedInUser(String setLoggedInUser) {
        loggedInUser = setLoggedInUser;
    }

    /**
     * Get the logged in users username
     * @return the username from the user
     */
    public static String getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Checks the login input from the user
     * otherwise return true
     * @param username the username of the user as a String
     * @param password the password of the user as a String
     * @return return false if the username and / or password field is empty
     */
    public static boolean checkLoginInput(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            LOGGER.error("No Username and/or password found.");
            return false;
        }
        return true;
    }

    /**
     * A database connection to check if the users username and password are correct with the entrys in the database
     * @param username the username of the user as a String
     * @param password the password of the user as a String
     * @return If the input is correct, return true, otherwise return false
     */
    public static boolean sqlLogin(String username, String password) {
        try {
            // Verbindung aufbauen
            Connection con = DriverManager.getConnection(SqlParameter.URL, SqlParameter.USER, SqlParameter.PASSW);
            LOGGER.debug("Connection to database successful.");

            String searchInDB = "SELECT * FROM userdata WHERE username = ? and password = ?;";

            PreparedStatement preparedStatement =
                    con.prepareStatement(searchInDB);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                //Safe Username in String
                setLoggedInUser(rs.getString(2));

                LOGGER.debug("User " + username + " is now logged in.");
                con.close();
                return true;
            } else {
                LOGGER.debug("User entered a wrong username or password.");
            }


            con.close();

            return false;

        } catch (SQLException e) {
            LOGGER.fatal(e.getMessage());
        }
        return false;
    }
}
