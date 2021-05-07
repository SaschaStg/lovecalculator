package de.hdm_stuttgart.love_calculator.catalog;


import de.hdm_stuttgart.love_calculator.questions.Question;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;


public class Catalog {
    /**
     * Relation (1 zu n) zu Questions - Klasse
     * ArrayList Type Object (alle Objekte die aus der Klasse Questions initialisiert werden)
     * Methoden addCatalog und deleteCatalog
     * Alles auf privat setzen
     */


    //questions.add(new Question("Figma oder Sigma oder Ligma?", 1, 'c', "Interest", Question.Priority.HIGH));


    //Read Questions from csv-File

    public static Question[] questionList;


    public static void initQuestions() {

        InputStream csvInputStream = Question.class.getClassLoader().getResourceAsStream("questions.csv");
        //check if an InputStream is available
        assert csvInputStream != null;
        BufferedReader csvReader = new BufferedReader(new InputStreamReader(csvInputStream));

        ArrayList<Question> questionList = new ArrayList<Question>();

        try {
            // This code depends on the file to have two columns of which the second one can be parsed as double
            while (csvReader.ready()) {
                // Read new line and split it at separation symbol
                String row = csvReader.readLine();
                String[] content = row.split(";");

                // Store split values in variables and convert to correct types
                String questionContent = content[0];
                char mode = content[1].charAt(0);
                char inputType = content[2].charAt(0);
                String category = content[3];
                Question.Priority priority = null;
                switch (content[4].toUpperCase()) {

                    case "LOW":
                        priority = Question.Priority.LOW;
                        break;
                    case "MEDIUM":
                        priority = Question.Priority.MEDIUM;
                        break;
                    case "HIGH":
                        priority = Question.Priority.HIGH;
                        break;
                    default:
                        //throw exception due to an incomplete Question (initPriorityException)

                }

                // Add new Question object to ArrayList
                questionList.add(new Question(questionContent, mode, inputType, category, priority));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Catalog.questionList = questionList.toArray(new Question[0]);



    }


}





