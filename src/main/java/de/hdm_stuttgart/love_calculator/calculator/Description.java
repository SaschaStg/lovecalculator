package de.hdm_stuttgart.love_calculator.calculator;

import de.hdm_stuttgart.love_calculator.game.Session;

public class Description {

    public static String generateDescription(Session sessionDescription, int finalPercentage) {
        int percentage = finalPercentage;
        if (percentage <= 10) {
            return "Leider passt ihr nicht zusammen :(";
        } else if (percentage > 10 && percentage <= 20) {
            return "Naja.. das wird glaub nix.";
        } else if (percentage > 20 && percentage <= 30) {
            return "Eventuell könnt ihr ja Freunde sein..";
        } else if (percentage > 30 && percentage <= 40) {
            return "Also gute Freunde könntet ihr ja schon werden";
        } else if (percentage > 40 && percentage <= 50) {
            return "Könnte schwierig werden.. aber nicht unmöglich!";
        } else if (percentage > 50 && percentage <= 60) {
            return "Zwar keine Liebe auf den ersten Blick aber auch nicht hoffnungslos!";
        } else if (percentage > 60 && percentage <= 70) {
            return "Es könnte öfter Stress in der Beziehung geben aber ihr passt schon ein wenig zusammen!";
        } else if (percentage > 70 && percentage <= 80) {
            return "Zwischen euch funkt es.. da ist was!";
        } else if (percentage > 80 && percentage <= 90) {
            return "Seid ihr schon zusammen? Nein? Dann wirds höchste Zeit! Das passt super!";
        } else if (percentage > 90 && percentage <= 100) {
            return "Besser gehts nicht! Das nennt man Liebe auf den ersten Blick!";
        }
        return "Eure Liebe überfordert sogar das System.. das ist wohl was ganz besonderes?";
    }

}
