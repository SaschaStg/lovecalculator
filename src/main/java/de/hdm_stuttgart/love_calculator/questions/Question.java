package de.hdm_stuttgart.love_calculator.questions;

public class Question {


    // Content: Questions
    // Mode: Basic / Advanced / Speed
    // Category: Headline/Topic of the question
    // Priority: x1<low> , 2x<medium>, 3X<high>



    final public String questionContent;
    final private String mode;
    final private String category;
    final private String priority;


    public Question(String content, String mode, String category, String priority) {

        this.questionContent = content;
        this.mode = mode;
        this.category = category;
        this.priority = priority;


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
