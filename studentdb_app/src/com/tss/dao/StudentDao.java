package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tss.database.DBConnection;
import com.tss.model.Student;

public class StudentDao {

	private Connection connection = null;
	private Statement statement = null;

	public StudentDao() {
		this.connection = DBConnection.connect();
	}

	public List<Student> readAllStudents() {
		List<Student> students = new ArrayList<Student>();
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from students");

			while (result.next()) {
				Student student = new Student();
				student.setStudentId(result.getInt("studentid"));
				student.setAge(result.getInt("age"));
				student.setPercentage(result.getDouble("percentage"));
				student.setStudentName(result.getString("name"));
				student.setRollno(result.getInt("rollnumber"));
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}
	
	public Student readOneStudent(int studentId) {
	    Student student = null;
	    try {
	        String sql = "SELECT * FROM students WHERE studentid = ?";

	        PreparedStatement pstmt = connection.prepareStatement(sql);
	        pstmt.setInt(1, studentId);

	        ResultSet result = pstmt.executeQuery();

	        if (result.next()) {
	            student = new Student();
	            student.setStudentId(result.getInt("studentid"));
	            student.setAge(result.getInt("age"));
	            student.setPercentage(result.getDouble("percentage"));
	            student.setStudentName(result.getString("name"));
	            student.setRollno(result.getInt("rollnumber"));
	        }

	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return student;
	}

	public void deleteStudent(int studentId) {
		try {

			String sql = "delete from  students where studentid = ?";

			// Create PreparedStatement
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, studentId);
			

			// Execute the update
			int updates = pstmt.executeUpdate();

			if (updates > 0) {
				System.out.println(" Record deleted successfully.");
			}
			else {
				System.out.println("student id not fount");
			}

			// Close the statement
			pstmt.close();

		} catch (SQLException e) {
			System.out.println(" SQL error during insert.");
			e.printStackTrace();
		}

	} 
 
	public void updateStudentPercentage(int studentId, double percentage) {
		try { 

			String sql = "Update students set percentage = ? where studentid = ?";

			// Create PreparedStatement
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setDouble(1, percentage);
			pstmt.setInt(2, studentId);
			

			// Execute the update
			int updates = pstmt.executeUpdate();

			if (updates > 0) {
				System.out.println(" Record added successfully.");
			}
			else {
				System.out.println("student id not fount");
			}

			// Close the statement
			pstmt.close();

		} catch (SQLException e) {
			System.out.println(" SQL error during insert.");
			e.printStackTrace();
		}

	} 

	public void addNewStudent(Student student) {
		try {

			String sql = "INSERT INTO students (studentid, rollnumber, name, percentage, age) VALUES (?, ?, ?, ?, ?)";

			// Create PreparedStatement
			PreparedStatement pstmt = connection.prepareStatement(sql);

			pstmt.setInt(1, student.getStudentId());
			pstmt.setInt(2, student.getRollno());
			pstmt.setString(3, student.getStudentName());
			pstmt.setDouble(4, student.getPercentage());
			pstmt.setInt(5, student.getAge());

			// Execute the update
			int updates = pstmt.executeUpdate();

			if (updates > 0) {
				System.out.println(" Record added successfully.");
			}

			// Close the statement
			pstmt.close();

		} catch (SQLException e) {
			System.out.println(" SQL error during insert.");
			e.printStackTrace();
		}

	}
}