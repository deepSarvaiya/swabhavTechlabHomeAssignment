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
import model.RejectReason;
import service.EmployeeService;
import service.LeaveService;
import service.RejectReasonService;

/**
 * Servlet implementation class dminController
 */
@WebServlet(urlPatterns = {"/admin", "/AdminController"})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private LeaveService leaveService = new LeaveService();
	    private EmployeeService employeeService = new EmployeeService();
	    private RejectReasonService rejectReasonService = new RejectReasonService();

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
	            String rejectReason = req.getParameter("rejectReason");
	            if (rejectReason == null || rejectReason.trim().isEmpty()) {
	                req.setAttribute("error", "Rejection reason is required.");
	            } else if (rejectReason.trim().length() > 45) {
	                req.setAttribute("error", "Rejection reason must be 45 characters or less.");
	            } else {
	                boolean updated = leaveService.updateStatus(requestId, "Rejected");
	                if (updated) {
	                    // Store rejection reason
	                    RejectReason rr = new RejectReason();
	                    rr.setReason(rejectReason.trim());
	                    rr.setEmpId(lr.getEmployeeId());
	                    rr.setLeaveId(requestId);
	                    
	                    if (rejectReasonService.addRejectReason(rr)) {
	                        req.setAttribute("message", "Request rejected with reason: " + rejectReason.trim());
	                    } else {
	                        req.setAttribute("message", "Request rejected but failed to save reason.");
	                    }
	                } else {
	                req.setAttribute("error", "Failed to update request status.");
	                }
	            }
	        }
	        req.setAttribute("requests", leaveService.getAllRequests());
	        req.getRequestDispatcher("viewAllRequests.jsp").forward(req, resp);
	    }

}
