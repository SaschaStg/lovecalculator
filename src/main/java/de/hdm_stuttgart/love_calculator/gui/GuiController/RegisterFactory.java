package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.game.Session;
import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.gui.Navigatable;
import de.hdm_stuttgart.love_calculator.sql.SqlParameter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.Result;
import java.sql.*;

public class RegisterFactory {

    //create logger for every class
    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);


    public static boolean register(TextField usernameTextField, TextField passwordTextField, TextField vornameTextField, TextField nachnameTextField) {

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String vorname = vornameTextField.getText();
        String nachname = nachnameTextField.getText();



        if (username.isEmpty() || password.isEmpty() || vorname.isEmpty() || nachname.isEmpty()) {
            LOGGER.error("Not all fields are filled");

            return false;
        }
        LOGGER.info("All inputs available");



        try {
            // Verbindung aufbauen
            Connection con = DriverManager.getConnection(SqlParameter.URL, SqlParameter.USER, SqlParameter.PASSW);
            System.out.println("Verbindung erfolgreich hergestellt");


            String searchInDB = "SELECT * FROM userdata WHERE username = ?;";

            PreparedStatement preparedStatement =
                    con.prepareStatement(searchInDB);

            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();


            if (rs.next()) {

                LOGGER.info("Tried to create an already existing username");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registrierfehler");
                alert.setHeaderText(null);
                alert.setContentText("Dieser Account existiert bereits. Bitte wähle einen anderen Benutzernamen");
                alert.showAndWait();


            } else {

                String createUser = "INSERT INTO userdata (username, password,  vorname, nachname, picture) VALUES (?, ?, ?, ?, ?);";

                PreparedStatement prepareInsertStatement =
                        con.prepareStatement(createUser);

                prepareInsertStatement.setString(1, username);
                prepareInsertStatement.setString(2, password);
                prepareInsertStatement.setString(3, vorname);
                prepareInsertStatement.setString(4, nachname);
                prepareInsertStatement.setInt(5, (int)Math.floor(Math.random() * (5 - 1 + 1)) + 1);




                int rs_insert = prepareInsertStatement.executeUpdate();

                System.out.println(rs_insert);


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Du bist registriert");
                alert.setHeaderText(null);
                alert.setContentText("Registrierung erfolgreich. Du kannst dich jetzt einloggen.");
                alert.showAndWait();

                LOGGER.info("User created: " + username + " " + password + " " + vorname + " " + nachname);


            }


            con.close();

            FxmlGuiDriver.setScene("/fxml/loginScene.fxml");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        //Username & pw found

        return true;
    }
}
