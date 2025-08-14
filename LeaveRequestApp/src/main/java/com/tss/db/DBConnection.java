package com.tss.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static Connection connection = null;
    private static final String URL = "jdbc:mysql://localhost:3306/leaveapplication";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Deep@123456";
    
    // Database connection parameters
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final int MAX_RETRIES = 3;
    private static final int RETRY_DELAY = 1000; // 1 second
    
    public static Connection connect() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Database connected successfully.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
    
    public static Connection getConnection() {
        return connect();
    }
    
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
    
    public static Connection connectWithRetry() {
        int retryCount = 0;
        while (retryCount < MAX_RETRIES) {
            try {
                if (connection == null || connection.isClosed()) {
                    Class.forName(DRIVER);
                    connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                    System.out.println("Database connected successfully on attempt " + (retryCount + 1));
                    return connection;
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception on attempt " + (retryCount + 1) + ": " + e.getMessage());
                retryCount++;
                if (retryCount < MAX_RETRIES) {
                    try {
                        Thread.sleep(RETRY_DELAY);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                System.out.println("JDBC Driver not found: " + e.getMessage());
                break;
            }
        }
        return null;
    }
    
    // Test connection method
    public static boolean testConnection() {
        try (Connection testConn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            System.out.println("Test connection successful.");
            return true;
        } catch (SQLException e) {
            System.out.println("Test connection failed: " + e.getMessage());
            return false;
        }
    }
    
    // Get database info
    public static String getDatabaseInfo() {
        try {
            if (connection != null && !connection.isClosed()) {
                return "Connected to: " + connection.getMetaData().getDatabaseProductName() + 
                       " " + connection.getMetaData().getDatabaseProductVersion();
            }
        } catch (SQLException e) {
            System.out.println("Error getting database info: " + e.getMessage());
        }
        return "Not connected to database";
    }
}
