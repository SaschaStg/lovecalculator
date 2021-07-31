package de.hdm_stuttgart.love_calculator.Calculator;

import de.hdm_stuttgart.love_calculator.Game.Answers;
import de.hdm_stuttgart.love_calculator.Game.Catalog;
import de.hdm_stuttgart.love_calculator.Game.Question;
import de.hdm_stuttgart.love_calculator.Game.Session;

/**
 * Class providing the calculation logic with thread support
 */
public class Calculator extends Thread {
    /**
     * Carries the calculation result
     */
    public int calculationResult;
    /**
     * Provides the session
     */
    Session session;
    /**
     * The current question index
     */
    int questionIndex;

    /**
     * Constructor of an Calculator object
     *
     * @param session       the current session
     * @param questionIndex the current question index
     */
    public Calculator(Session session, int questionIndex) {
        this.session = session;
        this.questionIndex = questionIndex;

    }

    /**
     * searches the index of an answer based on the following parameter
     *
     * @param session       current session
     * @param questionIndex current question index
     * @param isUser1       boolean which shows if user one is active or not
     * @return answer index as an integer
     */
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

    /**
     * Calculates the compatibility of two players, based on their input
     */
    public void run() {


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

        double divideByAnswerCount = (double) 100 / answers.getAnswersCount();

        sum = sum * divideByAnswerCount;
        calculationResult = 100 - (int) sum;
        System.out.println(calculationResult);

    }

}
