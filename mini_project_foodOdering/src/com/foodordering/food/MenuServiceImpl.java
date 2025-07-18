package com.foodordering.food;

import com.foodordering.model.FoodItem;
import com.foodordering.util.Serializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuServiceImpl implements IMenuService {
    private static final String MENU_FILE = "menu.ser";
    private List<FoodItem> menu;
    private final Scanner sc = new Scanner(System.in);

    private static int nextId = 1;
    private static double discountPercentage = 10.0; // default 10%

    private int generateId() {
        return nextId++;
    }

    public MenuServiceImpl() {
        menu = Serializer.deserialize(MENU_FILE);
        if (menu == null || menu.isEmpty()) {
            menu = new ArrayList<>();

            // Default 10 food items
            menu.add(new FoodItem(generateId(), "Khaman Dhokla (Gujarati)", 50));
            menu.add(new FoodItem(generateId(), "Undhiyu (Gujarati)", 130));
            menu.add(new FoodItem(generateId(), "Thepla with Pickle (Gujarati)", 40));
            menu.add(new FoodItem(generateId(), "Dal Dhokli (Gujarati)", 90));
            menu.add(new FoodItem(generateId(), "Chole Bhature (Punjabi)", 120));
            menu.add(new FoodItem(generateId(), "Rajma Chawal (Punjabi)", 110));
            menu.add(new FoodItem(generateId(), "Paratha (Punjabi)", 140));
            menu.add(new FoodItem(generateId(), "Paneer Tikka Masala (Punjabi)", 150));
            menu.add(new FoodItem(generateId(), "Lassi (Punjabi)", 50));
            menu.add(new FoodItem(generateId(), "Basundi (Gujarati Dessert)", 60));

            Serializer.serialize(menu, MENU_FILE);
        }
    }

    @Override
    public List<FoodItem> getMenu() {
        return menu;
    }

    @Override
    public void viewMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║                 FOOD MENU              ║");
        System.out.println("╚════════════════════════════════════════╝");

        if (menu.isEmpty()) {
            System.out.println(" No food items available.");
            return;
        }

        System.out.printf("%-5s %-35s %s\n", "ID", "Name", "Price (₹)");
        System.out.println("------------------------------------------------------");

        for (FoodItem item : menu) {
            System.out.printf("%-5d %-35s ₹%.2f\n", item.getId(), item.getName(), item.getPrice());
        }
    }

    @Override
    public void removeItem() {
        viewMenu();
        System.out.print("\nEnter item ID to remove: ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean removed = menu.removeIf(item -> item.getId() == id);
        if (removed) {
            Serializer.serialize(menu, MENU_FILE);
            System.out.println("\nItem removed successfully.");
        } else {
            System.out.println("\nItem not found. Please try again.");
        }
    }

    public static double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double percent) {
        discountPercentage = percent;
        System.out.println("\nDiscount set to " + percent + "% on orders above ₹500.");
    }

    @Override
    public void addItem() {
        System.out.println("\n╔════════════════════════════╗");
        System.out.println("║       ADD NEW FOOD ITEM    ║");
        System.out.println("╚════════════════════════════╝");

        System.out.print("Enter Food Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        int id = generateId();
        FoodItem newItem = new FoodItem(id, name, price);
        menu.add(newItem);

        Serializer.serialize(menu, MENU_FILE);
        System.out.println("\nItem added successfully!");
    }
}
