package de.hdm_stuttgart.love_calculator.gui.GuiController;

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

    public static void checkLogin(TextField usernameTextField, TextField passwordTextField) {

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if(username.isEmpty() || password.isEmpty()) {
            LOGGER.error("No Username and/or password found.");

        }
        LOGGER.info("Login input found");

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
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Benutzername oder Passwort falsch");
                alert.setHeaderText(null);
                alert.setContentText("Bist du schon registriert?");
                alert.showAndWait();
                LOGGER.info("User entered a wrong username or password.");
            }


            con.close();



        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static void setLoggedInUser(String setLoggedInUser) {
        loggedInUser = setLoggedInUser;
    }

    public static String getLoggedInUser() {
        return loggedInUser;
    }







}
