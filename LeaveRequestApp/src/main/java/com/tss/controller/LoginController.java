package com.tss.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.model.User;
import com.tss.service.UserService;

/**
 * Servlet implementation class LoginController
 * Handles user authentication and login
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UserService userService;
    
    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect GET requests to login page
        response.sendRedirect("login.jsp");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html;charset=UTF-8");
        
        // Get form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("remember");
        
        // Validate input parameters
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            
            // Set error message and redirect back to login
            request.setAttribute("errorMessage", "Username and password are required");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        try {
            // Authenticate user
            User user = userService.authenticateUser(username.trim(), password);
            
            if (user != null) {
                // Login successful
                HttpSession session = request.getSession();
                
                // Set session attributes
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("username", user.getUsername());
                session.setAttribute("fullName", user.getFullName());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("role", user.getRole());
                session.setAttribute("user", user);
                
                // Set remember me cookie if requested
                if ("on".equals(rememberMe)) {
                    // TODO: Implement remember me functionality with cookies
                    System.out.println("Remember me requested for user: " + username);
                }
                
                // Log successful login
                System.out.println("User logged in successfully: " + username + " (Role: " + user.getRole() + ")");
                
                // Redirect based on user role
                if (user.isAdmin()) {
                    response.sendRedirect("admin/dashboard.jsp");
                } else {
                    response.sendRedirect("employee/dashboard.jsp");
                }
                
            } else {
                // Login failed
                System.out.println("Login failed for username: " + username);
                
                // Set error message and redirect back to login
                request.setAttribute("errorMessage", "Invalid username or password");
                request.setAttribute("username", username); // Preserve username
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            // Handle any exceptions
            System.out.println("Error during login process: " + e.getMessage());
            e.printStackTrace();
            
            // Set error message and redirect back to login
            request.setAttribute("errorMessage", "An error occurred during login. Please try again.");
            request.setAttribute("username", username); // Preserve username
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
    
    /**
     * Check if user is already logged in
     */
    private boolean isUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Integer userId = (Integer) session.getAttribute("userId");
            String username = (String) session.getAttribute("username");
            
            if (userId != null && username != null) {
                // Validate session user
                return userService.validateSessionUser(userId, username);
            }
        }
        return false;
    }
    
    /**
     * Get user role from session
     */
    private String getUserRole(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (String) session.getAttribute("role");
        }
        return null;
    }
    
    /**
     * Redirect to appropriate dashboard based on user role
     */
    private void redirectToDashboard(HttpServletResponse response, String role) throws IOException {
        if ("ADMIN".equalsIgnoreCase(role)) {
            response.sendRedirect("admin/dashboard.jsp");
        } else {
            response.sendRedirect("employee/dashboard.jsp");
        }
    }
}
