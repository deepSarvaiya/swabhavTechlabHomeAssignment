package com.tss.test;

import java.util.Scanner;

public class EvenOddTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter A number:");
		int num = scanner.nextInt();
		
		if(num%2==0) {
			System.out.println("Even");
			scanner.close();
			return ;
		}
		
		System.out.println("Odd");
		
		scanner.close();
	}

}
