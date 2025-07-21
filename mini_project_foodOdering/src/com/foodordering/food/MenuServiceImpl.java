package com.foodordering.food;

import com.foodordering.model.FoodItem;
import com.foodordering.util.Serializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class MenuServiceImpl implements IMenuService {
    private static final String MENU_FILE_GU = "menu_gujarati.ser";
    private static final String MENU_FILE_PU = "menu_punjabi.ser";
    private static final String MENU_FILE_CH = "menu_chinese.ser";
    private static final String MENU_FILE_SO = "menu_southindian.ser";
    private List<FoodItem> menu;
    private String currentMenuType;
    private final Scanner sc = new Scanner(System.in);

    private static double discountPercentage = 10.0; // default 10%
    private static double discountMinOrder = 500.0; // default min order for discount

    private static final Map<String, Double> COUPONS = new HashMap<>() {{
        put("SAVE10", 10.0);
        put("FOOD20", 20.0);
        put("LUNCH15", 15.0);
        put("DINNER5", 5.0);
    }};
    private static final String[] COUPON_CODES = {"SAVE10", "FOOD20", "LUNCH15", "DINNER5"};
    private static final Random random = new Random();

    public static String getRandomCoupon() {
        int idx = random.nextInt(COUPON_CODES.length);
        String code = COUPON_CODES[idx];
        return code + " (" + COUPONS.get(code) + "% OFF)";
    }

    public static double getCouponDiscount(String code) {
        return COUPONS.getOrDefault(code, 0.0);
    }

    private String getMenuFile(String menuType) {
        return switch (menuType) {
            case "GU" -> MENU_FILE_GU;
            case "PU" -> MENU_FILE_PU;
            case "CH" -> MENU_FILE_CH;
            case "SO" -> MENU_FILE_SO;
            default -> MENU_FILE_GU;
        };
    }

    public String menuTypePrompt() {
        System.out.println("Select Menu Type:");
        System.out.println("1. Gujarati");
        System.out.println("2. Punjabi");
        System.out.println("3. Chinese");
        System.out.println("4. South Indian");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        return switch (choice) {
            case 1 -> "GU";
            case 2 -> "PU";
            case 3 -> "CH";
            case 4 -> "SO";
            default -> "GU";
        };
    }

    public void loadMenu(String menuType) {
        menu = Serializer.deserialize(getMenuFile(menuType));
        if (menu == null) menu = new ArrayList<>();
        currentMenuType = menuType;
    }

    private void saveMenu() {
        Serializer.serialize(menu, getMenuFile(currentMenuType));
    }

    private String generateId(String menuType) {
        int max = 0;
        for (FoodItem item : menu) {
            if (item.getId().startsWith(menuType)) {
                try {
                    int num = Integer.parseInt(item.getId().substring(2));
                    if (num > max) max = num;
                } catch (Exception ignored) {}
            }
        }
        return menuType + (max + 1);
    }

    public MenuServiceImpl() {
        // Default: load Gujarati menu
        loadMenu("GU");
    }

    @Override
    public List<FoodItem> getMenu() {
        return menu;
    }

    @Override
    public void viewMenu() {
        String menuType = menuTypePrompt();
        loadMenu(menuType);
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║                 FOOD MENU              ║");
        System.out.println("╚════════════════════════════════════════╝");

        if (menu.isEmpty()) {
            System.out.println(" No food items available.");
            return;
        }

        System.out.printf("%-7s %-35s %s\n", "ID", "Name", "Price (₹)");
        System.out.println("------------------------------------------------------");

        for (FoodItem item : menu) {
            System.out.printf("%-7s %-35s ₹%.2f\n", item.getId(), item.getName(), item.getPrice());
        }
    }

    @Override
    public void removeItem() {
        String menuType = menuTypePrompt();
        loadMenu(menuType);
        viewMenu();
        System.out.print("\nEnter item ID to remove: ");
        String id = sc.nextLine();
        boolean removed = menu.removeIf(item -> item.getId().equals(id));
        if (removed) {
            saveMenu();
            System.out.println("\nItem removed successfully.");
        } else {
            System.out.println("\nItem not found. Please try again.");
        }
    }

    public static double getDiscountPercentage() {
        return discountPercentage;
    }

    public static double getDiscountMinOrder() {
        return discountMinOrder;
    }

    public void setDiscount(double percent, double minOrder) {
        discountPercentage = percent;
        discountMinOrder = minOrder;
        System.out.println("\nDiscount set to " + percent + "% on orders above ₹" + minOrder + ".");
    }

    @Override
    public void addItem() {
        String menuType = menuTypePrompt();
        loadMenu(menuType);
        System.out.println("\n╔════════════════════════════╗");
        System.out.println("║       ADD NEW FOOD ITEM    ║");
        System.out.println("╚════════════════════════════╝");

        System.out.print("Enter Food Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        String id = generateId(menuType);
        FoodItem newItem = new FoodItem(id, name, price, menuType);
        menu.add(newItem);

        saveMenu();
        System.out.println("\nItem added successfully!");
    }

    @Override
    public void setDiscountPercentage(double percent) {
        setDiscount(percent, discountMinOrder);
    }
}
