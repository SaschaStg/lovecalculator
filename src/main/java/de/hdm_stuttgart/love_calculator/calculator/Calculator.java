package de.hdm_stuttgart.love_calculator.calculator;

import de.hdm_stuttgart.love_calculator.game.Answers;
import de.hdm_stuttgart.love_calculator.game.Catalog;
import de.hdm_stuttgart.love_calculator.game.Question;
import de.hdm_stuttgart.love_calculator.game.Session;

public class Calculator {



    public static int findIndexOfAnswer(Session session, int questionIndex, boolean isUser1) {

        Question question = Catalog.INSTANCE.getQuestion(questionIndex);
        Answers answers = Catalog.INSTANCE.getAnswers(question);


        //System.out.println("Answers count: " + answers.getAnswersCount());
        //System.out.println("Answer 1: " + answers.getAnswer(2));

        for (int i = 0; i < answers.getAnswersCount(); i++) {

            if (session.getUserAnswer(isUser1, questionIndex).get(0).equals(answers.getAnswer(i))) {
                return i;
            }
        }
        return 0;
    }



    public static int calculate(Session session, int questionIndex) {

        int indexUser1 = findIndexOfAnswer(session, questionIndex, true);
        int indexUser2 = findIndexOfAnswer(session, questionIndex, false);
        double sum = 0;

        if (indexUser1 >= indexUser2) {
            sum = indexUser1 - indexUser2;
        } else {
            sum = indexUser2 - indexUser1;
        }

        Question question = Catalog.INSTANCE.getQuestion(questionIndex);
        Answers answers = Catalog.INSTANCE.getAnswers(question);

        double divideByAnswerCount = (double)100 / answers.getAnswersCount();

        sum = sum * divideByAnswerCount;
        return 100 - (int)sum;
    }
}
