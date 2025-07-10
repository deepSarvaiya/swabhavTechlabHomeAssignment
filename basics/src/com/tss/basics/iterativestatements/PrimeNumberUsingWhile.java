package com.tss.basics.iterativestatements;
import java.util.Scanner;
public class PrimeNumberUsingWhile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number : ");
		int number = scanner.nextInt();
		Boolean isNotPrime = false;
		
		int i = 2;
		while(i <= number/2) {
			if(number%i == 0) {
				isNotPrime = true;
				break;
			}
			i++;
		}
		
		if(isNotPrime == true ) {
			System.out.println("Not a Prime");
		}
		else {
			System.out.println("prime");
		}
		
		scanner.close();

	}

}
