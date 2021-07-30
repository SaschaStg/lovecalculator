package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.game.Session;
import de.hdm_stuttgart.love_calculator.gui.AlertDialogue;
import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.util.Optional;

public class AdvancedMode extends Scene {
    //Button
    private final Button next;
    private final Button back;
    //empty session object
    private final Session session;
    //sets order on buttons for example
    private final StackPane mainPane;

    public AdvancedMode() {

        //Super Group
        super(new Group());
        Group parent = (Group) getRoot();

        //overloaded constructor with classic mode = true and user1 = true
        session = new Session(false);

        mainPane = new StackPane();
        mainPane.setMinHeight(670);
        mainPane.setMinWidth(1065);
        //[parent[ mainPane[ Buttons... ] ] ]
        parent.getChildren().add(mainPane);

        back = new Button();
        back.getStyleClass().add("btn-small");
        back.setText("Zurück zum Menü");
        back.setTranslateY(270);
        back.setTranslateX(-380);

        back.setOnAction(e -> session.backToMenu());

        next = new Button();
        next.getStyleClass().add("btn-small");
        next.setText("Weiter");
        next.setTranslateY(270);
        next.setTranslateX(440);

        next.setOnAction(e -> {
            Optional<Boolean> result = QuestionsFactory.tryAdvanceTurn(session, mainPane);
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

    private void setupPane() {
        mainPane.getChildren().clear();

        QuestionsFactory.generateQuestionPane(session, mainPane);

        mainPane.getChildren().addAll(next, back);
    }


    private void showResults() {
        FxmlGuiDriver.setScene("/fxml/resultsPageAdvanced.fxml", session);
    }
}
