package de.hdm_stuttgart.love_calculator.questions;

public class Question {


    // Content: Questions
    // Mode: Basic / Advanced / Speed
    // Input-Type: Checkbox<c> / Radio<r> / Text <t> / Slider <s>
    // Category: Headline/Topic of the question
    // Priority: x1<low> , 2x<medium>, 3X<high>



    private String questionContent;
    private char mode;
    private char inputType;
    private String category;

    public Question(String content, char mode, char inputType, String category, Priority priority) {

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


    @Override
    public String toString() {
        return "Question{" +
                "questionContent='" + questionContent + '\'' +
                ", mode=" + mode +
                ", inputType=" + inputType +
                ", category='" + category + '\'' +
                '}';
    }











}
