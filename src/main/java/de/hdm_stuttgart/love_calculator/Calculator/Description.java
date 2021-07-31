package de.hdm_stuttgart.love_calculator.Calculator;

/**
 * Provides description texts
 */
public class Description {

    /**
     * Describes the compatibility of two players
     *
     * @param percentage based on this integer, the description gets chosen
     * @return the description as a string
     */
    public static String generateDescription(int percentage) {
        if (percentage <= 4) {
            return "Schon mal überlegt enthalten zu leben?";
        } else if (percentage <= 9) {
            return "Warum reimt sich dein Name auf Zölibat?";
        } else if (percentage <= 14) {
            return "Tausendmal berührt, tausendmal ist nichts passiert und dabei bleibt es auch!";
        } else if (percentage <= 19) {
            return "Im Leben bist du mal Windschutzscheibe und mal Insekt, mit dem Ergebnis wohl eher das Insekt.";
        } else if (percentage <= 24) {
            return "Satz mit x… das wird wohl nix,";
        } else if (percentage <= 29) {
            return "*Blütenblätter pflücken* liebt dich, liebt dich nicht, liebt dich, liebt dich NICHT!";
        } else if (percentage <= 34) {
            return "Verkauf dich nicht unter deinem Wert.";
        } else if (percentage <= 39) {
            return "Andere Eltern haben auch schöne Kinder.";
        } else if (percentage <= 44) {
            return "Lieber widerlich als wieder nicht.";
        } else if (percentage <= 49) {
            return "Einmal ist keinmal.";
        } else if (percentage <= 54) {
            return "Hm, ... irgendwie nichts Ganzes und nichts Halbes.";
        } else if (percentage <= 59) {
            return "Naja immerhin braucht eure Liebe keine weiteren Parteien für die absolute Mehrheit.";
        } else if (percentage <= 64) {
            return "Könnte besser sein, könnte schlechter sein, könnt's auch sein lassen.";
        } else if (percentage <= 69) {
            return "Könnte schwierig werden... aber nicht unmöglich!";
        } else if (percentage <= 74) {
            return "Besser wirds wohl nicht.";
        } else if (percentage <= 79) {
            return "Zwischen euch funkt es… da ist was!";
        } else if (percentage <= 84) {
            return "Auf gehts raus und Blumen kaufen!";
        } else if (percentage <= 89) {
            return "Eure Herzen schlagen im gleichen Takt.";
        } else if (percentage <= 94) {
            return "Ich höre schon die Hochzeitsglocken Leuten. Ab nach Vegas!";
        } else if (percentage <= 99) {
            return "Sehr geehrte Damen und Herren, darf ich Ihnen vorstellen? Das Traumpaar des Jahres!";
        } else if (percentage == 100) {
            return "Glaubst du an die große Liebe? Dann hast du sie gefunden – Glückwunsch!";

        }
        return "Eure Liebe überfordert sogar das System.. das ist wohl was ganz besonderes?";
    }

}
