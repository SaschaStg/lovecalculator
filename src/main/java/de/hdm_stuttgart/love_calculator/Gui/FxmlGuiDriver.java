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

    /**
     * Logger
     */
    public static final Logger LOGGER = LogManager.getLogger(FxmlGuiDriver.class);

    private static Stage window;


    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Sets the title of the window
     *
     * @param title the title to set for the window
     */
    public static void setTitle(String title) {
        window.setTitle(title);
    }

    /**
     * Switches the scene to a new one
     *
     * @param fxmlPath the path of the fxml file
     * @param argument the session from the game that the user is playing
     */
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

    /**
     * Switches the scene to a new one
     *
     * @param fxmlPath the path of the fxml file
     */
    public static void setScene(String fxmlPath) {
        setScene(fxmlPath, null);
    }

    /**
     * Switches the scene to a new one
     *
     * @param scene the scene that the window should show
     */
    public static void setScene(Scene scene) {
        window.setScene(scene);
    }

    /**
     * Starts the LoveCalculator, creates a new Catalog instance, a new window, a new parent, a new scene and
     * starts it so the user can see the start screen
     *
     * @param primaryStage the stage to use
     * @throws Exception
     */
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


}
