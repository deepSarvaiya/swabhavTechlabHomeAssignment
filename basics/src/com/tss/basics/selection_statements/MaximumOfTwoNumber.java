package com.tss.basics.selection_statements;

import java.util.Scanner;

public class MaximumOfTwoNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter first number : ");
		int number1 = scanner.nextInt();
		
		System.out.println("Enter Second number : ");
		int number2 = scanner.nextInt();
		
		maximumOfTwoNumber(number1,number2);
		scanner.close();

		
	}
	
	private static void maximumOfTwoNumber(int number1,int number2) {
		if(number1 > number2) {
			System.out.println(number1 + " is Greater then " + number2);
		}
		else if(number1 < number2) {
			System.out.println(number2 + " is Greater then " + number1);
		}
		else {
			System.out.println(number1 + " and " + number2 + " is equal");
		}
	}

}
