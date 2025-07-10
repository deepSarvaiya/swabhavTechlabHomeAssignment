package com.tss.basics.selection_statements;

import java.util.Scanner;

public class MaximumOfThreeNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter first number : ");
		int number1 = scanner.nextInt();
		
		System.out.println("Enter Second number : ");
		int number2 = scanner.nextInt();
		
		System.out.println("Enter Third number : ");
		int number3 = scanner.nextInt();

		
		maximumOfThreeNumber(number1,number2,number3);
		scanner.close();

		
	}
	
	private static void maximumOfThreeNumber(int number1,int number2, int number3) {
		if(number1 > number2) {
			
			if (number1 > number3) {
				System.out.println(number1 + " is Greater then " + number2 + " and " + number3);
			}
			else { 
				System.out.println(number3 + " is Greater then " + number2 + " and " + number1);

			}
			System.out.println(number1 + " is Greater then " + number2);
		}
		else {
			if (number2 > number3) {
				System.out.println(number2 + " is Greater then " + number1 + " and " + number3);
			}
			else { 
				System.out.println(number3 + " is Greater then " + number2 + " and " + number1);

			}
		}
	}

}
