<%@ page import="model.Employee, model.LeaveRequest"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
Employee emp = (Employee) session.getAttribute("employee");
LeaveRequest leaveRequest = (LeaveRequest) request.getAttribute("leaveRequest");
if (emp == null || leaveRequest == null) {
	response.sendRedirect(request.getContextPath() + "/login");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Edit Leave Request - Leave Management System</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
<style>
    :root {
        --primary-bg: #0f172a;
        --secondary-bg: #1e293b;
        --card-bg: #334155;
        --accent-color: #10b981;
        --accent-hover: #059669;
        --text-primary: #f8fafc;
        --text-secondary: #cbd5e1;
        --border-color: #475569;
        --success-color: #10b981;
        --error-color: #ef4444;
        --gradient-primary: linear-gradient(135deg, #10b981, #059669);
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
            radial-gradient(circle at 20% 80%, rgba(16, 185, 129, 0.1) 0%, transparent 50%),
            radial-gradient(circle at 80% 20%, rgba(59, 130, 246, 0.1) 0%, transparent 50%),
            radial-gradient(circle at 40% 40%, rgba(139, 92, 246, 0.05) 0%, transparent 50%);
        z-index: -1;
    }

    .page-container {
        max-width: 600px;
        margin: 0 auto;
        padding: 2rem;
        min-height: 100vh;
        display: flex;
        flex-direction: column;
        justify-content: center;
    }

    .header-section {
        text-align: center;
        margin-bottom: 2rem;
    }

    .header-section h1 {
        font-size: 2.5rem;
        font-weight: 700;
        background: var(--gradient-primary);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
        background-clip: text;
        margin-bottom: 1rem;
        position: relative;
    }

    .header-section h1::after {
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

    .request-info {
        background: var(--gradient-secondary);
        border: 1px solid var(--border-color);
        border-radius: 16px;
        padding: 1.5rem;
        text-align: center;
        margin-bottom: 2rem;
        box-shadow: 
            0 10px 25px -5px rgba(0, 0, 0, 0.3),
            0 0 0 1px rgba(255, 255, 255, 0.05);
    }

    .request-info .request-id {
        font-size: 1.5rem;
        font-weight: 700;
        color: var(--accent-color);
        margin-bottom: 0.5rem;
    }

    .request-info .request-status {
        color: var(--text-secondary);
        font-size: 1rem;
        font-weight: 500;
    }

    .request-info .edit-note {
        color: var(--accent-color);
        font-size: 0.9rem;
        font-weight: 600;
        margin-top: 0.5rem;
        padding: 0.5rem 1rem;
        background: rgba(16, 185, 129, 0.1);
        border: 1px solid rgba(16, 185, 129, 0.3);
        border-radius: 8px;
        display: inline-block;
    }

    .form-container {
        background: var(--gradient-secondary);
        border: 1px solid var(--border-color);
        border-radius: 20px;
        padding: 2.5rem;
        box-shadow: 
            0 20px 40px -10px rgba(0, 0, 0, 0.4),
            0 0 0 1px rgba(255, 255, 255, 0.05);
        backdrop-filter: blur(20px);
    }

    .form-group {
        margin-bottom: 1.5rem;
    }

    .form-label {
        font-weight: 500;
        color: var(--text-secondary);
        margin-bottom: 0.5rem;
        font-size: 0.9rem;
        text-transform: uppercase;
        letter-spacing: 0.5px;
        display: block;
    }

    .form-input {
        width: 100%;
        padding: 1rem;
        background: var(--secondary-bg);
        border: 1px solid var(--border-color);
        border-radius: 12px;
        color: var(--text-primary);
        font-size: 1rem;
        transition: all 0.3s ease;
    }

    .form-input:focus {
        outline: none;
        border-color: var(--accent-color);
        box-shadow: 0 0 0 3px rgba(16, 185, 129, 0.2);
        transform: translateY(-2px);
    }

    .form-input::placeholder {
        color: var(--text-secondary);
        opacity: 0.7;
    }

    .form-input:disabled {
        background: var(--primary-bg);
        border-color: var(--border-color);
        color: var(--text-secondary);
        opacity: 0.6;
        cursor: not-allowed;
        transform: none;
    }

    .form-input:disabled:focus {
        border-color: var(--border-color);
        box-shadow: none;
        transform: none;
    }

    .button-group {
        display: flex;
        gap: 1rem;
        margin-top: 1.5rem;
    }

    .submit-btn, .cancel-btn {
        flex: 1;
        background: var(--gradient-primary);
        border: none;
        color: var(--text-primary);
        font-weight: 600;
        padding: 1rem 2rem;
        border-radius: 12px;
        font-size: 1.1rem;
        text-transform: uppercase;
        letter-spacing: 0.5px;
        transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
        position: relative;
        overflow: hidden;
        cursor: pointer;
        text-decoration: none;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .cancel-btn {
        background: var(--gradient-secondary);
        border: 1px solid var(--border-color);
    }

    .submit-btn::before {
        content: '';
        position: absolute;
        top: 0;
        left: -100%;
        width: 100%;
        height: 100%;
        background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.2), transparent);
        transition: left 0.5s;
    }

    .submit-btn:hover::before {
        left: 100%;
    }

    .submit-btn:hover, .cancel-btn:hover {
        transform: translateY(-3px);
        box-shadow: 0 20px 40px rgba(16, 185, 129, 0.4);
    }

    .cancel-btn:hover {
        box-shadow: 0 20px 40px rgba(71, 85, 105, 0.4);
    }

    .error-message {
        background: rgba(239, 68, 68, 0.1);
        color: var(--error-color);
        padding: 1rem 1.5rem;
        border-radius: 12px;
        border: 1px solid rgba(239, 68, 68, 0.3);
        margin-top: 1rem;
        text-align: center;
        font-weight: 500;
    }

    /* Responsive design */
    @media (max-width: 768px) {
        .page-container {
            padding: 1rem;
        }
        
        .header-section h1 {
            font-size: 2rem;
        }
        
        .form-container {
            padding: 2rem;
        }

        .button-group {
            flex-direction: column;
        }
    }

    @media (max-width: 480px) {
        .header-section h1 {
            font-size: 1.75rem;
        }
        
        .form-container {
            padding: 1.5rem;
        }
    }
</style>
</head>
<body>

<div class="page-container">
    <div class="header-section">
        <h1>Edit Leave Request</h1>
        <div class="request-info">
            <div class="request-id">Request #<%=leaveRequest.getId()%></div>
            <div class="request-status">Currently Pending</div>
            <div class="edit-note">Only reason can be modified</div>
        </div>
    </div>

    <div class="form-container">
        <form method="post" action="<%=request.getContextPath()%>/employee" onsubmit="validateDates(event)">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="requestId" value="<%=leaveRequest.getId()%>">
            
            <%
            // Get today's date in yyyy-MM-dd format for min attribute
            java.time.LocalDate today = java.time.LocalDate.now();
            %>

            <div class="form-group">
                <label for="startDate" class="form-label">
                    <i class="fas fa-lock me-1"></i>Start Date (Read-only)
                </label>
                <input type="date" id="startDate" name="startDate" class="form-input" 
                       value="<%=leaveRequest.getStartDate()%>" disabled>
                <input type="hidden" name="startDate" value="<%=leaveRequest.getStartDate()%>">
            </div>

            <div class="form-group">
                <label for="endDate" class="form-label">
                    <i class="fas fa-lock me-1"></i>End Date (Read-only)
                </label>
                <input type="date" id="endDate" name="endDate" class="form-input" 
                       value="<%=leaveRequest.getEndDate()%>" disabled>
                <input type="hidden" name="endDate" value="<%=leaveRequest.getEndDate()%>">
            </div>

            <div class="form-group">
                <label for="reason" class="form-label">
                    <i class="fas fa-edit me-1"></i>Reason for Leave (Editable)
                </label>
                <input type="text" id="reason" name="reason" class="form-input" 
                       value="<%=leaveRequest.getReason()%>" 
                       placeholder="Please provide a reason for your leave request" required>
            </div>

            <div class="button-group">
                <button type="submit" class="submit-btn">
                    <i class="fas fa-save me-2"></i>Update Reason
                </button>
                <a href="<%=request.getContextPath()%>/employee?action=myrequests" class="cancel-btn">
                    <i class="fas fa-times me-2"></i>Cancel
                </a>
            </div>
        </form>
    </div>

    <%
    if (request.getAttribute("error") != null) {
    %>
    <div class="error-message">
        <i class="fas fa-exclamation-triangle me-2"></i>
        <%=request.getAttribute("error")%>
    </div>
    <%
    }
    %>
</div>

<script>
    // Form validation (dates are read-only, only reason is editable)
    const reason = document.getElementById('reason');

    // Form submission validation
    function validateDates(event) {
        const reasonValue = reason.value.trim();
        
        if (!reasonValue) {
            alert('Please provide a reason for your leave request');
            event.preventDefault();
            return false;
        }
        
        return true;
    }

    // Form submission loading state
    const form = document.querySelector('form');
    const submitBtn = document.querySelector('.submit-btn');
    
    form.addEventListener('submit', function() {
        submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin me-2"></i>Updating...';
        submitBtn.disabled = true;
    });

    // Add staggered animation on load
    document.addEventListener('DOMContentLoaded', function() {
        const elements = document.querySelectorAll('.header-section, .form-container');
        
        elements.forEach((element, index) => {
            element.style.opacity = '0';
            element.style.transform = 'translateY(20px)';
            
            setTimeout(() => {
                element.style.transition = 'all 0.6s cubic-bezier(0.4, 0, 0.2, 1)';
                element.style.opacity = '1';
                element.style.transform = 'translateY(0)';
            }, index * 200);
        });
    });
</script>

</body>
</html>
