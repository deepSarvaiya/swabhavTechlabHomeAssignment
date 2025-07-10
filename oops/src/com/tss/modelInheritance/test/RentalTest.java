package com.tss.modelInheritance.test;

import java.util.Scanner;

import com.tss.modelInheritance.model.BikeRental;
import com.tss.modelInheritance.model.CarRental;
import com.tss.modelInheritance.model.TruckRental;

public class RentalTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter No. Of Days");
		int noOfdays = scanner.nextInt();
		
		BikeRental bike = new BikeRental(noOfdays);
		bike.display();
		
//		scanner.nextLine();
		System.out.println(" ");
		CarRental car = new CarRental(noOfdays);
		car.display();
//		scanner.nextLine();
		System.out.println("");

		TruckRental truck = new TruckRental(noOfdays);
		truck.display();

		
	}

}
