package de.hdm_stuttgart.love_calculator.gui.GuiController;

import de.hdm_stuttgart.love_calculator.game.Session;
import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.util.Optional;

public class ClassicMode extends Scene {
    //Button
    private Button next;
    private Button back;
    //empty session object
    private final Session session;
    private final StackPane mainPane;

    public ClassicMode() {
        super(new Group());
        Group parent = (Group)getRoot();
        session = new Session(true);

        mainPane = new StackPane();
        mainPane.setMinHeight(670);
        mainPane.setMinWidth(1065);
        //[parent[ mainPane[ Buttons... ] ] ]
        parent.getChildren().add(mainPane);

            back = new Button();
            back.getStyleClass().add("nextButton");
            back.setText("Zurück");
            back.setTranslateY(300);
            back.setTranslateX(-400);

            next = new Button();
            next.getStyleClass().add("nextButton");
            next.setText("Weiter");
            next.setTranslateY(300);
            next.setTranslateX(400);
            next.setOnAction(e -> {
                //
                Optional<Boolean> result = QuestionsFactory.tryAdvanceTurn(session, mainPane);
                if (result.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Fehler!");
                    alert.setHeaderText(null);
                    alert.setContentText("Bitte wähle eine Antwort aus!");
                    alert.showAndWait();
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
        FxmlGuiDriver.setScene("/fxml/resultsPageClassic.fxml", session);
    }
}