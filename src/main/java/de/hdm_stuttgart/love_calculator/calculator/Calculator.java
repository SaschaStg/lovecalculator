package de.hdm_stuttgart.love_calculator.calculator;

import de.hdm_stuttgart.love_calculator.game.Answers;
import de.hdm_stuttgart.love_calculator.game.Catalog;
import de.hdm_stuttgart.love_calculator.game.Question;
import de.hdm_stuttgart.love_calculator.game.Session;

public class Calculator extends Thread{

    Session session;
    int questionIndex;

    public int calculationResult;


    public Calculator(Session session, int questionIndex){
        this.session = session;
        this.questionIndex = questionIndex;

    }

    public void run(){


        System.out.println("Started Thread");

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
        calculationResult = 100 - (int)sum;
        System.out.println(calculationResult);

    }


        public static int findIndexOfAnswer(Session session, int questionIndex, boolean isUser1) {

        Question question = Catalog.INSTANCE.getQuestion(questionIndex);
        Answers answers = Catalog.INSTANCE.getAnswers(question);


        for (int i = 0; i < answers.getAnswersCount(); i++) {

            if (session.getUserAnswer(isUser1, questionIndex).get(0).equals(answers.getAnswer(i))) {
                return i;
            }
        }
        return 0;
    }

}
