package com.foodordering.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.foodordering.delivery.DeliveryService;
import com.foodordering.food.MenuServiceImpl;
import com.foodordering.model.CartItem;
import com.foodordering.model.FoodItem;
import com.foodordering.model.Order;
import com.foodordering.payment.PaymentProcessor;

public class OrderManager {
    private static final List<CartItem> cart = new ArrayList<>();

    public static void addToCart(int itemId, int quantity) {
        List<FoodItem> menu = new MenuServiceImpl().getMenu();

        for (FoodItem item : menu) {
            if (item.getId() == itemId) {
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

    public static void removeFromCart(int itemId) {
        boolean removed = cart.removeIf(cartItem -> cartItem.getItem().getId() == itemId);
        if (removed) {
            System.out.println("\n Item removed from cart.");
        } else {
            System.out.println("\n Item not found in cart.");
        }
    }

    public static void placeOrder(String username) {
        if (cart.isEmpty()) {
            System.out.println("\n Cannot place order. Cart is empty.");
            return;
        }

        double total = cart.stream().mapToDouble(CartItem::getSubtotal).sum();
        PaymentProcessor processor = new PaymentProcessor();

        if (processor.processPayment(total)) {
            double discount = 0;
            double finalAmount = total;
            String paymentMode = "Regular";

            if (total > 500) {
                discount = total * MenuServiceImpl.getDiscountPercentage() / 100.0;
                finalAmount = total - discount;
                paymentMode = "Discounted";
            }

            DeliveryService deliveryService = new DeliveryService();
            String deliveryAgent = deliveryService.assignAgent();

            Map<FoodItem, Integer> orderedItems = new HashMap<>();
            for (CartItem cartItem : cart) {
                orderedItems.put(cartItem.getItem(), cartItem.getQuantity());
            }

            Order order = new Order(orderedItems, total, discount, finalAmount, paymentMode, deliveryAgent);
            order.printInvoice();
            cart.clear();
        }
    }
}
