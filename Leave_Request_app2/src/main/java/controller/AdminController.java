package controller;

import java.io.IOException;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import model.LeaveRequest;
import service.EmployeeService;
import service.LeaveService;

/**
 * Servlet implementation class dminController
 */
@WebServlet(urlPatterns = {"/admin", "/AdminController"})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private LeaveService leaveService = new LeaveService();
	    private EmployeeService employeeService = new EmployeeService();

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        HttpSession s = req.getSession(false);
	        if (s == null || s.getAttribute("admin") == null) {
	            resp.sendRedirect(req.getContextPath() + "/login");
	            return;
	        }
	        String action = req.getParameter("action");
	        if ("view".equals(action)) {
	            req.setAttribute("requests", leaveService.getAllRequests());
	            req.getRequestDispatcher("viewAllRequests.jsp").forward(req, resp);
	        } else {
	            req.getRequestDispatcher("adminDashboard.jsp").forward(req, resp);
	        }
	    }

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        HttpSession s = req.getSession(false);
	        if (s == null || s.getAttribute("admin") == null) {
	            resp.sendRedirect(req.getContextPath() + "/login");
	            return;
	        }
	        String action = req.getParameter("action");
	        int requestId = Integer.parseInt(req.getParameter("requestId"));
	        LeaveRequest lr = leaveService.getById(requestId);
	        if (lr == null) {
	            req.setAttribute("error", "Request not found");
	            req.getRequestDispatcher("adminDashboard.jsp").forward(req, resp);
	            return;
	        }

	        if ("approve".equals(action)) {
	            long days = ChronoUnit.DAYS.between(lr.getStartDate().toLocalDate(), lr.getEndDate().toLocalDate()) + 1;
	            Employee emp = employeeService.getById(lr.getEmployeeId());
	            if (emp.getLeaveBalance() < days) {
	                req.setAttribute("error", "Employee does not have enough leave balance to approve.");
	            } else {
	                boolean updated = leaveService.updateStatus(requestId, "Approved");
	                if (updated) {
	                    employeeService.updateLeaveBalance(emp.getId(), emp.getLeaveBalance() - (int) days);
	                    req.setAttribute("message", "Approved and updated balance.");
	                } else {
	                    req.setAttribute("error", "Failed to update request status.");
	                }
	            }
	        } else if ("reject".equals(action)) {
	            boolean updated = leaveService.updateStatus(requestId, "Rejected");
	            if (updated) req.setAttribute("message", "Request rejected.");
	            else req.setAttribute("error", "Failed to update request status.");
	        }
	        req.setAttribute("requests", leaveService.getAllRequests());
	        req.getRequestDispatcher("viewAllRequests.jsp").forward(req, resp);
	    }

}
