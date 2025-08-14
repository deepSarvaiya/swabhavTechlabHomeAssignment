package com.tss.dao;

import com.tss.db.DBConnection;
import com.tss.model.LeaveRequest;
import com.tss.model.LeaveType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveRequestDao {
    
    // Insert a new leave request
    public boolean insertLeaveRequest(LeaveRequest leaveRequest) {
        String sql = "INSERT INTO leave_requests (user_id, leave_type_id, start_date, end_date, reason, status, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, leaveRequest.getUserId());
            pstmt.setInt(2, leaveRequest.getLeaveTypeId());
            pstmt.setDate(3, new java.sql.Date(leaveRequest.getStartDate().getTime()));
            pstmt.setDate(4, new java.sql.Date(leaveRequest.getEndDate().getTime()));
            pstmt.setString(5, leaveRequest.getReason());
            pstmt.setString(6, leaveRequest.getStatus());
            pstmt.setTimestamp(7, new Timestamp(leaveRequest.getCreatedAt().getTime()));
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Get all leave requests for a specific user
    public List<LeaveRequest> getLeaveRequestsByUserId(int userId) {
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        String sql = "SELECT lr.*, lt.leave_name, u.full_name as user_name " +
                    "FROM leave_requests lr " +
                    "JOIN leave_types lt ON lr.leave_type_id = lt.leave_type_id " +
                    "JOIN users u ON lr.user_id = u.user_id " +
                    "WHERE lr.user_id = ? " +
                    "ORDER BY lr.created_at DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                LeaveRequest leaveRequest = mapResultSetToLeaveRequest(rs);
                leaveRequest.setLeaveTypeName(rs.getString("leave_name"));
                leaveRequest.setUserName(rs.getString("user_name"));
                leaveRequests.add(leaveRequest);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return leaveRequests;
    }
    
    // Get all leave requests (for admin)
    public List<LeaveRequest> getAllLeaveRequests() {
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        String sql = "SELECT lr.*, lt.leave_name, u.full_name as user_name, " +
                    "a.full_name as admin_name " +
                    "FROM leave_requests lr " +
                    "JOIN leave_types lt ON lr.leave_type_id = lt.leave_type_id " +
                    "JOIN users u ON lr.user_id = u.user_id " +
                    "LEFT JOIN users a ON lr.admin_id = a.user_id " +
                    "ORDER BY lr.created_at DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                LeaveRequest leaveRequest = mapResultSetToLeaveRequest(rs);
                leaveRequest.setLeaveTypeName(rs.getString("leave_name"));
                leaveRequest.setUserName(rs.getString("user_name"));
                leaveRequest.setAdminName(rs.getString("admin_name"));
                leaveRequests.add(leaveRequest);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return leaveRequests;
    }
    
    // Get pending leave requests (for admin)
    public List<LeaveRequest> getPendingLeaveRequests() {
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        String sql = "SELECT lr.*, lt.leave_name, u.full_name as user_name " +
                    "FROM leave_requests lr " +
                    "JOIN leave_types lt ON lr.leave_type_id = lt.leave_type_id " +
                    "JOIN users u ON lr.user_id = u.user_id " +
                    "WHERE lr.status = 'PENDING' " +
                    "ORDER BY lr.created_at ASC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                LeaveRequest leaveRequest = mapResultSetToLeaveRequest(rs);
                leaveRequest.setLeaveTypeName(rs.getString("leave_name"));
                leaveRequest.setUserName(rs.getString("user_name"));
                leaveRequests.add(leaveRequest);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return leaveRequests;
    }
    
    // Update leave request status (approve/reject)
    public boolean updateLeaveRequestStatus(int requestId, String status, int adminId) {
        String sql = "UPDATE leave_requests SET status = ?, admin_id = ?, decision_date = ? WHERE request_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            pstmt.setInt(2, adminId);
            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(4, requestId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Get leave request by ID
    public LeaveRequest getLeaveRequestById(int requestId) {
        String sql = "SELECT lr.*, lt.leave_name, u.full_name as user_name " +
                    "FROM leave_requests lr " +
                    "JOIN leave_types lt ON lr.leave_type_id = lt.leave_type_id " +
                    "JOIN users u ON lr.user_id = u.user_id " +
                    "WHERE lr.request_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, requestId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                LeaveRequest leaveRequest = mapResultSetToLeaveRequest(rs);
                leaveRequest.setLeaveTypeName(rs.getString("leave_name"));
                leaveRequest.setUserName(rs.getString("user_name"));
                return leaveRequest;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    // Helper method to map ResultSet to LeaveRequest object
    private LeaveRequest mapResultSetToLeaveRequest(ResultSet rs) throws SQLException {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setRequestId(rs.getInt("request_id"));
        leaveRequest.setUserId(rs.getInt("user_id"));
        leaveRequest.setLeaveTypeId(rs.getInt("leave_type_id"));
        leaveRequest.setStartDate(rs.getDate("start_date"));
        leaveRequest.setEndDate(rs.getDate("end_date"));
        leaveRequest.setReason(rs.getString("reason"));
        leaveRequest.setStatus(rs.getString("status"));
        
        // Handle nullable fields
        int adminId = rs.getInt("admin_id");
        if (adminId != 0) {
            leaveRequest.setAdminId(adminId);
        }
        
        Timestamp decisionDate = rs.getTimestamp("decision_date");
        if (decisionDate != null) {
            leaveRequest.setDecisionDate(decisionDate);
        }
        
        leaveRequest.setCreatedAt(rs.getTimestamp("created_at"));
        
        return leaveRequest;
    }
}
