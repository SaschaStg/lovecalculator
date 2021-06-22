package de.hdm_stuttgart.love_calculator.game;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private final boolean classicMode;
    private final List<List<String>> answersUser1 = new ArrayList<>();
    private final List<List<String>> answersUser2 = new ArrayList<>();

    private boolean isUser1;
    private static int index;

    public Session(boolean classic) {
        this(true, classic);
    }

    public Session(boolean user1Starts, boolean classicMode) {
        isUser1 = user1Starts;
        this.classicMode = classicMode;

        for (int i = 0; i < Catalog.INSTANCE.getQuestionsCount(); i++) {
            answersUser1.add(new ArrayList<>());
            answersUser2.add(new ArrayList<>());
        }
    }

    public boolean isClassicMode() {
        return classicMode;
    }

    public boolean hasAnswers() {
        if (isUser1) {
            return answersUser1.get(index).size() > 0;
        } else {
            return answersUser2.get(index).size() > 0;
        }
    }

    public boolean isUser1Turn() {
        return isUser1;
    }

    public int getCurrentQuestionIndex() {
        return index;
    }

    /**
     * @return if game should end / results should be displayed (true if game should continue)
     */
    public boolean nextTurn() {
        nextUser();

        if (isUser1) return nextQuestion();

        return true;
    }

    private boolean nextQuestion() {
        index++;

        boolean result = (classicMode && index >= 2) || index < Catalog.INSTANCE.getQuestionsCount();
        if (!result) index = 0;

        return result;
    }

    private void nextUser() {
        isUser1 = !isUser1;
    }

    public void addAnswer(String answer) {
        if (isUser1) {
            answersUser1.get(index).add(answer);
        } else {
            answersUser2.get(index).add(answer);
        }
    }

    public void removeAnswer(String answer) {
        if (isUser1) {
            answersUser1.get(index).remove(answer);
        } else {
            answersUser2.get(index).remove(answer);
        }
    }

    public void clearAnswers() {
        if (isUser1) {
            answersUser1.get(index).clear();
        } else {
            answersUser2.get(index).clear();
        }
    }
}
