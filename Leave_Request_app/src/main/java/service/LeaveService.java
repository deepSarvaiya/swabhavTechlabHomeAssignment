package service;
import java.util.List;

import dao.LeaveDao;
import model.LeaveRequest;

public class LeaveService {
    private LeaveDao dao = new LeaveDao();

    public boolean applyLeave(LeaveRequest lr) {
        return dao.applyLeave(lr);
    }

    public List<LeaveRequest> getByEmployee(int employeeId) {
        return dao.getByEmployee(employeeId);
    }

    public List<LeaveRequest> getAllRequests() {
        return dao.getAllRequests();
    }

    public LeaveRequest getById(int id) { return dao.getById(id); }

    public boolean updateStatus(int id, String status) {
        return dao.updateStatus(id, status);
    }

    /**
     * Check if there are any date conflicts for the given employee
     */
    public boolean hasDateConflict(int employeeId, java.sql.Date startDate, java.sql.Date endDate, int excludeRequestId) {
        return dao.hasDateConflict(employeeId, startDate, endDate, excludeRequestId);
    }

    /**
     * Calculate total leave days used by an employee in a specific month
     */
    public int getMonthlyLeaveDays(int employeeId, int year, int month) {
        return dao.getMonthlyLeaveDays(employeeId, year, month);
    }

    /**
     * Get available leave days for the current month
     */
    public int getAvailableMonthlyLeaveDays(int employeeId) {
        return dao.getAvailableMonthlyLeaveDays(employeeId);
    }

    /**
     * Get available leave days for a specific month
     */
    public int getAvailableMonthlyLeaveDays(int employeeId, int year, int month) {
        return dao.getAvailableMonthlyLeaveDays(employeeId, year, month);
    }

    /**
     * Update leave request details
     */
    public boolean updateLeaveRequest(LeaveRequest lr) {
        return dao.updateLeaveRequest(lr);
    }

    /**
     * Delete leave request
     */
    public boolean deleteLeaveRequest(int requestId, int employeeId) {
        return dao.deleteLeaveRequest(requestId, employeeId);
    }

    /**
     * Check if leave request can be edited
     */
    public boolean canEditRequest(int requestId, int employeeId) {
        return dao.canEditRequest(requestId, employeeId);
    }
}
