package com.tss.service;

import com.tss.dao.LeaveRequestDao;
import com.tss.dao.LeaveTypeDao;
import com.tss.model.LeaveRequest;
import com.tss.model.LeaveType;

import java.util.Date;
import java.util.List;

public class LeaveRequestService {
    
    private LeaveRequestDao leaveRequestDao;
    private LeaveTypeDao leaveTypeDao;
    
    public LeaveRequestService() {
        this.leaveRequestDao = new LeaveRequestDao();
        this.leaveTypeDao = new LeaveTypeDao();
    }
    
    // Submit a new leave request
    public boolean submitLeaveRequest(LeaveRequest leaveRequest) {
        // Validate the leave request
        if (!validateLeaveRequest(leaveRequest)) {
            return false;
        }
        
        // Check if dates are valid
        if (!validateDates(leaveRequest.getStartDate(), leaveRequest.getEndDate())) {
            return false;
        }
        
        // Check if user has sufficient leave balance (basic check)
        if (!checkLeaveBalance(leaveRequest)) {
            return false;
        }
        
        // Submit the leave request
        return leaveRequestDao.insertLeaveRequest(leaveRequest);
    }
    
    // Get all leave requests for a specific user
    public List<LeaveRequest> getUserLeaveRequests(int userId) {
        return leaveRequestDao.getLeaveRequestsByUserId(userId);
    }
    
    // Get all leave requests (for admin)
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestDao.getAllLeaveRequests();
    }
    
    // Get pending leave requests (for admin)
    public List<LeaveRequest> getPendingLeaveRequests() {
        return leaveRequestDao.getPendingLeaveRequests();
    }
    
    // Approve or reject a leave request
    public boolean updateLeaveRequestStatus(int requestId, String status, int adminId) {
        if (!"APPROVED".equals(status) && !"REJECTED".equals(status)) {
            return false;
        }
        
        return leaveRequestDao.updateLeaveRequestStatus(requestId, status, adminId);
    }
    
    // Get leave request by ID
    public LeaveRequest getLeaveRequestById(int requestId) {
        return leaveRequestDao.getLeaveRequestById(requestId);
    }
    
    // Get all leave types
    public List<LeaveType> getAllLeaveTypes() {
        return leaveTypeDao.getAllLeaveTypes();
    }
    
    // Validate leave request data
    private boolean validateLeaveRequest(LeaveRequest leaveRequest) {
        if (leaveRequest == null) {
            return false;
        }
        
        if (leaveRequest.getUserId() <= 0) {
            return false;
        }
        
        if (leaveRequest.getLeaveTypeId() <= 0) {
            return false;
        }
        
        if (leaveRequest.getStartDate() == null || leaveRequest.getEndDate() == null) {
            return false;
        }
        
        if (leaveRequest.getReason() == null || leaveRequest.getReason().trim().isEmpty()) {
            return false;
        }
        
        return true;
    }
    
    // Validate dates
    private boolean validateDates(Date startDate, Date endDate) {
        Date currentDate = new Date();
        
        // Start date should not be in the past
        if (startDate.before(currentDate)) {
            return false;
        }
        
        // End date should not be before start date
        if (endDate.before(startDate)) {
            return false;
        }
        
        // Check if dates are not too far in the future (e.g., 1 year)
        long oneYearInMillis = 365L * 24 * 60 * 60 * 1000;
        if (startDate.getTime() - currentDate.getTime() > oneYearInMillis) {
            return false;
        }
        
        return true;
    }
    
    // Basic leave balance check
    private boolean checkLeaveBalance(LeaveRequest leaveRequest) {
        // This is a basic check - in a real application, you would implement
        // more sophisticated balance checking logic
        
        // For now, we'll just check if the leave type exists
        LeaveType leaveType = leaveTypeDao.getLeaveTypeById(leaveRequest.getLeaveTypeId());
        return leaveType != null;
    }
    
    // Calculate number of days between two dates (excluding weekends)
    public int calculateWorkingDays(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return 0;
        }
        
        int workingDays = 0;
        Date currentDate = new Date(startDate.getTime());
        
        while (!currentDate.after(endDate)) {
            // Check if it's not weekend (Saturday = 6, Sunday = 0)
            int dayOfWeek = currentDate.getDay();
            if (dayOfWeek != 0 && dayOfWeek != 6) {
                workingDays++;
            }
            
            // Move to next day
            currentDate.setTime(currentDate.getTime() + (24 * 60 * 60 * 1000));
        }
        
        return workingDays;
    }
}
