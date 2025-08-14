package com.tss.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.model.User;
import com.tss.service.UserService;

/**
 * Servlet implementation class RegisterController
 * Handles user registration and account creation
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UserService userService;
    
    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect GET requests to registration page
        response.sendRedirect("register.jsp");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html;charset=UTF-8");
        
        // Get form parameters
        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String terms = request.getParameter("terms");
        String privacy = request.getParameter("privacy");
        
        // Validate input parameters
        if (!validateRegistrationInput(fullName, username, email, role, password, confirmPassword, terms, privacy)) {
            // Set error message and redirect back to registration
            request.setAttribute("errorMessage", "Please fill all required fields and agree to terms");
            request.setAttribute("user", createUserFromRequest(fullName, username, email, role));
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        // Validate password confirmation
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match");
            request.setAttribute("user", createUserFromRequest(fullName, username, email, role));
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        // Validate terms and privacy agreement
        if (!"on".equals(terms) || !"on".equals(privacy)) {
            request.setAttribute("errorMessage", "You must agree to Terms & Conditions and Privacy Policy");
            request.setAttribute("user", createUserFromRequest(fullName, username, email, role));
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        try {
            // Create user object
            User user = new User(username, password, fullName, email, role);
            
            // Attempt to register user
            boolean success = userService.registerUser(user);
            
            if (success) {
                // Registration successful
                System.out.println("User registered successfully: " + username);
                
                // Set success message and redirect to login
                request.setAttribute("successMessage", "Account created successfully! Please login with your credentials.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                
            } else {
                // Registration failed
                System.out.println("Registration failed for username: " + username);
                
                // Set error message and redirect back to registration
                request.setAttribute("errorMessage", "Registration failed. Username or email may already exist.");
                request.setAttribute("user", createUserFromRequest(fullName, username, email, role));
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            // Handle any exceptions
            System.out.println("Error during registration process: " + e.getMessage());
            e.printStackTrace();
            
            // Set error message and redirect back to registration
            request.setAttribute("errorMessage", "An error occurred during registration. Please try again.");
            request.setAttribute("user", createUserFromRequest(fullName, username, email, role));
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
    
    /**
     * Validate all registration input parameters
     */
    private boolean validateRegistrationInput(String fullName, String username, String email, 
                                           String role, String password, String confirmPassword, 
                                           String terms, String privacy) {
        
        // Check if all required fields are present
        if (fullName == null || fullName.trim().isEmpty() ||
            username == null || username.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            role == null || role.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            confirmPassword == null || confirmPassword.trim().isEmpty()) {
            return false;
        }
        
        // Validate field lengths
        if (fullName.trim().length() < 2 || username.trim().length() < 3 || 
            password.length() < 6) {
            return false;
        }
        
        // Validate email format
        if (!isValidEmail(email.trim())) {
            return false;
        }
        
        // Validate role
        if (!"EMPLOYEE".equals(role) && !"ADMIN".equals(role)) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Validate email format
     */
    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        // Basic email validation regex
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.trim().matches(emailRegex);
    }
    
    /**
     * Create a User object from request parameters for form repopulation
     */
    private User createUserFromRequest(String fullName, String username, String email, String role) {
        User user = new User();
        user.setFullName(fullName != null ? fullName.trim() : "");
        user.setUsername(username != null ? username.trim() : "");
        user.setEmail(email != null ? email.trim() : "");
        user.setRole(role != null ? role.trim() : "");
        return user;
    }
    
    /**
     * Check if user is already logged in
     */
    private boolean isUserLoggedIn(HttpServletRequest request) {
        return request.getSession(false) != null && 
               request.getSession().getAttribute("userId") != null;
    }
    
    /**
     * Redirect logged-in users to appropriate dashboard
     */
    private void redirectLoggedInUser(HttpServletResponse response, String role) throws IOException {
        if ("ADMIN".equalsIgnoreCase(role)) {
            response.sendRedirect("admin/dashboard.jsp");
        } else {
            response.sendRedirect("employee/dashboard.jsp");
        }
    }
}
