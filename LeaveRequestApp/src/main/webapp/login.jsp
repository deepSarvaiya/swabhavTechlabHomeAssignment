<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Leave Management System</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="login-container">
            <div class="login-header">
                <div class="logo">
                    <i class="logo-icon">📋</i>
                    <h1>Leave Manager</h1>
                </div>
                <p class="subtitle">Sign in to your account</p>
            </div>

            <!-- Error Message Display -->
            <c:if test="${not empty errorMessage}">
                <div class="error-message">
                    <i class="error-icon">⚠️</i>
                    <span>${errorMessage}</span>
                </div>
            </c:if>

            <form action="LoginController" method="post" class="login-form">
                <div class="form-group">
                    <label for="username">Username</label>
                    <div class="input-wrapper">
                        <i class="input-icon">👤</i>
                        <input type="text" id="username" name="username" required 
                               placeholder="Enter your username" 
                               value="${username}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <div class="input-wrapper">
                        <i class="input-icon">🔒</i>
                        <input type="password" id="password" name="password" required 
                               placeholder="Enter your password">
                    </div>
                </div>

                <div class="form-options">
                    <label class="checkbox-wrapper">
                        <input type="checkbox" name="remember" id="remember">
                        <span class="checkmark"></span>
                        Remember me
                    </label>
                    <a href="#" class="forgot-password">Forgot Password?</a>
                </div>

                <button type="submit" class="login-btn">
                    <span>Sign In</span>
                    <i class="btn-icon">→</i>
                </button>
            </form>

            <div class="register-link">
                <p>Don't have an account? <a href="register.jsp">Register here</a></p>
            </div>

            <div class="features">
                <div class="feature-item">
                    <i class="feature-icon">📅</i>
                    <span>Easy Leave Management</span>
                </div>
                <div class="feature-item">
                    <i class="feature-icon">⚡</i>
                    <span>Quick Approvals</span>
                </div>
                <div class="feature-item">
                    <i class="feature-icon">📊</i>
                    <span>Real-time Reports</span>
                </div>
            </div>
        </div>
    </div>

    <script src="js/login.js"></script>
</body>
</html>
