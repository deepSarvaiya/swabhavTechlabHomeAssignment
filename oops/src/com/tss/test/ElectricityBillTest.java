package com.tss.test;

import java.util.Scanner;

import com.tss.model.ElectricityBill;

public class ElectricityBillTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the Number of bill to be created: ");
		int noOfBills=scanner.nextInt();
		
		ElectricityBill[] electricityBill = new ElectricityBill[noOfBills];
		
		int noOfCreatedBill=0; 
		boolean running = true;
		while (running) {
			int choice = 0;
			if (noOfCreatedBill < noOfBills) {
				System.out.println("\nChoose an option:");
				System.out.println("1. Create Bill");
				System.out.println("2. Display Bill");
				System.out.println("3. Change The Cost Per Unit");
			
				System.out.println("7. Exit");

				System.out.print("Enter your choice: ");
				choice = scanner.nextInt();

			} else {
				System.out.println("\nChoose an option:");
				System.out.println("2. display Bill");
				System.out.println("3. Change The Cost Per Unit");
				System.out.println("7. Exit");
				System.out.print("Enter your choice: ");
				choice = scanner.nextInt();
				if(choice == 1) {
					
					System.out.println("Please Enter Valid Number!");
					continue;
				}
			}


			switch (choice) {
			case 1:
				readBillDetails(electricityBill,scanner,noOfCreatedBill);
				noOfCreatedBill++;
				break;

			case 2:
				System.out.println("Enter Apartment Number : ");
				int apratmentNumber = scanner.nextInt();
				ElectricityBill eleBill = findApartmentNumber(electricityBill,apratmentNumber);
				if(eleBill != null) {
					
					System.out.println("Bill is: "+ eleBill.getBillPrice()); 
				}
			
				break;

			case 3:
				System.out.println("enter New Cost Of Unit");
				float unitPrice = scanner.nextFloat();
				ElectricityBill.setCostPerUnit(unitPrice);
				break;

			
			case 7:
				System.out.println("Exiting...");
				running = false;
				break;
			default:
				System.out.println("Invalid choice. Try again.");
			}
		}

		
	}
	public static ElectricityBill findApartmentNumber(ElectricityBill[] electricityBill, int apartmentNumber) {
		for (ElectricityBill acc : electricityBill) {
			if (acc != null && acc.getApartmentNumber() == apartmentNumber) {
				return acc;
			}
		}
		return null;
	}

	private static void readBillDetails(ElectricityBill[] electricityBill, Scanner scanner, int noOfCreatedBill) {
		// TODO Auto-generated method stub
		
		
		System.out.println("Enter a Apartment Number: ");
		int apartmentNumber = scanner.nextInt();
		
		System.out.println("Enter a Consumed Unit: ");
		double consumedUnit = scanner.nextDouble();
		
		electricityBill[noOfCreatedBill] = new ElectricityBill(apartmentNumber,consumedUnit);
		
		
		electricityBill[noOfCreatedBill].calculateBill();
		
		
	}

}
