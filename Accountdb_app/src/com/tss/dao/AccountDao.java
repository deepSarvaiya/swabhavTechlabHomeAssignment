package com.tss.dao;

import com.tss.model.Account;
import java.sql.*;

public class AccountDao {
    public Account getAccountById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Account(rs.getInt("id"), rs.getString("name"), rs.getDouble("balance"));
        }
        return null;
    }

    public void updateBalance(Connection conn, int id, double newBalance) throws SQLException {
        String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDouble(1, newBalance);
        stmt.setInt(2, id);
        stmt.executeUpdate();
    }
}
