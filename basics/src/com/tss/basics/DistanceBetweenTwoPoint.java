package com.tss.basics;

import java.util.Scanner;


public class DistanceBetweenTwoPoint {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter first Point of x1 : ");
		int x1 = scanner.nextInt();
		
		System.out.println("Enter Second Point of x2 : ");
		int x2 = scanner.nextInt();
		
		
		System.out.println("Enter first Point of y1 : ");
		int y1 = scanner.nextInt();
		
		System.out.println("Enter Second Point of y2 : ");
		int y2 = scanner.nextInt();
		
		calculateDistance(x1,x2,y1,y2);
		scanner.close();

	}
	
	private static void calculateDistance(int x1,int x2,int y1,int y2) {
		double distance = Math.sqrt(Math.pow((x1-x2),2) + Math.pow((y1-y2),2));
		
		System.out.println("Distance : "+ distance);

	}

}
