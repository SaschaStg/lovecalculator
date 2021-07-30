package de.hdm_stuttgart.love_calculator.game;

import de.hdm_stuttgart.love_calculator.exception.InvalidCsvFileSize;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Catalog {
    public static Catalog INSTANCE = new Catalog();
    private Catalog() {}

    /**
     * Relation (1 zu n) zu Questions - Klasse
     * ArrayList Type Object (alle Objekte die aus der Klasse Questions initialisiert werden)
     * Methoden addCatalog und deleteCatalog
     * Alles auf privat setzen
     */
    private final Map<Question, Answers> questions = new HashMap<>();



    public int getQuestionsCount() {
        return questions.size();
    }

    public Question getQuestion(int index) {
        return questions.keySet().stream().filter(q -> q.index == index).findFirst().get();
    }

    public Answers getAnswers(Question question) {
        return questions.get(question);
    }

    public void initialize() throws InvalidCsvFileSize {
        try {
            List<String> questionsCsv = Files.readAllLines(getPath("questions.csv"));
            List<String> answersCsv = Files.readAllLines(getPath("answers.csv"));

            if (answersCsv.size() == questionsCsv.size()) {

                for (int i = 0; i < questionsCsv.size(); i++) {
                    Question q = getQuestion(questionsCsv.get(i), i);
                    Answers a = getAnswers(answersCsv.get(i));

                    this.questions.put(q, a);
                }
            } else {
                throw new InvalidCsvFileSize("QuestionsCsv and AnswersCsv do not have the same size. Shutting down.");
            }
            } catch(IOException e){
                e.printStackTrace();
            }
    }

    private static Path getPath(String resource) {
        return Paths.get(URI.create(Question.class.getClassLoader().getResource(resource).toExternalForm()));
    }

    private static Question getQuestion(String questionCsvLine, int index) {
        String[] content = questionCsvLine.split(";");

        // Store split values in variables and convert to correct types
        String questionContent = content[0];
        String questionContentUser2 = content[1];

        // Add new Question object to ArrayList
        return new Question(index, questionContent, questionContentUser2);
    }



    private static Answers getAnswers(String answersCsvLine) {
        String[] content = answersCsvLine.split(";");

        // Store split values in variables and convert to correct types
        String inputType = content[0];
        String firstAnswer = content[1];
        String[] moreAnswers = new String[0];
        if (content.length > 2) {
            moreAnswers = Arrays.copyOfRange(content, 2, content.length);
        }

        // Add new Question object to ArrayList
        return new Answers(inputType, firstAnswer, moreAnswers);
    }
}





