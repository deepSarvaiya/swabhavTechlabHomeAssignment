package com.tss.test;

import java.util.Scanner;
import com.tss.model.Book;

public class BookTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Book book = new Book();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Book Menu ===");
            System.out.println("1. Enter Book Info");
            System.out.println("2. Display Book Info");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    getInfo(book, scanner);
                    break;
                case 2:
                    book.display();
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void getInfo(Book book, Scanner scanner) {
        System.out.print("Enter Book Id: ");
        book.setBookid(scanner.nextInt());

        scanner.nextLine(); 

        System.out.print("Enter Book Name: ");
        book.setName(scanner.nextLine());

        System.out.print("Enter Book Author: ");
        book.setAuthor(scanner.nextLine());

        System.out.print("Enter Book Price: ");
        book.setPrice(scanner.nextInt());

        scanner.nextLine(); 

        System.out.print("Enter Book Publication: ");
        book.setPublication(scanner.nextLine());

        book.calculateDiscount();
        System.out.println("Book info saved successfully.");
    }
}
