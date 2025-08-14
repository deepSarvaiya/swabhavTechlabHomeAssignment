<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Dashboard - Leave Management System</title>
    <link rel="stylesheet" href="../css/dashboard.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <!-- Session Check -->
    <c:if test="${empty sessionScope.userId or sessionScope.role != 'EMPLOYEE'}">
        <c:redirect url="../login.jsp" />
    </c:if>

    <div class="dashboard-container">
        <!-- Sidebar Navigation -->
        <nav class="sidebar">
            <div class="sidebar-header">
                <div class="logo">
                    <i class="logo-icon">📋</i>
                    <h2>Leave Manager</h2>
                </div>
            </div>
            
            <div class="user-info">
                <div class="user-avatar">
                    <i class="fas fa-user"></i>
                </div>
                <div class="user-details">
                    <h4>${sessionScope.fullName}</h4>
                    <p>${sessionScope.role}</p>
                </div>
            </div>
            
            <ul class="nav-menu">
                <li class="nav-item active">
                    <a href="dashboard.jsp" class="nav-link">
                        <i class="fas fa-tachometer-alt"></i>
                        <span>Dashboard</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="../ApplyLeaveController" class="nav-link">
                        <i class="fas fa-plus-circle"></i>
                        <span>Apply Leave</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="my-leaves.jsp" class="nav-link">
                        <i class="fas fa-calendar-check"></i>
                        <span>My Leaves</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="leave-balance.jsp" class="nav-link">
                        <i class="fas fa-chart-pie"></i>
                        <span>Leave Balance</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="profile.jsp" class="nav-link">
                        <i class="fas fa-user-cog"></i>
                        <span>Profile</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="../LogoutController" class="nav-link">
                        <i class="fas fa-sign-out-alt"></i>
                        <span>Logout</span>
                    </a>
                </li>
            </ul>
        </nav>

        <!-- Main Content Area -->
        <main class="main-content">
            <!-- Top Header -->
            <header class="top-header">
                <div class="header-left">
                    <button class="sidebar-toggle" id="sidebarToggle">
                        <i class="fas fa-bars"></i>
                    </button>
                    <h1>Welcome back, ${sessionScope.fullName}!</h1>
                </div>
                <div class="header-right">
                    <div class="notifications">
                        <i class="fas fa-bell"></i>
                        <span class="notification-badge">3</span>
                    </div>
                    <div class="user-menu">
                        <img src="https://ui-avatars.com/api/?name=${sessionScope.fullName}&background=667eea&color=fff" 
                             alt="User Avatar" class="user-avatar-small">
                        <span>${sessionScope.fullName}</span>
                        <i class="fas fa-chevron-down"></i>
                    </div>
                </div>
            </header>

            <!-- Dashboard Content -->
            <div class="dashboard-content">
                <!-- Quick Stats Cards -->
                <div class="stats-grid">
                    <div class="stat-card">
                        <div class="stat-icon">
                            <i class="fas fa-calendar-plus"></i>
                        </div>
                        <div class="stat-content">
                            <h3>12</h3>
                            <p>Total Leave Days</p>
                        </div>
                    </div>
                    
                    <div class="stat-card">
                        <div class="stat-icon">
                            <i class="fas fa-clock"></i>
                        </div>
                        <div class="stat-content">
                            <h3>3</h3>
                            <p>Pending Requests</p>
                        </div>
                    </div>
                    
                    <div class="stat-card">
                        <div class="stat-icon">
                            <i class="fas fa-check-circle"></i>
                        </div>
                        <div class="stat-content">
                            <h3>8</h3>
                            <p>Approved Leaves</p>
                        </div>
                    </div>
                    
                    <div class="stat-card">
                        <div class="stat-icon">
                            <i class="fas fa-times-circle"></i>
                        </div>
                        <div class="stat-content">
                            <h3>1</h3>
                            <p>Rejected Leaves</p>
                        </div>
                    </div>
                </div>

                <!-- Main Dashboard Sections -->
                <div class="dashboard-sections">
                    <!-- Leave Balance Section -->
                    <div class="dashboard-section">
                        <div class="section-header">
                            <h2>Leave Balance</h2>
                            <a href="leave-balance.jsp" class="view-all">View All</a>
                        </div>
                        <div class="leave-balance-grid">
                            <div class="leave-type-card">
                                <div class="leave-type-header">
                                    <i class="fas fa-umbrella-beach"></i>
                                    <h4>Casual Leave</h4>
                                </div>
                                <div class="leave-balance-info">
                                    <div class="balance-circle">
                                        <span class="balance-number">8</span>
                                        <span class="balance-label">days left</span>
                                    </div>
                                    <div class="balance-details">
                                        <p>Total: 12 days</p>
                                        <p>Used: 4 days</p>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="leave-type-card">
                                <div class="leave-type-header">
                                    <i class="fas fa-procedures"></i>
                                    <h4>Sick Leave</h4>
                                </div>
                                <div class="leave-balance-info">
                                    <div class="balance-circle">
                                        <span class="balance-number">7</span>
                                        <span class="balance-label">days left</span>
                                    </div>
                                    <div class="balance-details">
                                        <p>Total: 10 days</p>
                                        <p>Used: 3 days</p>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="leave-type-card">
                                <div class="leave-type-header">
                                    <i class="fas fa-star"></i>
                                    <h4>Earned Leave</h4>
                                </div>
                                <div class="leave-balance-info">
                                    <div class="balance-circle">
                                        <span class="balance-number">12</span>
                                        <span class="balance-label">days left</span>
                                    </div>
                                    <div class="balance-details">
                                        <p>Total: 15 days</p>
                                        <p>Used: 3 days</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Recent Leave Requests -->
                    <div class="dashboard-section">
                        <div class="section-header">
                            <h2>Recent Leave Requests</h2>
                            <a href="my-leaves.jsp" class="view-all">View All</a>
                        </div>
                        <div class="leave-requests-table">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Leave Type</th>
                                        <th>From</th>
                                        <th>To</th>
                                        <th>Days</th>
                                        <th>Status</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <div class="leave-type-badge casual">
                                                <i class="fas fa-umbrella-beach"></i>
                                                Casual Leave
                                            </div>
                                        </td>
                                        <td>Dec 15, 2024</td>
                                        <td>Dec 16, 2024</td>
                                        <td>2 days</td>
                                        <td>
                                            <span class="status-badge pending">Pending</span>
                                        </td>
                                        <td>
                                            <button class="btn-edit" title="Edit">
                                                <i class="fas fa-edit"></i>
                                            </button>
                                            <button class="btn-cancel" title="Cancel">
                                                <i class="fas fa-times"></i>
                                            </button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="leave-type-badge sick">
                                                <i class="fas fa-procedures"></i>
                                                Sick Leave
                                            </div>
                                        </td>
                                        <td>Dec 10, 2024</td>
                                        <td>Dec 12, 2024</td>
                                        <td>3 days</td>
                                        <td>
                                            <span class="status-badge approved">Approved</span>
                                        </td>
                                        <td>
                                            <button class="btn-view" title="View">
                                                <i class="fas fa-eye"></i>
                                            </button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="leave-type-badge earned">
                                                <i class="fas fa-star"></i>
                                                Earned Leave
                                            </div>
                                        </td>
                                        <td>Nov 25, 2024</td>
                                        <td>Nov 27, 2024</td>
                                        <td>3 days</td>
                                        <td>
                                            <span class="status-badge rejected">Rejected</span>
                                        </td>
                                        <td>
                                            <button class="btn-view" title="View">
                                                <i class="fas fa-eye"></i>
                                            </button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <!-- Quick Actions -->
                    <div class="dashboard-section">
                        <div class="section-header">
                            <h2>Quick Actions</h2>
                        </div>
                        <div class="quick-actions-grid">
                            <a href="apply-leave.jsp" class="quick-action-card">
                                <div class="action-icon">
                                    <i class="fas fa-plus-circle"></i>
                                </div>
                                <h4>Apply for Leave</h4>
                                <p>Submit a new leave request</p>
                            </a>
                            
                            <a href="leave-balance.jsp" class="quick-action-card">
                                <div class="action-icon">
                                    <i class="fas fa-chart-pie"></i>
                                </div>
                                <h4>Check Balance</h4>
                                <p>View your leave balance</p>
                            </a>
                            
                            <a href="my-leaves.jsp" class="quick-action-card">
                                <div class="action-icon">
                                    <i class="fas fa-calendar-check"></i>
                                </div>
                                <h4>My Leaves</h4>
                                <p>View all your leave requests</p>
                            </a>
                            
                            <a href="profile.jsp" class="quick-action-card">
                                <div class="action-icon">
                                    <i class="fas fa-user-cog"></i>
                                </div>
                                <h4>Update Profile</h4>
                                <p>Manage your account details</p>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>

    <script src="../js/dashboard.js"></script>
</body>
</html>
