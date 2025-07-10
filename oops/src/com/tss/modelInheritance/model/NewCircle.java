package com.tss.modelInheritance.model;


public class NewCircle implements IShape {

	private int radius ;
	
	@Override
	public void area() {
		System.out.println("Area of circle :" + (3.14*(radius*radius)));
		
	}
	public NewCircle(int radius) {
		super();
		this.radius = radius;
	}

}