package de.hdm_stuttgart.love_calculator.Gui.GuiFactory;

import de.hdm_stuttgart.love_calculator.Gui.AlertDialogue;
import de.hdm_stuttgart.love_calculator.Gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.Sql.SqlParameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class RegisterFactory {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);


    public static void register(String username, String password, String vorname, String nachname) {

        if (validRegister(username, password, vorname, nachname)) {

            if (sqlRegister(username, password, vorname, nachname)) {
                AlertDialogue.showInfoAlert("Registrierung erfolgreich!", "Du kannst dich jetzt einloggen!");
            } else {
                AlertDialogue.showInfoAlert("Fehler!", "Dieser Benutzer exisitert bereits!");

            }
        } else {
            AlertDialogue.showInfoAlert("Fehler", "Bitte fülle alle Felder aus!");
        }
    }

    public static boolean validRegister(String username, String password, String vorname, String nachname) {
        if (username.isEmpty() || password.isEmpty() || vorname.isEmpty() || nachname.isEmpty()) {
            LOGGER.debug("Not all fields are filled.");
            return false;
        }
        return true;
    }

    public static boolean sqlRegister(String username, String password, String vorname, String nachname) {
        try {
            // Verbindung aufbauen
            Connection con = DriverManager.getConnection(SqlParameter.URL, SqlParameter.USER, SqlParameter.PASSW);
            LOGGER.debug("Connection to database successful.");


            String searchInDB = "SELECT * FROM userdata WHERE username = ?;";

            PreparedStatement preparedStatement =
                    con.prepareStatement(searchInDB);

            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();


            if (rs.next()) {
                LOGGER.debug("Tried to create an already existing username (" + username + ")");
                return false;

            } else {

                String createUser = "INSERT INTO userdata (username, password,  vorname, nachname, picture) VALUES (?, ?, ?, ?, ?);";

                PreparedStatement prepareInsertStatement =
                        con.prepareStatement(createUser);

                int randomPicture = (int) Math.floor(Math.random() * (5 - 1 + 1)) + 1;

                prepareInsertStatement.setString(1, username);
                prepareInsertStatement.setString(2, password);
                prepareInsertStatement.setString(3, vorname);
                prepareInsertStatement.setString(4, nachname);
                prepareInsertStatement.setInt(5, randomPicture);

                String randomPictureString = String.valueOf(randomPicture);

                List<String> loggerList =
                        Arrays.asList(username, password, vorname, nachname, randomPictureString);

                loggerList.forEach(s -> LOGGER.info("Created " + s + " in database."));

                int rs_insert = prepareInsertStatement.executeUpdate();
            }

            con.close();

            FxmlGuiDriver.setScene("/fxml/loginScene.fxml");
            return true;

        } catch (SQLException e) {
            LOGGER.fatal(e.getMessage());
        }
        return false;
    }
}