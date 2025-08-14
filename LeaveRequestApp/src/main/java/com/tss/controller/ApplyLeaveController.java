package com.tss.controller;

import com.tss.model.LeaveRequest;
import com.tss.model.LeaveType;
import com.tss.model.User;
import com.tss.service.LeaveRequestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/ApplyLeaveController")
public class ApplyLeaveController extends HttpServlet {
    
    private LeaveRequestService leaveRequestService;
    
    @Override
    public void init() throws ServletException {
        leaveRequestService = new LeaveRequestService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        User user = (User) session.getAttribute("user");
        
        // Check if user is an employee
        if (!"EMPLOYEE".equals(user.getRole())) {
            response.sendRedirect("admin/dashboard.jsp");
            return;
        }
        
        // Get all leave types for the form
        List<LeaveType> leaveTypes = leaveRequestService.getAllLeaveTypes();
        request.setAttribute("leaveTypes", leaveTypes);
        
        // Forward to apply leave page
        request.getRequestDispatcher("employee/apply-leave.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        User user = (User) session.getAttribute("user");
        
        // Check if user is an employee
        if (!"EMPLOYEE".equals(user.getRole())) {
            response.sendRedirect("admin/dashboard.jsp");
            return;
        }
        
        // Get form parameters
        String leaveTypeIdStr = request.getParameter("leaveTypeId");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String reason = request.getParameter("reason");
        
        // Validate required fields
        if (leaveTypeIdStr == null || startDateStr == null || endDateStr == null || 
            reason == null || reason.trim().isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required.");
            forwardToApplyLeaveForm(request, response);
            return;
        }
        
        try {
            // Parse parameters
            int leaveTypeId = Integer.parseInt(leaveTypeIdStr);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);
            
            // Create leave request object
            LeaveRequest leaveRequest = new LeaveRequest(
                user.getUserId(), 
                leaveTypeId, 
                startDate, 
                endDate, 
                reason.trim()
            );
            
            // Submit leave request
            boolean success = leaveRequestService.submitLeaveRequest(leaveRequest);
            
            if (success) {
                // Redirect to success page or dashboard with success message
                session.setAttribute("successMessage", "Leave request submitted successfully!");
                response.sendRedirect("employee/dashboard.jsp");
            } else {
                request.setAttribute("errorMessage", "Failed to submit leave request. Please try again.");
                // Preserve form data
                request.setAttribute("leaveTypeId", leaveTypeId);
                request.setAttribute("startDate", startDateStr);
                request.setAttribute("endDate", endDateStr);
                request.setAttribute("reason", reason);
                forwardToApplyLeaveForm(request, response);
            }
            
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid leave type selected.");
            forwardToApplyLeaveForm(request, response);
        } catch (ParseException e) {
            request.setAttribute("errorMessage", "Invalid date format. Please use YYYY-MM-DD format.");
            forwardToApplyLeaveForm(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An error occurred. Please try again.");
            forwardToApplyLeaveForm(request, response);
        }
    }
    
    private void forwardToApplyLeaveForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get all leave types for the form
        List<LeaveType> leaveTypes = leaveRequestService.getAllLeaveTypes();
        request.setAttribute("leaveTypes", leaveTypes);
        
        // Forward to apply leave page
        request.getRequestDispatcher("employee/apply-leave.jsp").forward(request, response);
    }
}
