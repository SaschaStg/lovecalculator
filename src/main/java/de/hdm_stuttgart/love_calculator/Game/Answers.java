package de.hdm_stuttgart.love_calculator.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Provides the answers
 */
public class Answers {
    // Input-Type: checkbox / radiobutton / textfield / slider
    public final String INPUTTYPE;
    /**
     * Stores the answer options
     */
    private final List<String> answerOptions = new ArrayList<>();

    public Answers(String inputType, final String answer, String[] moreAnswers){
        this.INPUTTYPE = inputType.trim().toUpperCase();
        this.answerOptions.add(answer);
        if (moreAnswers.length > 0)
            this.answerOptions.addAll(Arrays.asList(moreAnswers));
    }

    public int getAnswersCount() {
        return answerOptions.size();
    }

    public String getAnswer(int answerIndex) {
        return answerOptions.get(answerIndex);
    }
}
