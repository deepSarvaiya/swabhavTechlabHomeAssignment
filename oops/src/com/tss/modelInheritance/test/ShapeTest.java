package com.tss.modelInheritance.test;

import com.tss.modelInheritance.model.Circle;
import com.tss.modelInheritance.model.Shape;
import com.tss.modelInheritance.model.Square;

public class ShapeTest {

	public static void main(String[] args) {
		
		Shape circle = new Circle(10);
		circle.area();

		Shape square = new Square(20);
		square.area();
	}

}
