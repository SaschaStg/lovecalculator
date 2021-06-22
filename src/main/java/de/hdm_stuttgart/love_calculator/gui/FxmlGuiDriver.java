package de.hdm_stuttgart.love_calculator.gui;

import de.hdm_stuttgart.love_calculator.game.Catalog;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Driver class for a simple JavaFX demonstration.
 *
 */
public class FxmlGuiDriver extends Application {
	public static final Logger log = LogManager.getLogger(FxmlGuiDriver.class);
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

        //Scanner input = new Scanner(new File("src/main/resources/fxml/mainMenu.fxml"));
        log.info("Starting Hello JavaFX and Maven demonstration application");
        //final String fxmlFile = "src/main/resources/fxml/mainMenu.fxml";
        final FXMLLoader loader = new FXMLLoader();
        final Parent root = FXMLLoader.load(getClass().getResource("/fxml/startScene.fxml"));
        //final Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        log.debug("Showing JFX scene");
        final Scene scene = new Scene(root, 1065,670);
        scene.getStylesheets().add(FxmlGuiDriver.class.getResource("/styles/styles.css").toExternalForm());
        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void setTitle(String title) {
        window.setTitle(title);
    }

    public static void setScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(FxmlGuiDriver.class.getResource(fxmlPath));
            setScene(new Scene(loader.load(), 1065, 670));
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public static void setScene(Scene scene) {
        window.setScene(scene);
    }

}
