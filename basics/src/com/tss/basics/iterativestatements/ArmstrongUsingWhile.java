package com.tss.basics.iterativestatements;

import java.util.Scanner;

public class ArmstrongUsingWhile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int sum = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number:-");
		int number = sc.nextInt();
		int temp = number;
		int length_of_number = String.valueOf(number).length();
		
		while(number>0)
		{
			int digit = number % 10;
			sum += Math.pow(digit, length_of_number);
			number = number/10;
		}
		
		if(temp == sum)
		{
			System.out.println("Number is armstrong");
		}
		else
		{
			System.out.println("Number is not armstrong");
		}
sc.close();
	}

}
