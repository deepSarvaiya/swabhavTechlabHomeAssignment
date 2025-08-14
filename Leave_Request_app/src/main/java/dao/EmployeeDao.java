package dao;

import database.DBConnection;
import model.Employee;
import java.sql.*;

public class EmployeeDao {

	public Employee login(String username, String password) {
		String sql = "SELECT * FROM employees WHERE username = ? AND password = ?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, username);
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Employee e = new Employee();
					e.setId(rs.getInt("id"));
					e.setName(rs.getString("name"));
					e.setUsername(rs.getString("username"));
					e.setPassword(rs.getString("password"));
					e.setLeaveBalance(rs.getInt("leave_balance"));
					return e;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public Employee getById(int id) {
		String sql = "SELECT * FROM employees WHERE id = ?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Employee e = new Employee();
					e.setId(rs.getInt("id"));
					e.setName(rs.getString("name"));
					e.setUsername(rs.getString("username"));
					e.setPassword(rs.getString("password"));
					e.setLeaveBalance(rs.getInt("leave_balance"));
					return e;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public boolean updateLeaveBalance(int employeeId, int newBalance) {
		String sql = "UPDATE employees SET leave_balance = ? WHERE id = ?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, newBalance);
			ps.setInt(2, employeeId);
			return ps.executeUpdate() > 0;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public boolean registerEmployee(Employee emp) {
		String sql = "INSERT INTO employees (name, username, password, leave_balance) VALUES (?, ?, ?, ?)";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getUsername());
			ps.setString(3, emp.getPassword());
			ps.setInt(4, emp.getLeaveBalance());

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
