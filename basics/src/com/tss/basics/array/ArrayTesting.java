package com.tss.basics.array;

import java.util.Random;

public class ArrayTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random random = new Random();
		int array[]= new int[5];
		
		for(int i = 0 ; i < array.length; i++) {
			array[i] = random.nextInt(100);
			
		}
		for(int i = 0 ; i < array.length; i++)	{
			System.out.println(array[i]);
		}

}
}