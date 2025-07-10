package com.tss.test;

import com.tss.model.Bill;
import java.util.function.Function;
import java.util.function.Supplier;

public class BillTest {

    public static void main(String[] args) {

        String itemName = "Mouse";
        double basePrice = 1000.0;

        Supplier<Double> shippingSupplier = () -> 50.0;

        Function<Double, Bill> billFunction = price -> {
            double tax = price * 0.18;
            double shipping = shippingSupplier.get();
            double totalAmount = price + tax + shipping;

            return new Bill(itemName, price, tax, shipping, totalAmount);
        };

        Bill finalBill = billFunction.apply(basePrice);
        System.out.println(finalBill);
    }
}
