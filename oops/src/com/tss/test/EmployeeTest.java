package com.tss.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.tss.model.Employee;

public class EmployeeTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Employee emp = new Employee(20,20,"deep","20-12-2005");
        boolean running = true;

        while (running) {
            System.out.println("\n=== Employee Details ===");
            System.out.println("1. Enter Employee Info");
            System.out.println("2. Display Employee Info");
            System.out.println("3. Calculate Bonus");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    getInfo(emp, scanner);
                    break;
                case 2:
                    emp.display();
                    break;
                case 3:
                    emp.calculateBonus();
                    System.out.println("Bonus added. New Salary: " + emp.getSalary());
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

 private static void getInfo(Employee emp, Scanner scanner) {
    System.out.print("Enter Employee ID: ");
    emp.setEmpid(scanner.nextInt());

    scanner.nextLine(); 

    System.out.print("Enter Employee Name: ");
    emp.setName(scanner.nextLine());

    System.out.print("Enter Salary: ");
    emp.salary(scanner.nextInt());

    scanner.nextLine();
    
    boolean validDate = false;
    String dateInput;

    while (!validDate) {
        System.out.print("Enter Joining Date (dd-MM-yyyy): ");
        dateInput = scanner.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate.parse(dateInput, formatter);
            emp.setJoiningDate(dateInput);
            validDate = true;
        } catch (Exception e) {
            System.out.println("Invalid  format enter in dd-MM-yyyy format.");
        }
    }

    System.out.println("Employee info saved successfully.");
}

}
