package de.hdm_stuttgart.love_calculator.Sql;

import de.hdm_stuttgart.love_calculator.Game.Session;
import de.hdm_stuttgart.love_calculator.Gui.GuiFactory.LoginFactory;
import de.hdm_stuttgart.love_calculator.Gui.GuiFactory.QuestionsFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ResultUpdater {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);

    public static void updateResult(Session session, int highestMatchParameter) {

        if (LoginFactory.getLoggedInUser() != null) {


            try {
                // Verbindung aufbauen
                Connection con = DriverManager.getConnection(SqlParameter.URL, SqlParameter.USER, SqlParameter.PASSW);
                System.out.println("Verbindung erfolgreich hergestellt");


                String searchInDB = "UPDATE userdata SET gamecount = gamecount + 1 WHERE username = ?";

                PreparedStatement prepareInsertStatement =
                        con.prepareStatement(searchInDB);

                prepareInsertStatement.setString(1, LoginFactory.getLoggedInUser());

                int rs_insert = prepareInsertStatement.executeUpdate();

                LOGGER.info("User " + LoginFactory.getLoggedInUser() + " is logged in! Incremented gamecount by 1.");


                String checkPercentage = "SELECT * FROM userdata WHERE username = ?";
                PreparedStatement prepareCheckStatement = con.prepareStatement(checkPercentage);

                prepareCheckStatement.setString(1, LoginFactory.getLoggedInUser());

                ResultSet percentageResult = prepareCheckStatement.executeQuery();

                if (percentageResult.next()) {

                    int highestMatch = percentageResult.getInt(6);


                    if (highestMatchParameter > highestMatch) {
                        LOGGER.info("User " + LoginFactory.getLoggedInUser() + " got a new highest match: " + highestMatchParameter);

                        String setHighestmatch = "UPDATE userdata SET highestmatch = ?, highestmatchNumber = ? WHERE username = ?";

                        PreparedStatement prepareUpdateStatement =
                                con.prepareStatement(setHighestmatch);

                        prepareUpdateStatement.setString(1, session.getUserAnswer(false, 0).get(0));
                        prepareUpdateStatement.setInt(2, highestMatchParameter);
                        prepareUpdateStatement.setString(3, LoginFactory.getLoggedInUser());


                        int rs_update = prepareUpdateStatement.executeUpdate();
                    }

                }


                con.close();


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

    }
}
