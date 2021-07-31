package de.hdm_stuttgart.love_calculator.Gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;

/**
 * AlertDialogue popup for user notification
 */
public class AlertDialogue {

    /**
     * Creates a new Info DialogAlert for easier use of the Alert function
     *
     * @param title       the title of the Alert
     * @param description the description of the Alert
     */
    public static void showInfoAlert(String title, String description) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(description);
        alert.showAndWait();
    }
}
