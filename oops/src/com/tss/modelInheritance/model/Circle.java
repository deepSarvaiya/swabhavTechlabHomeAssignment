package com.tss.modelInheritance.model;


public class Circle extends Shape {

	private int radius ;
	
	@Override
	public void area() {
		System.out.println("Area of circle :" + (3.14*(radius*radius)));
		
	}

	public Circle(int radius) {
		super();
		this.radius = radius;
	}

}
