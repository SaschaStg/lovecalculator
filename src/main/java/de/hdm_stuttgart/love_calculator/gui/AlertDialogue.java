package de.hdm_stuttgart.love_calculator.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;

public class AlertDialogue {

    public static void showInfoAlert(String title, String description) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(description);
        alert.showAndWait();
    }
}
