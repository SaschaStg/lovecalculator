package de.hdm_stuttgart.love_calculator.calculator;

public class NameCalculation {


    private static int findSum(int no) {
        int sum = 0;
        while (no > 0) {
            sum += no % 10;
            no /= 10;
        }
        return sum;
    }



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
        int bothNamesSum = 0;
        int sumOfAllDigits = 0;


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
