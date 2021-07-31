package de.hdm_stuttgart.love_calculator.Gui;

import de.hdm_stuttgart.love_calculator.Game.Catalog;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Objects;

/**
 * Driver class for a simple JavaFX demonstration.
 */
public class FxmlGuiDriver extends Application {
    public static final Logger LOGGER = LogManager.getLogger(FxmlGuiDriver.class);
    private static Stage window;

    /**
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    public void start(Stage primaryStage) throws Exception {
        //Read csv's from questions and answers
        Catalog.INSTANCE.initialize();

        window = primaryStage;
        window.setResizable(false);

        final Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/startScene.fxml")));
        LOGGER.info("Showing JFX scene");
        final Scene scene = new Scene(root, 1065, 670);
        scene.getStylesheets().add(Objects.requireNonNull(FxmlGuiDriver.class.getResource("/styles/styles.css")).toExternalForm());
        primaryStage.setTitle("LoveCalculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void setTitle(String title) {
        window.setTitle(title);
    }

    public static void setScene(String fxmlPath, Object argument) {
        try {
            FXMLLoader loader = new FXMLLoader(FxmlGuiDriver.class.getResource(fxmlPath));
            Parent p = loader.load();
            Object controller = loader.getController();
            if (controller instanceof Navigatable) {
                ((Navigatable) controller).onShow(argument);
            }
            setScene(new Scene(p, 1065, 670));
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static void setScene(String fxmlPath) {
        setScene(fxmlPath, null);
    }

    public static void setScene(Scene scene) {
        window.setScene(scene);
    }


}
