package com.tss.modelInheritance.model;

public class CarRental extends RentalVehicle {

	public CarRental(int numberOfDays) {
		super("6060", 600 , "cng", numberOfDays);
	}

	public void calculate() {
		System.out.print(numberOfDays * rentPerday); }
	
	
	public void display() {
		super.display();
		System.out.println("CarRent:");

		calculate();
	}
}
