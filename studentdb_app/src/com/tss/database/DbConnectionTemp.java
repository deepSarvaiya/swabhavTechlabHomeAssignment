package com.tss.database;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionTemp {

    // Load environment variables from .env
    private static final Dotenv dotenv = Dotenv.load();

    private static final String DB_URL = dotenv.get("DB_URL");
    private static final String DB_USERNAME = dotenv.get("DB_USERNAME");
    private static final String DB_PASSWORD = dotenv.get("DB_PASSWORD");

    private static Connection connection;

    // Method to establish and return connection
    public static Connection connect() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                System.out.println("✅ Connected to database successfully.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Failed to connect to the database:");
            e.printStackTrace();
        }
        return connection;
    }

    // Main method for testing
    public static void main(String[] args) {
        Connection testConnection = connect();

        if (testConnection != null) {
            System.out.println("🟢 Connection test successful.");
        } else {
            System.out.println("🔴 Connection test failed.");
        }
    }
}
