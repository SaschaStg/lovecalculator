package gui;

import de.hdm_stuttgart.love_calculator.Gui.GuiFactory.LoginFactory;
import org.junit.Assert;
import org.junit.Test;

public class LoginFactoryTest {

    @Test
    public void test_getLoggedInUser() {

        Assert.assertNull(LoginFactory.getLoggedInUser());
        LoginFactory.setLoggedInUser("TestUser");
        Assert.assertEquals("TestUser", LoginFactory.getLoggedInUser());
    }

    @Test
    public void test_sqlLogin() {
        Assert.assertFalse(LoginFactory.sqlLogin("sascha1337", "FalschesPasswort"));
        Assert.assertTrue(LoginFactory.sqlLogin("sascha1337", "sascha"));
        Assert.assertTrue(LoginFactory.sqlLogin("max", "testpw"));
    }

    @Test
    public void test_checkLoginInput() {
        Assert.assertFalse(LoginFactory.sqlLogin("kriha", ""));
        Assert.assertFalse(LoginFactory.sqlLogin("", ""));
        Assert.assertFalse(LoginFactory.sqlLogin("", "kr1h4_1234"));
        Assert.assertTrue(LoginFactory.sqlLogin("test123", "test4567"));
    }

    }
