package com.tss.basics.selection_statements;

import java.util.Scanner;

public class EvenOddFinder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter number : ");
		int number = scanner.nextInt();
		
		if(number%2==0) {
			System.out.println(number + " is even");
			
		}
		else {
			System.out.println(number + " is odd");

		}
		
		scanner.close();

	}

}
