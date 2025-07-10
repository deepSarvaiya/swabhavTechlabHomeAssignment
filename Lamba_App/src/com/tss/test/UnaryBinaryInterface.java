package com.tss.test;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class UnaryBinaryInterface {

	public static void main(String[] args) {

		UnaryOperator<Integer> square = x -> x * x;

		System.out.println(square.apply(5));

		BinaryOperator<Integer> add = (a, b) -> a + b;
		
		System.out.println(add.apply(10, 20));
	}

}
