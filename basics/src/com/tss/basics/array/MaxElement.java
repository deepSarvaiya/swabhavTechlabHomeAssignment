package com.tss.basics.array;

import java.util.Scanner;

public class MaxElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("enter size of array");
		int n = scanner.nextInt();
		int array[]= new int[n];
		int max = 0;
		for(int i = 0;i< n ;i++) {
			System.out.println("enter element " + i );
			array[i] = scanner.nextInt();
			if(max < array[i]) {
				max = array[i];
			}
		}
		
		System.out.println("Max is : " + max);
		scanner.close();

	}

}
