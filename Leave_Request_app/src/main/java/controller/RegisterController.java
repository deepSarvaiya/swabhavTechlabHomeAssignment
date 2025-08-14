package controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBConnection;
import model.Employee;
import service.EmployeeService;

@WebServlet(urlPatterns = {"/registerEmployee", "/RegisterController"})
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String name = request.getParameter("name");
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        int leaveBalance = 20;
	        
	        Employee emp = new Employee(name, username, password, leaveBalance);

	        try (Connection conn = DBConnection.getConnection()) {
	            EmployeeService registerService = new EmployeeService();
	            boolean isRegistered = registerService.registerEmployee(emp);

	            if (isRegistered) {
	                request.setAttribute("success", "Employee registered successfully!");
	            } else {
	                request.setAttribute("error", "Failed to register employee. Try again.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("error", "Error occurred: " + e.getMessage());
	        }

	        request.getRequestDispatcher("/login.jsp").forward(request, response);
	    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
