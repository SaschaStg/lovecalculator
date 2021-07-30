package gui;

import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.gui.GuiController.LoginFactory;
import de.hdm_stuttgart.love_calculator.gui.GuiController.RegisterFactory;
import de.hdm_stuttgart.love_calculator.sql.SqlParameter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.junit.Assert;
import org.junit.Test;
import javafx.fxml.FXML;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class RegisterFactoryTest {

    @Test
    public void test_validRegister() {

        Assert.assertEquals(true, RegisterFactory.validRegister("TestName", "S4p3rS1ch3r", "Max", "Mustermann"));

        Assert.assertEquals(false, RegisterFactory.validRegister("Test", "", "", ""));
        Assert.assertEquals(false, RegisterFactory.validRegister("a", "1234", "", ""));
        Assert.assertEquals(false, RegisterFactory.validRegister("a", "", "Sascha", ""));
        Assert.assertEquals(false, RegisterFactory.validRegister("a", "", "", "Müller"));

    }

    @Test
    public void test_sqlRegister() {

        Assert.assertEquals(true, RegisterFactory.validRegister("ExistiertBereits", "S4p3rS1ch3r", "Max", "Mustermann"));

        try {
            // Verbindung aufbauen
            Connection con = DriverManager.getConnection(SqlParameter.URL, SqlParameter.USER, SqlParameter.PASSW);

            String searchInDB = "DELETE FROM userdata WHERE username = ?";

            PreparedStatement preparedStatement =
                    con.prepareStatement(searchInDB);

            preparedStatement.setString(1, "meme");

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {

            }
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
