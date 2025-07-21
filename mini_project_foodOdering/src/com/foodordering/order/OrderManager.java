package com.foodordering.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.foodordering.delivery.DeliveryService;
import com.foodordering.food.MenuServiceImpl;
import com.foodordering.model.CartItem;
import com.foodordering.model.FoodItem;
import com.foodordering.model.Order;
import com.foodordering.payment.PaymentProcessor;

public class OrderManager {
    private static final List<CartItem> cart = new ArrayList<>();

    public static void addToCart(String itemId, int quantity, String menuType) {
        MenuServiceImpl menuService = new MenuServiceImpl();
        menuService.loadMenu(menuType);
        List<FoodItem> menuItems = menuService.getMenu();
        for (FoodItem item : menuItems) {
            if (item.getId().equals(itemId)) {
                cart.add(new CartItem(item, quantity));
                System.out.println("\nItem added to cart.");
                return;
            }
        }
        System.out.println("\n Invalid item ID.");
    }

    public static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("\n[!] Cart is empty.");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║                      YOUR CART                 ║");
        System.out.println("╠════════════════════════════════════════════════╣");

        double total = 0;
        for (CartItem cartItem : cart) {
            String itemLine = String.format("║ %-40s ║", cartItem.toString());
            System.out.println(itemLine);
            total += cartItem.getSubtotal();
        }

        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.printf ("║ %-30s ₹%10.2f ║%n", "Total Amount", total);
        System.out.println("╚════════════════════════════════════════════════╝");

        System.out.println("Use removeFromCart(itemId) to remove an item.\n");
    }

    public static void removeFromCart(String itemId, int quantity) {
        for (int i = 0; i < cart.size(); i++) {
            CartItem cartItem = cart.get(i);
            if (cartItem.getItem().getId().equals(itemId)) {
                if (quantity > cartItem.getQuantity()) {
                    System.out.println("\nYou have only " + cartItem.getQuantity() + " quantity in the cart.");
                    return;
                } else if (quantity == cartItem.getQuantity()) {
                    cart.remove(i);
                    System.out.println("\nAll quantities of the item removed from cart.");
                } else {
                    cartItem.setQuantity(cartItem.getQuantity() - quantity);
                    System.out.println("\n" + quantity + " quantity removed from cart. Remaining: " + cartItem.getQuantity());
                }
                return;
            }
        }
        System.out.println("\nItem not found in cart.");
    }

    public static void placeOrder(String username) {
        if (cart.isEmpty()) {
            System.out.println("\n Cannot place order. Cart is empty.");
            return;
        }

        double total = cart.stream().mapToDouble(CartItem::getSubtotal).sum();
        PaymentProcessor processor = new PaymentProcessor();

        //  random coupon to the user
        String randomCoupon = MenuServiceImpl.getRandomCoupon();
        System.out.println("\nHere is a coupon you can use: " + randomCoupon);
        System.out.print("Enter coupon code to apply (or press Enter to skip): ");
        Scanner sc = new Scanner(System.in);
        String couponCode = sc.nextLine().trim();
        double couponDiscountPercent = 0;
        if (!couponCode.isEmpty()) {
            couponDiscountPercent = MenuServiceImpl.getCouponDiscount(couponCode);
            if (couponDiscountPercent > 0) {
                System.out.println("Coupon applied: " + couponCode + " (" + couponDiscountPercent + "% OFF)");
            } else {
                System.out.println("Invalid coupon code. No coupon discount applied.");
                couponCode = "";
            }
        }

        // Calculate discounts before payment
        double regularDiscount = 0;
        double afterRegularDiscount = total;
        String paymentMode = "Regular";
        if (total >= MenuServiceImpl.getDiscountMinOrder()) {
            regularDiscount = total * MenuServiceImpl.getDiscountPercentage() / 100.0;
            afterRegularDiscount = total - regularDiscount;
            paymentMode = "Discounted";
        }
        double couponAmount = 0;
        if (couponDiscountPercent > 0) {
            couponAmount = afterRegularDiscount * couponDiscountPercent / 100.0;
        }
        double finalAmount = afterRegularDiscount - couponAmount;
        double totalDiscount = regularDiscount + couponAmount;

        // Show  before payment
        if (regularDiscount > 0)
            System.out.printf("Regular discount applied: ₹%.2f (%.1f%%)\n", regularDiscount, MenuServiceImpl.getDiscountPercentage());
        if (couponAmount > 0)
            System.out.printf("Coupon discount applied: ₹%.2f (%.1f%%)\n", couponAmount, couponDiscountPercent);
        if (totalDiscount > 0)
            System.out.printf("Total discount: ₹%.2f\n", totalDiscount);
        System.out.printf("Final amount to pay: ₹%.2f\n", finalAmount);

        if (processor.processPayment(finalAmount)) {
            DeliveryService deliveryService = new DeliveryService();
            String deliveryAgent = deliveryService.assignAgent();

            Map<FoodItem, Integer> orderedItems = new HashMap<>();
            for (CartItem cartItem : cart) {
                orderedItems.put(cartItem.getItem(), cartItem.getQuantity());
            }

            Order order = new Order(orderedItems, total, regularDiscount, couponAmount, finalAmount, paymentMode, couponCode, deliveryAgent);
            order.printInvoice();
            cart.clear();
        }
    }
}
