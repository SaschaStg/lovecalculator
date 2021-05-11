package de.hdm_stuttgart.love_calculator.answers;

import java.util.ArrayList;

public class Answer {

    // Input-Type: Checkbox<c> / Radio<r> / Text <t> / Slider <s>

    final public char inputType;
    final public ArrayList<String> answerOptions = new ArrayList<String>();


    public Answer(char inputType, final String answer){

        this.inputType = inputType;
        this.answerOptions.add(answer);
    }

    public void addAnswer(final String answer){

        this.answerOptions.add(answer);

    }




    //Raphael bumms der den Scheiss ausliest!








}
