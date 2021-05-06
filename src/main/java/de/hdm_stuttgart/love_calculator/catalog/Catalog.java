package de.hdm_stuttgart.love_calculator.catalog;


import de.hdm_stuttgart.love_calculator.questions.Question;

import java.util.ArrayList;


public class Catalog {
    /**
     * Relation (1 zu n) zu Questions - Klasse
     * ArrayList Type Object (alle Objekte die aus der Klasse Questions initialisiert werden)
     * Methoden addCatalog und deleteCatalog
     * Alles auf privat setzen
     */

    public static void main(String[] args) {

        ArrayList<Object> questions = new ArrayList<Object>();

        questions.add(new Question("Figma oder Sigma oder Ligma?", 1, 'c', "Interest", Question.Priority.HIGH));





    }




}
