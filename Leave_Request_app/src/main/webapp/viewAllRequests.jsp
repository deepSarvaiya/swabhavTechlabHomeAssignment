<%@ page import="model.LeaveRequest"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
    List<LeaveRequest> list = (List<LeaveRequest>) request.getAttribute("requests");
%>
<!DOCTYPE html>
<html>
<head>
<title>All Leave Requests - Leave Management System</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
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
	font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI',
		Roboto, sans-serif;
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
	background: radial-gradient(circle at 20% 80%, rgba(59, 130, 246, 0.1)
		0%, transparent 50%),
		radial-gradient(circle at 80% 20%, rgba(16, 185, 129, 0.1) 0%,
		transparent 50%),
		radial-gradient(circle at 40% 40%, rgba(139, 92, 246, 0.05) 0%,
		transparent 50%);
	z-index: -1;
}

.page-container {
	max-width: 1400px;
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

.messages {
	margin-bottom: 2rem;
}

.message {
	max-width: 600px;
	margin: 0 auto 1rem auto;
	padding: 1rem 1.5rem;
	border-radius: 12px;
	font-weight: 500;
	font-size: 1rem;
	text-align: center;
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
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

/* Search and Filter Section */
.filters-section {
	background: var(--gradient-secondary);
	border: 1px solid var(--border-color);
	border-radius: 20px;
	padding: 2rem;
	margin-bottom: 2rem;
	box-shadow: 0 10px 25px -5px rgba(0, 0, 0, 0.3), 0 0 0 1px
		rgba(255, 255, 255, 0.05);
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

.search-input, .search-select, .search-date {
	width: 100%;
	padding: 0.75rem 1rem;
	background: var(--secondary-bg);
	border: 1px solid var(--border-color);
	border-radius: 12px;
	color: var(--text-primary);
	font-size: 0.9rem;
	transition: all 0.3s ease;
}

.search-input:focus, .search-select:focus, .search-date:focus {
	outline: none;
	border-color: var(--accent-color);
	box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.2);
	transform: translateY(-2px);
}

.search-input::placeholder {
	color: var(--text-secondary);
	opacity: 0.7;
}

.search-select {
	cursor: pointer;
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

.filter-btn.pending:hover, .filter-btn.pending.active {
	background: linear-gradient(135deg, #f59e0b, #d97706);
	border-color: var(--warning-color);
	color: var(--text-primary);
}

.filter-btn.approved {
	border-color: var(--success-color);
}

.filter-btn.approved:hover, .filter-btn.approved.active {
	background: linear-gradient(135deg, #10b981, #059669);
	border-color: var(--success-color);
	color: var(--text-primary);
}

.filter-btn.rejected {
	border-color: var(--error-color);
}

.filter-btn.rejected:hover, .filter-btn.rejected.active {
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
	box-shadow: 0 20px 40px -10px rgba(0, 0, 0, 0.4), 0 0 0 1px
		rgba(255, 255, 255, 0.05);
	backdrop-filter: blur(20px);
	overflow: hidden;
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

.action-buttons {
	display: flex;
	gap: 0.5rem;
	justify-content: center;
	flex-wrap: wrap;
}

.action-btn {
	padding: 0.75rem 1.25rem;
	border: none;
	border-radius: 8px;
	font-weight: 600;
	font-size: 0.875rem;
	cursor: pointer;
	transition: all 0.3s ease;
	color: var(--text-primary);
	min-width: 100px;
	text-decoration: none;
	display: inline-flex;
	align-items: center;
	justify-content: center;
	gap: 0.5rem;
}

.action-btn.approve {
	background: var(--gradient-primary);
	box-shadow: 0 4px 15px rgba(59, 130, 246, 0.3);
}

.action-btn.approve:hover {
	transform: translateY(-2px);
	box-shadow: 0 8px 25px rgba(59, 130, 246, 0.4);
}

.action-btn.reject {
	background: linear-gradient(135deg, #ef4444, #dc2626);
	box-shadow: 0 4px 15px rgba(239, 68, 68, 0.3);
}

.action-btn.reject:hover {
	transform: translateY(-2px);
	box-shadow: 0 8px 25px rgba(239, 68, 68, 0.4);
}

.back-link {
	display: block;
	margin: 3rem auto 0 auto;
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

/* Responsive design */
@media ( max-width : 1024px) {
	.page-container {
		padding: 1.5rem;
	}
	.table-container, .filters-section {
		padding: 1.5rem;
	}
}

@media ( max-width : 768px) {
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
	.requests-table thead th, .requests-table tbody td {
		padding: 1rem;
		font-size: 0.875rem;
	}
	.action-buttons {
		flex-direction: column;
		gap: 0.25rem;
	}
	.action-btn {
		min-width: 80px;
		padding: 0.5rem 1rem;
		font-size: 0.8rem;
	}
}

@media ( max-width : 480px) {
	.header-section h1 {
		font-size: 2rem;
	}
	.requests-table {
		font-size: 0.8rem;
	}
	.requests-table thead th, .requests-table tbody td {
		padding: 0.75rem 0.5rem;
		font-size: 0.8rem;
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

.no-actions {
	color: var(--text-secondary);
	font-size: 0.8rem;
	font-style: italic;
}

/* Modal Styles */
.modal {
	display: none;
	position: fixed;
	z-index: 1000;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	backdrop-filter: blur(5px);
}

.modal-content {
	background: var(--gradient-secondary);
	border: 1px solid var(--border-color);
	border-radius: 20px;
	margin: 5% auto;
	padding: 0;
	width: 90%;
	max-width: 500px;
	box-shadow: 0 20px 40px rgba(0, 0, 0, 0.5);
	animation: modalSlideIn 0.3s ease-out;
}

@
keyframes modalSlideIn {from { opacity:0;
	transform: translateY(-50px);
}

to {
	opacity: 1;
	transform: translateY(0);
}

}
.modal-header {
	padding: 1.5rem 2rem;
	border-bottom: 1px solid var(--border-color);
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.modal-header h3 {
	margin: 0;
	color: var(--text-primary);
	font-size: 1.5rem;
}

.close {
	color: var(--text-secondary);
	font-size: 2rem;
	font-weight: bold;
	cursor: pointer;
	transition: color 0.3s ease;
}

.close:hover {
	color: var(--error-color);
}

.modal-body {
	padding: 2rem;
}

.modal-body p {
	margin-bottom: 1.5rem;
	color: var(--text-secondary);
}

.form-group {
	margin-bottom: 1.5rem;
}

.form-label {
	display: block;
	margin-bottom: 0.5rem;
	color: var(--text-secondary);
	font-weight: 500;
	font-size: 0.9rem;
}

.form-textarea {
	width: 100%;
	min-height: 100px;
	padding: 1rem;
	background: var(--secondary-bg);
	border: 1px solid var(--border-color);
	border-radius: 12px;
	color: var(--text-primary);
	font-size: 1rem;
	resize: vertical;
	transition: all 0.3s ease;
}

.form-textarea:focus {
	outline: none;
	border-color: var(--error-color);
	box-shadow: 0 0 0 3px rgba(239, 68, 68, 0.2);
}

.char-count {
	text-align: right;
	margin-top: 0.5rem;
	color: var(--text-secondary);
	font-size: 0.8rem;
}

.modal-actions {
	display: flex;
	gap: 1rem;
	justify-content: flex-end;
	margin-top: 2rem;
}

.btn-reject, .btn-cancel {
	padding: 0.75rem 1.5rem;
	border: none;
	border-radius: 8px;
	font-weight: 600;
	cursor: pointer;
	transition: all 0.3s ease;
}

.btn-reject {
	background: var(--error-color);
	color: white;
}

.btn-reject:hover {
	background: #dc2626;
	transform: translateY(-2px);
}

.btn-cancel {
	background: var(--border-color);
	color: var(--text-primary);
}

.btn-cancel:hover {
	background: var(--text-secondary);
	transform: translateY(-2px);
}

/* Rejection Reason Display */
.rejection-reason {
	color: var(--error-color);
	cursor: pointer;
	display: flex;
	align-items: center;
	gap: 0.5rem;
	transition: all 0.3s ease;
	position: relative;
}

.rejection-reason:hover {
	color: #dc2626;
	transform: translateY(-1px);
}

.rejection-reason i {
	font-size: 1rem;
}

.rejection-reason:hover::after {
	content: attr(title);
	position: absolute;
	bottom: 100%;
	left: 50%;
	transform: translateX(-50%);
	background: var(--primary-bg);
	color: var(--text-primary);
	padding: 0.75rem 1rem;
	border-radius: 8px;
	border: 1px solid var(--border-color);
	font-size: 0.85rem;
	white-space: nowrap;
	z-index: 1000;
	box-shadow: 0 8px 25px rgba(0, 0, 0, 0.4);
	max-width: 300px;
	word-wrap: break-word;
	white-space: normal;
	text-align: center;
}
</style>
</head>
<body>

	<div class="page-container">
		<div class="header-section">
			<h1>Leave Requests</h1>
			<p class="header-subtitle">Review and manage employee leave
				applications</p>
		</div>

		<div class="messages">
			<% if (request.getAttribute("message") != null) { %>
			<div class="message success">
				<i class="fas fa-check-circle me-2"></i>
				<%= request.getAttribute("message") %>
			</div>
			<% } %>
			<% if (request.getAttribute("error") != null) { %>
			<div class="message error">
				<i class="fas fa-exclamation-triangle me-2"></i>
				<%= request.getAttribute("error") %>
			</div>
			<% } %>
		</div>

		<!-- Search and Filter Section -->
		<div class="filters-section">
			<div class="filters-row">
				<div class="search-group">
					<label for="searchInput" class="search-label">Search
						Requests</label>
					<div class="search-row">
						<div class="input-field">
							<label for="employeeIdFilter" class="search-label">Employee
								Name</label> <input type="text" id="employeeIdFilter"
								class="search-input" placeholder="e.g., John Doe" />
						</div>
						<div class="input-field">
							<label for="reasonFilter" class="search-label">Reason</label> <input
								type="text" id="reasonFilter" class="search-input"
								placeholder="e.g., Vacation, Sick Leave" />
						</div>
						<div class="input-field">
							<label for="startDateFilter" class="search-label">Start
								Date</label> <input type="date" id="startDateFilter" class="search-date" />
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
				<span id="resultsCount">Showing all requests</span> <span
					id="filterStatus"></span>
			</div>
		</div>

		<div class="table-container">
			<table class="requests-table">
				<thead>
					<tr>
						<th>Leave ID</th>
						<th>Employee ID</th>
						<th>Period</th>
						<th>Reason</th>
						<th>Status</th>
						<th>Applied On</th>
						<th>Rejection Reason</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody id="requestsTableBody">
					<% for (LeaveRequest r : list) { %>
					<tr data-status="<%= r.getStatus().toLowerCase() %>"
						data-search="<%= r.getEmployeeName() != null ? r.getEmployeeName() : "" %> <%= r.getReason() %> <%= r.getStartDate() %> <%= r.getEndDate() %>">
						<td><%= r.getId() %></td>
						<td><%= r.getEmployeeId() %></td>
						<td><%= r.getStartDate() %> to <%= r.getEndDate() %></td>
						<td><%= r.getReason() %></td>
						<td><span
							class="status-badge status-<%= r.getStatus().toLowerCase() %>">
								<%= r.getStatus() %>
						</span></td>
						<td><%= r.getAppliedOn() %></td>
						<td>
							<% if ("Rejected".equalsIgnoreCase(r.getStatus())) { %> <span
							class="rejection-reason"
							title="<%= r.getRejectionReason() != null ? r.getRejectionReason() : "No reason provided" %>">
								<i class="fas fa-info-circle"></i> View Reason
						</span> <% } else { %> <span class="text-muted">N/A</span> <% } %>
						</td>
						<td>
							<% if ("Pending".equalsIgnoreCase(r.getStatus())) { %>
							<div class="action-buttons">
								<form method="post" action="<%=request.getContextPath()%>/admin"
									style="display: inline;">
									<input type="hidden" name="requestId" value="<%=r.getId()%>" />
									<input type="hidden" name="action" value="approve" />
									<button type="submit" class="action-btn approve">
										<i class="fas fa-check"></i> Approve
									</button>
								</form>
								<button type="button" class="action-btn reject"
									data-request-id="<%=r.getId()%>"
									data-employee-name="<%=r.getEmployeeName() != null ? r.getEmployeeName() : "Unknown"%>">
									<i class="fas fa-times"></i> Reject
								</button>
							</div> <% } else { %> <span class="text-muted">N/A</span> <% } %>
						</td>
					</tr>
					<% } %>
				</tbody>
			</table>
		</div>

		<a href="<%=request.getContextPath()%>/admin" class="back-link"> <i
			class="fas fa-arrow-left me-2"></i> Back to Dashboard
		</a>

		<!-- Rejection Modal -->
		<div id="rejectModal" class="modal" style="display: none;">
			<div class="modal-content">
				<div class="modal-header">
					<h3>Reject Leave Request</h3>
					<span class="close">&times;</span>
				</div>
				<div class="modal-body">
					<p>
						You are rejecting the leave request for: <strong
							id="rejectEmployeeName"></strong>
					</p>
					<form id="rejectForm" method="post"
						action="<%=request.getContextPath()%>/admin">
						<input type="hidden" name="requestId" id="rejectRequestId" /> <input
							type="hidden" name="action" value="reject" />
						<div class="form-group">
							<label for="rejectReason" class="form-label">Rejection
								Reason (Max 45 characters)</label>
							<textarea id="rejectReason" name="rejectReason"
								class="form-textarea"
								placeholder="Please provide a reason for rejection..."
								maxlength="45" required></textarea>
							<div class="char-count">
								<span id="charCount">0</span>/45 characters
							</div>
						</div>
						<div class="modal-actions">
							<button type="submit" class="btn-reject">Reject Request</button>
							<button type="button" class="btn-cancel">Cancel</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script>
    // Search and Filter Functionality
    class LeaveRequestManager {
        constructor() {
            this.employeeIdInput = document.getElementById('employeeIdFilter');
            this.reasonInput = document.getElementById('reasonFilter');
            this.startDateInput = document.getElementById('startDateFilter');
            this.endDateInput = document.getElementById('endDateFilter');
            this.filterButtons = document.querySelectorAll('.filter-btn[data-status]');
            this.clearButton = document.querySelector('.clear-filters');
            this.tableBody = document.getElementById('requestsTableBody');
            this.rows = Array.from(this.tableBody.querySelectorAll('tr'));
            this.currentFilter = 'all';
            this.currentFilters = {
                employeeId: '',
                reason: '',
                startDate: '',
                endDate: ''
            };
            
            this.initializeEventListeners();
        }

        initializeEventListeners() {
            // Search inputs
            this.employeeIdInput.addEventListener('input', (e) => {
                this.currentFilters.employeeId = e.target.value.toLowerCase();
                this.filterAndSearch();
            });

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
                employeeId: '',
                reason: '',
                startDate: '',
                endDate: ''
            };
            
            this.employeeIdInput.value = '';
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
                const employeeId = row.querySelector('td:nth-child(2)').textContent.toLowerCase();
                const reason = row.querySelector('td:nth-child(4)').textContent.toLowerCase();
                const startDate = row.querySelector('td:nth-child(3)').textContent.toLowerCase().split(' to ')[0];
                const endDate = row.querySelector('td:nth-child(3)').textContent.toLowerCase().split(' to ')[1];
                
                let shouldShow = true;

                // Apply status filter
                if (this.currentFilter !== 'all' && status !== this.currentFilter) {
                    shouldShow = false;
                }

                // Apply employee ID filter
                if (this.currentFilters.employeeId && !employeeId.includes(this.currentFilters.employeeId)) {
                    shouldShow = false;
                }

                // Apply reason filter
                if (this.currentFilters.reason && !reason.includes(this.currentFilters.reason)) {
                    shouldShow = false;
                }

                // Apply date range filter
                if (this.currentFilters.startDate || this.currentFilters.endDate) {
                    const period = row.querySelector('td:nth-child(3)').textContent;
                    const [rowStartStr, rowEndStr] = period.split(' to ');
                    const rowStartDate = new Date(rowStartStr);
                    const rowEndDate = new Date(rowEndStr);

                    if (this.currentFilters.startDate) {
                        const filterStartDate = new Date(this.currentFilters.startDate);
                        if (rowStartDate < filterStartDate) {
                            shouldShow = false;
                        }
                    }

                    if (this.currentFilters.endDate) {
                        const filterEndDate = new Date(this.currentFilters.endDate);
                        if (rowEndDate > filterEndDate) {
                            shouldShow = false;
                        }
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
            if (this.currentFilters.employeeId) {
                activeFilters.push('Employee Name: "' + this.currentFilters.employeeId + '"');
            }
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
    const requestManager = new LeaveRequestManager();

    // Modal functionality
    const modal = document.getElementById('rejectModal');
    const closeBtn = document.querySelector('.close');
    const cancelBtn = document.querySelector('.btn-cancel');
    const rejectReasonTextarea = document.getElementById('rejectReason');
    const charCount = document.getElementById('charCount');

    // Show rejection modal
    function showRejectModal(requestId, employeeName) {
        document.getElementById('rejectRequestId').value = requestId;
        document.getElementById('rejectEmployeeName').textContent = employeeName;
        modal.style.display = 'block';
        rejectReasonTextarea.focus();
    }

    // Close modal
    function closeModal() {
        modal.style.display = 'none';
        rejectReasonTextarea.value = '';
        charCount.textContent = '0';
    }

    // Close modal when clicking on X or Cancel
    closeBtn.onclick = closeModal;
    cancelBtn.onclick = closeModal;

    // Close modal when clicking outside
    window.onclick = function(event) {
        if (event.target === modal) {
            closeModal();
        }
    }

    // Character count for rejection reason
    rejectReasonTextarea.addEventListener('input', function() {
        const length = this.value.length;
        charCount.textContent = length;
        
        if (length > 40) {
            charCount.style.color = '#f59e0b';
        } else if (length > 45) {
            charCount.style.color = '#ef4444';
        } else {
            charCount.style.color = '#cbd5e1';
        }
    });

    // Add event listeners for reject buttons
    document.addEventListener('DOMContentLoaded', function() {
        document.querySelectorAll('.action-btn.reject').forEach(btn => {
            btn.addEventListener('click', function() {
                const requestId = this.getAttribute('data-request-id');
                const employeeName = this.getAttribute('data-employee-name');
                showRejectModal(requestId, employeeName);
            });
        });
    });

    // Add staggered animation on load
    const tableRows = document.querySelectorAll('.requests-table tbody tr');
    
    tableRows.forEach((row, index) => {
        row.style.opacity = '0';
        row.style.transform = 'translateY(20px)';
        
        setTimeout(() => {
            row.style.transition = 'all 0.6s cubic-bezier(0.4, 0, 0.2, 1)';
            row.style.opacity = '1';
            row.style.transform = 'translateY(0)';
        }, index * 100);
    });

    // Add confirmation for actions
    document.querySelectorAll('.action-btn').forEach(btn => {
        btn.addEventListener('click', function(e) {
            const action = this.textContent.trim().toLowerCase();
            if (!confirm('Are you sure you want to ' + action + ' this leave request?')) {
                e.preventDefault();
            }
        });
    });
</script>

</body>
</html>
