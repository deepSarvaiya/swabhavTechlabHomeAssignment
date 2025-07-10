package com.tss.basics.iterativestatements;

import java.util.Scanner;

public class ReverseNumberUsingWhile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int rev = 0;
		System.out.println("Enter number : ");
		int number = scanner.nextInt();
		while(number>0) {
			
			int digit = number % 10 ;
			rev = (rev*10) + digit;
			number = number / 10;

			
		}
		
		System.out.println("Reverse is : " + rev);
		scanner.close();

	}

}
