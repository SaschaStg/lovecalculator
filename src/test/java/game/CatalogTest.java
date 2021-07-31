package game;

import de.hdm_stuttgart.love_calculator.Exception.InvalidCsvFileSize;
import de.hdm_stuttgart.love_calculator.Game.Catalog;
import org.junit.Assert;
import org.junit.Test;


public class CatalogTest {

    @Test
    public void test_getAnswerQuestionCount() {

        try {
            Catalog.INSTANCE.initialize();
        } catch (InvalidCsvFileSize invalidCsvFileSize) {
            invalidCsvFileSize.printStackTrace();
        }
        Assert.assertEquals(6, Catalog.INSTANCE.getQuestionsCount());

    }


}
