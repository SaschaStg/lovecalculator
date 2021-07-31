package de.hdm_stuttgart.love_calculator.Calculator;

/**
 * Provides the calculation logic of two names
 */
public class NameCalculation {

    /**
     * Finds the sum of an integer, in our case the sum of both names combined
     * @param no integer which sum we need
     * @return the sum as an integer
     */
    private static int findSum(int no) {
        int sum = 0;
        while (no > 0) {
            sum += no % 10;
            no /= 10;
        }
        return sum;
    }

    /**
     * Calculates the compatibility of the two players names
     * @param nameUser1 name of user one
     * @param nameUser2 name of user two
     * @return compatibility as an integer
     */
    public static int calculate(String nameUser1, String nameUser2){

        nameUser1 = nameUser1.toLowerCase();
        nameUser2 = nameUser2.toLowerCase();

        //little easter egg
        if(nameUser1.equals("kriha") || nameUser2.equals("kriha")){
            return 100;
        }

        int asciiSumUser1 = 0;
        int asciiSumUser2 = 0;
        int sumLove = 15;
        int bothNamesSum;
        int sumOfAllDigits;

        for(int i = 0; i < nameUser1.length(); i++){
            asciiSumUser1 += nameUser1.charAt(i);
        }

        for(int i = 0; i < nameUser2.length(); i++){
            asciiSumUser2 += nameUser2.charAt(i);
        }

        bothNamesSum = asciiSumUser1 + asciiSumUser2;
        sumOfAllDigits = findSum(bothNamesSum);

        if(sumOfAllDigits > sumLove){
            sumOfAllDigits = sumLove - (sumOfAllDigits - sumLove);
        }

        return (sumOfAllDigits * 100 /sumLove);
    }
}
