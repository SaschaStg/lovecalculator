package calculator;

import de.hdm_stuttgart.love_calculator.calculator.Description;
import de.hdm_stuttgart.love_calculator.game.Session;
import org.junit.Assert;
import org.junit.Test;

public class DescriptionTest {
    Session session;

    @Test
    public void test_checkDescription() {

        Assert.assertEquals("Hm, ... irgendwie nichts Ganzes und nichts Halbes.", Description.generateDescription(session, 50));
        Assert.assertEquals("Schon mal überlegt enthalten zu leben?", Description.generateDescription(session, 0));
        Assert.assertEquals("Glaubst du an die große Liebe? Dann hast du sie gefunden – Glückwunsch!", Description.generateDescription(session, 100));
        Assert.assertNotEquals("Hm, ... irgendwie nichts Ganzes und nichts Halbes.", Description.generateDescription(session, 30));
        Assert.assertNotEquals("Schon mal überlegt enthalten zu leben?", Description.generateDescription(session, 20));
        Assert.assertNotEquals("Glaubst du an die große Liebe? Dann hast du sie gefunden – Glückwunsch!", Description.generateDescription(session, 88));
    }
}
