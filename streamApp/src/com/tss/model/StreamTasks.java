package com.tss.model;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class StreamTasks {

    static class Employee {
        int id;
        String name;
        double salary;
        String dept;

        Employee(int id, String name, double salary, String dept) {
            this.id = id;
            this.name = name;
            this.salary = salary;
            this.dept = dept;
        }

        public String getName() { return name; }
        public double getSalary() { return salary; }
        public String getDept() { return dept; }

        @Override
        public String toString() {
            return name + " (" + salary + ", " + dept + ")";
        }
    }

    static class Product {
        String name;
        double price;
        int quantity;

        Product(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }
    }

    public static void main(String[] args) {

        // 1: Sort Employees ---
        List<Employee> employees = Arrays.asList(
            new Employee(1, "aman", 5000, "HR"),
            new Employee(2, "baman", 7000, "IT"),
            new Employee(3, "chaman", 7000, "IT"),
            new Employee(4, "dhaman", 4000, "HR")
        );

        List<Employee> sortedEmployees = employees.stream()
            .sorted(Comparator.comparingDouble(Employee::getSalary).reversed()
            .thenComparing(Employee::getName))
            .collect(Collectors.toList());

        System.out.println("Task 1 - Sorted Employees:");
        sortedEmployees.forEach(System.out::println);

        // 2: Highest paid employee per department ---
        Map<String, Employee> highestPaid = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDept,
                Collectors.collectingAndThen(
                    Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
                    Optional::get)));

        System.out.println("\nTask 2 - Highest Paid by Department:");
        highestPaid.forEach((dept, emp) -> System.out.println(dept + ": " + emp));

        // 3: Group strings by starting letter ---
        List<String> words = List.of("apple", "banana", "apricot", "blueberry", "cherry");

        Map<Character, List<String>> groupedByFirstChar = words.stream()
            .collect(Collectors.groupingBy(s -> s.charAt(0)));

        System.out.println("\nTask 3 - Grouped by First Letter:");
        groupedByFirstChar.forEach((ch, list) -> System.out.println(ch + "=" + list));

        //  4: Map of starting letters to count 
        Map<Character, Long> countMap = words.stream()
            .collect(Collectors.groupingBy(s -> s.charAt(0), Collectors.counting()));

        System.out.println("\nTask 4 - Count by First Letter:");
        countMap.forEach((ch, count) -> System.out.println(ch + ": " + count));

        // 5: Extract all unique words (case-insensitive)
        List<String> sentences = List.of("Hello world", "hello Java", "Stream API");

        Set<String> uniqueWords = sentences.stream()
            .flatMap(s -> Arrays.stream(s.toLowerCase().split("\\s+")))
            .collect(Collectors.toCollection(TreeSet::new)); 

        System.out.println("\nTask 5 - Unique Words:");
        System.out.println(uniqueWords);

        // 6: Total bill using reduce 
        List<Product> products = List.of(
            new Product("Pen", 10.0, 3),
            new Product("Notebook", 50.0, 2),
            new Product("Pencil", 5.0, 10)
        );

        double totalBill = products.stream()
            .mapToDouble(p -> p.price * p.quantity)
            .reduce(0.0, Double::sum);

        System.out.println("\nTask 6 - Total Bill: ₹" + totalBill);

        // 7: Extract domains from emails 
        List<String> emails = List.of("deep@gmail.com", "abc@yahoo.com", "hemlii@gmail.com");

        Set<String> domains = emails.stream()
            .map(email -> email.substring(email.indexOf('@') + 1))
            .collect(Collectors.toSet());

        System.out.println("\nTask 7 - Email Domains:");
        System.out.println(domains);
    }
}
