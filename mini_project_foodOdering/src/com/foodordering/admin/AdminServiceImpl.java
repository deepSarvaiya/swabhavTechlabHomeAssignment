package com.foodordering.admin;

import java.util.Scanner;

import com.foodordering.delivery.DeliveryService;
import com.foodordering.food.IMenuService;
import com.foodordering.food.MenuServiceImpl;

public class AdminServiceImpl implements IAdminService {
	private static final String ADMIN_USERNAME = "deep";
	private static final String ADMIN_PASSWORD = "deep1234";
	private final Scanner sc = new Scanner(System.in);
	private final IMenuService menuService = new MenuServiceImpl();

	@Override
	public boolean login(String username, String password) {
		return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
	}

	@Override
	public void showAdminDashboard() {
		int choice;
		do {
			System.out.println("\n--- Admin Dashboard ---");
			System.out.println("1. Manage Menu");
			System.out.println("2. Manage Discounts");
			System.out.println("3. Manage Delivery Agents");
			System.out.println("4. Logout");
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();

			switch (choice) {
			case 1 -> handleMenuManagement();
			case 2 -> handleDiscountManagement();
			case 3 -> handleAgentManagement();
			case 4 -> System.out.println("Logging out...");
			default -> System.out.println("Invalid choice.");
			}
		} while (choice != 4);
	}

	private void handleMenuManagement() {
		int choice;
		do {
			System.out.println("\n--- Manage Menu ---");
			System.out.println("1. View Menu");
			System.out.println("2. Add Food Item");
			System.out.println("3. Remove Food Item");
			System.out.println("4. Back");
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();

			switch (choice) {
			case 1 -> menuService.viewMenu();
			case 2 -> menuService.addItem();
			case 3 -> menuService.removeItem();
			case 4 -> System.out.println("Returning to admin dashboard...");
			default -> System.out.println("Invalid option.");
			}
		} while (choice != 4);
	}
	
	private void handleDiscountManagement() {
	    int choice;
	    do {
	        System.out.println("\n======= Manage Discount =======");
	        System.out.printf("1. View Current Discount (%.2f%% on orders above ₹%.2f)\n", MenuServiceImpl.getDiscountPercentage(), MenuServiceImpl.getDiscountMinOrder());
	        System.out.println("2. Edit Discount");
	        System.out.println("3. Back");
	        System.out.print("Enter your choice: ");
	        choice = sc.nextInt();

	        switch (choice) {
	            case 1 -> {
	                double currentDiscount = MenuServiceImpl.getDiscountPercentage();
	                double minOrder = MenuServiceImpl.getDiscountMinOrder();
	                System.out.printf("Current Discount: %.2f%% (applied on orders above ₹%.2f)%n", currentDiscount, minOrder);
	            }
	            case 2 -> {
	                System.out.print("Enter new discount percentage: ");
	                double newDiscount = sc.nextDouble();
	                System.out.print("Enter new minimum order value for discount: ");
	                double newMinOrder = sc.nextDouble();
	                if (newDiscount >= 0 && newDiscount <= 100 && newMinOrder >= 0) {
	                    menuService.setDiscount(newDiscount, newMinOrder);
	                } else {
	                    System.out.println("Invalid input. Please enter valid values.");
	                }
	            }
	            case 3 -> System.out.println("Returning to admin dashboard...");
	            default -> System.out.println("Invalid option.");
	        }
	    } while (choice != 3);
	}

	

	private void handleAgentManagement() {
	    DeliveryService deliveryService = new DeliveryService(); 
	    int choice;
	    do {
	        System.out.println("\n--- Manage Delivery Agents ---");
	        System.out.println("1. View Agents");
	        System.out.println("2. Add Agent");
	        System.out.println("3. Remove Agent");
	        System.out.println("4. Back");
	        System.out.print("Enter your choice: ");
	        choice = sc.nextInt();
	        sc.nextLine(); // consume newline

	        switch (choice) {
	            case 1 -> deliveryService.viewAgents();
	            case 2 -> {
	                System.out.print("Enter agent name to add: ");
	                String name = sc.nextLine();
	                deliveryService.addAgent(name);
	            }
	            case 3 -> {
	                System.out.print("Enter agent name to remove: ");
	                String name = sc.nextLine();
	                deliveryService.removeAgent(name);
	            }
	            case 4 -> System.out.println("Returning to dashboard...");
	            default -> System.out.println("Invalid option.");
	        }
	    } while (choice != 4);
	}

}