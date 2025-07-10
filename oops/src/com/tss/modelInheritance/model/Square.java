package com.tss.modelInheritance.model;



public class Square extends Shape {

	private int length;
	
	@Override
	public void area() {
		System.out.println("Area of Square :" + (length*length));
		
	}

	public Square(int length) {
		super();
		this.length = length;
	}
}
