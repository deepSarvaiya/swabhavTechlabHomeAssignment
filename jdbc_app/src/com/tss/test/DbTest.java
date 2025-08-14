package com.tss.test;

import com.tss.database.Database;
import com.tss.model.Student;

public class DbTest {

	public static void main(String[] args) {
		
		Database studentDatabase = new Database();
//		
//		Student student = new Student(22,121,25,85.00,"hemlii");
//		
//		
//		studentDatabase.connect();
//		studentDatabase.addStudent(student);
//		studentDatabase.connect();
		studentDatabase.printDatabaseMetadata();             
		studentDatabase.printTableMetadata("students"); 

	}
}
