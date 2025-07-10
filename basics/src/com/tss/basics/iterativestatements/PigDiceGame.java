package com.tss.basics.iterativestatements;

import java.util.Random;
import java.util.Scanner;

public class PigDiceGame {

    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        int score = 0;
        int max_try = 5;

        while (max_try > 0) {
            int currentTurn = 6 - max_try;
            System.out.println("Your Turn No. : " + currentTurn);
            System.out.println("Enter Your choice (Roll => r / Hold => h):");
            char userChoice = sc.next().charAt(0);

            while (userChoice == 'r' || userChoice == 'R') {
                int diceRoll = rand.nextInt(6) + 1;

                System.out.println("You rolled: " + diceRoll);

                if (diceRoll == 1) {
                    System.out.println("Oops! You rolled a 1. Turn over, no points added.");
                    score = 0; 
                    break;
                } else {
                    score += diceRoll;
                    System.out.println("Total score: " + score);
                }

                // Early win check
                if (score >= 20) {
                    System.out.println("Congratulations! You reached " + score + " points in turn " + currentTurn);
                    sc.close();
                    return;
                }

                System.out.println("Enter Your choice (Roll => r / Hold => h):");
                char userChoice1 = sc.next().charAt(0);

                if (userChoice1 == 'r' || userChoice1 == 'R') {
                    userChoice = 'r';
                } else {
                    break; 
                }
            }

            max_try--; 
            System.out.println("End of Turn. Your current score: " + score);
            System.out.println("-------------------------------------------------");
        }

        System.out.println("Game Over! Final score: " + score);
        sc.close();
    }
}
