package com.tss.modelInheritance.model;

public class NewSquare implements IShape {
	
	private int length;
	
	@Override
	public void area() {
		System.out.println("Area of Square :" + (length*length));		
	}
	
	public NewSquare(int length) {
		super();
		this.length = length;
	}

}