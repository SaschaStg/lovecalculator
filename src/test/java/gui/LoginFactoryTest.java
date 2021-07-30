package gui;

import de.hdm_stuttgart.love_calculator.gui.GuiController.LoginFactory;
import org.junit.Assert;
import org.junit.Test;

public class LoginFactoryTest {

    @Test
    public void test_getLoggedInUser() {

        Assert.assertEquals(null, LoginFactory.getLoggedInUser());
        LoginFactory.setLoggedInUser("TestUser");
        Assert.assertEquals("TestUser", LoginFactory.getLoggedInUser());
    }

    @Test
    public void test_sqlLogin() {
        Assert.assertEquals(false, LoginFactory.sqlLogin("sascha1337", "FalschesPasswort"));
        Assert.assertEquals(true, LoginFactory.sqlLogin("sascha1337", "sascha"));
        Assert.assertEquals(true, LoginFactory.sqlLogin("max", "testpw"));
    }

    @Test
    public void test_checkLoginInput() {
        Assert.assertEquals(false, LoginFactory.sqlLogin("kriha", ""));
        Assert.assertEquals(false, LoginFactory.sqlLogin("", ""));
        Assert.assertEquals(false, LoginFactory.sqlLogin("", "kr1h4_1234"));
        Assert.assertEquals(true, LoginFactory.sqlLogin("test123", "test4567"));
        );
    }

    }
