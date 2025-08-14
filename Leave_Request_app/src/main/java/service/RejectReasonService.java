package service;

import java.util.List;

import dao.RejectReasonDao;
import model.RejectReason;

public class RejectReasonService {
    private RejectReasonDao dao = new RejectReasonDao();
    
    public boolean addRejectReason(RejectReason rejectReason) {
        return dao.addRejectReason(rejectReason);
    }
    
    public RejectReason getByEmployeeId(int empId) {
        return dao.getByEmployeeId(empId);
    }
    
    public RejectReason getByLeaveId(int leaveId) {
        return dao.getByLeaveId(leaveId);
    }
    
    public List<RejectReason> getAllRejectReasons() {
        return dao.getAllRejectReasons();
    }
    
    public boolean updateRejectReason(RejectReason rejectReason) {
        return dao.updateRejectReason(rejectReason);
    }
    
    public boolean deleteRejectReason(int empId) {
        return dao.deleteRejectReason(empId);
    }
    
    public boolean deleteRejectReasonByLeaveId(int leaveId) {
        return dao.deleteRejectReasonByLeaveId(leaveId);
    }
}
