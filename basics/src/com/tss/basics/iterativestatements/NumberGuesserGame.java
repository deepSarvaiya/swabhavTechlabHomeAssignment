package com.tss.basics.iterativestatements;

import java.util.Random;
import java.util.Scanner;

public class NumberGuesserGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner sc = new Scanner(System.in);
		 Random rand = new Random();
	        
	     int randomNumber = rand.nextInt(100) + 1;
//	     System.out.println("Random Number:" + randomNumber);
	     int max_try = 5;
    	 System.out.println("Please Guess a Number between 1 to 100");

	     while(max_try > 0) {
	    	 System.out.println("Enter a Number");
	    	 int guess_no = sc.nextInt();
	    	  if (guess_no < 1 || guess_no > 100) {
	                System.out.println("Invalid input! Please enter a number between 1 and 100.");
	                continue;
	            }
	    	 if(randomNumber == guess_no) {
	    		 System.out.println("You Won: in attemp " + (6 - max_try ));
	    		 break;
	    	 }
	    	 else if(randomNumber > guess_no) {
	    		 System.out.println("Sorry too low");
	    		 System.out.println("attemp left:" + (max_try -1));
	    	 }
	    	 else{
	    		 System.out.println("Sorry too high");
	    		 System.out.println("attemp left:" + (max_try -1));

	    	 }
	    	 max_try --;
	    	 
	    	  if (max_try == 0) {
	                System.out.println("Out of attempts! The correct number was: " + randomNumber);
	            }
	    	 
	     }
	     sc.close();

	}

}
