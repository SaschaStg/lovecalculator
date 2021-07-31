package de.hdm_stuttgart.love_calculator.Game;


/**
 * Provides the question objects
 * Construct of an question object
 */
public class Question {
    /**
     * Index which is used to locate the questions and answers
     */
    public final int INDEX;

    /**
     * "Raw" question content fro user one
     */
    public final String QUESTIONCONTENT;
    /**
     * "Raw" question content form user two
     */
    public final String QUESTIONCONTENTUSER2;

    /**
     * Question constructor
     *
     * @param index    index of an question object
     * @param content  actual question content for user one
     * @param content2 actual question content for user two
     */
    public Question(int index, String content, String content2) {
        this.INDEX = index;
        this.QUESTIONCONTENT = content;
        this.QUESTIONCONTENTUSER2 = content2;
    }
}
