package com.tss.basics;

import java.util.Scanner;

public class SimpleCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter first number : ");
		int number1 = scanner.nextInt();
		
		System.out.println("Enter Second number : ");
		int number2 = scanner.nextInt();
		
		addition(number1,number2);
		differnce(number1,number2);
		multiplication(number1,number2);
		division(number1,number2);
	
		scanner.close();

	}
	
	private static void addition(int number1, int number2) {

		int sum = number1+number2;
		System.out.println("Sum = " + sum);

	}

	private static void differnce(int number1, int number2) {

		int differnce = number1-number2;
		System.out.println("differnce = " + differnce);

	}
	
	private static void multiplication(int number1, int number2) {

		int multiplication = number1*number2;
		System.out.println("multiplication = " + multiplication);
	
	}
	
	private static void division(int number1, int number2) {

		int division = number1/number2;
		System.out.println("division = " + division);
	
	}
}
