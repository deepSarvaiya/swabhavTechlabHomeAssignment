<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Leave Management System</title>
    <link rel="stylesheet" href="../css/dashboard.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <!-- Session Check -->
    <c:if test="${empty sessionScope.userId or sessionScope.role != 'ADMIN'}">
        <c:redirect url="../login.jsp" />
    </c:if>

    <div class="dashboard-container">
        <!-- Sidebar Navigation -->
        <nav class="sidebar">
            <div class="sidebar-header">
                <div class="logo">
                    <i class="logo-icon">📋</i>
                    <h2>Admin Panel</h2>
                </div>
            </div>
            
            <div class="user-info">
                <div class="user-avatar">
                    <i class="fas fa-user-shield"></i>
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
                    <a href="leave-approvals.jsp" class="nav-link">
                        <i class="fas fa-clipboard-check"></i>
                        <span>Leave Approvals</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="employees.jsp" class="nav-link">
                        <i class="fas fa-users"></i>
                        <span>Employees</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="leave-types.jsp" class="nav-link">
                        <i class="fas fa-tags"></i>
                        <span>Leave Types</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="reports.jsp" class="nav-link">
                        <i class="fas fa-chart-bar"></i>
                        <span>Reports</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="settings.jsp" class="nav-link">
                        <i class="fas fa-cog"></i>
                        <span>Settings</span>
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
                    <h1>Welcome, ${sessionScope.fullName}!</h1>
                </div>
                <div class="header-right">
                    <div class="notifications">
                        <i class="fas fa-bell"></i>
                        <span class="notification-badge">5</span>
                    </div>
                    <div class="user-menu">
                        <img src="https://ui-avatars.com/api/?name=${sessionScope.fullName}&background=667eea&color=fff" 
                             alt="Admin Avatar" class="user-avatar-small">
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
                            <i class="fas fa-users"></i>
                        </div>
                        <div class="stat-content">
                            <h3>24</h3>
                            <p>Total Employees</p>
                        </div>
                    </div>
                    
                    <div class="stat-card">
                        <div class="stat-icon">
                            <i class="fas fa-clock"></i>
                        </div>
                        <div class="stat-content">
                            <h3>8</h3>
                            <p>Pending Approvals</p>
                        </div>
                    </div>
                    
                    <div class="stat-card">
                        <div class="stat-icon">
                            <i class="fas fa-calendar-check"></i>
                        </div>
                        <div class="stat-content">
                            <h3>156</h3>
                            <p>Approved This Month</p>
                        </div>
                    </div>
                    
                    <div class="stat-card">
                        <div class="stat-icon">
                            <i class="fas fa-chart-line"></i>
                        </div>
                        <div class="stat-content">
                            <h3>92%</h3>
                            <p>Approval Rate</p>
                        </div>
                    </div>
                </div>

                <!-- Main Dashboard Sections -->
                <div class="dashboard-sections">
                    <!-- Pending Leave Requests -->
                    <div class="dashboard-section">
                        <div class="section-header">
                            <h2>Pending Leave Requests</h2>
                            <a href="leave-approvals.jsp" class="view-all">View All</a>
                        </div>
                        <div class="leave-requests-table">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Employee</th>
                                        <th>Leave Type</th>
                                        <th>From</th>
                                        <th>To</th>
                                        <th>Days</th>
                                        <th>Reason</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <div class="employee-info">
                                                <img src="https://ui-avatars.com/api/?name=John+Doe&background=667eea&color=fff" 
                                                     alt="John Doe" class="employee-avatar">
                                                <div>
                                                    <strong>John Doe</strong>
                                                    <small>Software Developer</small>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="leave-type-badge casual">
                                                <i class="fas fa-umbrella-beach"></i>
                                                Casual Leave
                                            </div>
                                        </td>
                                        <td>Dec 20, 2024</td>
                                        <td>Dec 22, 2024</td>
                                        <td>3 days</td>
                                        <td>Family vacation</td>
                                        <td>
                                            <button class="btn-approve" title="Approve">
                                                <i class="fas fa-check"></i>
                                            </button>
                                            <button class="btn-reject" title="Reject">
                                                <i class="fas fa-times"></i>
                                            </button>
                                            <button class="btn-view" title="View Details">
                                                <i class="fas fa-eye"></i>
                                            </button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="employee-info">
                                                <img src="https://ui-avatars.com/api/?name=Jane+Smith&background=48bb78&color=fff" 
                                                     alt="Jane Smith" class="employee-avatar">
                                                <div>
                                                    <strong>Jane Smith</strong>
                                                    <small>UI/UX Designer</small>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="leave-type-badge sick">
                                                <i class="fas fa-procedures"></i>
                                                Sick Leave
                                            </div>
                                        </td>
                                        <td>Dec 18, 2024</td>
                                        <td>Dec 19, 2024</td>
                                        <td>2 days</td>
                                        <td>Medical appointment</td>
                                        <td>
                                            <button class="btn-approve" title="Approve">
                                                <i class="fas fa-check"></i>
                                            </button>
                                            <button class="btn-reject" title="Reject">
                                                <i class="fas fa-times"></i>
                                            </button>
                                            <button class="btn-view" title="View Details">
                                                <i class="fas fa-eye"></i>
                                            </button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="employee-info">
                                                <img src="https://ui-avatars.com/api/?name=Mike+Johnson&background=f6ad55&color=fff" 
                                                     alt="Mike Johnson" class="employee-avatar">
                                                <div>
                                                    <strong>Mike Johnson</strong>
                                                    <small>Project Manager</small>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="leave-type-badge earned">
                                                <i class="fas fa-star"></i>
                                                Earned Leave
                                            </div>
                                        </td>
                                        <td>Dec 25, 2024</td>
                                        <td>Dec 27, 2024</td>
                                        <td>3 days</td>
                                        <td>Holiday break</td>
                                        <td>
                                            <button class="btn-approve" title="Approve">
                                                <i class="fas fa-check"></i>
                                            </button>
                                            <button class="btn-reject" title="Reject">
                                                <i class="fas fa-times"></i>
                                            </button>
                                            <button class="btn-view" title="View Details">
                                                <i class="fas fa-eye"></i>
                                            </button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <!-- Employee Overview -->
                    <div class="dashboard-section">
                        <div class="section-header">
                            <h2>Employee Overview</h2>
                            <a href="employees.jsp" class="view-all">View All</a>
                        </div>
                        <div class="employee-overview-grid">
                            <div class="employee-overview-card">
                                <div class="overview-header">
                                    <i class="fas fa-user-clock"></i>
                                    <h4>On Leave Today</h4>
                                </div>
                                <div class="overview-content">
                                    <span class="overview-number">3</span>
                                    <p>employees</p>
                                </div>
                                <div class="overview-list">
                                    <div class="overview-item">
                                        <span>John Doe</span>
                                        <small>Casual Leave</small>
                                    </div>
                                    <div class="overview-item">
                                        <span>Sarah Wilson</span>
                                        <small>Sick Leave</small>
                                    </div>
                                    <div class="overview-item">
                                        <span>David Brown</span>
                                        <small>Earned Leave</small>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="employee-overview-card">
                                <div class="overview-header">
                                    <i class="fas fa-calendar-plus"></i>
                                    <h4>Leave Requests</h4>
                                </div>
                                <div class="overview-content">
                                    <span class="overview-number">8</span>
                                    <p>pending</p>
                                </div>
                                <div class="overview-stats">
                                    <div class="stat-item">
                                        <span class="stat-label">This Week</span>
                                        <span class="stat-value">12</span>
                                    </div>
                                    <div class="stat-item">
                                        <span class="stat-label">This Month</span>
                                        <span class="stat-value">45</span>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="employee-overview-card">
                                <div class="overview-header">
                                    <i class="fas fa-chart-pie"></i>
                                    <h4>Leave Distribution</h4>
                                </div>
                                <div class="overview-content">
                                    <div class="leave-distribution">
                                        <div class="distribution-item">
                                            <span class="dist-label">Casual</span>
                                            <div class="dist-bar">
                                                <div class="dist-fill" style="width: 35%"></div>
                                            </div>
                                            <span class="dist-value">35%</span>
                                        </div>
                                        <div class="distribution-item">
                                            <span class="dist-label">Sick</span>
                                            <div class="dist-bar">
                                                <div class="dist-fill" style="width: 25%"></div>
                                            </div>
                                            <span class="dist-value">25%</span>
                                        </div>
                                        <div class="distribution-item">
                                            <span class="dist-label">Earned</span>
                                            <div class="dist-bar">
                                                <div class="dist-fill" style="width: 40%"></div>
                                            </div>
                                            <span class="dist-value">40%</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Quick Actions -->
                    <div class="dashboard-section">
                        <div class="section-header">
                            <h2>Quick Actions</h2>
                        </div>
                        <div class="quick-actions-grid">
                            <a href="leave-approvals.jsp" class="quick-action-card">
                                <div class="action-icon">
                                    <i class="fas fa-clipboard-check"></i>
                                </div>
                                <h4>Review Requests</h4>
                                <p>Approve or reject leave requests</p>
                            </a>
                            
                            <a href="employees.jsp" class="quick-action-card">
                                <div class="action-icon">
                                    <i class="fas fa-user-plus"></i>
                                </div>
                                <h4>Add Employee</h4>
                                <p>Register new employees</p>
                            </a>
                            
                            <a href="reports.jsp" class="quick-action-card">
                                <div class="action-icon">
                                    <i class="fas fa-chart-bar"></i>
                                </div>
                                <h4>Generate Reports</h4>
                                <p>View leave statistics</p>
                            </a>
                            
                            <a href="settings.jsp" class="quick-action-card">
                                <div class="action-icon">
                                    <i class="fas fa-cog"></i>
                                </div>
                                <h4>System Settings</h4>
                                <p>Configure application</p>
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
