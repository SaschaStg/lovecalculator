package de.hdm_stuttgart.love_calculator.Game;

public class Question {
    public final int INDEX;
    // Content: Questions
    public final String QUESTIONCONTENT;
    public final String QUESTIONCONTENTUSER2;

    public Question(int index, String content, String content2) {
        this.INDEX = index;
        this.QUESTIONCONTENT = content;
        this.QUESTIONCONTENTUSER2 = content2;
    }
}
