package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tss.db.DBConnection;
import com.tss.model.User;

public class UserDao {
	
	
	public boolean register(User user) {
		String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
		try (Connection conn = DBConnection.connect();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());

			int rows = stmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			System.out.println("Error in UserDao.register: " + e.getMessage());
			return false;
		}
	}
	
	public boolean validate(String username, String password) {
		String query = "SELECT * FROM users WHERE username = ? AND password = ?";
		try (Connection conn = DBConnection.connect();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			return rs.next(); // true if user exists
		} catch (SQLException e) {
			System.out.println("Error in UserDao.validate: " + e.getMessage());
			return false;
		}
	}
	
	public int getUserIdByCredentials(String username, String password) {
	    int userId = -1;

	    try (Connection conn = DBConnection.connect();
	         PreparedStatement ps = conn.prepareStatement("SELECT id FROM users WHERE username = ? AND password = ?")) {
	        ps.setString(1, username);
	        ps.setString(2, password);

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            userId = rs.getInt("id");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return userId;
	}


}
