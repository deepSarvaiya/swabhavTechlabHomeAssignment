package com.tss.basics.array2D;

import java.util.Scanner;

public class MatrixTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int matrix[][] = new int[3][3];
		
		Scanner scanner = new Scanner(System.in);
		
		for(int i= 0 ; i < matrix[0].length ; i++) {
			for(int j= 0 ; j < matrix[0].length ; j++) {
				System.out.print("Enter element matrix "+ i + j + " : ");
				matrix[i][j] = scanner.nextInt();
			}
		}
		
		
		for(int i= 0 ; i < matrix[0].length ; i++) {
			for(int j= 0 ; j < matrix[0].length ; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println(" ");
		}
		
		scanner.close();
	}

}
