package de.hdm_stuttgart.love_calculator.questions;

public class Question {


    // Content: Questions
    // Mode: Basic / Advanced / Speed
    // Category: Headline/Topic of the question
    // Priority: x1<low> , 2x<medium>, 3X<high>



    final public String questionContent;
    final public char mode;
    final public String category;
    final public String priority;

    public Question(String content, char mode, String category, String priority) {

        this.questionContent = content;
        this.mode = mode;
        this.inputType = inputType;
        this.category = category;

    }

    public enum Priority {

        LOW(1),
        MEDIUM(2),
        HIGH(3);

        Priority(final int value) {
            this.value = value;
        }

        private final int value;

    }


    /*@Override
    public String toString() {
        return "Question{" +
                "questionContent='" + questionContent + '\'' +
                ", mode=" + mode +
                ", inputType=" + inputType +
                ", category='" + category +
                ", priority='" + priority + '\'' +
                '}';
    }*/











}
