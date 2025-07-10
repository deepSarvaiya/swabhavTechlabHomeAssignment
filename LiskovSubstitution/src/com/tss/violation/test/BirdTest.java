package com.tss.violation.test;

import com.tss.violation.model.Bird;
import com.tss.violation.model.Ostrich;
import com.tss.violation.model.Pigeon;
import com.tss.violation.model.Sparrow;

public class BirdTest {
	public static void main(String []args)
	{
		Bird b1 = new Sparrow();
		b1.fly();
		
		Bird b2 = new Pigeon();
		b2.fly();
		
		Bird b3 = new Ostrich();
		b3.fly();
		
		
	}
}
