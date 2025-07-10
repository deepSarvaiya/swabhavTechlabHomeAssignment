package com.tss.test;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int number1=Integer.parseInt(args[0]);
		int number2=Integer.parseInt(args[1]);
		try
		{
		float result =number1/number2;
		System.out.println("Division is: "+result);
		}
		catch(ArithmeticException exception)
		{
		System.out.println("Divisor can not be zero: Enter some other value");
		}
		finally {
			System.out.println("hiiiiiiiiiiiiiii");
		}
		System.out.println("Exiting");
	}

}
