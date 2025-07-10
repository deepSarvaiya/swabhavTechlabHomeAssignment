package com.tss.basics.array2D;

import java.util.Scanner;

public class MultiplicationOfTwoMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter number of columns:");
        int cols = scanner.nextInt();

        if (cols != rows) {
            System.out.println("Matrix multiplication not possible. ");
            scanner.close();
            return;
        }

        int[][] matrix1 = new int[rows][cols];
        int[][] matrix2 = new int[rows][cols];
        int[][] result = new int[rows][cols];

        System.out.println("Enter elements for matrix1:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("matrix1[" + i + "][" + j + "] = ");
                matrix1[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter elements for matrix2:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("matrix2[" + i + "][" + j + "] = ");
                matrix2[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < cols; k++) { 
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        System.out.println("Resultant Matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
