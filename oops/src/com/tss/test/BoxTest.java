package com.tss.test;

import com.tss.model.Box;

public class BoxTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Box box1 = new Box();
		box1.initialize();
		box1.display();
		
		box1.setWidth(50);
		System.out.println("Width: " + box1.getWidth());
	}

}
