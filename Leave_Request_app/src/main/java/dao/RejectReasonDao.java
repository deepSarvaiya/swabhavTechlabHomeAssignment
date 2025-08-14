package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import model.RejectReason;

public class RejectReasonDao {
    
    /**
     * Add a new rejection reason
     */
    public boolean addRejectReason(RejectReason rejectReason) {
        String sql = "INSERT INTO reject_reason (reason, empid, leave_id) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, rejectReason.getReason());
            ps.setInt(2, rejectReason.getEmpId());
            ps.setInt(3, rejectReason.getLeaveId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * Get rejection reason by employee ID
     */
    public RejectReason getByEmployeeId(int empId) {
        String sql = "SELECT r.*, e.name as employee_name FROM reject_reason r " +
                     "JOIN employees e ON r.empid = e.id WHERE r.empid = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, empId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * Get rejection reason by leave ID
     */
    public RejectReason getByLeaveId(int leaveId) {
        String sql = "SELECT r.*, e.name as employee_name FROM reject_reason r " +
                     "JOIN employees e ON r.empid = e.id WHERE r.leave_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, leaveId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * Get all rejection reasons with employee names
     */
    public List<RejectReason> getAllRejectReasons() {
        List<RejectReason> list = new ArrayList<>();
        String sql = "SELECT r.*, e.name as employee_name FROM reject_reason r " +
                     "JOIN employees e ON r.empid = e.id ORDER BY r.id DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    /**
     * Update rejection reason
     */
    public boolean updateRejectReason(RejectReason rejectReason) {
        String sql = "UPDATE reject_reason SET reason = ?, leave_id = ? WHERE empid = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, rejectReason.getReason());
            ps.setInt(2, rejectReason.getLeaveId());
            ps.setInt(3, rejectReason.getEmpId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * Delete rejection reason by employee ID
     */
    public boolean deleteRejectReason(int empId) {
        String sql = "DELETE FROM reject_reason WHERE empid = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, empId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * Delete rejection reason by leave ID
     */
    public boolean deleteRejectReasonByLeaveId(int leaveId) {
        String sql = "DELETE FROM reject_reason WHERE leave_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, leaveId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    private RejectReason mapRow(ResultSet rs) throws SQLException {
        RejectReason rr = new RejectReason();
        rr.setId(rs.getInt("id"));
        rr.setReason(rs.getString("reason"));
        rr.setEmpId(rs.getInt("empid"));
        rr.setLeaveId(rs.getInt("leave_id"));
        rr.setEmployeeName(rs.getString("employee_name"));
        return rr;
    }
}
