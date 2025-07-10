package com.tss.basics.command_line;

public class AreaOfCircle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		float radius = Float.parseFloat(args[0]);
		
		areaOfCircle(radius);
		
	}
	private static void areaOfCircle(float radius) {
		double pi = 3.14 ;
		double area = pi * radius * radius ;

		System.out.println("Area of circle : " + area);

	}

}
