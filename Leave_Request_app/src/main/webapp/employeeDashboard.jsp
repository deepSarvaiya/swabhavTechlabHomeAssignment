<%@ page import="model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Employee emp = (Employee) session.getAttribute("employee");
    if (emp == null) { 
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
    int leaveBalance = emp.getLeaveBalance();
    int totalLeaves = 20; // Example
    int leavesTaken = totalLeaves - leaveBalance;
%>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Dashboard - Leave Management System</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        :root {
            --primary-bg: #0f172a;
            --secondary-bg: #1e293b;
            --card-bg: #334155;
            --accent-color: #3b82f6;
            --accent-hover: #60a5fa;
            --text-primary: #f8fafc;
            --text-secondary: #cbd5e1;
            --border-color: #475569;
            --success-color: #10b981;
            --warning-color: #f59e0b;
            --error-color: #ef4444;
            --gradient-primary: linear-gradient(135deg, #3b82f6, #1d4ed8);
            --gradient-secondary: linear-gradient(135deg, #1e293b, #334155);
            --gradient-success: linear-gradient(135deg, #10b981, #059669);
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
            background: var(--primary-bg);
            color: var(--text-primary);
            min-height: 100vh;
            position: relative;
            overflow-x: hidden;
        }

        /* Animated background */
        body::before {
            content: '';
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: 
                radial-gradient(circle at 20% 80%, rgba(59, 130, 246, 0.1) 0%, transparent 50%),
                radial-gradient(circle at 80% 20%, rgba(16, 185, 129, 0.1) 0%, transparent 50%),
                radial-gradient(circle at 40% 40%, rgba(139, 92, 246, 0.05) 0%, transparent 50%);
            z-index: -1;
        }

        .dashboard-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 2rem;
            min-height: 100vh;
        }

        .welcome-section {
            text-align: center;
            margin-bottom: 3rem;
            position: relative;
        }

        .welcome-section h1 {
            font-size: 3rem;
            font-weight: 700;
            background: var(--gradient-primary);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
            margin-bottom: 1rem;
            position: relative;
        }

        .welcome-section h1::after {
            content: '';
            position: absolute;
            bottom: -10px;
            left: 50%;
            transform: translateX(-50%);
            width: 120px;
            height: 4px;
            background: var(--gradient-primary);
            border-radius: 2px;
        }

        .employee-name {
            color: var(--text-primary);
            font-size: 1.5rem;
            font-weight: 600;
            margin-bottom: 0.5rem;
        }

        .leave-stats {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 1.5rem;
            margin-bottom: 3rem;
        }

        .stat-card {
            background: var(--gradient-secondary);
            border: 1px solid var(--border-color);
            border-radius: 20px;
            padding: 2rem;
            text-align: center;
            transition: all 0.4s cubic-bezier(0.4, 0, 0.2, 1);
            position: relative;
            overflow: hidden;
            box-shadow: 
                0 10px 25px -5px rgba(0, 0, 0, 0.3),
                0 0 0 1px rgba(255, 255, 255, 0.05);
        }

        .stat-card::before {
            content: '';
            position: absolute;
            top: 0;
            left: -100%;
            width: 100%;
            height: 100%;
            background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.1), transparent);
            transition: left 0.6s;
        }

        .stat-card:hover::before {
            left: 100%;
        }

        .stat-card:hover {
            transform: translateY(-8px);
            box-shadow: 
                0 20px 40px -10px rgba(0, 0, 0, 0.4),
                0 0 0 1px rgba(255, 255, 255, 0.1);
        }

        .stat-card.balance {
            border-color: var(--success-color);
        }

        .stat-card.taken {
            border-color: var(--warning-color);
        }

        .stat-card.total {
            border-color: var(--accent-color);
        }

        .stat-card .icon {
            font-size: 3rem;
            margin-bottom: 1rem;
            display: block;
        }

        .stat-card.balance .icon {
            color: var(--success-color);
        }

        .stat-card.taken .icon {
            color: var(--warning-color);
        }

        .stat-card.total .icon {
            color: var(--accent-color);
        }

        .stat-card .number {
            font-size: 2.5rem;
            font-weight: 700;
            color: var(--text-primary);
            margin-bottom: 0.5rem;
        }

        .stat-card .label {
            color: var(--text-secondary);
            font-size: 1rem;
            font-weight: 500;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .chart-section {
            background: var(--gradient-secondary);
            border: 1px solid var(--border-color);
            border-radius: 20px;
            padding: 2rem;
            margin-bottom: 3rem;
            box-shadow: 
                0 10px 25px -5px rgba(0, 0, 0, 0.3),
                0 0 0 1px rgba(255, 255, 255, 0.05);
        }

        .chart-section h3 {
            text-align: center;
            color: var(--text-primary);
            font-size: 1.5rem;
            font-weight: 600;
            margin-bottom: 2rem;
        }

        .chart-container {
            position: relative;
            height: 300px;
            margin: 0 auto;
            max-width: 400px;
        }

        .actions-section {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 1.5rem;
            margin-bottom: 3rem;
        }

        .action-btn {
            background: var(--gradient-secondary);
            border: 1px solid var(--border-color);
            color: var(--text-primary);
            padding: 1.5rem 2rem;
            border-radius: 16px;
            text-decoration: none;
            font-weight: 600;
            font-size: 1.1rem;
            transition: all 0.4s cubic-bezier(0.4, 0, 0.2, 1);
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 1rem;
            box-shadow: 
                0 8px 20px -5px rgba(0, 0, 0, 0.3),
                0 0 0 1px rgba(255, 255, 255, 0.05);
        }

        .action-btn:hover {
            transform: translateY(-5px);
            box-shadow: 
                0 15px 30px -10px rgba(0, 0, 0, 0.4),
                0 0 0 1px rgba(255, 255, 255, 0.1);
            color: var(--text-primary);
        }

        .action-btn.apply {
            border-color: var(--success-color);
        }

        .action-btn.apply:hover {
            background: var(--gradient-success);
            border-color: var(--success-color);
        }

        .action-btn.requests {
            border-color: var(--accent-color);
        }

        .action-btn.requests:hover {
            background: var(--gradient-primary);
            border-color: var(--accent-color);
        }

        .action-btn.logout {
            border-color: var(--error-color);
        }

        .action-btn.logout:hover {
            background: linear-gradient(135deg, #ef4444, #dc2626);
            border-color: var(--error-color);
        }

        .action-btn .icon {
            font-size: 1.5rem;
        }

        .messages {
            margin-bottom: 2rem;
        }

        .message {
            padding: 1rem 1.5rem;
            border-radius: 12px;
            font-weight: 500;
            text-align: center;
            margin-bottom: 1rem;
        }

        .message.success {
            background: rgba(16, 185, 129, 0.1);
            color: var(--success-color);
            border: 1px solid rgba(16, 185, 129, 0.3);
        }

        .message.error {
            background: rgba(239, 68, 68, 0.1);
            color: var(--error-color);
            border: 1px solid rgba(239, 68, 68, 0.3);
        }

        /* Responsive design */
        @media (max-width: 768px) {
            .dashboard-container {
                padding: 1rem;
            }
            
            .welcome-section h1 {
                font-size: 2.5rem;
            }
            
            .leave-stats {
                grid-template-columns: 1fr;
                gap: 1rem;
            }
            
            .actions-section {
                grid-template-columns: 1fr;
                gap: 1rem;
            }
        }

        @media (max-width: 480px) {
            .welcome-section h1 {
                font-size: 2rem;
            }
            
            .stat-card {
                padding: 1.5rem;
            }
            
            .action-btn {
                padding: 1.25rem 1.5rem;
                font-size: 1rem;
            }
        }

        /* Custom scrollbar */
        ::-webkit-scrollbar {
            width: 8px;
        }

        ::-webkit-scrollbar-track {
            background: var(--secondary-bg);
        }

        ::-webkit-scrollbar-thumb {
            background: var(--border-color);
            border-radius: 4px;
        }

        ::-webkit-scrollbar-thumb:hover {
            background: var(--accent-color);
        }
    </style>
</head>
<body>

<div class="dashboard-container">
    <div class="welcome-section">
        <h1>Welcome Back!</h1>
        <div class="employee-name"><%= emp.getName() %></div>
        <p class="text-light">Manage your leave requests and view your balance</p>
    </div>

    <div class="leave-stats">
        <div class="stat-card balance">
            <i class="fas fa-calendar-check icon"></i>
            <div class="number"><%= leaveBalance %></div>
            <div class="label">Days Available</div>
        </div>
        
        <div class="stat-card taken">
            <i class="fas fa-calendar-times icon"></i>
            <div class="number"><%= leavesTaken %></div>
            <div class="label">Days Taken</div>
        </div>
        
        <div class="stat-card total">
            <i class="fas fa-calendar-alt icon"></i>
            <div class="number"><%= totalLeaves %></div>
            <div class="label">Total Days</div>
        </div>
    </div>

    <div class="chart-section">
        <h3>Leave Balance Overview</h3>
        <div class="chart-container">
            <canvas id="leaveChart"></canvas>
        </div>
    </div>

    <div class="actions-section">
        <a href="<%=request.getContextPath()%>/employee?action=apply" class="action-btn apply">
            <i class="fas fa-plus icon"></i>
            Apply for Leave
        </a>
        
        <a href="<%=request.getContextPath()%>/employee?action=myrequests" class="action-btn requests">
            <i class="fas fa-list icon"></i>
            My Requests
        </a>
        
        <a href="<%=request.getContextPath()%>/logout" class="action-btn logout">
            <i class="fas fa-sign-out-alt icon"></i>
            Sign Out
        </a>
        </div>

    <div class="messages">
        <% if (request.getAttribute("message") != null) { %>
            <div class="message success"><%= request.getAttribute("message") %></div>
        <% } %>
        <% if (request.getAttribute("error") != null) { %>
            <div class="message error"><%= request.getAttribute("error") %></div>
        <% } %>
    </div>
</div>

<script>
    // Chart.js configuration for leave balance
    const ctx = document.getElementById('leaveChart').getContext('2d');
    
    new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ['Available', 'Taken'],
            datasets: [{
                data: [<%= leaveBalance %>, <%= leavesTaken %>],
                backgroundColor: [
                    '#10b981',
                    '#f59e0b'
                ],
                borderColor: [
                    '#059669',
                    '#d97706'
                ],
                borderWidth: 2,
                hoverOffset: 4
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    position: 'bottom',
                    labels: {
                        color: '#cbd5e1',
                        font: {
                            size: 14,
                            family: 'Inter'
                        },
                        padding: 20
                    }
                }
            },
            cutout: '60%',
            animation: {
                animateRotate: true,
                animateScale: true,
                duration: 2000,
                easing: 'easeOutQuart'
            }
        }
    });

    // Add staggered animation on load
    document.addEventListener('DOMContentLoaded', function() {
        const statCards = document.querySelectorAll('.stat-card');
        const actionBtns = document.querySelectorAll('.action-btn');
        
        // Animate stat cards
        statCards.forEach((card, index) => {
            card.style.opacity = '0';
            card.style.transform = 'translateY(20px)';
            
            setTimeout(() => {
                card.style.transition = 'all 0.6s cubic-bezier(0.4, 0, 0.2, 1)';
                card.style.opacity = '1';
                card.style.transform = 'translateY(0)';
            }, index * 150);
        });
        
        // Animate action buttons
        actionBtns.forEach((btn, index) => {
            btn.style.opacity = '0';
            btn.style.transform = 'translateY(20px)';
            
            setTimeout(() => {
                btn.style.transition = 'all 0.6s cubic-bezier(0.4, 0, 0.2, 1)';
                btn.style.opacity = '1';
                btn.style.transform = 'translateY(0)';
            }, (statCards.length * 150) + (index * 100));
        });
    });
</script>

</body>
</html>
