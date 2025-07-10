package com.tss.modelInheritance.model;

public class TruckRental extends RentalVehicle{
	

	public TruckRental(int numberOfDays) {
		super("6070", 800 , "diesal", numberOfDays);
	}

	public void calculate() {
		System.out.print((numberOfDays * rentPerday)+500); }
	
	public void display() {
		super.display();
		System.out.print("TruckRent:");
		calculate();
		
	}
		
}
