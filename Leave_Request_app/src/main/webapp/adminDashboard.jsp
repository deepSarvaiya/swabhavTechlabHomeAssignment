<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard - Leave Management System</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
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
            --error-color: #ef4444;
            --gradient-primary: linear-gradient(135deg, #3b82f6, #1d4ed8);
            --gradient-secondary: linear-gradient(135deg, #1e293b, #334155);
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
            display: flex;
            flex-direction: column;
        }

        .dashboard-header {
            text-align: center;
            margin-bottom: 3rem;
            position: relative;
        }

        .dashboard-header h1 {
            font-size: 3rem;
            font-weight: 700;
            background: var(--gradient-primary);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
            margin-bottom: 0.5rem;
            position: relative;
        }

        .dashboard-header h1::after {
            content: '';
            position: absolute;
            bottom: -10px;
            left: 50%;
            transform: translateX(-50%);
            width: 100px;
            height: 4px;
            background: var(--gradient-primary);
            border-radius: 2px;
        }

        .dashboard-subtitle {
            color: var(--text-secondary);
            font-size: 1.1rem;
            font-weight: 400;
        }

        .nav-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 1.5rem;
            margin-bottom: 3rem;
        }

        .nav-card {
            background: var(--gradient-secondary);
            border: 1px solid var(--border-color);
            border-radius: 20px;
            padding: 2rem;
            text-decoration: none;
            color: var(--text-primary);
            transition: all 0.4s cubic-bezier(0.4, 0, 0.2, 1);
            position: relative;
            overflow: hidden;
            display: flex;
            align-items: center;
            gap: 1.5rem;
            box-shadow: 
                0 10px 25px -5px rgba(0, 0, 0, 0.3),
                0 0 0 1px rgba(255, 255, 255, 0.05);
        }

        .nav-card::before {
            content: '';
            position: absolute;
            top: 0;
            left: -100%;
            width: 100%;
            height: 100%;
            background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.1), transparent);
            transition: left 0.6s;
        }

        .nav-card:hover::before {
            left: 100%;
        }

        .nav-card:hover {
            transform: translateY(-8px) scale(1.02);
            box-shadow: 
                0 20px 40px -10px rgba(0, 0, 0, 0.4),
                0 0 0 1px rgba(255, 255, 255, 0.1);
            border-color: var(--accent-color);
        }

        .nav-card .icon {
            font-size: 2.5rem;
            color: var(--accent-color);
            transition: all 0.3s ease;
        }

        .nav-card:hover .icon {
            transform: scale(1.1);
            color: var(--accent-hover);
        }

        .nav-card .content h3 {
            font-size: 1.25rem;
            font-weight: 600;
            margin-bottom: 0.5rem;
            color: var(--text-primary);
        }

        .nav-card .content p {
            color: var(--text-secondary);
            font-size: 0.9rem;
            margin: 0;
        }

        .logout-section {
            text-align: center;
            margin-top: auto;
            padding-top: 2rem;
        }

        .logout-btn {
            background: var(--gradient-secondary);
            border: 1px solid var(--border-color);
            color: var(--text-primary);
            padding: 1rem 2rem;
            border-radius: 12px;
            text-decoration: none;
            font-weight: 500;
            transition: all 0.3s ease;
            display: inline-flex;
            align-items: center;
            gap: 0.5rem;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
        }

        .logout-btn:hover {
            background: var(--gradient-primary);
            border-color: var(--accent-color);
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(59, 130, 246, 0.3);
            color: var(--text-primary);
        }

        .footer {
            text-align: center;
            margin-top: 2rem;
            padding: 1.5rem 0;
            color: var(--text-secondary);
            font-size: 0.9rem;
            border-top: 1px solid var(--border-color);
        }

        /* Responsive design */
        @media (max-width: 768px) {
            .dashboard-container {
                padding: 1rem;
            }
            
            .dashboard-header h1 {
                font-size: 2.5rem;
            }
            
            .nav-grid {
                grid-template-columns: 1fr;
                gap: 1rem;
            }
            
            .nav-card {
                padding: 1.5rem;
            }
        }

        @media (max-width: 480px) {
            .dashboard-header h1 {
                font-size: 2rem;
            }
            
            .nav-card {
                flex-direction: column;
                text-align: center;
                gap: 1rem;
            }
        }

        /* Loading animation */
        .nav-card:active {
            transform: scale(0.98);
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
    <div class="dashboard-header">
        <h1>Admin Dashboard</h1>
        <p class="dashboard-subtitle">Manage leave requests and system operations</p>
    </div>

    <div class="nav-grid">
        <a href="<%=request.getContextPath()%>/admin?action=view" class="nav-card">
            <div class="icon">
                <i class="fas fa-file-alt"></i>
            </div>
            <div class="content">
                <h3>View All Requests</h3>
                <p>Review and manage employee leave applications</p>
            </div>
        </a>

        <a href="<%=request.getContextPath()%>/admin?action=reports" class="nav-card">
            <div class="icon">
                <i class="fas fa-chart-bar"></i>
            </div>
            <div class="content">
                <h3>Analytics & Reports</h3>
                <p>View leave statistics and generate reports</p>
            </div>
        </a>

        <a href="<%=request.getContextPath()%>/admin?action=employees" class="nav-card">
            <div class="icon">
                <i class="fas fa-users"></i>
            </div>
            <div class="content">
                <h3>Manage Employees</h3>
                <p>Add, edit, or remove employee accounts</p>
            </div>
        </a>

        <a href="<%=request.getContextPath()%>/admin?action=settings" class="nav-card">
            <div class="icon">
                <i class="fas fa-cog"></i>
            </div>
            <div class="content">
                <h3>System Settings</h3>
                <p>Configure application preferences and policies</p>
            </div>
        </a>
    </div>

    <div class="logout-section">
        <a href="<%=request.getContextPath()%>/logout" class="logout-btn" onclick="return confirmLogout(event)">
            <i class="fas fa-sign-out-alt"></i>
            Sign Out
        </a>
    </div>

    <div class="footer">
        &copy; <%= java.time.Year.now() %> Leave Management System. All Rights Reserved.
    </div>
</div>

<script>
    function confirmLogout(e) {
        if (!confirm("Are you sure you want to sign out?")) {
            e.preventDefault();
            return false;
        }
        return true;
    }

    // Add hover effects and animations
    document.addEventListener('DOMContentLoaded', function() {
        const navCards = document.querySelectorAll('.nav-card');
        
        navCards.forEach((card, index) => {
            // Add staggered animation on load
            card.style.opacity = '0';
            card.style.transform = 'translateY(20px)';
            
            setTimeout(() => {
                card.style.transition = 'all 0.6s cubic-bezier(0.4, 0, 0.2, 1)';
                card.style.opacity = '1';
                card.style.transform = 'translateY(0)';
            }, index * 100);
        });
    });
</script>

</body>
</html>
