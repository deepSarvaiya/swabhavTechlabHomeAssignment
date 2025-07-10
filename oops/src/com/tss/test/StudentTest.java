package com.tss.test;

import java.util.Scanner;

import com.tss.model.Student;

public class StudentTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("For Student 1");
		Student student1 = new Student();
		get_info(student1,scanner);
		
		System.out.println("For Student 2");
		Student student2 = new Student();
		get_info(student2,scanner);
		
		
	}

	private static void get_info(Student student,Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("Enter Roll Number of Student:");
		int rollnumber = scanner.nextInt();
		student.setRollnumber(rollnumber);
		
		System.out.println("Enter Name of Student: ");
		String name = scanner.next();
		student.setname(name);
		
		System.out.println("Enter Age Of Student: ");
		int age = scanner.nextInt();
		student.setAge(age);
		
		System.out.println("Enter Subject 1 Marks: ");
		int subject1Marks = scanner.nextInt();
		
		System.out.println("Enter Subject 2 Marks: ");
		int subject2Marks = scanner.nextInt();
		
		System.out.println("Enter Subject 3 Marks: ");
		int subject3Marks = scanner.nextInt();
		student.setMarks(subject1Marks, subject2Marks, subject3Marks);
		
		student.calculate_average();
		student.display();
	}

}
