package com.tss.basics.iterativestatements;

import java.util.Scanner;

public class SumOfDigitsUsingWhile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter number : ");
		int number = scanner.nextInt();
		int sum = 0;
		while(number>0) {
			
			int digit = number % 10 ;
			sum = sum + digit;
			number = number / 10;

			
		}
		
		System.out.println("sum is : " + sum);
		scanner.close();

	}

}
