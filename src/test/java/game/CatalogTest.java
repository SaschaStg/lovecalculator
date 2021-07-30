package game;

import de.hdm_stuttgart.love_calculator.exception.InvalidCsvFileSize;
import de.hdm_stuttgart.love_calculator.game.Answers;
import de.hdm_stuttgart.love_calculator.game.Catalog;
import de.hdm_stuttgart.love_calculator.game.Question;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.util.Arrays;


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
