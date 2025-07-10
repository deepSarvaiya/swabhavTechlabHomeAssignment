package com.tss.test;

import java.util.ArrayList;

public class autoboxingAndUnbosing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   char ch = 'a';
	        Character a = ch; //autoboing
	        char c = a;			// unboxing

	        ArrayList<Integer> arrayList
	            = new ArrayList<Integer>();

	        arrayList.add(25); //autoboing
//	        System.out.println(arrayList.get(0));
	        
	        int num = arrayList.get(0);	// unboxing

	        
	        
	        
	        
	}

}
