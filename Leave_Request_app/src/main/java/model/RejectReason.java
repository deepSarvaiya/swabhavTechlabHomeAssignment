package model;

public class RejectReason {
    private int id;
    private String reason;
    private int empId;
    private int leaveId;
    private String employeeName; // For display purposes
    
    public RejectReason() {}
    
    public RejectReason(int id, String reason, int empId, int leaveId) {
        this.id = id;
        this.reason = reason;
        this.empId = empId;
        this.leaveId = leaveId;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public int getEmpId() {
        return empId;
    }
    
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    
    public int getLeaveId() {
        return leaveId;
    }
    
    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }
    
    public String getEmployeeName() {
        return employeeName;
    }
    
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
