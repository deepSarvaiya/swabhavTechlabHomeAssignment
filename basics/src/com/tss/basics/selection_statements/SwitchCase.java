package com.tss.basics.selection_statements;

import java.util.Random;

public class SwitchCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random random = new Random();
		
		int number = random.nextInt(6);
		
		System.out.println("Number Generated: " + number);
		
		switch(number) {
		case 1: System.out.println("one");
				break;
		case 2: System.out.println("two");
		break;
		case 3: System.out.println("three");
		break;
		case 4: System.out.println("four");
		break;
		case 5: System.out.println("five");
		break;
		
		default: System.out.println("out of 5");
		}
	}

}
