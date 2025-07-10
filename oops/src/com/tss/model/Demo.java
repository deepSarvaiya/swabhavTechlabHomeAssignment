package com.tss.model;

public class Demo {
	private int a;
	private int b;
	private static int c;
	public void increment()
	{
		a++;
		b++;
		c++;
	}
	public static void increment_1()
	{
		c++;
	}
	public void display()
	{
		System.out.println("a: "+a);
		System.out.println("b: "+b);
		System.out.println("c: "+c);
	}
	{
		System.out.println("Inside Demo");
	}
	static
	{
		System.out.println("Inside Demo Static");
	}
	
}
