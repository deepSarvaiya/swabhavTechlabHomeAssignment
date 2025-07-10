package com.tss.basics.iterativestatements;

import java.util.Random;
import java.util.Scanner;

public class PigGameWithUnlimitedMove {

    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        int score = 0;
        int turn = 1;

        while (true) {
            System.out.println("Your Turn No. : " + turn);
            System.out.println("Enter Your choice (Roll => r / Hold => h):");
            char userChoice = sc.next().charAt(0);

            while (userChoice == 'r' || userChoice == 'R') {
                int diceRoll = rand.nextInt(6) + 1;
                System.out.println("You rolled: " + diceRoll);

                if (diceRoll == 1) {
                    System.out.println("Oops! You rolled a 1. Turn over, no points added.");
                    score = 0; 
                    System.out.println("Final score: " + score);
                    sc.close();
                    return; 
                } else {
                    score += diceRoll;
                    System.out.println("Total score: " + score);
                }

                if (score >= 20) {
                    System.out.println("Congratulations! You reached " + score + " points in turn " + turn);
                    sc.close();
                    return;
                }

                System.out.println("Enter Your choice (Roll => r / Hold => h):");
                char userChoice1 = sc.next().charAt(0);

                if (userChoice1 == 'r' || userChoice1 == 'R') {
                    userChoice = 'r';
                } else {
                    System.out.println("You chose to hold. Final score: " + score);
                    sc.close();
                    return;
                }
            }

            turn++;
            System.out.println("End of Turn. Current score: " + score);
            System.out.println("---------------------------------------");
        }
    }
}
