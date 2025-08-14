<%@ page import="model.Employee"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
Employee emp = (Employee) session.getAttribute("employee");
if (emp == null) {
	response.sendRedirect(request.getContextPath() + "/login");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Apply Leave - Leave Management System</title>
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

    .balance-info {
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

    .balance-row {
        display: flex;
        justify-content: space-around;
        margin-bottom: 1.5rem;
        gap: 2rem;
    }

    .balance-item {
        flex: 1;
    }

    .balance-info .balance-number {
        font-size: 2rem;
        font-weight: 700;
        color: var(--success-color);
        margin-bottom: 0.5rem;
    }

    .balance-info .balance-label {
        color: var(--text-secondary);
        font-size: 0.9rem;
        font-weight: 500;
    }

    .monthly-progress {
        margin-top: 1rem;
    }

    .progress-bar {
        width: 100%;
        height: 8px;
        background: var(--border-color);
        border-radius: 4px;
        overflow: hidden;
        margin-bottom: 0.5rem;
    }

    .progress-fill {
        height: 100%;
        background: var(--gradient-primary);
        border-radius: 4px;
        transition: width 0.3s ease;
    }

    .progress-text {
        color: var(--text-secondary);
        font-size: 0.8rem;
        font-weight: 500;
    }

    .progress-note {
        margin-top: 0.5rem;
        color: var(--text-secondary);
        font-size: 0.7rem;
        font-weight: 400;
    }

    .balance-note {
        margin-top: 1rem;
        padding: 0.75rem;
        background: rgba(59, 130, 246, 0.1);
        border: 1px solid rgba(59, 130, 246, 0.3);
        border-radius: 8px;
        display: flex;
        align-items: center;
        gap: 0.5rem;
        color: var(--accent-color);
        font-size: 0.85rem;
        font-weight: 500;
    }

    .balance-note i {
        color: var(--accent-color);
        font-size: 1rem;
    }

    .validation-section {
        margin-top: 1.5rem;
        padding: 1rem;
        background: rgba(239, 68, 68, 0.05);
        border: 1px solid rgba(239, 68, 68, 0.2);
        border-radius: 12px;
    }

    .validation-item {
        display: flex;
        align-items: center;
        gap: 0.75rem;
        padding: 0.75rem;
        margin-bottom: 0.5rem;
        background: rgba(239, 68, 68, 0.1);
        border-radius: 8px;
        color: var(--error-color);
        font-size: 0.9rem;
        font-weight: 500;
    }

    .validation-item:last-child {
        margin-bottom: 0;
    }

    .validation-item i {
        font-size: 1rem;
        color: var(--error-color);
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

    .submit-btn {
        width: 100%;
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
        margin-top: 1rem;
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

    .submit-btn:hover {
        transform: translateY(-3px);
        box-shadow: 0 20px 40px rgba(16, 185, 129, 0.4);
    }

    .back-link {
        display: block;
        text-align: center;
        margin-top: 2rem;
        color: var(--accent-color);
        text-decoration: none;
        font-weight: 500;
        font-size: 1rem;
        transition: all 0.3s ease;
        padding: 1rem;
        border-radius: 12px;
        background: var(--gradient-secondary);
        border: 1px solid var(--border-color);
    }

    .back-link:hover {
        color: var(--accent-hover);
        transform: translateY(-2px);
        box-shadow: 0 8px 20px rgba(16, 185, 129, 0.3);
    }

    .monthly-info {
        background: var(--gradient-secondary);
        border: 1px solid var(--border-color);
        border-radius: 16px;
        padding: 1.5rem;
        margin-top: 1.5rem;
        box-shadow: 0 10px 25px -5px rgba(0, 0, 0, 0.3);
    }

    .monthly-header {
        display: flex;
        align-items: center;
        gap: 0.75rem;
        margin-bottom: 1rem;
        color: var(--accent-color);
        font-weight: 600;
        font-size: 1.1rem;
    }

    .monthly-header i {
        font-size: 1.2rem;
    }

    .monthly-details {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
        gap: 1rem;
    }

    .monthly-item {
        display: flex;
        flex-direction: column;
        align-items: center;
        text-align: center;
        padding: 1rem;
        background: var(--card-bg);
        border-radius: 12px;
        border: 1px solid var(--border-color);
    }

    .monthly-label {
        color: var(--text-secondary);
        font-size: 0.8rem;
        font-weight: 500;
        text-transform: uppercase;
        letter-spacing: 0.5px;
        margin-bottom: 0.5rem;
    }

    .monthly-value {
        color: var(--text-primary);
        font-size: 1.5rem;
        font-weight: 700;
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

        .balance-row {
            flex-direction: column;
            gap: 1rem;
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

<div class="page-container">
    <div class="header-section">
        <h1>Apply for Leave</h1>
        <div class="balance-info">
            <div class="balance-row">
                <div class="balance-item">
                    <div class="balance-number"><%=emp.getLeaveBalance()%></div>
                    <div class="balance-label">Total Days Available</div>
                </div>
                <div class="balance-item">
                    <div class="balance-number" id="monthlyAvailable">3</div>
                    <div class="balance-label">Days Available This Month</div>
                </div>
            </div>
            <div class="monthly-progress">
                <div class="progress-bar">
                    <div class="progress-fill" id="monthlyProgress"></div>
                </div>
                <div class="progress-text">
                    <span id="monthlyUsed">0</span> of 3 days used this month
                </div>
                <div class="progress-note">
                    <small>Progress shown for current month only</small>
                </div>
            </div>
            <div class="balance-note">
                <i class="fas fa-info-circle"></i>
                <span>You can apply for up to 3 days of leave per month. Each month starts fresh with 3 days available.</span>
            </div>
        </div>
    </div>

    <div class="form-container">
        <form method="post" action="<%=request.getContextPath()%>/employee" onsubmit="validateDates(event)">
            <%
            // Get today's date in yyyy-MM-dd format for min attribute
            java.time.LocalDate today = java.time.LocalDate.now();
            %>

            <div class="form-group">
                <label for="startDate" class="form-label">Start Date</label>
                <input type="date" id="startDate" name="startDate" class="form-input" min="<%=today%>" required>
            </div>

            <div class="form-group">
                <label for="endDate" class="form-label">End Date</label>
                <input type="date" id="endDate" name="endDate" class="form-input" min="<%=today%>" required>
            </div>

            <div class="form-group">
                <label for="reason" class="form-label">Reason for Leave</label>
                <input type="text" id="reason" name="reason" class="form-input" placeholder="Please provide a reason for your leave request" required>
            </div>

            <div class="monthly-info" id="monthlyInfo" style="display: none;">
                <div class="monthly-header">
                    <i class="fas fa-calendar-alt"></i>
                    <span id="monthlyHeaderText">Monthly Leave Status</span>
                </div>
                <div class="monthly-details">
                    <div class="monthly-item">
                        <span class="monthly-label">Days Used:</span>
                        <span class="monthly-value" id="monthlyUsedDays">0</span>
                    </div>
                    <div class="monthly-item">
                        <span class="monthly-label">Days Available:</span>
                        <span class="monthly-value" id="monthlyAvailableDays">3</span>
                    </div>
                    <div class="monthly-item">
                        <span class="monthly-label">Requested Days:</span>
                        <span class="monthly-value" id="requestedDays">0</span>
                    </div>
                </div>
            </div>

            <button type="submit" class="submit-btn">
                <i class="fas fa-paper-plane me-2"></i>Submit Request
            </button>
        </form>

        <div class="validation-section" id="validationSection" style="display: none;">
            <div class="validation-item" id="dateConflictWarning" style="display: none;">
                <i class="fas fa-exclamation-triangle"></i>
                <span>Date conflict detected with existing leave requests</span>
            </div>
            <div class="validation-item" id="monthlyLimitWarning" style="display: none;">
                <i class="fas fa-calendar-times"></i>
                <span>Monthly leave limit would be exceeded</span>
            </div>
            <div class="validation-item" id="balanceWarning" style="display: none;">
                <i class="fas fa-coins"></i>
                <span>Insufficient leave balance</span>
            </div>
        </div>
    </div>

    <a href="<%=request.getContextPath()%>/employee" class="back-link">
        <i class="fas fa-arrow-left me-2"></i>Back to Dashboard
    </a>

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
    // Date validation and real-time feedback
    const startDate = document.getElementById('startDate');
    const endDate = document.getElementById('endDate');
    const reason = document.getElementById('reason');
    const validationSection = document.getElementById('validationSection');
    const dateConflictWarning = document.getElementById('dateConflictWarning');
    const monthlyLimitWarning = document.getElementById('monthlyLimitWarning');
    const balanceWarning = document.getElementById('balanceWarning');
    const monthlyAvailable = document.getElementById('monthlyAvailable');
    const monthlyUsed = document.getElementById('monthlyUsed');
    const monthlyProgress = document.getElementById('monthlyProgress');
    const monthlyInfo = document.getElementById('monthlyInfo');
    const monthlyUsedDaysSpan = document.getElementById('monthlyUsedDays');
    const monthlyAvailableDaysSpan = document.getElementById('monthlyAvailableDays');
    const requestedDaysSpan = document.getElementById('requestedDays');
    const monthlyHeaderText = document.getElementById('monthlyHeaderText');

    // Get employee data from the page
    const totalBalance = parseInt(document.querySelector('.balance-number').textContent);
    let monthlyUsedDays = 0;

    // Calculate days between two dates
    function calculateDays(start, end) {
        if (!start || !end) return 0;
        const startDate = new Date(start);
        const endDate = new Date(end);
        const timeDiff = endDate.getTime() - startDate.getTime();
        return Math.ceil(timeDiff / (1000 * 3600 * 24)) + 1;
    }

    // Update monthly progress bar
    function updateMonthlyProgress() {
        const available = Math.max(0, 3 - monthlyUsedDays);
        monthlyAvailable.textContent = available;
        monthlyUsed.textContent = monthlyUsedDays;
        
        const progressPercent = (monthlyUsedDays / 3) * 100;
        monthlyProgress.style.width = Math.min(progressPercent, 100) + '%';
        
        // Change color based on usage
        if (progressPercent >= 100) {
            monthlyProgress.style.background = 'var(--error-color)';
        } else if (progressPercent >= 80) {
            monthlyProgress.style.background = '#f59e0b';
        } else {
            monthlyProgress.style.background = 'var(--success-color)';
        }
    }

    // Validate leave request
    function validateLeaveRequest() {
        const start = startDate.value;
        const end = endDate.value;
        const days = calculateDays(start, end);
        
        let hasErrors = false;
        
        // Hide all warnings initially
        dateConflictWarning.style.display = 'none';
        monthlyLimitWarning.style.display = 'none';
        balanceWarning.style.display = 'none';
        validationSection.style.display = 'none';
        
        if (start && end && days > 0) {
            // Check total balance
            if (days > totalBalance) {
                balanceWarning.style.display = 'flex';
                hasErrors = true;
            }
            
            // Show validation section if there are errors
            if (hasErrors) {
                validationSection.style.display = 'block';
            }
        }
        
        return !hasErrors;
    }

    // Event listeners for real-time validation
    startDate.addEventListener('change', () => {
        endDate.min = startDate.value;
        if (endDate.value && endDate.value < startDate.value) {
            endDate.value = startDate.value;
        }
        checkDateConflict();
        updateMonthlyInfo();
    });

    endDate.addEventListener('change', () => {
        if (startDate.value && endDate.value < startDate.value) {
            alert('End date cannot be before start date');
            endDate.value = startDate.value;
        }
        checkDateConflict();
        updateMonthlyInfo();
    });

    reason.addEventListener('input', validateLeaveRequest);

    // Form submission validation
    function validateDates(event) {
        const start = startDate.value;
        const end = endDate.value;
        const days = calculateDays(start, end);
        
        if (!start || !end) {
            alert('Please select both start and end dates');
            event.preventDefault();
            return false;
        }
        
        if (days <= 0) {
            alert('End date must be after start date');
            event.preventDefault();
            return false;
        }
        
        // Check monthly limit for the selected month - synchronous check
        if (start && end && days > 0) {
            // Get the month from the start date
            const selectedStartDate = new Date(start);
            const currentDate = new Date();
            
            // Only check monthly limit for current month
            if (selectedStartDate.getMonth() === currentDate.getMonth() && selectedStartDate.getFullYear() === currentDate.getFullYear()) {
                // Current month - check against current usage
                if (monthlyUsedDays + days > 3) {
                    alert('Monthly limit exceeded. You can only take ' + (3 - monthlyUsedDays) + ' more days this month.');
                    event.preventDefault();
                    return false;
                }
            }
            // Future months - no monthly limit check, allow up to 3 days
        }
        
        if (!validateLeaveRequest()) {
            event.preventDefault();
            return false;
        }
        
        return true;
    }

    // Form submission loading state
    const form = document.querySelector('form');
    const submitBtn = document.querySelector('.submit-btn');
    
    form.addEventListener('submit', function() {
        submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin me-2"></i>Submitting...';
        submitBtn.disabled = true;
    });

    // Initialize monthly progress
    updateMonthlyProgress();

    // Load monthly leave usage data
    function loadMonthlyUsage() {
        fetch('leaveValidation?action=monthlyUsage')
            .then(response => response.json())
            .then(data => {
                monthlyUsedDays = data.used;
                updateMonthlyProgress();
            })
            .catch(error => console.error('Error loading monthly usage:', error));
    }

    // Check for date conflicts
    function checkDateConflict() {
        const start = startDate.value;
        const end = endDate.value;
        
        if (start && end) {
            fetch('leaveValidation?action=checkConflict&startDate=' + start + '&endDate=' + end)
                .then(response => response.json())
                .then(data => {
                    if (data.hasConflict) {
                        dateConflictWarning.style.display = 'flex';
                        validationSection.style.display = 'block';
                    } else {
                        dateConflictWarning.style.display = 'none';
                    }
                    // Don't call validateLeaveRequest here as it might interfere with monthly validation
                })
                .catch(error => console.error('Error checking date conflict:', error));
        }
    }

    // Load initial data
    loadMonthlyUsage();

    // Add staggered animation on load
    document.addEventListener('DOMContentLoaded', function() {
        const elements = document.querySelectorAll('.header-section, .form-container, .back-link');
        
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

    // Function to update monthly info display
    function updateMonthlyInfo() {
        const start = startDate.value;
        const end = endDate.value;
        const days = calculateDays(start, end);

        if (start && end && days > 0) {
            // Get the month from the start date
            const selectedStartDate = new Date(start);
            const currentDate = new Date();
            const monthName = selectedStartDate.toLocaleDateString('en-US', { month: 'long', year: 'numeric' });
            
            // Check if it's current month or future month
            if (selectedStartDate.getMonth() === currentDate.getMonth() && selectedStartDate.getFullYear() === currentDate.getFullYear()) {
                // Current month - use existing monthly data
                monthlyUsedDaysSpan.textContent = monthlyUsedDays;
                monthlyAvailableDaysSpan.textContent = Math.max(0, 3 - monthlyUsedDays);
                requestedDaysSpan.textContent = days;
                monthlyHeaderText.textContent = 'Monthly Leave Status for ' + monthName;
                
                // Show warning if monthly limit would be exceeded
                if (monthlyUsedDays + days > 3) {
                    monthlyLimitWarning.style.display = 'flex';
                    validationSection.style.display = 'block';
                } else {
                    monthlyLimitWarning.style.display = 'none';
                }
            } else {
                // Future month - show fresh monthly allowance
                monthlyUsedDaysSpan.textContent = '0';
                monthlyAvailableDaysSpan.textContent = '3';
                requestedDaysSpan.textContent = days;
                monthlyHeaderText.textContent = 'Monthly Leave Status for ' + monthName + ' (Future Month)';
                
                // Hide monthly limit warning for future months
                monthlyLimitWarning.style.display = 'none';
            }
            
            monthlyInfo.style.display = 'block';
        } else {
            monthlyInfo.style.display = 'none';
        }
    }
</script>

</body>
</html>
