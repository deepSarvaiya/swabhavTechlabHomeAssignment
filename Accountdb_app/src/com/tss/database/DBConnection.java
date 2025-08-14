package com.tss.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection connection;

	private DBConnection() {

	}

	public static Connection connect() {
		try {
			if (connection == null) {

				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tss_students_2025", "root", "Deep@123456");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}


