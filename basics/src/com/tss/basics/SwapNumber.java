package com.tss.basics;

import java.util.Scanner;

public class SwapNumber {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter first number : ");
		int number1 = scanner.nextInt();
		
		System.out.println("Enter Second number : ");
		int number2 = scanner.nextInt();
		
		System.out.println("Before Swapping numbers: ");
		System.out.println("Number1: " + number1);

		System.out.println("Number2: " + number2);
		
		
		swapWithTempVariable(number1,number2);
		swapWithoutTempVariable(number1,number2);
		scanner.close();

	}
	
	private static void swapWithTempVariable(int number1,int number2) {
		int temp = number1;
		number1 = number2;
		number2 = temp;
		
		
		System.out.println("After Swapping numbers With temp variable: ");
		System.out.println("Number1: " + number1);

		System.out.println("Number2: " + number2);
		
		
	}
	
	private static void swapWithoutTempVariable(int number1,int number2) {
		
		number1 = number1+number2;
		number2 = number1-number2;
		number1 = number1-number2;
		
		
		System.out.println("After Swapping numbers Without temp variable : ");
		System.out.println("Number1: " + number1);

		System.out.println("Number2: " + number2);
		
		
	}
}
