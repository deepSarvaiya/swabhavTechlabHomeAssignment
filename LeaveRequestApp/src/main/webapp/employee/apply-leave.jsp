<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Apply Leave - Leave Management System</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/dashboard.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>
    <div class="dashboard-container">
        <!-- Sidebar -->
        <div class="sidebar">
            <div class="sidebar-header">
                <div class="logo">
                    <i class="logo-icon">📋</i>
                    <h2>LeaveMS</h2>
                </div>
            </div>
            <nav class="sidebar-nav">
                <a href="dashboard.jsp" class="nav-item">
                    <i class="nav-icon">🏠</i>
                    <span>Dashboard</span>
                </a>
                <a href="ApplyLeaveController" class="nav-item active">
                    <i class="nav-icon">📝</i>
                    <span>Apply Leave</span>
                </a>
                <a href="#" class="nav-item">
                    <i class="nav-icon">📊</i>
                    <span>My Requests</span>
                </a>
                <a href="#" class="nav-item">
                    <i class="nav-icon">📅</i>
                    <span>Leave Balance</span>
                </a>
                <a href="../LogoutController" class="nav-item">
                    <i class="nav-icon">🚪</i>
                    <span>Logout</span>
                </a>
            </nav>
        </div>

        <!-- Main Content -->
        <div class="main-content">
            <!-- Header -->
            <header class="dashboard-header">
                <div class="header-left">
                    <h1>Apply for Leave</h1>
                    <p>Submit your leave request for approval</p>
                </div>
                <div class="header-right">
                    <div class="user-menu">
                        <span class="user-name">${sessionScope.user.fullName}</span>
                        <span class="user-role">${sessionScope.user.role}</span>
                    </div>
                </div>
            </header>

            <!-- Apply Leave Form -->
            <div class="content-section">
                <div class="form-container">
                    <c:if test="${not empty errorMessage}">
                        <div class="error-message">
                            <i class="error-icon">⚠️</i>
                            <span>${errorMessage}</span>
                        </div>
                    </c:if>
                    
                    <form action="ApplyLeaveController" method="post" class="apply-leave-form">
                        <div class="form-row">
                            <div class="form-group">
                                <label for="leaveTypeId">Leave Type *</label>
                                <div class="input-wrapper select-wrapper">
                                    <i class="input-icon">📋</i>
                                    <select id="leaveTypeId" name="leaveTypeId" required>
                                        <option value="">Select Leave Type</option>
                                        <c:forEach var="leaveType" items="${leaveTypes}">
                                            <option value="${leaveType.leaveTypeId}" 
                                                    <c:if test="${leaveTypeId eq leaveType.leaveTypeId}">selected</c:if>>
                                                ${leaveType.leaveName} (Max: ${leaveType.maxDays} days)
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group">
                                <label for="startDate">Start Date *</label>
                                <div class="input-wrapper">
                                    <i class="input-icon">📅</i>
                                    <input type="date" id="startDate" name="startDate" required
                                           value="${startDate}" min="${java.time.LocalDate.now()}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="endDate">End Date *</label>
                                <div class="input-wrapper">
                                    <i class="input-icon">📅</i>
                                    <input type="date" id="endDate" name="endDate" required
                                           value="${endDate}" min="${java.time.LocalDate.now()}">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="reason">Reason for Leave *</label>
                            <div class="input-wrapper">
                                <i class="input-icon">💭</i>
                                <textarea id="reason" name="reason" rows="4" required
                                          placeholder="Please provide a detailed reason for your leave request">${reason}</textarea>
                            </div>
                        </div>

                        <div class="form-actions">
                            <button type="button" class="btn-secondary" onclick="history.back()">
                                <i class="btn-icon">←</i>
                                <span>Cancel</span>
                            </button>
                            <button type="submit" class="btn-primary">
                                <span>Submit Request</span>
                                <i class="btn-icon">→</i>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="../js/dashboard.js"></script>
    <script src="../js/apply-leave.js"></script>
</body>
</html>
