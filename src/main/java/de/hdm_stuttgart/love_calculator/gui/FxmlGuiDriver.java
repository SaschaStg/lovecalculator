package de.hdm_stuttgart.love_calculator.gui;

import de.hdm_stuttgart.love_calculator.catalog.Catalog;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
	public Stage window;

    /**
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        //Read csv's from questions and answers
        Catalog.initQuestions();
        Catalog.initAnswers();

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
        window.setTitle("FXML Welcome");
        window.setScene(scene);
        window.show();
    }


    @FXML
    public static void sceneSwitcher(String path, Button button) {
        try {
            FXMLLoader loader = new FXMLLoader(FxmlGuiDriver.class.getResource(path));
            Stage stage = (Stage) button.getScene().getWindow();
            Scene scene = new Scene(loader.load(), 1065, 670);
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }


}
