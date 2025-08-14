package com.tss.dao;

import com.tss.db.DBConnection;
import com.tss.model.LeaveType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveTypeDao {
    
    // Get all leave types
    public List<LeaveType> getAllLeaveTypes() {
        List<LeaveType> leaveTypes = new ArrayList<>();
        String sql = "SELECT * FROM leave_types ORDER BY leave_name";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                LeaveType leaveType = mapResultSetToLeaveType(rs);
                leaveTypes.add(leaveType);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return leaveTypes;
    }
    
    // Get leave type by ID
    public LeaveType getLeaveTypeById(int leaveTypeId) {
        String sql = "SELECT * FROM leave_types WHERE leave_type_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, leaveTypeId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToLeaveType(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    // Helper method to map ResultSet to LeaveType object
    private LeaveType mapResultSetToLeaveType(ResultSet rs) throws SQLException {
        LeaveType leaveType = new LeaveType();
        leaveType.setLeaveTypeId(rs.getInt("leave_type_id"));
        leaveType.setLeaveName(rs.getString("leave_name"));
        leaveType.setMaxDays(rs.getInt("max_days"));
        leaveType.setDurationInDays(rs.getDouble("duration_in_days"));
        return leaveType;
    }
}
