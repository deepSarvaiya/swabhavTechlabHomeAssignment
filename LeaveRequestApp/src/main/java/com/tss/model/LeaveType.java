package com.tss.model;

public class LeaveType {
    private int leaveTypeId;
    private String leaveName;
    private int maxDays;
    private double durationInDays;
    
    // Default constructor
    public LeaveType() {}
    
    // Constructor with essential fields
    public LeaveType(int leaveTypeId, String leaveName, int maxDays, double durationInDays) {
        this.leaveTypeId = leaveTypeId;
        this.leaveName = leaveName;
        this.maxDays = maxDays;
        this.durationInDays = durationInDays;
    }
    
    // Getters and Setters
    public int getLeaveTypeId() {
        return leaveTypeId;
    }
    
    public void setLeaveTypeId(int leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }
    
    public String getLeaveName() {
        return leaveName;
    }
    
    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }
    
    public int getMaxDays() {
        return maxDays;
    }
    
    public void setMaxDays(int maxDays) {
        this.maxDays = maxDays;
    }
    
    public double getDurationInDays() {
        return durationInDays;
    }
    
    public void setDurationInDays(double durationInDays) {
        this.durationInDays = durationInDays;
    }
    
    @Override
    public String toString() {
        return "LeaveType{" +
                "leaveTypeId=" + leaveTypeId +
                ", leaveName='" + leaveName + '\'' +
                ", maxDays=" + maxDays +
                ", durationInDays=" + durationInDays +
                '}';
    }
}
