package com.tss.basics.selection_statements;

import java.util.Scanner;

public class CheckSeasonOfMonth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner =new Scanner(System.in);
		System.out.println("Enter Month name: " );
		String month = scanner.next();
		
		
		switch(month.toLowerCase()) {
		case "nov" : case "dec" : case "jan" : case "feb":  
				System.out.println("Winter");
				break;
		case "mar" : case "apr" : case "may" : case "jun":  
			System.out.println("Winter");
			break;
		case "aug" : case "sep" : case "oct" : case "jul":  
			System.out.println("Winter");
			break;
			
		default : System.out.println("error");
		}
		
		scanner.close();

			
	}

}
