package com.tss.test;

import com.tss.model.IDemo;

public class Demotest {

	public static void main(String args[]) {
		
		IDemo obj1 = (number) -> {
				System.out.print("hiii" + number);
			
		};
		obj1.display(10);
	}

}
