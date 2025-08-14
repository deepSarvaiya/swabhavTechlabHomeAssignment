<%@ page import="model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Employee emp = (Employee) session.getAttribute("employee");
    if (emp == null) { 
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
    int leaveBalance = emp.getLeaveBalance();
    int totalLeaves = 30; // Example
    int leavesTaken = totalLeaves - leaveBalance;
%>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            background: linear-gradient(135deg, #e3f2fd, #bbdefb);
        }
        .dashboard-card {
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        canvas {
            max-height: 200px !important;
        }
    </style>
</head>
<body class="p-3">
<div class="container mt-5">
    <div class="card dashboard-card p-4">
        <h2 class="text-center text-primary mb-3">Welcome, <%= emp.getName() %></h2>
        <p class="text-center fs-5">Leave Balance: <strong><%= leaveBalance %></strong> days</p>
        
   

        <div class="d-flex justify-content-center mt-4 gap-3">
            <a href="<%=request.getContextPath()%>/employee?action=apply" class="btn btn-primary">Apply for Leave</a>
            <a href="<%=request.getContextPath()%>/employee?action=myrequests" class="btn btn-info text-white">My Requests</a>
            <a href="<%=request.getContextPath()%>/logout" class="btn btn-danger">Logout</a>
        </div>

        <% if (request.getAttribute("message") != null) { %>
            <p class="text-success text-center fw-bold mt-3"><%= request.getAttribute("message") %></p>
        <% } %>
        <% if (request.getAttribute("error") != null) { %>
            <p class="text-danger text-center fw-bold mt-3"><%= request.getAttribute("error") %></p>
        <% } %>
    </div>
</div>

</body>
</html>
