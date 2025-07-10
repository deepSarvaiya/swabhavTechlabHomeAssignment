package com.tss.basics;

import java.util.Scanner;

public class CalculateSimpleInterest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter Priciple Amount : ");
		int priciple_amount = scanner.nextInt();
		
		System.out.println("Enter Rate of interest : ");
		double rate_of_interest = scanner.nextDouble();
		
		System.out.println("Enter Duration");
		double duration = scanner.nextDouble();
		
		calculateSimpleInterest(priciple_amount, rate_of_interest, duration);
		scanner.close();

	}
	
	private static void calculateSimpleInterest(int priciple_amount,double rate_of_interest, double duration) {
		
		double interest = (priciple_amount * rate_of_interest * duration) /100;
		System.out.println("Interest is : " + interest);

	}


	
}
