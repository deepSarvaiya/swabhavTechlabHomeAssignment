package com.tss.modelInheritance.test;

import com.tss.modelInheritance.model.ElectricCar;

public class ElectricCarTest {

	public static void main(String[] args) {
		ElectricCar car = new ElectricCar(150000, 2004, "0000", 4, 70);
		car.displayInfo();
		car.displayBetteryStatus();
		car.lockDoors();
		car.unlockDoors();
		

	}
}
