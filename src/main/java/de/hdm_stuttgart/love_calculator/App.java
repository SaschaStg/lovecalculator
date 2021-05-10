package de.hdm_stuttgart.love_calculator;

import de.hdm_stuttgart.love_calculator.catalog.Catalog;
import de.hdm_stuttgart.love_calculator.questions.Question;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {

        Catalog.initQuestions();

        System.out.println(Catalog.getQuestionList().get(0));

        System.out.println("debug");














    }
}
