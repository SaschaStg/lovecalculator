package de.hdm_stuttgart.love_calculator.Exception;

/**
 * Handles invalid CSV file size
 * Question Csv file and answer CSV file need the same amount of lines because every question object needs one answer object and vice versa
 */
public class InvalidCsvFileSize extends Exception {

    /**
     * Displays exception message
     *
     * @param message describes why exception occurred
     */
    public InvalidCsvFileSize(String message) {
        super(message);
    }
}
