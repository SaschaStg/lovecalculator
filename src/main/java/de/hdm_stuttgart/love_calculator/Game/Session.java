package de.hdm_stuttgart.love_calculator.Game;

import de.hdm_stuttgart.love_calculator.Gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.Gui.GuiFactory.QuestionsFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private final boolean CLASSICMODE;
    private final List<List<String>> ANSWERSUSER1 = new ArrayList<>();
    private final List<List<String>> ANSWERSUSER2 = new ArrayList<>();

    private boolean isUser1;
    private static int index;

    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);

    //toggles user1 and classic mode
    public Session(boolean user1Starts, boolean CLASSICMODE) {
        isUser1 = user1Starts;
        this.CLASSICMODE = CLASSICMODE;

        //creates a list inside of a list (the (multiple) answer options, for every question, are
        //stored in an arraylists "answersToQuestion1") and this arraylist is stored in "everyAnswerFromUser1"
        //creates as much empty array lists as there are questions
        for (int i = 0; i < Catalog.INSTANCE.getQuestionsCount(); i++) {
            ANSWERSUSER1.add(new ArrayList<>());
            ANSWERSUSER2.add(new ArrayList<>());
        }
    }

    //overloads constructor and sets user1 always true
    public Session(boolean classic) {
        this(true, classic);
    }

    //is classic mode active? return state
    public boolean isCLASSICMODE() {
        return CLASSICMODE;
    }

    public List<String> getUserAnswer(boolean user1, int questionIndex) {
        if (user1) {
            return ANSWERSUSER1.get(questionIndex);
        }
        return ANSWERSUSER2.get(questionIndex);
    }

    public boolean hasAnswers() {

        if (ANSWERSUSER1 == null) {
            return false;
        } else if (ANSWERSUSER2.get(index) == null) {
            return false;
        }

        if (isUser1) {
            return ANSWERSUSER1.get(index).size() > 0;
        } else {
            return ANSWERSUSER2.get(index).size() > 0;
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


    public void backToMenu() {
        index = 0;
        ANSWERSUSER1.clear();
        ANSWERSUSER2.clear();
        isUser1 = false;
        FxmlGuiDriver.setScene("/fxml/startScene.fxml");
        LOGGER.info("User canceled game, going back to startScene");
    }

    //handles whether a next question should be displayed or if it's the end of the game
    private boolean nextQuestion() {

        //question index is incremented by one
        index++;
        //first expression handles end of classic mode, second handles end of advanced mode (index is smaller than QuestionCount until end of questions is reached)
        //question 1 -> index 0
        //question 2 -> index 1 -> stop here for classic question results
        boolean result = (CLASSICMODE && index < 2) || (!CLASSICMODE && index < Catalog.INSTANCE.getQuestionsCount());

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

        if (ANSWERSUSER1.size() == 0 || ANSWERSUSER2.size() == 0) {

            List<String> firstInput = new ArrayList<>();
            ANSWERSUSER1.add(firstInput);
        }
        if (isUser1) {
            ANSWERSUSER1.get(index).add(answer);
        } else {
            ANSWERSUSER2.get(index).add(answer);
        }
    }

    //in case user un ticks answer option
    public void removeAnswer(String answer) {
        if (isUser1) {
            ANSWERSUSER1.get(index).remove(answer);
        } else {
            ANSWERSUSER2.get(index).remove(answer);
        }
    }

    //clears answers
    public void clearAnswers() {
        if (isUser1) {
            ANSWERSUSER1.get(index).clear();
        } else {
            ANSWERSUSER2.get(index).clear();
        }
    }


    public int getSizeOfAnswers() {
        if (isUser1) {
            return ANSWERSUSER1.get(index).size();
        } else {
            return ANSWERSUSER2.get(index).size();
        }
    }


    public int getCurrentIndex() {
        return index;
    }
}
