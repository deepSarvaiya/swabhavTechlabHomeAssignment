package com.tss.creational.factory.test;

import java.util.Scanner;

import com.tss.creational.factory.model.Vehicle;
import com.tss.creational.factory.model.VehicleFactory;
import com.tss.creational.factory.model.VehicleType;

public class VehicleTest {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Vehicle vehicle = null;
		System.out.print(
				"Enter Your Vehicle Type:\n 1. Two Wheeler \n 2. Four Wheeler \n 3. Heavy vehicle\n Enter Your Choice: ");
		int choice = scanner.nextInt();

		if (choice == 1) {
			vehicle = VehicleFactory.getVehicle(VehicleType.TWOWHEELER);
			System.out.println("TwoWheeler Plate: " + vehicle.generateLicensePlate());
			return;
		}
		if (choice == 2) {
			vehicle = VehicleFactory.getVehicle(VehicleType.FOURWHEELER);
			System.out.println("FourWheeler Plate: " + vehicle.generateLicensePlate());
			return;
		}
		if (choice == 3) {
			vehicle = VehicleFactory.getVehicle(VehicleType.HEAVYVEHICLE);
			System.out.println("HeavyVehicle Plate: " + vehicle.generateLicensePlate());
			return;
		}
		System.out.println("Enter Valid Things!");
		return;
	}
}
