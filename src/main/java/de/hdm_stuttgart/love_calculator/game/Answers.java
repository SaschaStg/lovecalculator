package de.hdm_stuttgart.love_calculator.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Answers {
    // Input-Type: checkbox / radiobutton / textfield / slider
    public final String inputType;
    private final List<String> answerOptions = new ArrayList<>();

    public Answers(String inputType, final String answer, String[] moreAnswers){
        this.inputType = inputType.trim().toUpperCase();
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
