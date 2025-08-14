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
}
