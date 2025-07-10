package com.tss.basics.array;

import java.util.Scanner;

public class Avarage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("enter size of array");
		int n = scanner.nextInt();
		int array[]= new int[n];
		int sum = 0;
		for(int i = 0;i< n ;i++) {
			System.out.println("enter element " + i );
			array[i] = scanner.nextInt();
			sum += array[i];
		}
		
		System.out.println("Average is : " + (sum/n));
		
		scanner.close();

}
	
}
