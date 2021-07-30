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
        Assert.assertEquals(true, LoginFactory.sqlLogin("ExistiertNicht", "123"));
        Assert.assertEquals(true, LoginFactory.sqlLogin("sascha1337", "sascha"));
    }

    }
