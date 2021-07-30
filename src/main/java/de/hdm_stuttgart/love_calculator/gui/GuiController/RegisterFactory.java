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
import java.util.Arrays;
import java.util.List;

public class RegisterFactory {

    //create logger for every class
    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);


    public static boolean register(TextField usernameTextField, TextField passwordTextField, TextField vornameTextField, TextField nachnameTextField) {

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String vorname = vornameTextField.getText();
        String nachname = nachnameTextField.getText();



        if (username.isEmpty() || password.isEmpty() || vorname.isEmpty() || nachname.isEmpty()) {
            LOGGER.error("Not all fields are filled.");

        if (validRegister(username, password, vorname, nachname)) {

            if(sqlRegister(username, password, vorname, nachname)) {
                AlertDialogue.showInfoAlert("Registrierung erfolgreich!", "Du kannst dich jetzt einloggen!");
            } else {
                AlertDialogue.showInfoAlert("Fehler!", "Dieser Benutzer exisitert bereits!");

            }
        } else {
            AlertDialogue.showInfoAlert("Fehler", "Bitte fülle alle Felder aus!");
        }
    }

        return true;
    }
}
