package de.hdm_stuttgart.love_calculator.Game;

import de.hdm_stuttgart.love_calculator.Gui.FxmlGuiDriver;
import de.hdm_stuttgart.love_calculator.Gui.GuiFactory.QuestionsFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides a game session
 */
public class Session {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);
    /**
     * Current (current in a game session) index of questions
     */
    private static int index;
    /**
     * Boolean which stores whether classic mode is active or not
     */
    private final boolean CLASSICMODE;
    /**
     * List of answers, user one gave
     */
    private final List<List<String>> ANSWERSUSER1 = new ArrayList<>();
    /**
     * List of answers, user two gave
     */
    private final List<List<String>> ANSWERSUSER2 = new ArrayList<>();
    /**
     * Boolean which stores whether user one is active or not
     */
    private boolean isUser1;

    /**
     * Constructor of session object
     * Toggles user one/two and classsic/advanced mode
     *
     * @param user1Starts indicates whether user one or two starts
     * @param CLASSICMODE indicates whether classic or advanced mode is selected
     */
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


    /**
     * Overloads constructor and sets user one always true
     *
     * @param classic
     */
    public Session(boolean classic) {
        this(true, classic);
    }

    /**
     * Checks if classic mode is active
     *
     * @return current state whether classic mode is active or not
     */
    public boolean isCLASSICMODE() {
        return CLASSICMODE;
    }

    /**
     * Provides the given answers of a user to a question
     *
     * @param user1         indicates whether user one or two is meant
     * @param questionIndex indicates which question is meant
     * @return given answers of a user as a list
     */
    public List<String> getUserAnswer(boolean user1, int questionIndex) {
        if (user1) {
            return ANSWERSUSER1.get(questionIndex);
        }
        return ANSWERSUSER2.get(questionIndex);
    }

    /**
     * Checks whether a user gave answers or not
     *
     * @return true or fals (has given answers or not)
     */
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

    /**
     * Checks if it's user ones turn
     *
     * @return whether it's user ones turn or not
     */
    public boolean isUser1Turn() {
        return isUser1;
    }

    /**
     * Provides the current question index
     *
     * @return question index as an integer
     */
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


    /**
     * Switches the scene back to the menu scene while clearing every game input
     * "User canceled game, go back to start scene"
     */
    public void backToMenu() {
        index = 0;
        ANSWERSUSER1.clear();
        ANSWERSUSER2.clear();
        isUser1 = false;
        FxmlGuiDriver.setScene("/fxml/startScene.fxml");
        LOGGER.info("User canceled game, going back to startScene");
    }

    /**
     * Handles whether a next question should be displayed or if it's the end of the game
     *
     * @return state of next question
     */
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

    /**
     * Switches users
     */
    private void nextUser() {
        isUser1 = !isUser1;
    }

    /**
     * Fills the empty array list, created in Session constructor, with chosen answers depending on the user
     *
     * @param answer chosen answer as a string
     */
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

    /**
     * Handles the case when a user un ticks an answer
     *
     * @param answer answer to be removed as a string
     */
    public void removeAnswer(String answer) {
        if (isUser1) {
            ANSWERSUSER1.get(index).remove(answer);
        } else {
            ANSWERSUSER2.get(index).remove(answer);
        }
    }

    /**
     * Clears answers (deletes every chosen answer)
     */
    public void clearAnswers() {
        if (isUser1) {
            ANSWERSUSER1.get(index).clear();
        } else {
            ANSWERSUSER2.get(index).clear();
        }
    }


    /**
     * Provides the amount of answers, a user has given
     *
     * @return size of answers in answeruser list depending on active user
     */
    public int getSizeOfAnswers() {
        if (isUser1) {
            return ANSWERSUSER1.get(index).size();
        } else {
            return ANSWERSUSER2.get(index).size();
        }
    }

    /**
     * Provides the current index
     *
     * @return index as an integer
     */
    public int getCurrentIndex() {
        return index;
    }
}
