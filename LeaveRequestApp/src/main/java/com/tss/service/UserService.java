package com.tss.service;

import com.tss.dao.UserDao;
import com.tss.model.User;

public class UserService {
    
    private UserDao userDao;
    
    public UserService() {
        this.userDao = new UserDao();
    }
    
    // Register a new user
    public boolean registerUser(User user) {
        // Validate user data
        if (!validateUserData(user)) {
            return false;
        }
        
        // Check if username already exists
        if (userDao.isUsernameExists(user.getUsername())) {
            System.out.println("Username already exists: " + user.getUsername());
            return false;
        }
        
        // Check if email already exists
        if (userDao.isEmailExists(user.getEmail())) {
            System.out.println("Email already exists: " + user.getEmail());
            return false;
        }
        
        // Set default role if not specified
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            user.setRole("EMPLOYEE");
        }
        
        // Attempt to register user
        return userDao.register(user);
    }
    
    // Validate user login
    public User validateUser(String username, String password) {
        // Basic validation
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            return null;
        }
        
        // Attempt to validate user
        return userDao.validate(username.trim(), password);
    }
    
    // Validate user data for registration
    private boolean validateUserData(User user) {
        if (user == null) {
            return false;
        }
        
        // Validate username
        if (user.getUsername() == null || user.getUsername().trim().length() < 3) {
            System.out.println("Username must be at least 3 characters long");
            return false;
        }
        
        // Validate password
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            System.out.println("Password must be at least 6 characters long");
            return false;
        }
        
        // Validate full name
        if (user.getFullName() == null || user.getFullName().trim().isEmpty()) {
            System.out.println("Full name is required");
            return false;
        }
        
        // Validate email
        if (user.getEmail() == null || !isValidEmail(user.getEmail())) {
            System.out.println("Valid email is required");
            return false;
        }
        
        // Validate role
        if (user.getRole() != null && !isValidRole(user.getRole())) {
            System.out.println("Invalid role specified");
            return false;
        }
        
        return true;
    }
    
    // Validate email format
    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        // Basic email validation regex
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.trim().matches(emailRegex);
    }
    
    // Validate role
    private boolean isValidRole(String role) {
        if (role == null) {
            return false;
        }
        
        String upperRole = role.toUpperCase();
        return "EMPLOYEE".equals(upperRole) || "ADMIN".equals(upperRole);
    }
    
    // Get user by ID
    public User getUserById(int userId) {
        if (userId <= 0) {
            return null;
        }
        
        return userDao.getUserById(userId);
    }
    
    // Get all users (admin only)
    public java.util.List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    
    // Get users by role
    public java.util.List<User> getUsersByRole(String role) {
        if (!isValidRole(role)) {
            return new java.util.ArrayList<>();
        }
        
        return userDao.getUsersByRole(role);
    }
    
    // Update user profile
    public boolean updateUserProfile(User user) {
        if (user == null || user.getUserId() <= 0) {
            return false;
        }
        
        // Validate updated data
        if (user.getFullName() == null || user.getFullName().trim().isEmpty()) {
            return false;
        }
        
        if (user.getEmail() == null || !isValidEmail(user.getEmail())) {
            return false;
        }
        
        return userDao.updateUser(user);
    }
    
    // Change user password
    public boolean changeUserPassword(int userId, String currentPassword, String newPassword) {
        if (userId <= 0 || newPassword == null || newPassword.length() < 6) {
            return false;
        }
        
        // Verify current password
        User currentUser = userDao.getUserById(userId);
        if (currentUser == null || !currentUser.getPassword().equals(currentPassword)) {
            return false;
        }
        
        return userDao.changePassword(userId, newPassword);
    }
    
    // Delete user (admin only)
    public boolean deleteUser(int userId) {
        if (userId <= 0) {
            return false;
        }
        
        return userDao.deleteUser(userId);
    }
    
    // Get user statistics
    public java.util.Map<String, Integer> getUserStatistics() {
        java.util.Map<String, Integer> stats = new java.util.HashMap<>();
        
        stats.put("totalUsers", userDao.getUserCount());
        stats.put("employees", userDao.getUserCountByRole("EMPLOYEE"));
        stats.put("admins", userDao.getUserCountByRole("ADMIN"));
        
        return stats;
    }
    
    // Check if user is admin
    public boolean isUserAdmin(int userId) {
        User user = getUserById(userId);
        return user != null && user.isAdmin();
    }
    
    // Check if user is employee
    public boolean isUserEmployee(int userId) {
        User user = getUserById(userId);
        return user != null && user.isEmployee();
    }
    
    // Authenticate user and return user object
    public User authenticateUser(String username, String password) {
        User user = validateUser(username, password);
        
        if (user != null) {
            // Log successful login
            System.out.println("User logged in successfully: " + username);
        } else {
            // Log failed login attempt
            System.out.println("Failed login attempt for username: " + username);
        }
        
        return user;
    }
    
    // Validate session user
    public boolean validateSessionUser(int userId, String username) {
        if (userId <= 0 || username == null || username.trim().isEmpty()) {
            return false;
        }
        
        User user = getUserById(userId);
        return user != null && username.equals(user.getUsername());
    }
}
