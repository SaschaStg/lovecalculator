package de.hdm_stuttgart.love_calculator.answers;

import java.util.ArrayList;

public class Answer {

    // Input-Type: Checkbox<c> / Radio<r> / Text <t> / Slider <s>

    final public String inputType;
    final public ArrayList<String> answerOptions = new ArrayList<String>();


    public Answer(String inputType, final String answer){

        this.inputType = inputType;
        this.answerOptions.add(answer);
    }

    public void addAnswer(final String answer){

        this.answerOptions.add(answer);

    }

    public void getAnswers(){
        for(int i = 0; i < answerOptions.size(); i++){

            System.out.println(answerOptions.get(i));

        }
    }

    public String getOneAnswer(int answerIndex){
        return answerOptions.get(answerIndex);
    }




    //Raphael bumms der den Scheiss ausliest!








}
