package com.tss.test;

import java.util.Scanner;

public class MatrixTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int matrix[][] = new int[3][3];

		Scanner scanner = new Scanner(System.in);

		readMatrix(matrix, scanner);
		printMatrix(matrix);
		for (int i = 0; i < matrix[0].length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j]);
			}

		}
	}

	private static void printMatrix(int[][] matrix) {
		// TODO Auto-generated method stub
		for (int i = 0; i < matrix[0].length; i++) {

			printRow(matrix, i);
		}
	}

	private static void printRow(int[][] matrix, int i) {
		// TODO Auto-generated method stub
		for (int j = 0; j < matrix[0].length; j++) {
			System.out.print("Enter element matrix " + matrix[i][j]);

		}

	}

	private static void readMatrix(int matrix[][], Scanner scanner) {
		for (int i = 0; i < matrix[0].length; i++) {

			readRow(matrix, scanner, i);
		}
	}

	private static void readRow(int matrix[][], Scanner scanner, int i) {
		for (int j = 0; j < matrix[0].length; j++) {
			System.out.print("Enter element matrix " + i + j + " : ");
			matrix[i][j] = scanner.nextInt();
		}
	}
}
