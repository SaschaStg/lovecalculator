package de.hdm_stuttgart.love_calculator.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provides the answer objects
 * Construct of an answer object
 */
public class Answers {

    /**
     * Stores the input type of an answer object
     * radiobutton, checkbox, textfield
     */
    public final String INPUTTYPE;

    /**
     * Stores the multiple answer options
     */
    private final List<String> answerOptions = new ArrayList<>();

    /**
     * Constructor of an answer object
     *
     * @param inputType   the input type as a string
     * @param answer      the first answer as a string
     * @param moreAnswers if a question has multiple answer options, they are stored in this string array
     */
    public Answers(String inputType, final String answer, String[] moreAnswers) {
        this.INPUTTYPE = inputType.trim().toUpperCase();
        this.answerOptions.add(answer);
        if (moreAnswers.length > 0)
            this.answerOptions.addAll(Arrays.asList(moreAnswers));
    }


    /**
     * Informs you about the number of answers
     *
     * @return the size of list answerOptions
     */
    public int getAnswersCount() {
        return answerOptions.size();
    }

    /**
     * Provides the "raw" answer as a string, based on an index
     *
     * @param answerIndex index of the required answer
     * @return needed answer as a string
     */
    public String getAnswer(int answerIndex) {
        return answerOptions.get(answerIndex);
    }
}
