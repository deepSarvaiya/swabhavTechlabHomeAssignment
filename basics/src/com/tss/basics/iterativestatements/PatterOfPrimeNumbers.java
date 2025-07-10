package com.tss.basics.iterativestatements;

import java.util.Scanner;

public class PatterOfPrimeNumbers {

    private static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter No. of rows: ");
        int noOfRows = scanner.nextInt();

        int totalPrimesNeeded = (noOfRows * (noOfRows + 1)) / 2;
        int[] primeArray = new int[totalPrimesNeeded];

        int count = 0;
        int num = 1; 
        while (count < totalPrimesNeeded) {
            if (num == 1 || isPrime(num)) {
                primeArray[count] = num;
                count++;
            }
            num++;
        }

        int index = 0;
        for (int i = 1; i <= noOfRows; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(primeArray[index++] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
