package service;
import dao.EmployeeDao;
import model.Employee;

public class EmployeeService {
    private EmployeeDao dao = new EmployeeDao();

    public Employee login(String username, String password) {
        return dao.login(username, password);
    }

    public Employee getById(int id) {
        return dao.getById(id);
    }

    public boolean updateLeaveBalance(int employeeId, int newBalance) {
        return dao.updateLeaveBalance(employeeId, newBalance);
    }

	public boolean registerEmployee(Employee emp) {
		return dao.registerEmployee(emp);
	}
}
