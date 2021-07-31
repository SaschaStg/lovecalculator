package de.hdm_stuttgart.love_calculator.Gui.GameModes;

import de.hdm_stuttgart.love_calculator.Game.Session;
import de.hdm_stuttgart.love_calculator.Gui.AlertDialogue;
import de.hdm_stuttgart.love_calculator.Gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.Gui.GuiFactory.QuestionsFactory;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.util.Optional;

public class AdvancedMode extends Scene {

    //Button
    private final Button NEXT;
    private final Button BACK;
    //empty SESSION object
    private final Session SESSION;
    //sets order on buttons for example
    private final StackPane MAINPANE;

    /**
     * Starts AdvanceMode, sets the pane properties and generates all buttons and starts a new session for the game.
     */
    public AdvancedMode() {

        //Super Group
        super(new Group());
        Group parent = (Group) getRoot();

        //overloaded constructor with classic mode = true and user1 = true
        SESSION = new Session(false);

        MAINPANE = new StackPane();
        MAINPANE.setMinHeight(670);
        MAINPANE.setMinWidth(1065);
        //[parent[ MAINPANE[ Buttons... ] ] ]
        parent.getChildren().add(MAINPANE);

        BACK = new Button();
        BACK.getStyleClass().add("btn-small");
        BACK.setText("Zurück zum Menü");
        BACK.setTranslateY(270);
        BACK.setTranslateX(-380);

        BACK.setOnAction(e -> SESSION.backToMenu());

        NEXT = new Button();
        NEXT.getStyleClass().add("btn-small");
        NEXT.setText("Weiter");
        NEXT.setTranslateY(270);
        NEXT.setTranslateX(440);

        NEXT.setOnAction(e -> {
            Optional<Boolean> result = QuestionsFactory.tryAdvanceTurn(SESSION, MAINPANE);
            if (result.isEmpty()) {
                AlertDialogue.showInfoAlert("Fehler!", "Bitte gebe eine Antwort ein.");
            } else if (result.get()) {
                setupPane();
            } else {
                showResults();
            }
        });

        setupPane();
    }

    /**
     * Clears the pane and generates a new one with the new questions and answers
     */
    private void setupPane() {
        MAINPANE.getChildren().clear();

        QuestionsFactory.generateQuestionPane(SESSION, MAINPANE);

        MAINPANE.getChildren().addAll(NEXT, BACK);
    }

    /**
     * Shows the resultPageAdvanced.fxml
     */
    private void showResults() {
        FxmlGuiDriver.setScene("/fxml/resultsPageAdvanced.fxml", SESSION);
    }
}
