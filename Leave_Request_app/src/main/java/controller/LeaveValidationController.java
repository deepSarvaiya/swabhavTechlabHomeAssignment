package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import service.LeaveService;

@WebServlet("/leaveValidation")
public class LeaveValidationController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LeaveService leaveService = new LeaveService();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("employee") == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        
        Employee emp = (Employee) session.getAttribute("employee");
        String action = request.getParameter("action");
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        if ("monthlyUsage".equals(action)) {
            // Get monthly leave usage for current month
            int monthlyUsed = leaveService.getMonthlyLeaveDays(emp.getId(), 
                LocalDate.now().getYear(), LocalDate.now().getMonthValue());
            int available = leaveService.getAvailableMonthlyLeaveDays(emp.getId());
            
            String json = String.format("{\"used\": %d, \"available\": %d, \"total\": 3}", 
                monthlyUsed, available, 3);
            out.print(json);
            
        } else if ("monthlyUsageForDate".equals(action)) {
            // Get monthly leave usage for a specific date/month
            String dateStr = request.getParameter("date");
            if (dateStr != null) {
                LocalDate date = LocalDate.parse(dateStr);
                int monthlyUsed = leaveService.getMonthlyLeaveDays(emp.getId(), 
                    date.getYear(), date.getMonthValue());
                int available = leaveService.getAvailableMonthlyLeaveDays(emp.getId(), 
                    date.getYear(), date.getMonthValue());
                
                String json = String.format("{\"used\": %d, \"available\": %d, \"total\": 3, \"year\": %d, \"month\": %d}", 
                    monthlyUsed, available, 3, date.getYear(), date.getMonthValue());
                out.print(json);
            } else {
                out.print("{\"error\": \"Date parameter required\"}");
            }
            
        } else if ("checkConflict".equals(action)) {
            // Check for date conflicts
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");
            
            if (startDateStr != null && endDateStr != null) {
                Date startDate = Date.valueOf(startDateStr);
                Date endDate = Date.valueOf(endDateStr);
                
                boolean hasConflict = leaveService.hasDateConflict(emp.getId(), startDate, endDate, 0);
                long days = ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate()) + 1;
                
                String json = String.format("{\"hasConflict\": %s, \"days\": %d}", 
                    hasConflict, days);
                out.print(json);
            } else {
                out.print("{\"error\": \"Invalid dates\"}");
            }
        }
        
        out.flush();
    }
}
