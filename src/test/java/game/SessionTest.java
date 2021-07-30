package game;

import de.hdm_stuttgart.love_calculator.game.Session;
import org.junit.Assert;
import org.junit.Test;

public class SessionTest {

    Session user1Classic = new Session(true, true);
    Session user2Classic = new Session(false, true);
    Session user1Advanced = new Session(true, false);
    Session user2Advanced = new Session(false, false);


    @Test
    public void test_isClassicMode(){


        Assert.assertEquals(true, user1Classic.isClassicMode());
        Assert.assertEquals(true, user2Classic.isClassicMode());
        Assert.assertEquals(false, user1Advanced.isClassicMode());
        Assert.assertEquals(false, user2Advanced.isClassicMode());

        Assert.assertNotEquals(false, user1Classic.isClassicMode());
        Assert.assertNotEquals(false, user2Classic.isClassicMode());
        Assert.assertNotEquals(true, user1Advanced.isClassicMode());
        Assert.assertNotEquals(true, user2Advanced.isClassicMode());
    }

    @Test
    public void test_hasAnswer(){
        Session user1Classic = new Session(true, true);

        Assert.assertEquals(false, user1Classic.hasAnswers());
    }


   @Test
   public void test_isUser1Turn(){


       Assert.assertEquals(true, user1Classic.isUser1Turn());
       Assert.assertEquals(false, user2Classic.isUser1Turn());
       Assert.assertEquals(true, user1Advanced.isUser1Turn());
       Assert.assertEquals(false, user2Advanced.isUser1Turn());
       Assert.assertNotEquals(false, user1Classic.isUser1Turn());
       Assert.assertNotEquals(true, user2Classic.isUser1Turn());
       Assert.assertNotEquals(false, user1Classic.isUser1Turn());
       Assert.assertNotEquals(true, user2Classic.isUser1Turn());

   }

    @Test
    public void test_nextQuestion(){

        Assert.assertEquals(true, user1Classic.nextTurn());
        Assert.assertEquals(true, user2Classic.nextTurn());
        Assert.assertEquals(true, user1Advanced.nextTurn());
        Assert.assertEquals(false, user2Advanced.nextTurn());


    }

    @Test
    public void test_answerList(){

        //add one answer
        user1Classic.addAnswer("Medieninformatik");

        //check size of the answer list
        int answerSize = user1Classic.getSizeOfAnswers();

        //add another answer
        user1Classic.addAnswer("Mobile Medien");

        //check size again
        int answerSizeOneAnswerAdded = user1Classic.getSizeOfAnswers();

        //test
        Assert.assertEquals(answerSize + 1, answerSizeOneAnswerAdded);
        Assert.assertNotEquals(answerSize, answerSizeOneAnswerAdded);


        user1Classic.removeAnswer("Medieninformatik");
        int answerSizeRemovedAnswer = user1Classic.getSizeOfAnswers();
        Assert.assertEquals(answerSize, answerSizeRemovedAnswer);
        Assert.assertNotEquals(answerSize, answerSizeOneAnswerAdded);


        user1Classic.clearAnswers();
        int answerSizeCleared = user1Classic.getSizeOfAnswers();
        Assert.assertEquals(0, answerSizeCleared);
        Assert.assertNotEquals(5, answerSizeCleared);


    }







}
