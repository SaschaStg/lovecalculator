package de.hdm_stuttgart.love_calculator.Game;

import de.hdm_stuttgart.love_calculator.Exception.InvalidCsvFileSize;
import de.hdm_stuttgart.love_calculator.Gui.GuiFactory.QuestionsFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


/**
 * Initializes question objects and answer objects
 * Acts as a connector between the answer objects and question objects and provides basic methods to handle them
 * 1 to n relation to question class
 */
public class Catalog {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(QuestionsFactory.class);
    /**
     * Instance of catalog which is created once
     */
    public static Catalog INSTANCE = new Catalog();
    /**
     * HashMap which connects the question objects to the answer objects
     * A right order in the CSV file is required
     */
    private final Map<Question, Answers> QUESTIONS = new HashMap<>();

    /**
     * empty constructor
     */
    private Catalog() {
    }

    /**
     * Provides the amount of questions
     *
     * @return size of question HashMap
     */
    public int getQuestionsCount() {
        return QUESTIONS.size();
    }

    /**
     * Provides a question object based on a given index
     *
     * @param index index of needed question object
     * @return question object
     */
    public Question getQuestion(int index) {
        return QUESTIONS.keySet().stream().filter(q -> q.INDEX == index).findFirst().get();
    }

    /**
     * Provides an answer object based on a given question object
     *
     * @param question question object of which the answers are needed
     * @return answer object
     */
    public Answers getAnswers(Question question) {
        return QUESTIONS.get(question);
    }

    /**
     * Ininitializes questions and answers objects by reading them out of CSV files
     *
     * @throws InvalidCsvFileSize is thrown when there is an unequal amount of lines in the question CSV file and answer CSV file
     *                            every question object needs one answer object
     */
    public void initialize() throws InvalidCsvFileSize {
        try {
            List<String> questionsCsv = Files.readAllLines(getPath("questions.csv"));
            List<String> answersCsv = Files.readAllLines(getPath("answers.csv"));

            if (answersCsv.size() == questionsCsv.size()) {

                for (int i = 0; i < questionsCsv.size(); i++) {
                    Question q = getQuestion(questionsCsv.get(i), i);
                    Answers a = getAnswers(answersCsv.get(i));

                    this.QUESTIONS.put(q, a);
                    LOGGER.info("Question: " + q + "saved in map.");
                    LOGGER.info("Answer: " + a + "saved in map.");
                }
            } else {
                throw new InvalidCsvFileSize("QuestionsCsv and AnswersCsv do not have the same size. Shutting down.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a resource string to a path object
     *
     * @param resource resource as a string
     * @return path object
     */
    private Path getPath(String resource) {
        return Paths.get(URI.create(Objects.requireNonNull(Question.class.getClassLoader().getResource(resource)).toExternalForm()));
    }

    /**
     * Builds a question object based on the raw string of the question CSV file
     *
     * @param questionCsvLine raw string from question CSV file
     * @param index           index of question
     * @return question object
     */
    private Question getQuestion(String questionCsvLine, int index) {
        String[] content = questionCsvLine.split(";");

        // Store split values in variables and convert to correct types
        String questionContent = content[0];
        String questionContentUser2 = content[1];

        // Add new Question object to ArrayList
        return new Question(index, questionContent, questionContentUser2);
    }


    /**
     * Buildsan answer object based on the raw string of the answer CSV file
     *
     * @param answersCsvLine raw string from answer CSV file
     * @return answer object
     */
    private Answers getAnswers(String answersCsvLine) {
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