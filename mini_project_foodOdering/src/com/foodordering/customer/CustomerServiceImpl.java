package com.foodordering.customer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.foodordering.food.IMenuService;
import com.foodordering.food.MenuServiceImpl;
import com.foodordering.model.Customer;
import com.foodordering.order.OrderManager;
import com.foodordering.util.Serializer;

public class CustomerServiceImpl implements ICustomerService {
	private static final String CUSTOMER_FILE = "customers.ser";
	private final Scanner sc = new Scanner(System.in);
	private Map<String, Customer> customerMap;

	public CustomerServiceImpl() {
		customerMap = Serializer.deserialize(CUSTOMER_FILE);
		if (customerMap == null)
			customerMap = new HashMap<>();
	}

	@Override
	public void register() {
//        sc.nextLine();
		System.out.print("Enter full name: ");
		String name = sc.nextLine();
		System.out.print("Enter phone: ");
		String phone = sc.nextLine();
		System.out.print("Enter email: ");
		String email = sc.nextLine();
		System.out.print("Enter address: ");
		String address = sc.nextLine();
		System.out.print("Choose username: ");
		String username = sc.nextLine();

		if (customerMap.containsKey(username)) {
			System.out.println("Username already exists. Try login.");
			return;
		}

		System.out.print("Choose password: ");
		String password = sc.nextLine();

		Customer customer = new Customer(name, phone, email, address, username, password);
		customerMap.put(username, customer);
		Serializer.serialize(customerMap, CUSTOMER_FILE);
		System.out.println("Registration successful. Please login.");
	}

	@Override
	public void login() {
//        sc.nextLine(); 
		System.out.print("Enter username: ");
		String username = sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.nextLine();

		Customer customer = customerMap.get(username);
		if (customer != null && customer.getPassword().equals(password)) {
			System.out.println("Login successful. Welcome, " + customer.getName() + "!");
			customerDashboard(username); // Go to dashboard
		} else {
			System.out.println("Invalid credentials.");
		}
	}

	@Override
	public void customerDashboard(String username) {
		IMenuService menuService = new MenuServiceImpl();

		while (true) {
			System.out.println("\n=== Customer Dashboard ===");
			System.out.println("1. View Menu");
			System.out.println("2. Add Item to Cart");
			System.out.println("3. View Cart");
			
			System.out.println("4. Place Order");
			System.out.println("5. Remove Item from Cart");

			System.out.println("6. Logout");
			System.out.print("Choose option: ");

			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				menuService.viewMenu();
				break;

			case 2:
				System.out.println("Select Menu Type to Add Item From:");
				String menuTypeAdd = ((MenuServiceImpl) menuService).menuTypePrompt();
				((MenuServiceImpl) menuService).loadMenu(menuTypeAdd);
				menuService.viewMenu();
				System.out.print("Enter food item ID to add to cart: ");
				String itemId = sc.nextLine();
				System.out.print("Enter Quantity: ");
				int quantity = sc.nextInt();
				sc.nextLine();
				OrderManager.addToCart(itemId, quantity, menuTypeAdd);
				break;

			case 3:
				OrderManager.viewCart();
				break;

			case 4:
				OrderManager.placeOrder(username);
				break;
			case 5:
				System.out.println("Select Menu Type to Remove Item From:");
				String menuTypeRm = ((MenuServiceImpl) menuService).menuTypePrompt();
				((MenuServiceImpl) menuService).loadMenu(menuTypeRm);
				menuService.viewMenu();
				System.out.print("Enter food item ID to Remove from cart: ");
				String itemRmId = sc.nextLine();
				System.out.print("Enter quantity to remove: ");
				int qtyToRemove = sc.nextInt();
				sc.nextLine();
				OrderManager.removeFromCart(itemRmId, qtyToRemove);
				break;
			case 6:
				System.out.println("Logged out successfully.");
				return;

			default:
				System.out.println("Invalid option. Try again.");
			}
		}
	}

}
