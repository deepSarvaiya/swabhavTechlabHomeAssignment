package com.tss.basics.selection_statements;

import java.util.Scanner;

public class RideGameCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		System.out.println("please enter your height ");
		float height = sc.nextFloat();

		int charge = 0;
		if (height > 120) {
			System.out.println("Can Ride");

			System.out.println("please enter your age ");
			int age = sc.nextInt();

			

			if (age <= 0) {
				System.out.println("You Enetred Wrong age");
				
			}
			else {
				System.out.println("You Want photos (true or false)");
				boolean photo = sc.nextBoolean();
				if (age < 12) {

					charge += 5;
				} else if ((age >= 12) && (age < 18)) {
					charge += 7;

				} else if ((age >= 18) && (age < 45)) {
					charge += 12;

				} else if ((age >= 45) && (age < 55)) {
					charge += 0;

				} else {

					charge += 12;
				}

				if (photo == true) {
					charge += 3;
				}

			}

		} else {
			System.out.println("Cannot Ride");
		}

		System.out.println("charges : " + charge);
		sc.close();

	}

}
