package de.hdm_stuttgart.love_calculator;

import de.hdm_stuttgart.love_calculator.answers.Answer;
import de.hdm_stuttgart.love_calculator.catalog.Catalog;
import de.hdm_stuttgart.love_calculator.questions.Question;

import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {

        Catalog.initQuestions();

        //System.out.println(Catalog.getQuestionList().get(ques));

        for(int i = 0; i < Catalog.getQuestionList().size(); i++) {
            System.out.println("");
            System.out.println("##### Frage " + i + " #####");
            System.out.println(Catalog.getQuestionList().get(i).questionContent);
            System.out.println(Catalog.getQuestionList().get(i).mode);
            System.out.println(Catalog.getQuestionList().get(i).category);
            System.out.println(Catalog.getQuestionList().get(i).priority);
            System.out.println("");

        }


        Answer essenstyp = new Answer( 'a', "Vegetarisch");
        Answer sporttyp = new Answer( 'b', "Fussball");
        sporttyp.addAnswer("Basketball");

        System.out.println(essenstyp.inputType);
        System.out.println(essenstyp.answerOptions.get(0));

        for(int i = 0; i < sporttyp.answerOptions.size(); i++ ){

            System.out.println(sporttyp.answerOptions.get(i));

        }











    }
}
