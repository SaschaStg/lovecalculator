package de.hdm_stuttgart.love_calculator.calculator;

public class partyCalculation {


    private static int calculate(int partyUser1, int partyUser2, int instaUser1, int instaUser2) {

        int partyPoints = 0;
        int resultPoints;

        //session.getUserAnswer(false, 0).get(0)

        //   for(int i = 0; i <= answe)


        if (partyUser1 >= partyUser2) {
            resultPoints = partyUser1 - partyUser2;
        } else {
            resultPoints = partyUser2 - partyUser1;
        }

        switch (resultPoints) {

            case 0:
                partyPoints = 100;
                break;
            case 1:
                partyPoints = 80;
                break;
            case 2:
                partyPoints = 60;
                break;
            case 3:
                partyPoints = 140;
                break;
            case 4:
                partyPoints = 20;
                break;
            case 5:
                partyPoints = 0;
                break;
            default:
                System.out.println("ERROR. This shouldn't be possible");

        }

        return 1;
    }


}
