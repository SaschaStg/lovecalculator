package game;

import de.hdm_stuttgart.love_calculator.game.Answers;
import de.hdm_stuttgart.love_calculator.game.Catalog;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.util.Arrays;

public class AnswersTest {



    @Test
    public void test_getAnswer(){



        String[] test_moreAnswers = new String[2];
        test_moreAnswers[0] = "Mobile Medien";
        test_moreAnswers[1] = "AM";
        Answers test_Answer = new Answers("RadioButton", "Medieninformatik", test_moreAnswers);


        Assert.assertEquals("Mobile Medien", test_Answer.getAnswer(1));
        Assert.assertEquals("Medieninformatik", test_Answer.getAnswer(0));
        Assert.assertEquals("AM", test_Answer.getAnswer(2));

        Assert.assertNotEquals("Am", test_Answer.getAnswer(1));
        Assert.assertNotEquals("", test_Answer.getAnswer(1));
        Assert.assertNotEquals("Jura", test_Answer.getAnswer(1));


    }

    @Test
    public void test_getAnswerCount(){

        String[] test_moreAnswers = new String[2];
        test_moreAnswers[0] = "Mobile Medien";
        test_moreAnswers[1] = "AM";
        Answers test_Answer = new Answers("RadioButton", "Medieninformatik", test_moreAnswers);

        Assert.assertEquals(3, test_Answer.getAnswersCount());
        Assert.assertNotEquals(66, test_Answer.getAnswersCount());

    }



}
