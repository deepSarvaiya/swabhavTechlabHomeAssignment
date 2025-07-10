package com.tss.model;

public class ElectricityBill {
	
	private int apartmentNumber;
	private double unitConsumed;
	private static float costPerUnit=5;
	private double billPrice ;
	
	public ElectricityBill(int apartmentNumber, double unitConsumed) {
		super();
		this.apartmentNumber = apartmentNumber;
		this.unitConsumed = unitConsumed;
	}
	
	public int getApartmentNumber() {
		return apartmentNumber;
	}
	public void setApartmentNumber(int apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}
	public double getUnitConsumed() {
		return unitConsumed;
	}

	public void setUnitConsumed(double unitConsumed) {
		if (unitConsumed < 0) {
			System.out.println("Invalid");
			
		}else {
			this.unitConsumed = unitConsumed;

		}
		
//		this.unitConsumed = unitConsumed;
	}
	public static float getCostPerUnit() {
		return costPerUnit;
	}
	public static void setCostPerUnit(float costPerUnit) {
		if (costPerUnit < 0) {
			System.out.println("Invalid");
			
		}else {
		ElectricityBill.costPerUnit = costPerUnit;
		}
	}
	
	public void calculateBill() {
		billPrice = unitConsumed * costPerUnit;
		System.out.println("Bill is: " + billPrice);
	}
	public double getBillPrice() {
		return billPrice;
	}
	public void setBillPrice(double billPrice) {
		this.billPrice = billPrice;
	}
	
	
}
