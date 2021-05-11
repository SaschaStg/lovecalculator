package de.hdm_stuttgart.love_calculator.catalog;


import de.hdm_stuttgart.love_calculator.answers.Answer;
import de.hdm_stuttgart.love_calculator.questions.Question;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Catalog {

    /**
     * Relation (1 zu n) zu Questions - Klasse
     * ArrayList Type Object (alle Objekte die aus der Klasse Questions initialisiert werden)
     * Methoden addCatalog und deleteCatalog
     * Alles auf privat setzen
     */


    //questions.add(new Question("Figma oder Sigma oder Ligma?", 1, 'c', "Interest", Question.Priority.HIGH));


    //Read Questions from csv-File

    private static List<Question> questionList;
    private static List<Answer> answerList;


    public static List<Question> getQuestionList() {
        return questionList;
    }

    public static List<Answer> getAnswerList() {
        return answerList;
    }


    public static void initQuestions() {

        InputStream csvInputStream = Question.class.getClassLoader().getResourceAsStream("questions.csv");
        //check if an InputStream is available
        assert csvInputStream != null;
        BufferedReader csvReader = new BufferedReader(new InputStreamReader(csvInputStream));

        questionList = new ArrayList<Question>();

        try {
            // This code depends on the file to have several columns which include the information for the Question Object
            while (csvReader.ready()) {
                // Read new line and split it at separation symbol
                String row = csvReader.readLine();
                String[] content = row.split(";");

                // Store split values in variables and convert to correct types
                String questionContent = content[0];
                char mode = content[1].charAt(0);
                String category = content[2];
                String priority = content[3];

                // Add new Question object to ArrayList
                questionList.add(new Question(questionContent, mode, category, priority));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }




    }


    public static void initAnswers() {

        InputStream csvInputStream = Question.class.getClassLoader().getResourceAsStream("answers.csv");
        //check if an InputStream is available
        assert csvInputStream != null;
        BufferedReader csvReader = new BufferedReader(new InputStreamReader(csvInputStream));

        answerList = new ArrayList<Answer>();

        try {
            // This code depends on the file to have several columns which include the information for the Answer Object
            while (csvReader.ready()) {
                // Read new line and split it at separation symbol
                String row = csvReader.readLine();
                String[] content = row.split(";");

                // Store split values in variables and convert to correct types
                char inputType = content[0].charAt(0);
                String answer = content[1];

                // Add new Question object to ArrayList
                answerList.add(new Answer(inputType, answer));

                if(content.length > 2){

                    for(int i = 2; i < content.length; i++){

                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }





    }


}





