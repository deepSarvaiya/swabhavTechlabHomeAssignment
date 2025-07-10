package com.tss.creational.abstractfactory.test;

import java.util.Scanner;

import com.tss.creational.abstractfactory.model.ICarFactory;
import com.tss.creational.abstractfactory.model.ICars;
import com.tss.creational.abstractfactory.model.MahindraCarFactory;
import com.tss.creational.abstractfactory.model.MarutiCarFactory;
import com.tss.creational.abstractfactory.model.TataCarFactory;

public class CarTest {

	public static void main(String[] args) {
		ICars car = null;
		ICarFactory carfactory = null;
		Scanner scanner = new Scanner(System.in);
		System.out.print(" 1.Maruti\n 2.Tata\n 3.Mahindra\n Enter Your Choice: ");
		int choice = scanner.nextInt();
		
		if(choice == 1)
		{
			 carfactory  = new MarutiCarFactory();
			 car = carfactory.makeCars();
			 car.start();
			 car.stop();
			 return;
		}
		if(choice == 2)
		{
			 carfactory  = new TataCarFactory();
			 car = carfactory.makeCars();
			 car.start();
			 car.stop();
			 return;
		}
		if(choice == 3)
		{
			 carfactory  = new MahindraCarFactory();
			 car = carfactory.makeCars();
			 car.start();
			 car.stop();
			 return;
		}
		System.out.println("Invalid Choice");
		return;
	}
	

}
