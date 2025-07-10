package com.tss.creational.factory.model;

public class FourWheeler implements Vehicle{
	@Override
	public String generateLicensePlate() {
		int uniqueNumber = (int)(Math.random() * 9000) + 1000;        
		return "TW" + uniqueNumber;
	}
}
