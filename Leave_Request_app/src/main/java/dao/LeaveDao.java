package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import model.LeaveRequest;

public class LeaveDao {

    public boolean applyLeave(LeaveRequest lr) {
        String sql = "INSERT INTO leave_requests (employee_id, start_date, end_date, reason, status) VALUES (?, ?, ?, ?, 'Pending')";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, lr.getEmployeeId());
            ps.setDate(2, lr.getStartDate());
            ps.setDate(3, lr.getEndDate());
            ps.setString(4, lr.getReason());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<LeaveRequest> getByEmployee(int employeeId) {
        List<LeaveRequest> list = new ArrayList<>();
        String sql = "SELECT e.name, l.*, rr.reason as rejection_reason FROM leave_requests AS l " +
                     "JOIN employees AS e ON l.employee_id = e.id " +
                     "LEFT JOIN reject_reason rr ON l.id = rr.leave_id " +
                     "WHERE l.employee_id = ? ORDER BY l.applied_on DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LeaveRequest lr = mapRow(rs);
                    // Set rejection reason if available
                    String rejectionReason = rs.getString("rejection_reason");
                    if (rejectionReason != null && "Rejected".equalsIgnoreCase(lr.getStatus())) {
                        lr.setRejectionReason(rejectionReason);
                    }
                    list.add(lr);
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return list;
    }

    public List<LeaveRequest> getAllRequests() {
        List<LeaveRequest> list = new ArrayList<>();
        String sql = "SELECT l.*, e.name as employee_name, rr.reason as rejection_reason FROM leave_requests l " +
                     "JOIN employees e ON l.employee_id = e.id " +
                     "LEFT JOIN reject_reason rr ON l.id = rr.leave_id " +
                     "ORDER BY l.applied_on DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                LeaveRequest lr = mapRow(rs);
                // Set rejection reason if available
                String rejectionReason = rs.getString("rejection_reason");
                if (rejectionReason != null && "Rejected".equalsIgnoreCase(lr.getStatus())) {
                    lr.setRejectionReason(rejectionReason);
                }
                list.add(lr);
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return list;
    }

    public LeaveRequest getById(int id) {
        String sql = "SELECT l.*, rr.reason as rejection_reason FROM leave_requests l " +
                     "LEFT JOIN reject_reason rr ON l.id = rr.leave_id " +
                     "WHERE l.id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    LeaveRequest lr = mapRow(rs);
                    // Set rejection reason if available
                    String rejectionReason = rs.getString("rejection_reason");
                    if (rejectionReason != null && "Rejected".equalsIgnoreCase(lr.getStatus())) {
                        lr.setRejectionReason(rejectionReason);
                    }
                    return lr;
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return null;
    }

    public boolean updateStatus(int id, String status) {
        String sql = "UPDATE leave_requests SET status = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Check if there are any date conflicts for the given employee
     * Returns true if there's a conflict, false otherwise
     */
    public boolean hasDateConflict(int employeeId, Date startDate, Date endDate, int excludeRequestId) {
        String sql = "SELECT COUNT(*) FROM leave_requests WHERE employee_id = ? AND status IN ('Pending', 'Approved') " +
                     "AND ((start_date BETWEEN ? AND ?) OR (end_date BETWEEN ? AND ?) OR (? BETWEEN start_date AND end_date) OR (? BETWEEN start_date AND end_date)) " +
                     "AND id != ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, employeeId);
            ps.setDate(2, startDate);
            ps.setDate(3, endDate);
            ps.setDate(4, startDate);
            ps.setDate(5, endDate);
            ps.setDate(6, startDate);
            ps.setDate(7, endDate);
            ps.setInt(8, excludeRequestId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Calculate total leave days used by an employee in a specific month
     */
    public int getMonthlyLeaveDays(int employeeId, int year, int month) {
        String sql = "SELECT SUM(DATEDIFF(end_date, start_date) + 1) FROM leave_requests " +
                     "WHERE employee_id = ? AND YEAR(start_date) = ? AND MONTH(start_date) = ? " +
                     "AND status IN ('Approved', 'Pending')";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, employeeId);
            ps.setInt(2, year);
            ps.setInt(3, month);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int days = rs.getInt(1);
                    return rs.wasNull() ? 0 : days;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * Get available leave days for a specific month
     */
    public int getAvailableMonthlyLeaveDays(int employeeId, int year, int month) {
        int usedDays = getMonthlyLeaveDays(employeeId, year, month);
        return Math.max(0, 3 - usedDays); // 3 days per month limit
    }

    /**
     * Get available leave days for the current month
     */
    public int getAvailableMonthlyLeaveDays(int employeeId) {
        java.time.LocalDate now = java.time.LocalDate.now();
        return getAvailableMonthlyLeaveDays(employeeId, now.getYear(), now.getMonthValue());
    }

    /**
     * Update leave request details (for editing)
     */
    public boolean updateLeaveRequest(LeaveRequest lr) {
        String sql = "UPDATE leave_requests SET start_date = ?, end_date = ?, reason = ? WHERE id = ? AND employee_id = ? AND status = 'Pending'";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, lr.getStartDate());
            ps.setDate(2, lr.getEndDate());
            ps.setString(3, lr.getReason());
            ps.setInt(4, lr.getId());
            ps.setInt(5, lr.getEmployeeId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Delete leave request (only for pending requests)
     */
    public boolean deleteLeaveRequest(int requestId, int employeeId) {
        String sql = "DELETE FROM leave_requests WHERE id = ? AND employee_id = ? AND status = 'Pending'";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, requestId);
            ps.setInt(2, employeeId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Check if leave request can be edited (pending status and belongs to employee)
     */
    public boolean canEditRequest(int requestId, int employeeId) {
        String sql = "SELECT COUNT(*) FROM leave_requests WHERE id = ? AND employee_id = ? AND status = 'Pending'";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, requestId);
            ps.setInt(2, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private LeaveRequest mapRow(ResultSet rs) throws SQLException {
        LeaveRequest lr = new LeaveRequest();
        lr.setId(rs.getInt("id"));
        lr.setEmployeeId(rs.getInt("employee_id"));
        lr.setStartDate(rs.getDate("start_date"));
        lr.setEndDate(rs.getDate("end_date"));
        lr.setReason(rs.getString("reason"));
        lr.setStatus(rs.getString("status"));
        lr.setAppliedOn(rs.getTimestamp("applied_on"));
        return lr;
    }
}