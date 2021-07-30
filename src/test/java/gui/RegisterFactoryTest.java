package gui;

import de.hdm_stuttgart.love_calculator.gui.GuiController.RegisterFactory;
import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertFalse(RegisterFactory.sqlRegister("TestName123", "S4p3rS1ch3r", "Max", "Mustermann"));
        Assert.assertFalse(RegisterFactory.sqlRegister("sascha1337", "pw123123", "Sascha", "Müller"));

    }
}
