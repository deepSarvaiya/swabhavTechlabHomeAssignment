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
