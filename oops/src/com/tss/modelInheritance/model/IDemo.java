package com.tss.modelInheritance.model;

public interface IDemo {
		
	public static void method3() {
		System.out.println("helooooooooooooooooooooooooooooooooooooooooooooooooooo");
	}
	public default void display() {
		method2();
		System.out.println("Inside static demo ohhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
	}
	
	private void method2() {
		System.out.println("Hiiiiiiiiiiiiiiiiiiiiiiiiii");
	}
}
