package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.game.Session;
import de.hdm_stuttgart.love_calculator.gui.Navigatable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.Result;
import java.sql.*;



public class LoginFactory {

    //create logger for every class
    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);

    public static boolean checkLogin(TextField usernameTextField, TextField passwordTextField) {

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if(username.isEmpty() || password.isEmpty()) {
            LOGGER.error("No Username and/or password found.");

        return false;
    }
        LOGGER.info("Login input found");



        // Datenbankadresse und Anmeldedaten
        String url = "jdbc:mysql://s230.goserver.host:3306/web21_db5";
        String user = "web21_5";
        String pass = "MLQCZRdX8DUIsxEk";



        try {
            // Verbindung aufbauen
            Connection con = DriverManager.getConnection(url, user, pass);
            System.out.println("Verbindung erfolgreich hergestellt");



            String searchInDB = "SELECT * FROM userdata WHERE username = ? and password = ?;";

            PreparedStatement preparedStatement =
                    con.prepareStatement(searchInDB);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();



            if(rs.next()){
                System.out.println("Username: " + rs.getString(2) + "Password: " + rs.getString(3));
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Benutzername oder Passwort falsch");
                alert.setHeaderText(null);
                alert.setContentText("Bist du schon registriert?");
                alert.showAndWait();
            }


            con.close();



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }





        //Username & pw found


        return true;
    }














}