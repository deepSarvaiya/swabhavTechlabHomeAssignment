package com.tss.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	   private static Connection connection = null;
	    private static final String URL = "jdbc:mysql://localhost:3306/userdb1";
	    private static final String USERNAME = "root";
	    private static final String PASSWORD = "Deep@123456";

	    public static Connection connect() {
	        try {
	            if (connection == null || connection.isClosed()) {
	                Class.forName("com.mysql.cj.jdbc.Driver");
	                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	                System.out.println("Database connected successfully.");
	            }
	        } catch (SQLException e) {
	            System.out.println("SQL Exception: " + e.getMessage());
	        } catch (ClassNotFoundException e) {
	            System.out.println("JDBC Driver not found: " + e.getMessage());
	        }
	        return connection;
	    }

}
