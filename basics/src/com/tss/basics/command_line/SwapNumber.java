package com.tss.basics.command_line;


public class SwapNumber {
	public static void main(String[] args) {
	
		
		int number1 = Integer.parseInt(args[0]);
		
		int number2 = Integer.parseInt(args[1]);
		
		System.out.println("Before Swapping numbers: ");
		System.out.println("Number1: " + number1);

		System.out.println("Number2: " + number2);
		
		
		swapWithTempVariable(number1,number2);
		swapWithoutTempVariable(number1,number2);
		
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
