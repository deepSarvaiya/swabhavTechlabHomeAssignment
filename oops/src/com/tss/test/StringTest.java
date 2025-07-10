package com.tss.test;

public class StringTest {
	public static void main(String[] args) {
		StringBuilder string1 = new StringBuilder("abc");
		StringBuilder string3 = new StringBuilder("xyz");
		System.out.println(string1.hashCode());
		System.out.println(string3.hashCode());

		System.out.println(string1 == string3);
		string1 = string3;
		System.out.println(string1.hashCode());
		System.out.println(string3.hashCode());
		System.out.println(string1 == string3);
		System.out.println(string3);
	}
}
