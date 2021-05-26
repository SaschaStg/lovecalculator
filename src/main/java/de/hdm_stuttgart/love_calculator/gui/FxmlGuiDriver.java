package de.hdm_stuttgart.love_calculator.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Driver class for a simple JavaFX demonstration.
 *
 */
public class FxmlGuiDriver extends Application {


	private static final Logger log = LogManager.getLogger(FxmlGuiDriver.class);

    /**
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        //Scanner input = new Scanner(new File("src/main/resources/fxml/mainMenu.fxml"));


        log.info("Starting Hello JavaFX and Maven demonstration application");
        //final String fxmlFile = "src/main/resources/fxml/mainMenu.fxml";
        final FXMLLoader loader = new FXMLLoader();
        final Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainMenu.fxml"));
        //final Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        log.debug("Showing JFX scene");
        final Scene scene = new Scene(root, 800,500);
        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }





}
