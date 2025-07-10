package com.tss.test;

import java.util.Scanner;
import com.tss.model.DigitalWaterDispenser;
import com.tss.exception.OverfillException;
import com.tss.exception.InsufficientWaterException;

public class DigitalWaterDispenserTest {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DigitalWaterDispenser dispenser = new DigitalWaterDispenser();

        boolean running = true;

        while (running) {
            System.out.println("\n----- Digital Water Dispenser -----");
            System.out.println("1. Fill Water");
            System.out.println("2. Dispense Water");
            System.out.println("3. Show Current Water Level");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter amount of water to fill (in liters): ");
                    int fillAmount = scanner.nextInt();
                    try {
                        dispenser.fillWater(fillAmount);
                        System.out.println("Successfully filled " + fillAmount + " liters of water.");
                    } catch (OverfillException e) {
                        System.out.println(e.getMessage());
                    }
                    finally {
                    	System.out.println("Current Water Level: " + dispenser.getCurrentWaterLevel() + " liters");
                    }
                    break;

                case 2:
                    System.out.print("Enter amount of water to dispense (in liters): ");
                    int dispenseAmount = scanner.nextInt();
                    try {
                        dispenser.dispenseWater(dispenseAmount);
                        System.out.println("Successfully dispensed " + dispenseAmount + " liters of water.");
                    } catch (InsufficientWaterException e) {
                        System.out.println(e.getMessage());
                    }
                    finally {
                    	System.out.println("Current Water Level: " + dispenser.getCurrentWaterLevel() + " liters");
                    }
                    break;

                case 3:
                    System.out.println("Current Water Level: " + dispenser.getCurrentWaterLevel() + " liters");
                    break;

                case 4:
                    running = false;
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1-4.");
                    break;
            }
        }

        scanner.close();
    }
}
