package com.tss.assignment.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.tss.assignment.modal.AuthorComparator;
import com.tss.assignment.modal.Book;
import com.tss.assignment.modal.TitleComparator;

public class BookTest {
    public static void main(String[] args) {
        List<Book> bookList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n========= Book Management =========");
            System.out.println("1. Add a new book");
            System.out.println("2. Issue a book by ID");
            System.out.println("3. Display all available books");
            System.out.println("4. Display all issued books");
            System.out.println("5. Return a book");
            System.out.println("6. Sort Books");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    bookList.add(new Book(id, title, author));
                    System.out.println("Book added successfully.");
                    break;

                case 2:
                    System.out.print("Enter Book ID to issue: ");
                    int issueId = sc.nextInt();
                    boolean foundIssue = false;
                    for (Book b : bookList) {
                        if (b.getId() == issueId && !b.isIssued()) {
                            b.setIssued(true);
                            foundIssue = true;
                            System.out.println("Book issued successfully.");
                            break;
                        }
                    }
                    if (!foundIssue)
                        System.out.println("Book not found or already issued.");
                    break;

                case 3:
                    System.out.println("\nAvailable Books:");
                    for (Book b : bookList) {
                        if (!b.isIssued())
                            System.out.println(b);
                    }
                    break;

                case 4:
                    System.out.println("\nIssued Books:");
                    for (Book b : bookList) {
                        if (b.isIssued())
                            System.out.println(b);
                    }
                    break;

                case 5:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    boolean foundReturn = false;
                    for (Book b : bookList) {
                        if (b.getId() == returnId && b.isIssued()) {
                            b.setIssued(false);
                            foundReturn = true;
                            System.out.println("Book returned successfully.");
                            break;
                        }
                    }
                    if (!foundReturn)
                        System.out.println("Invalid book ID or book is not issued.");
                    break;

                case 6:
                    System.out.println("Sort Books By:");
                    System.out.println("1. Ascending by Author");
                    System.out.println("2. Descending by Title");
                    int sortOption = sc.nextInt();
                    if (sortOption == 1) {
                        Collections.sort(bookList, new AuthorComparator());
                        System.out.println("Books sorted by Author (Ascending):");
                    } else if (sortOption == 2) {
                        Collections.sort(bookList, new TitleComparator());
                        System.out.println("Books sorted by Title (Descending):");
                    } else {
                        System.out.println("Invalid sort option.");
                        break;
                    }
                    for (Book b : bookList)
                        System.out.println(b);
                    break;

                case 0:
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        sc.close();
    }
}