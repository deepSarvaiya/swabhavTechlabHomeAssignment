package com.tss.test;

import com.tss.model.Demo;

public class DemoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Demo demo1 = new Demo();
		Demo demo2 = new Demo();
		
		demo1.display();
		demo2.display();
		
		demo1.increment();
		demo2.increment();
		
		Demo.increment_1();
		Demo.increment_1();
		
		demo1.display();
		demo2.display();
	}
	static
	{
		System.out.println("inside main");
	}
}
