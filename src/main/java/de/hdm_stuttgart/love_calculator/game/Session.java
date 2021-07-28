package de.hdm_stuttgart.love_calculator.game;

import de.hdm_stuttgart.love_calculator.gui.FxmlGuiDriver;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private final boolean classicMode;
    private final List<List<String>> answersUser1 = new ArrayList<>();
    private final List<List<String>> answersUser2 = new ArrayList<>();

    private boolean isUser1;
    private static int index;

    //toggles user1 and classic mode
    public Session(boolean user1Starts, boolean classicMode) {
        isUser1 = user1Starts;
        this.classicMode = classicMode;

        //creates a list inside of a list (the (multiple) answer options, for every question, are
        //stored in an arraylists "answersToQuestion1") and this arraylist is stored in "everyAnswerFromUser1"
        //creates as much empty array lists as there are questions
        for (int i = 0; i < Catalog.INSTANCE.getQuestionsCount(); i++) {
            answersUser1.add(new ArrayList<>());
            answersUser2.add(new ArrayList<>());
        }
    }

    //overloads constructor and sets user1 always true
    public Session(boolean classic) {
        this(true, classic);
    }

    //is classic mode active? return state
    public boolean isClassicMode() {
        return classicMode;
    }

    public List<String> getUserAnswer(boolean user1, int questionIndex) {
        if (user1) {
            return answersUser1.get(questionIndex);
        }
        return answersUser2.get(questionIndex);
    }

    public boolean hasAnswers() {
        if (isUser1) {
            return answersUser1.get(index).size() > 0;
        } else {
            return answersUser2.get(index).size() > 0;
        }
    }

    //returns whether its user 1s turn
    public boolean isUser1Turn() {
        return isUser1;
    }

    public int getCurrentQuestionIndex() {
        return index;
    }

    /**
     * it's the "flamme" (user2) turn, same question for "flamme" is about to be displayed
     * otherwise next question gets displayed (a whole new question)
     *
     * @return if game should end / results should be displayed (true if game should continue)
     */
    public boolean nextTurn() {
        //switches users
        nextUser();

        //if it's user1's turn, a whole new question is about to displayed
        if (isUser1) {
            return nextQuestion();
        }

        return true;
    }

    public boolean backQuestion() {
        //When its not the first question and user1
        if (index != 0 && isUser1Turn()) {
            if (answersUser1.get(index) != null) {
                System.out.println(answersUser1.get(index));
                answersUser1.remove(index);
                System.out.println(answersUser1.get(index));
            }
            index--;
            isUser1 = false;
            return true;
        }
        //When its the first question and user2
        else if (index == 0 && !isUser1Turn()) {
            if (answersUser1.get(index) != null) {
                System.out.println(answersUser2.get(index));
                answersUser2.remove(index);
                System.out.println(answersUser2.get(index));
            }
            isUser1 = true;
            return true;
        }
        //When its not the first question and user2
        else if (index != 0 && !isUser1Turn()) {
            if (answersUser1.get(index) != null) {
                System.out.println(answersUser2.get(index));
                answersUser2.remove(index);
                System.out.println(answersUser2.get(index));
            }
            index--;
            isUser1 = true;
            return true;
        }
        //When its the first question and user1
        answersUser1.remove(index);
        FxmlGuiDriver.setScene("/fxml/startScene.fxml");
        return false;
    }

    //handles whether a next question should be displayed or if it's the end of the game
    private boolean nextQuestion() {

        //question index is incremented by one
        index++;
        //first expression handles end of classic mode, second handles end of advanced mode (index is smaller than QuestionCount until end of questions is reached)
        //question 1 -> index 0
        //question 2 -> index 1 -> stop here for classic question results
        boolean result = (classicMode && index < 2) || (!classicMode && index < Catalog.INSTANCE.getQuestionsCount());

        //in case user wants to play another game, index is set to zero
        if (!result) {
            index = 0;
        }

        return result;
    }

    //switches users
    private void nextUser() {
        isUser1 = !isUser1;
    }

    //fills the empty arraylist, created in Session constructor
    public void addAnswer(String answer) {
        if (isUser1) {
            answersUser1.get(index).add(answer);
        } else {
            answersUser2.get(index).add(answer);
        }
    }

    //in case user un ticks answer option
    public void removeAnswer(String answer) {
        if (isUser1) {
            answersUser1.get(index).remove(answer);
        } else {
            answersUser2.get(index).remove(answer);
        }
    }

    //clears answers
    public void clearAnswers() {
        if (isUser1) {
            answersUser1.get(index).clear();
        } else {
            answersUser2.get(index).clear();
        }
    }


    public int getCurrentIndex() {
        return this.index;
    }





}
