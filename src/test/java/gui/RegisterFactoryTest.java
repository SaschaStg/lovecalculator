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

        Assert.assertTrue(RegisterFactory.validRegister("TestName", "S4p3rS1ch3r", "Max", "Mustermann"));

        Assert.assertFalse(RegisterFactory.validRegister("Test", "", "", ""));
        Assert.assertFalse(RegisterFactory.validRegister("a", "1234", "", ""));
        Assert.assertFalse(RegisterFactory.validRegister("a", "", "Sascha", ""));
        Assert.assertFalse(RegisterFactory.validRegister("a", "", "", "Müller"));

    }

    @Test
    public void test_sqlRegister() {
        Assert.assertEquals(true, RegisterFactory.validRegister("ExistiertBereits", "S4p3rS1ch3r", "Max", "Mustermann"));
    }
}
