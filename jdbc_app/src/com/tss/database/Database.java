package com.tss.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tss.model.Student;

public class Database {
	
	
	private  Connection connection = null;
	
	public Database() {
		
		  try {
	            // Load the JDBC driver (optional for newer versions)
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // Establish connection
	            connection = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/tss_students_2025", "root", "Deep@123456");

	            System.out.println("Connection Established ");

	        } catch (ClassNotFoundException e) {
	            System.out.println("JDBC Driver not found.");
	            e.printStackTrace();
	        } catch (SQLException e) {
	            System.out.println("SQL error occurred.");
	            e.printStackTrace();
	        }
	}

    public void connect() {

        try {
           
            // Create Statement
            Statement stmt = connection.createStatement();

            // Execute query
            String query = "SELECT * FROM students";
            ResultSet rs = stmt.executeQuery(query);

            // Process ResultSet
            while (rs.next()) {
                int id = rs.getInt("studentid");
                String name = rs.getString("name");
                double percentage = rs.getDouble("percentage");

                System.out.println("ID: " + id + ", Name: " + name + ", Percentage: " + percentage);
            }

         
            System.out.println("Data Retrieved");

        } catch (SQLException e) {
            System.out.println("SQL error occurred.");
            e.printStackTrace();
        }
    }
    
    public void addStudent(Student student) {
    	
        try {
			Statement stmt = connection.createStatement();
			
			String sql="insert into students values("+student.getStudentid()
			+","+student.getRollnumber()+",'"+student.getName()+"'," +student.getPercentage()
			+","+student.getAge() + ")";			
			int updates = stmt.executeUpdate(sql);
			if(updates>0) {
				System.out.println("Added record ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    }
    public void printDatabaseMetadata() {
        try {
            java.sql.DatabaseMetaData dbMetaData = connection.getMetaData();

            System.out.println("Database Driver Name: " + dbMetaData.getDriverName());
            System.out.println("Database Driver Version: " + dbMetaData.getDriverVersion());

            
            ResultSet tables = dbMetaData.getTables(null, null, "%", new String[]{"TABLE"});
            System.out.println("Tables in the database:");
            while (tables.next()) {
                System.out.println("- " + tables.getString("TABLE_NAME"));
            }
            tables.close();

        } catch (SQLException e) {
            System.out.println("Error fetching database metadata.");
            e.printStackTrace();
        }
    }
    public void printTableMetadata(String tableName) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " LIMIT 1");
            java.sql.ResultSetMetaData rsMetaData = rs.getMetaData();

            int columnCount = rsMetaData.getColumnCount();
            System.out.println("Number of Columns in table '" + tableName + "': " + columnCount);

            System.out.println("Columns:");
            for (int i = 1; i <= columnCount; i++) {
                String columnName = rsMetaData.getColumnName(i);
                String columnType = rsMetaData.getColumnTypeName(i);
                System.out.println("- " + columnName + " (" + columnType + ")");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error fetching table metadata.");
            e.printStackTrace();
        }
    }

    public void addStudentUsingPreparedStatement(Student student) {
    	try {
    	    
    	    String sql = "INSERT INTO students (studentid, rollnumber, name, percentage, age) VALUES (?, ?, ?, ?, ?)";

    	    // Create PreparedStatement
    	    PreparedStatement pstmt = connection.prepareStatement(sql);

    	    pstmt.setInt(1, student.getStudentid());
    	    pstmt.setInt(2, student.getRollnumber());
    	    pstmt.setString(3, student.getName());
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
