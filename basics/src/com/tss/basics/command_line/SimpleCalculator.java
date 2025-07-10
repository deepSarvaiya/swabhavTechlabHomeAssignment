package com.tss.basics.command_line;

public class SimpleCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int number1 = Integer.parseInt(args[0]);
		
		int number2 = Integer.parseInt(args[1]);
		
		addition(number1,number2);
		differnce(number1,number2);
		multiplication(number1,number2);
		division(number1,number2);
	
	
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
