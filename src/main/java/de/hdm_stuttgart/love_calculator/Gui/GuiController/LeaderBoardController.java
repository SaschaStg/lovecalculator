package de.hdm_stuttgart.love_calculator.Gui.GuiController;

import de.hdm_stuttgart.love_calculator.Gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.Gui.GuiFactory.LoginFactory;
import de.hdm_stuttgart.love_calculator.Gui.GuiFactory.QuestionsFactory;
import de.hdm_stuttgart.love_calculator.Gui.Navigatable;
import de.hdm_stuttgart.love_calculator.Sql.SqlParameter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

/**
 * Handles the leaderBoardScene.fxml
 */
public class LeaderBoardController implements Navigatable {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);
    @FXML
    private Label username1Label = new Label();
    @FXML
    private Label username2Label = new Label();
    @FXML
    private Label username3Label = new Label();
    @FXML
    private Label username4Label = new Label();
    @FXML
    private Label username5Label = new Label();
    @FXML
    private Label gamecount1Label = new Label();
    @FXML
    private Label gamecount2Label = new Label();
    @FXML
    private Label gamecount3Label = new Label();
    @FXML
    private Label gamecount4Label = new Label();
    @FXML
    private Label gamecount5Label = new Label();

    /**
     * Fires when the scene is opened. Loads the top5 players username and gamecount from the database
     * and writes it in the labels in the leaderBoardScene.fxml
     *
     * @param argument
     */
    @Override
    public void onShow(Object argument) {

        try {

            Connection con = DriverManager.getConnection(SqlParameter.URL, SqlParameter.USER, SqlParameter.PASSW);
            Statement stm = con.createStatement();

            ResultSet top5 = stm.executeQuery("SELECT gamecount, username FROM userdata;");
            int limit5 = 0;
            Map<Integer, String> top5Map = new HashMap<>();
            LinkedHashMap<Integer, String> top5MapSorted = new LinkedHashMap<>();
            while (top5.next() && limit5 < 5) {

                top5Map.put(top5.getInt(1), top5.getString(2));
                limit5++;

            }
            //sorting top 5 users
            top5Map.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .forEachOrdered(x -> top5MapSorted.put(x.getKey(), x.getValue()));

            LOGGER.debug("Sorted top 5 list");

            //Getting Set of keys from HashMap
            Set<Integer> keySet = top5MapSorted.keySet();
            //Creating an ArrayList of keys by passing the keySet
            ArrayList<Integer> top5UserValues = new ArrayList<>(keySet);
            Collection<String> values = top5MapSorted.values();
            ArrayList<String> top5Users = new ArrayList<>(values);


            gamecount1Label.setText(String.valueOf(top5UserValues.get(0)));
            username1Label.setText(top5Users.get(0));

            gamecount2Label.setText(String.valueOf(top5UserValues.get(1)));
            username2Label.setText(top5Users.get(1));

            gamecount3Label.setText(String.valueOf(top5UserValues.get(2)));
            username3Label.setText(top5Users.get(2));

            gamecount4Label.setText(String.valueOf(top5UserValues.get(3)));
            username4Label.setText(top5Users.get(3));

            gamecount5Label.setText(String.valueOf(top5UserValues.get(4)));
            username5Label.setText(top5Users.get(4));

            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Switches the scene to the loggedInScene.fxml if the player is logged in,
     * otherwise it switches to the profileScene.fxml
     */
    @FXML
    public void profileScene() {
        if (LoginFactory.getLoggedInUser() != null) {
            FxmlGuiDriver.setScene("/fxml/loggedInScene.fxml");
        } else {
            FxmlGuiDriver.setScene("/fxml/profileScene.fxml");
        }
    }

    /**
     * Switches the scene to the playScene.fxml
     */
    @FXML
    public void playScene() {
        FxmlGuiDriver.setScene("/fxml/playScene.fxml");
    }

    /**
     * Switches the scene to the startScene.fxml
     */
    @FXML
    public void startScene() {
        FxmlGuiDriver.setScene("/fxml/startScene.fxml");
    }

}
