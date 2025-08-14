<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - Leave Management System</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="register-container">
            <div class="register-header">
                <div class="logo">
                    <i class="logo-icon">📋</i>
                    <h1>Leave Manager</h1>
                </div>
                <p class="subtitle">Create your account</p>
            </div>

            <!-- Success/Error Message Display -->
            <c:if test="${not empty successMessage}">
                <div class="success-message">
                    <i class="success-icon">✅</i>
                    <span>${successMessage}</span>
                </div>
            </c:if>

            <c:if test="${not empty errorMessage}">
                <div class="error-message">
                    <i class="error-icon">⚠️</i>
                    <span>${errorMessage}</span>
                </div>
            </c:if>

            <form action="RegisterController" method="post" class="register-form" id="registerForm">
                <div class="form-row">
                    <div class="form-group">
                        <label for="fullName">Full Name</label>
                        <div class="input-wrapper">
                            <i class="input-icon">👤</i>
                            <input type="text" id="fullName" name="fullName" required 
                                   placeholder="Enter your full name" 
                                   value="${user.fullName}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="username">Username</label>
                        <div class="input-wrapper">
                            <i class="input-icon">🔑</i>
                            <input type="text" id="username" name="username" required 
                                   placeholder="Choose a username" 
                                   value="${user.username}">
                        </div>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="email">Email Address</label>
                        <div class="input-wrapper">
                            <i class="input-icon">📧</i>
                            <input type="email" id="email" name="email" required 
                                   placeholder="Enter your email" 
                                   value="${user.email}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="role">Role</label>
                        <div class="input-wrapper">
                            <i class="input-icon">👔</i>
                            <select id="role" name="role" required>
                                <option value="">Select your role</option>
                                <option value="EMPLOYEE" ${user.role == 'EMPLOYEE' ? 'selected' : ''}>Employee</option>
                                <option value="ADMIN" ${user.role == 'ADMIN' ? 'selected' : ''}>Administrator</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="password">Password</label>
                        <div class="input-wrapper">
                            <i class="input-icon">🔒</i>
                            <input type="password" id="password" name="password" required 
                                   placeholder="Create a password">
                            <button type="button" class="password-toggle" id="passwordToggle">
                                <i class="toggle-icon">👁️</i>
                            </button>
                        </div>
                        <div class="password-strength" id="passwordStrength">
                            <div class="strength-bar">
                                <div class="strength-fill" id="strengthFill"></div>
                            </div>
                            <span class="strength-text" id="strengthText">Password strength</span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="confirmPassword">Confirm Password</label>
                        <div class="input-wrapper">
                            <i class="input-icon">🔒</i>
                            <input type="password" id="confirmPassword" name="confirmPassword" required 
                                   placeholder="Confirm your password">
                            <button type="button" class="password-toggle" id="confirmPasswordToggle">
                                <i class="toggle-icon">👁️</i>
                            </button>
                        </div>
                    </div>
                </div>

                <div class="form-options">
                    <label class="checkbox-wrapper">
                        <input type="checkbox" name="terms" id="terms" required>
                        <span class="checkmark"></span>
                        I agree to the <a href="#" class="terms-link">Terms & Conditions</a>
                    </label>
                    <label class="checkbox-wrapper">
                        <input type="checkbox" name="privacy" id="privacy" required>
                        <span class="checkmark"></span>
                        I agree to the <a href="#" class="privacy-link">Privacy Policy</a>
                    </label>
                </div>

                <button type="submit" class="register-btn">
                    <span>Create Account</span>
                    <i class="btn-icon">→</i>
                </button>
            </form>

            <div class="login-link">
                <p>Already have an account? <a href="login.jsp">Sign in here</a></p>
            </div>

            <div class="features">
                <div class="feature-item">
                    <i class="feature-icon">🚀</i>
                    <span>Quick Setup</span>
                </div>
                <div class="feature-item">
                    <i class="feature-icon">🔐</i>
                    <span>Secure Access</span>
                </div>
                <div class="feature-item">
                    <i class="feature-icon">📱</i>
                    <span>Mobile Ready</span>
                </div>
            </div>
        </div>
    </div>

    <script src="js/register.js"></script>
</body>
</html>
