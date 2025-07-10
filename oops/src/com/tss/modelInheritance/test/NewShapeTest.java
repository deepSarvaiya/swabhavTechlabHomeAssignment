package com.tss.modelInheritance.test;

import com.tss.modelInheritance.model.NewCircle;
import com.tss.modelInheritance.model.NewSquare;

public class NewShapeTest {
	
	public static void main(String[] args) {
		
		NewCircle circle = new NewCircle(10);
		circle.area();

		NewSquare square = new NewSquare(20);
		square.area();
	}

}
