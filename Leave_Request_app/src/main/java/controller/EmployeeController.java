package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
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


@WebServlet(urlPatterns = {"/employee", "/EmployeeController"})
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LeaveService leaveService = new LeaveService();
    private EmployeeService empService = new EmployeeService();
       
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("employee") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        Employee emp = (Employee) s.getAttribute("employee");
        emp = empService.getById(emp.getId());
        s.setAttribute("employee", emp);

        String action = req.getParameter("action");
        if ("apply".equals(action)) {
            req.getRequestDispatcher("applyLeave.jsp").forward(req, resp);
        } else if ("myrequests".equals(action)) {
            req.setAttribute("requests", leaveService.getByEmployee(emp.getId()));
            req.getRequestDispatcher("employeeRequests.jsp").forward(req, resp);
        } else if ("edit".equals(action)) {
            // Handle edit leave request
            int requestId = Integer.parseInt(req.getParameter("id"));
            if (leaveService.canEditRequest(requestId, emp.getId())) {
                LeaveRequest lr = leaveService.getById(requestId);
                req.setAttribute("leaveRequest", lr);
                req.getRequestDispatcher("editLeave.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Cannot edit this leave request");
                req.getRequestDispatcher("employeeRequests.jsp").forward(req, resp);
            }
        } else if ("delete".equals(action)) {
            // Handle delete leave request
            int requestId = Integer.parseInt(req.getParameter("id"));
            if (leaveService.deleteLeaveRequest(requestId, emp.getId())) {
                req.setAttribute("message", "Leave request deleted successfully");
            } else {
                req.setAttribute("error", "Failed to delete leave request");
            }
            resp.sendRedirect(req.getContextPath() + "/employee?action=myrequests");
            return;
        } else {
            req.getRequestDispatcher("employeeDashboard.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	HttpSession s = req.getSession(false);
    	if (s == null || s.getAttribute("employee") == null) {
    	    resp.sendRedirect(req.getContextPath() + "/login");
    	    return;
    	}
    	Employee emp = (Employee) s.getAttribute("employee");

    	String action = req.getParameter("action");
    	
    	if ("update".equals(action)) {
    	    // Handle update leave request
    	    int requestId = Integer.parseInt(req.getParameter("requestId"));
    	    if (!leaveService.canEditRequest(requestId, emp.getId())) {
    	        req.setAttribute("error", "Cannot edit this leave request");
    	        resp.sendRedirect(req.getContextPath() + "/employee?action=myrequests");
    	        return;
    	    }
    	    
    	    String start = req.getParameter("startDate");
    	    String end = req.getParameter("endDate");
    	    String reason = req.getParameter("reason");

    	    Date startDate = Date.valueOf(start);
    	    Date endDate = Date.valueOf(end);

    	    // Since dates are read-only, we don't need to validate them again
    	    // Just validate the reason
    	    if (reason == null || reason.trim().isEmpty()) {
    	        req.setAttribute("error", "Reason cannot be empty");
    	        resp.sendRedirect(req.getContextPath() + "/employee?action=edit&id=" + requestId);
    	        return;
    	    }

    	    LeaveRequest lr = new LeaveRequest();
    	    lr.setId(requestId);
    	    lr.setEmployeeId(emp.getId());
    	    lr.setStartDate(startDate);
    	    lr.setEndDate(endDate);
    	    lr.setReason(reason.trim());

    	    boolean ok = leaveService.updateLeaveRequest(lr);
    	    if (ok) {
    	        req.setAttribute("message", "Leave request reason updated successfully!");
    	        resp.sendRedirect(req.getContextPath() + "/employee?action=myrequests");
    	    } else {
    	        req.setAttribute("error", "Failed to update leave request reason.");
    	        resp.sendRedirect(req.getContextPath() + "/employee?action=edit&id=" + requestId);
    	    }
    	    return;
    	}

    	String start = req.getParameter("startDate");
    	String end = req.getParameter("endDate");
    	String reason = req.getParameter("reason");

    	Date startDate = Date.valueOf(start);
    	Date endDate = Date.valueOf(end);

    	LocalDate today = LocalDate.now();

    	if (startDate.toLocalDate().isBefore(today) || endDate.toLocalDate().isBefore(today)) {
    	    req.setAttribute("error", "Leave dates cannot be in the past.");
    	    req.getRequestDispatcher("applyLeave.jsp").forward(req, resp);
    	    return;
    	}

    	long days = ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate()) + 1;
    	if (days <= 0) {
    	    req.setAttribute("error", "End date must be after start date");
    	    req.getRequestDispatcher("applyLeave.jsp").forward(req, resp);
    	    return;
    
        }

        // Check monthly leave limit (3 days per month) for the selected month
        int selectedMonth = startDate.toLocalDate().getMonthValue();
        int selectedYear = startDate.toLocalDate().getYear();
        
        // Get monthly usage for the selected month
        int monthlyUsedDays = leaveService.getMonthlyLeaveDays(emp.getId(), selectedYear, selectedMonth);
        int availableMonthlyDays = 3 - monthlyUsedDays;
        
        if (availableMonthlyDays < days) {
            req.setAttribute("error", "Monthly leave limit exceeded for " + startDate.toLocalDate().getMonth().toString() + " " + selectedYear + ". You can only take " + availableMonthlyDays + " more days in that month.");
            req.getRequestDispatcher("applyLeave.jsp").forward(req, resp);
            return;
        }

        // Check for date conflicts with existing requests
        if (leaveService.hasDateConflict(emp.getId(), startDate, endDate, 0)) {
            req.setAttribute("error", "Leave request conflicts with existing approved or pending leave requests for these dates.");
            req.getRequestDispatcher("applyLeave.jsp").forward(req, resp);
            return;
        }

        if (emp.getLeaveBalance() < days) {
            req.setAttribute("error", "Not enough leave balance. You need " + days + " days.");
            req.getRequestDispatcher("applyLeave.jsp").forward(req, resp);
            return;
        }

        LeaveRequest lr = new LeaveRequest();
        lr.setEmployeeId(emp.getId());
        lr.setStartDate(startDate);
        lr.setEndDate(endDate);
        lr.setReason(reason);
        lr.setStatus("Pending");

        boolean ok = leaveService.applyLeave(lr);
        if (ok) {
            req.setAttribute("message", "Leave request submitted successfully!");
            req.getRequestDispatcher("employeeDashboard.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Failed to submit leave request.");
            req.getRequestDispatcher("applyLeave.jsp").forward(req, resp);
        }
    }

}
