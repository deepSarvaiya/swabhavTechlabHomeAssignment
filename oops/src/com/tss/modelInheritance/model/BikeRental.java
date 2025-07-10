package com.tss.modelInheritance.model;

public class BikeRental extends RentalVehicle {

	public BikeRental(int numberOfDays) {
		super("6050", 500 , "petrol", numberOfDays);
	}

	public void calculate() {

		if (numberOfDays > 5) {
			System.out.print((numberOfDays * rentPerday)*0.9);
			return;
		}
		System.out.print(numberOfDays * rentPerday);
	}

	
	public void display() {
		super.display();
		System.out.println("BikeRent:");

		calculate();
	}
}
