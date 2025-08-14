package com.tss.test;

import com.tss.controller.StudentController;
import java.util.Scanner;

public class StudentAppTest {

    public static void main(String[] args) {
        StudentController controller = new StudentController();
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n====== Student Management Menu ======");
            System.out.println("1. Add New Student");
            System.out.println("2. Read All Student Records");
            System.out.println("3. Read A student By Id");
            System.out.println("4. Update Students Percentage By Id");
            System.out.println("5. Delete a Student By Id");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    controller.addNewStudent();
                    break;
                case 2:
                    controller.readAllRecords();
                    break;
                case 3:
                	controller.readOneRecord();
                	break;
                case 4:
                	controller.updateStudentPercentage();
                	break;
                case 5:
                	controller.deleteStudent();
                	break;
                
                	
                case 6:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 1 to 6.");
            }
        }
    }
}
