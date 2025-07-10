package com.tss.basics.array2D;

import java.util.Scanner;

public class AdditionAndMultiplicationOfTwoMetrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the size of rows");
		int rows = scanner.nextInt() ;
		System.out.println("Enter the size of colums");
		int colums = scanner.nextInt() ;
		int matrix1[][] = new int[rows][colums];
		int matrix2[][] = new int[rows][colums];
		
		System.out.println("Enter First Matrix");
		readMatrix(matrix1,scanner);
		
		System.out.println("Enter Secoond Matrix");

		readMatrix(matrix2,scanner);
		
	
		sumOfTwoMatrix(matrix1,matrix2,rows,colums);
		  if (colums != rows) {
	            System.out.println("Matrix multiplication not possible. ");
	            scanner.close();
	            return;
	        }
		multiplicationOfTwoArray(matrix1,matrix2,rows,colums);
		
		
		
		
		scanner.close();
	}
	
	private static void readMatrix(int[][] matrix, Scanner scanner) {
		
		// TODO Auto-generated method stub
		
		for(int i= 0 ; i < matrix[0].length ; i++) {
			for(int j= 0 ; j < matrix[0].length ; j++) {
				System.out.print("Enter element matrix "+ i + j + " : ");
				matrix[i][j] = scanner.nextInt();
			}
		}
		
	}

	public static void multiplicationOfTwoArray(int[][] matrix1,int[][] matrix2,int rows,int colums){
		int result[][] = new int[rows][colums];

		   for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < colums; j++) {
	                for (int k = 0; k < colums; k++) { 
	                    result[i][j] += matrix1[i][k] * matrix2[k][j];
	                }
	            }
	        }
			System.out.println("Multiplication");

		   printMatrix(result,rows,colums);
		   
		
	}
	private static void printMatrix(int[][] result,int rows,int colums) {
		// TODO Auto-generated method stub
		   System.out.println("Resultant Matrix of multiplication:");
	        for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < colums; j++) {
	                System.out.print(result[i][j] + " ");
	            }
	            System.out.println();
	        }
		
	}

	public static void sumOfTwoMatrix(int[][] matrix1,int[][] matrix2,int rows,int colums) {
		int sumOfMatrix[][] = new int[rows][colums];
		
		
		for(int i= 0 ; i < sumOfMatrix[0].length ; i++) {
			for(int j= 0 ; j < sumOfMatrix[0].length ; j++) {
				sumOfMatrix[i][j]= matrix1[i][j] + matrix2[i][j];
			}
			System.out.println(" ");
		}
		System.out.println("sum");
		   printMatrix(sumOfMatrix,rows,colums);
		   

		
		 
		
	}
}
