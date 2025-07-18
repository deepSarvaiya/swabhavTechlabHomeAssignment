package com.tss.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class calculatorTest {
	calculator calc ;
	
	@BeforeEach 
	void init() {
		calc = new calculator();
	}

	@AfterAll
	static void display() {
		System.out.println("Done");
	}
	@Test
	void testAddititon() {
		int actualTest =   calc.addititon(3,2);
		assertEquals(5,actualTest);
	}

	@Test
	void testSubtraction() {
		int actualTest =   calc.subtraction(3,2);
		assertEquals(1,actualTest);	}

	@Test
	void testMultiplication() {
		int actualTest =   calc.multiplication(3,2);
		assertEquals(6,actualTest);	}

	@Test
	void testDivisionByZero() {
	    assertThrows(ArithmeticException.class, () -> calc.division(4, 0));
	}
	@Test
	void testDivision() {
//        assertThrows(ArithmeticException.class, () -> calc.division(3, 0));
		int actualTest =   calc.division(3,1);
		assertEquals(3,actualTest);	}
//  assertThrows(ArithmeticException.class, () -> calc.division(3, 0));

}
