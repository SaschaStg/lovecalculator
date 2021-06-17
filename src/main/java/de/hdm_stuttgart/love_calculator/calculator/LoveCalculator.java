package de.hdm_stuttgart.love_calculator.calculator;

import java.util.Scanner;

public class LoveCalculator {

    private static int findSum(int no) {
        int sum = 0;
        while (no > 0) {
            sum += no % 10;
            no /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final String LOVE = "love";

        String firstName;
        String secondName;

        int firstSum;
        int secondSum;
        int loveSum;
        int totalSum;

        while (true) {
            System.out.println("♥️♥♥️♥♥️♥♥️♥♥️♥♥️♥♥️♥♥️♥♥️♥♥️♥♥️♥♥️♥♥️️");
            System.out.println("Enter q to exit : ");
            System.out.println("First Name : ");
            firstName = sc.nextLine();

            if (firstName.equals("q")) {
                break;
            }
            System.out.println("Second Name : ");
            secondName = sc.nextLine();

            firstSum = 0;
            secondSum = 0;
            loveSum = 0;

            firstName = firstName.toLowerCase();
            secondName = secondName.toLowerCase();

            for (var i = 0; i < firstName.length(); i++) {
                firstSum += firstName.charAt(i);
            }

            for (var i = 0; i < secondName.length(); i++) {
                secondSum += secondName.charAt(i);
            }

            for (var i = 0; i < LOVE.length(); i++) {
                loveSum += LOVE.charAt(i);
            }

            totalSum = findSum(firstSum + secondSum);
            loveSum = findSum(loveSum);

            if (totalSum > loveSum) {
                totalSum = loveSum - (totalSum - loveSum);
            }

            System.out.println("Love % : " + (totalSum * 100 / loveSum));

        }

    }

}
