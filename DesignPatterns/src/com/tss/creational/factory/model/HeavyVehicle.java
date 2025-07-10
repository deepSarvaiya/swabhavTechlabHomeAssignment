package com.tss.creational.factory.model;

public class HeavyVehicle implements Vehicle{
	@Override
	public String generateLicensePlate() {
		int uniqueNumber = (int)(Math.random() * 900000) + 100000;        
		return "TW" + uniqueNumber;
	}
}
