package com.tss.test;

import com.tss.model.Customer;
import java.util.function.*;
import java.util.*;

public class CustomerTest {

    public static void main(String[] args) {

        Supplier<List<Customer>> customerSupplier = () -> Arrays.asList(
            new Customer("a", 28, 500000, 1300),
            new Customer("B", 30, 200000, 1300),
            new Customer("c", 20, 30000, 700),
            new Customer("D", 25, 24000, 680),
            new Customer("E", 22, 26000, 800)
        );

        Predicate<Customer> isEligible = c ->
                c.getAge() >= 21 &&
                c.getIncome() >= 25000 &&
                c.getCreditScore() >= 650;

        Function<Customer, Double> calculateLoanAmount = c ->
                c.getIncome() * (c.getCreditScore() / 1000.0);

        Consumer<Customer> approvalMessage = c -> {
            double loanAmount = calculateLoanAmount.apply(c);
            System.out.println("Loan Approved for " + c.getName());
            System.out.println("Eligible Amount: ₹" + loanAmount);
            System.out.println("-------------------------------------------------------------------");
        };

        List<Customer> customers = customerSupplier.get();

        System.out.println("-------------- Loan Approval Result --------");
        customers.stream()
                .filter(isEligible)
                .forEach(approvalMessage);
    }
}
