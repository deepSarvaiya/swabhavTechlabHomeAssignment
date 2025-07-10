package com.tss.basics.selection_statements;

import java.util.Scanner;

public class TreasureIslandGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		System.out.println("please enter your choice (left or Right): ");

		String move1 = sc.next();

		if (move1.equalsIgnoreCase("left")) {

			System.out.println("please enter your choice (swim or wait): ");

			String move2 = sc.next();

			if (move2.equalsIgnoreCase("wait")) {

				System.out.println("please enter your choice (Red or Blue or Yellow): ");

				String move3 = sc.next();

				if (move3.equalsIgnoreCase("Yellow")) {
					System.out.println("You Win!");
				} else if (move3.equalsIgnoreCase("Red")) {
					System.out.println("Burned by fire \n Game Over");

				} else if (move3.equalsIgnoreCase("Blue")) {
					System.out.println("Eaten by Beasts \n Game Over");

				} else {
					System.out.println("Game Over");
				}

			}

			else {
				System.out.println("Attacked by trout \n Game Over");
			}
		} else {
			System.out.println("Fall into a hole \n Game Over");
		}
		sc.close();

	}

}
