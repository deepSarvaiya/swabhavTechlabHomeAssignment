package com.foodordering;

import java.util.Scanner;
import com.foodordering.admin.IAdminService;
import com.foodordering.admin.AdminServiceImpl;
import com.foodordering.customer.ICustomerService;
import com.foodordering.customer.CustomerServiceImpl;

public class main {
	private static final Scanner sc = new Scanner(System.in);
	private static final IAdminService adminService = new AdminServiceImpl();
	private static final ICustomerService customerService = new CustomerServiceImpl();

	public static void main(String[] args) {
		showMainMenu();
	}

	private static void showMainMenu() {
		int choice;
		do {
			System.out.println("\n====== Welcome to Food Ordering App ======");
			System.out.println("1. Login as Admin");
			System.out.println("2. Customer Section (Login / Register)");
			System.out.println("3. Exit");
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();

			switch (choice) {
			case 1 -> handleAdminLogin();
			case 2 -> handleCustomerSection();
			case 3 -> System.out.println("Exiting... Thank you!");
			default -> System.out.println("Invalid choice. Try again.");
			}
		} while (choice != 3);
	}

	private static void handleAdminLogin() {
		sc.nextLine();
		System.out.print("Enter admin username: ");
		String username = sc.nextLine();
		System.out.print("Enter admin password: ");
		String password = sc.nextLine();

		if (adminService.login(username, password)) {
			System.out.println("Welcome In the Admin Panel");
			adminService.showAdminDashboard();
		} else {
			System.out.println("Invalid admin credentials. Try again.");
		}
	}

	private static void handleCustomerSection() {
		int choice;
		do {
			System.out.println("\n--- Customer Section ---");
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Back to Main Menu");
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();

			switch (choice) {
			case 1 -> customerService.login();
			case 2 -> customerService.register();
			case 3 -> System.out.println("Returning to main menu...");
			default -> System.out.println("Invalid choice. Try again.");
			}
		} while (choice != 3);
	}
}
