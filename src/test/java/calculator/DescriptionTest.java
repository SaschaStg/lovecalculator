package calculator;

import de.hdm_stuttgart.love_calculator.Calculator.Description;
import de.hdm_stuttgart.love_calculator.Game.Session;
import org.junit.Assert;
import org.junit.Test;

public class DescriptionTest {
    Session session;

    @Test
    public void test_checkDescription() {

        Assert.assertEquals("Hm, ... irgendwie nichts Ganzes und nichts Halbes.", Description.generateDescription( 50));
        Assert.assertEquals("Schon mal überlegt enthalten zu leben?", Description.generateDescription(0));
        Assert.assertEquals("Glaubst du an die große Liebe? Dann hast du sie gefunden – Glückwunsch!", Description.generateDescription(100));
        Assert.assertNotEquals("Hm, ... irgendwie nichts Ganzes und nichts Halbes.", Description.generateDescription(30));
        Assert.assertNotEquals("Schon mal überlegt enthalten zu leben?", Description.generateDescription(20));
        Assert.assertNotEquals("Glaubst du an die große Liebe? Dann hast du sie gefunden – Glückwunsch!", Description.generateDescription(88));
    }
}
