package com.tss.service;

import java.util.List;

import com.tss.dao.StudentDao;
import com.tss.model.Student;

public class StudentService {
	private StudentDao studentDao;

	public StudentService() {
		super();
		this.studentDao = new StudentDao();
	}
	
	public List<Student> readAllStudent()
	{
		return studentDao.readAllStudents();
	}
	
	
	public Student readOneStudent(int studentId) {
		return studentDao.readOneStudent(studentId);
	}
	
	public void updateStudentPercentage(int studentId , double percentage) {
		if(percentage<0) {
			System.out.println("percentage must not be negative");
		}
		studentDao.updateStudentPercentage(studentId,percentage);
		
	}
	
	public void deleteStudent(int studentId) {
		
		studentDao.deleteStudent(studentId);
		
	}
	
	
	
	public void addNewStudent(Student student){
		
		if(student.getAge()<18) {
			System.out.println("Age must be greater than 18");
			return;
		}
		if(student.getPercentage()<0) {
			System.out.println("percentage must not be negative");
		}
		
		 studentDao.addNewStudent(student);
	}

}