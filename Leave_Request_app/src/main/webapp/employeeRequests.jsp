<%@ page import="model.LeaveRequest" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<LeaveRequest> list = (List<LeaveRequest>) request.getAttribute("requests");
%>
<!DOCTYPE html>
<html>
<head>
    <title>My Leave Requests - Leave Management System</title>
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
            --warning-color: #f59e0b;
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

        .page-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 2rem;
            min-height: 100vh;
        }

        .header-section {
            text-align: center;
            margin-bottom: 3rem;
            position: relative;
        }

        .header-section h1 {
            font-size: 3rem;
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
            width: 120px;
            height: 4px;
            background: var(--gradient-primary);
            border-radius: 2px;
        }

        .header-subtitle {
            color: var(--text-secondary);
            font-size: 1.1rem;
            font-weight: 400;
        }

        /* Search and Filter Section */
        .filters-section {
            background: var(--gradient-secondary);
            border: 1px solid var(--border-color);
            border-radius: 20px;
            padding: 2rem;
            margin-bottom: 2rem;
            box-shadow: 
                0 10px 25px -5px rgba(0, 0, 0, 0.3),
                0 0 0 1px rgba(255, 255, 255, 0.05);
        }

        .filters-row {
            display: flex;
            gap: 1.5rem;
            align-items: flex-end;
            flex-wrap: wrap;
            margin-bottom: 1.5rem;
        }

        .search-group {
            display: flex;
            flex-direction: column;
            gap: 1rem;
            flex: 1;
            min-width: 300px;
        }

        .search-row {
            display: flex;
            gap: 1rem;
            flex-wrap: wrap;
        }

        .input-field {
            flex: 1;
            min-width: 150px;
        }

        .search-label {
            display: block;
            font-weight: 500;
            color: var(--text-secondary);
            margin-bottom: 0.5rem;
            font-size: 0.9rem;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .search-input, .search-date {
            width: 100%;
            padding: 0.75rem 1rem;
            background: var(--secondary-bg);
            border: 1px solid var(--border-color);
            border-radius: 12px;
            color: var(--text-primary);
            font-size: 0.9rem;
            transition: all 0.3s ease;
        }

        .search-input:focus, .search-date:focus {
            outline: none;
            border-color: var(--accent-color);
            box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.2);
            transform: translateY(-2px);
        }

        .search-input::placeholder {
            color: var(--text-secondary);
            opacity: 0.7;
        }

        .search-date {
            cursor: pointer;
        }

        .status-filters {
            display: flex;
            gap: 1rem;
            flex-wrap: wrap;
            align-items: flex-end;
        }

        .filter-btn {
            padding: 0.75rem 1.5rem;
            border: 1px solid var(--border-color);
            background: var(--secondary-bg);
            color: var(--text-secondary);
            border-radius: 12px;
            font-weight: 500;
            font-size: 0.9rem;
            cursor: pointer;
            transition: all 0.3s ease;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .filter-btn:hover {
            border-color: var(--accent-color);
            color: var(--accent-color);
            transform: translateY(-2px);
        }

        .filter-btn.active {
            background: var(--gradient-primary);
            border-color: var(--accent-color);
            color: var(--text-primary);
            box-shadow: 0 4px 15px rgba(59, 130, 246, 0.3);
        }

        .filter-btn.pending {
            border-color: var(--warning-color);
        }

        .filter-btn.pending:hover,
        .filter-btn.pending.active {
            background: linear-gradient(135deg, #f59e0b, #d97706);
            border-color: var(--warning-color);
            color: var(--text-primary);
        }

        .filter-btn.approved {
            border-color: var(--success-color);
        }

        .filter-btn.approved:hover,
        .filter-btn.approved.active {
            background: linear-gradient(135deg, #10b981, #059669);
            border-color: var(--success-color);
            color: var(--text-primary);
        }

        .filter-btn.rejected {
            border-color: var(--error-color);
        }

        .filter-btn.rejected:hover,
        .filter-btn.rejected.active {
            background: linear-gradient(135deg, #ef4444, #dc2626);
            border-color: var(--error-color);
            color: var(--text-primary);
        }

        .clear-filters {
            padding: 0.75rem 1.5rem;
            border: 1px solid var(--border-color);
            background: transparent;
            color: var(--text-secondary);
            border-radius: 12px;
            font-weight: 500;
            font-size: 0.9rem;
            cursor: pointer;
            transition: all 0.3s ease;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .clear-filters:hover {
            background: var(--border-color);
            color: var(--text-primary);
        }

        .results-info {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 1rem;
            color: var(--text-secondary);
            font-size: 0.9rem;
        }

        .table-container {
            background: var(--gradient-secondary);
            border: 1px solid var(--border-color);
            border-radius: 20px;
            padding: 2rem;
            box-shadow: 
                0 20px 40px -10px rgba(0, 0, 0, 0.4),
                0 0 0 1px rgba(255, 255, 255, 0.05);
            backdrop-filter: blur(20px);
            overflow: hidden;
            margin-bottom: 2rem;
        }

        .requests-table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0 12px;
            background: transparent;
        }

        .requests-table thead th {
            background: var(--gradient-primary);
            color: var(--text-primary);
            font-weight: 600;
            text-transform: uppercase;
            font-size: 0.875rem;
            letter-spacing: 0.05em;
            padding: 1.25rem 1.5rem;
            text-align: center;
            vertical-align: middle;
            border: none;
            position: relative;
        }

        .requests-table thead th:first-child {
            border-top-left-radius: 12px;
            border-bottom-left-radius: 12px;
        }

        .requests-table thead th:last-child {
            border-top-right-radius: 12px;
            border-bottom-right-radius: 12px;
        }

        .requests-table tbody tr {
            background: var(--secondary-bg);
            transition: all 0.3s ease;
            border-radius: 12px;
            margin-bottom: 12px;
        }

        .requests-table tbody tr:hover {
            background: var(--card-bg);
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.3);
        }

        .requests-table tbody td {
            padding: 1.25rem 1.5rem;
            text-align: center;
            vertical-align: middle;
            border: none;
            font-size: 0.95rem;
            color: var(--text-primary);
            position: relative;
        }

        .requests-table tbody tr td:first-child {
            border-top-left-radius: 12px;
            border-bottom-left-radius: 12px;
        }

        .requests-table tbody tr td:last-child {
            border-top-right-radius: 12px;
            border-bottom-right-radius: 12px;
        }

        .status-badge {
            padding: 0.5rem 1rem;
            border-radius: 20px;
            font-weight: 600;
            font-size: 0.875rem;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .status-pending {
            background: rgba(245, 158, 11, 0.1);
            color: var(--warning-color);
            border: 1px solid rgba(245, 158, 11, 0.3);
        }

        .status-approved {
            background: rgba(16, 185, 129, 0.1);
            color: var(--success-color);
            border: 1px solid rgba(16, 185, 129, 0.3);
        }

        .status-rejected {
            background: rgba(239, 68, 68, 0.1);
            color: var(--error-color);
            border: 1px solid rgba(239, 68, 68, 0.3);
        }

        .no-requests {
            text-align: center;
            padding: 3rem 2rem;
            color: var(--text-secondary);
            font-size: 1.1rem;
            font-weight: 500;
        }

        .no-requests .icon {
            font-size: 3rem;
            color: var(--border-color);
            margin-bottom: 1rem;
            display: block;
        }

        .back-link {
            display: block;
            margin: 0 auto;
            text-align: center;
            max-width: 200px;
            font-weight: 600;
            font-size: 1rem;
            color: var(--accent-color);
            text-decoration: none;
            transition: all 0.3s ease;
            padding: 1rem 2rem;
            border-radius: 12px;
            background: var(--gradient-secondary);
            border: 1px solid var(--border-color);
        }

        .back-link:hover {
            color: var(--accent-hover);
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(59, 130, 246, 0.3);
        }

        /* Action buttons styling */
        .actions-cell {
            text-align: center;
            vertical-align: middle;
        }

        .action-buttons {
            display: flex;
            gap: 0.5rem;
            justify-content: center;
            align-items: center;
        }

        .action-btn {
            background: none;
            border: none;
            padding: 0.5rem;
            border-radius: 8px;
            cursor: pointer;
            transition: all 0.3s ease;
            display: flex;
            align-items: center;
            justify-content: center;
            min-width: 36px;
            height: 36px;
        }

        .edit-btn {
            color: var(--accent-color);
            background: rgba(59, 130, 246, 0.1);
            border: 1px solid rgba(59, 130, 246, 0.3);
        }

        .edit-btn:hover {
            background: rgba(59, 130, 246, 0.2);
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(59, 130, 246, 0.3);
        }

        .delete-btn {
            color: var(--error-color);
            background: rgba(239, 68, 68, 0.1);
            border: 1px solid rgba(239, 68, 68, 0.3);
        }

        .delete-btn:hover {
            background: rgba(239, 68, 68, 0.2);
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(239, 68, 68, 0.3);
        }

        .no-actions {
            color: var(--text-secondary);
            font-size: 0.8rem;
            font-style: italic;
        }

        /* Rejection reason styling */
        .rejection-reason-cell {
            max-width: 200px;
            word-wrap: break-word;
        }

        .rejection-reason-text {
            color: var(--error-color);
            font-size: 0.9rem;
            font-style: italic;
            cursor: help;
            position: relative;
        }

        .rejection-reason-text:hover::after {
            content: attr(title);
            position: absolute;
            bottom: 100%;
            left: 50%;
            transform: translateX(-50%);
            background: var(--primary-bg);
            color: var(--text-primary);
            padding: 0.5rem;
            border-radius: 8px;
            border: 1px solid var(--border-color);
            font-size: 0.8rem;
            white-space: nowrap;
            z-index: 1000;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
        }

        /* Message styling */
        .message {
            padding: 1rem 1.5rem;
            border-radius: 12px;
            margin-bottom: 1.5rem;
            font-weight: 500;
            display: flex;
            align-items: center;
            gap: 0.75rem;
        }

        .success-message {
            background: rgba(16, 185, 129, 0.1);
            color: var(--success-color);
            border: 1px solid rgba(16, 185, 129, 0.3);
        }

        .error-message {
            background: rgba(239, 68, 68, 0.1);
            color: var(--error-color);
            border: 1px solid rgba(239, 68, 68, 0.3);
        }

        /* Responsive design */
        @media (max-width: 1024px) {
            .page-container {
                padding: 1.5rem;
            }
            
            .table-container, .filters-section {
                padding: 1.5rem;
            }
        }

        @media (max-width: 768px) {
            .page-container {
                padding: 1rem;
            }
            
            .header-section h1 {
                font-size: 2.5rem;
            }
            
            .table-container, .filters-section {
                padding: 1rem;
            }
            
            .filters-row {
                flex-direction: column;
                align-items: stretch;
            }
            
            .search-group {
                min-width: auto;
            }
            
            .status-filters {
                justify-content: center;
            }
            
            .requests-table thead th,
            .requests-table tbody td {
                padding: 1rem;
                font-size: 0.875rem;
            }
        }

        @media (max-width: 480px) {
            .header-section h1 {
                font-size: 2rem;
            }
            
            .requests-table {
                font-size: 0.8rem;
            }
            
            .requests-table thead th,
            .requests-table tbody td {
                padding: 0.75rem 0.5rem;
                font-size: 0.8rem;
            }

            .action-buttons {
                flex-direction: column;
                gap: 0.25rem;
            }

            .action-btn {
                min-width: 32px;
                height: 32px;
                padding: 0.25rem;
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
        <h1>My Leave Requests</h1>
        <p class="header-subtitle">Track the status of your leave applications</p>
    </div>

    <!-- Success/Error Messages -->
    <%
    if (request.getAttribute("message") != null) {
    %>
    <div class="message success-message">
        <i class="fas fa-check-circle me-2"></i>
        <%=request.getAttribute("message")%>
    </div>
    <%
    }
    if (request.getAttribute("error") != null) {
    %>
    <div class="message error-message">
        <i class="fas fa-exclamation-triangle me-2"></i>
        <%=request.getAttribute("error")%>
    </div>
    <%
    }
    %>

    <!-- Search and Filter Section -->
    <div class="filters-section">
        <div class="filters-row">
            <div class="search-group">
                <label class="search-label">Search Requests</label>
                <div class="search-row">
                    <div class="input-field">
                        <label for="reasonFilter" class="search-label">Reason</label>
                        <input type="text" id="reasonFilter" class="search-input" placeholder="e.g., Vacation, Sick Leave" />
                    </div>
                    <div class="input-field">
                        <label for="startDateFilter" class="search-label">Start Date</label>
                        <input type="date" id="startDateFilter" class="search-date" />
                    </div>
                    <div class="input-field">
                        <label for="endDateFilter" class="search-label">End Date</label>
                        <input type="date" id="endDateFilter" class="search-date" />
                    </div>
                </div>
            </div>
            
            <div class="status-filters">
                <button class="filter-btn pending" data-status="pending">Pending</button>
                <button class="filter-btn approved" data-status="approved">Approved</button>
                <button class="filter-btn rejected" data-status="rejected">Rejected</button>
                <button class="clear-filters">Clear All</button>
            </div>
        </div>
        
        <div class="results-info">
            <span id="resultsCount">Showing all requests</span>
            <span id="filterStatus"></span>
        </div>
    </div>

    <div class="table-container">
        <table class="requests-table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Period</th>
            <th>Reason</th>
            <th>Status</th>
            <th>Applied On</th>
                    <th>Rejection Reason</th>
                    <th>Actions</th>
        </tr>
    </thead>
            <tbody id="requestsTableBody">
        <% if (list != null && !list.isEmpty()) { 
               for (LeaveRequest r : list) { %>
                <tr data-status="<%= r.getStatus().toLowerCase() %>" data-search="<%= r.getReason() %> <%= r.getStartDate() %> <%= r.getEndDate() %> <%= r.getStatus() %>">
                <td><%= r.getId() %></td>
                <td><%= r.getStartDate() %> to <%= r.getEndDate() %></td>
                <td><%= r.getReason() %></td>
                    <td>
                        <span class="status-badge status-<%= r.getStatus().toLowerCase() %>">
                            <%= r.getStatus() %>
                        </span>
                    </td>
                <td><%= r.getAppliedOn() %></td>
                    <td>
                        <% if ("rejected".equals(r.getStatus().toLowerCase())) { %>
                            <span class="rejection-reason-cell">
                                <span class="rejection-reason-text" title="<%= r.getRejectionReason() %>">
                                    <%= r.getRejectionReason() %>
                                </span>
                            </span>
                        <% } else { %>
                            N/A
                        <% } %>
                    </td>
                    <td class="actions-cell">
                        <% if ("pending".equals(r.getStatus().toLowerCase())) { %>
                            <div class="action-buttons">
                                <button class="action-btn edit-btn" data-request-id="<%= r.getId() %>" title="Edit Request">
                                    <i class="fas fa-edit"></i>
                                </button>
                                <button class="action-btn delete-btn" data-request-id="<%= r.getId() %>" title="Delete Request">
                                    <i class="fas fa-trash"></i>
                                </button>
                            </div>
                        <% } else { %>
                            <span class="no-actions">No actions available</span>
                        <% } %>
                    </td>
            </tr>
        <%   }
           } else { %>
            <tr>
                    <td colspan="7">
                        <div class="no-requests">
                            <i class="fas fa-inbox icon"></i>
                            <div>No leave requests found</div>
                            <div style="font-size: 0.9rem; margin-top: 0.5rem; opacity: 0.8;">
                                You haven't submitted any leave requests yet
                            </div>
                        </div>
                    </td>
            </tr>
        <% } %>
    </tbody>
</table>
    </div>

    <a href="<%=request.getContextPath()%>/employee" class="back-link">
        <i class="fas fa-arrow-left me-2"></i>
        Back to Dashboard
    </a>
</div>

<script>
    // Search and Filter Functionality
    class EmployeeRequestManager {
        constructor() {
            this.reasonInput = document.getElementById('reasonFilter');
            this.startDateInput = document.getElementById('startDateFilter');
            this.endDateInput = document.getElementById('endDateFilter');
            this.filterButtons = document.querySelectorAll('.filter-btn[data-status]');
            this.clearButton = document.querySelector('.clear-filters');
            this.tableBody = document.getElementById('requestsTableBody');
            this.rows = Array.from(this.tableBody.querySelectorAll('tr'));
            this.currentFilter = 'all';
            this.currentFilters = {
                reason: '',
                dateRange: ''
            };
            
            this.initializeEventListeners();
        }

        initializeEventListeners() {
            // Search inputs
            this.reasonInput.addEventListener('input', (e) => {
                this.currentFilters.reason = e.target.value.toLowerCase();
                this.filterAndSearch();
            });

            this.startDateInput.addEventListener('change', (e) => {
                this.currentFilters.startDate = e.target.value;
                // Set minimum end date to start date
                if (this.currentFilters.startDate) {
                    this.endDateInput.min = this.currentFilters.startDate;
                }
                this.filterAndSearch();
            });

            this.endDateInput.addEventListener('change', (e) => {
                this.currentFilters.endDate = e.target.value;
                // Set maximum start date to end date
                if (this.currentFilters.endDate) {
                    this.startDateInput.max = this.currentFilters.endDate;
                }
                this.filterAndSearch();
            });

            // Filter buttons
            this.filterButtons.forEach(btn => {
                btn.addEventListener('click', () => {
                    this.toggleFilter(btn);
                });
            });

            // Clear filters
            this.clearButton.addEventListener('click', () => {
                this.clearAllFilters();
            });
        }

        toggleFilter(clickedBtn) {
            // Remove active class from all buttons
            this.filterButtons.forEach(btn => btn.classList.remove('active'));
            
            // If clicking the same button, clear filter
            if (this.currentFilter === clickedBtn.dataset.status) {
                this.currentFilter = 'all';
            } else {
                // Set new filter and mark button as active
                this.currentFilter = clickedBtn.dataset.status;
                clickedBtn.classList.add('active');
            }
            
            this.filterAndSearch();
        }

        clearAllFilters() {
            this.currentFilter = 'all';
            this.currentFilters = {
                reason: '',
                dateRange: ''
            };
            
            this.reasonInput.value = '';
            this.startDateInput.value = '';
            this.endDateInput.value = '';
            
            this.filterButtons.forEach(btn => btn.classList.remove('active'));
            this.filterAndSearch();
        }

        filterAndSearch() {
            let visibleCount = 0;
            let filteredCount = 0;

            this.rows.forEach(row => {
                const status = row.dataset.status;
                const reason = row.querySelector('td:nth-child(3)').textContent.toLowerCase();
                const period = row.querySelector('td:nth-child(2)').textContent.toLowerCase();
                
                let shouldShow = true;

                // Apply status filter
                if (this.currentFilter !== 'all' && status !== this.currentFilter) {
                    shouldShow = false;
                }

                // Apply reason filter
                if (this.currentFilters.reason && !reason.includes(this.currentFilters.reason)) {
                    shouldShow = false;
                }

                // Apply date range filter
                if (this.currentFilters.startDate && this.currentFilters.endDate) {
                    const startDate = new Date(this.currentFilters.startDate);
                    const endDate = new Date(this.currentFilters.endDate);
                    const rowStartDate = new Date(period.split(' to ')[0]);
                    const rowEndDate = new Date(period.split(' to ')[1]);

                    if (rowStartDate < startDate || rowEndDate > endDate) {
                        shouldShow = false;
                    }
                }

                // Show/hide row
                if (shouldShow) {
                    row.style.display = '';
                    visibleCount++;
                    if (this.currentFilter !== 'all') filteredCount++;
                } else {
                    row.style.display = 'none';
                }
            });

            this.updateResultsInfo(visibleCount, filteredCount);
        }

        updateResultsInfo(visibleCount, filteredCount) {
            const resultsCount = document.getElementById('resultsCount');
            const filterStatus = document.getElementById('filterStatus');

            if (this.currentFilter === 'all' && !this.hasActiveFilters()) {
                resultsCount.textContent = 'Showing all ' + visibleCount + ' requests';
                filterStatus.textContent = '';
            } else {
                resultsCount.textContent = 'Showing ' + visibleCount + ' of ' + this.rows.length + ' requests';
                
                let filterText = '';
                if (this.currentFilter !== 'all') {
                    filterText += 'Filtered by: ' + this.currentFilter.charAt(0).toUpperCase() + this.currentFilter.slice(1);
                }
                
                const activeFilters = this.getActiveFilters();
                if (activeFilters.length > 0) {
                    if (filterText) filterText += ' | ';
                    filterText += 'Search: ' + activeFilters.join(', ');
                }
                
                filterStatus.textContent = filterText;
            }
        }

        hasActiveFilters() {
            return Object.values(this.currentFilters).some(filter => filter !== '');
        }

        getActiveFilters() {
            const activeFilters = [];
            if (this.currentFilters.reason) {
                activeFilters.push('Reason: "' + this.currentFilters.reason + '"');
            }
            if (this.currentFilters.startDate && this.currentFilters.endDate) {
                activeFilters.push('Date Range: ' + this.currentFilters.startDate + ' to ' + this.currentFilters.endDate);
            } else if (this.currentFilters.startDate) {
                activeFilters.push('From: ' + this.currentFilters.startDate);
            } else if (this.currentFilters.endDate) {
                activeFilters.push('Until: ' + this.currentFilters.endDate);
            }
            return activeFilters;
        }
    }

    // Initialize the request manager
    const requestManager = new EmployeeRequestManager();

    // Edit and Delete functionality
    function editRequest(requestId) {
        window.location.href = '<%=request.getContextPath()%>/employee?action=edit&id=' + requestId;
    }

    function deleteRequest(requestId) {
        if (confirm('Are you sure you want to delete this leave request? This action cannot be undone.')) {
            window.location.href = '<%=request.getContextPath()%>/employee?action=delete&id=' + requestId;
        }
    }

    // Add event listeners for action buttons
    document.addEventListener('DOMContentLoaded', function() {
        // Edit button event listeners
        document.querySelectorAll('.edit-btn').forEach(btn => {
            btn.addEventListener('click', function() {
                const requestId = this.getAttribute('data-request-id');
                editRequest(requestId);
            });
        });

        // Delete button event listeners
        document.querySelectorAll('.delete-btn').forEach(btn => {
            btn.addEventListener('click', function() {
                const requestId = this.getAttribute('data-request-id');
                deleteRequest(requestId);
            });
        });
    });
</script>

</body>
</html>
