<%@ page import="model.LeaveRequest" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<LeaveRequest> list = (List<LeaveRequest>) request.getAttribute("requests");
%>
<!DOCTYPE html>
<html>
<head>
    <title>My Requests</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
            color: #333;
            margin: 0;
            padding: 20px;
        }
        h3 {
            color: #2c3e50;
            text-align: center;
        }
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background: white;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }
        table thead {
            background-color: #3498db;
            color: white;
        }
        th, td {
            padding: 12px 15px;
            border: 1px solid #ddd;
            text-align: center;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        a {
            display: inline-block;
            text-decoration: none;
            padding: 8px 15px;
            background-color: #3498db;
            color: white;
            border-radius: 4px;
            margin: 10px auto;
            display: block;
            width: fit-content;
        }
        a:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>

<h3>My Leave Requests</h3>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Period</th>
            <th>Reason</th>
            <th>Status</th>
            <th>Applied On</th>
        </tr>
    </thead>
    <tbody>
        <% if (list != null && !list.isEmpty()) { 
               for (LeaveRequest r : list) { %>
            <tr>
                <td><%= r.getId() %></td>
                <td><%= r.getStartDate() %> to <%= r.getEndDate() %></td>
                <td><%= r.getReason() %></td>
                <td><%= r.getStatus() %></td>
                <td><%= r.getAppliedOn() %></td>
            </tr>
        <%   }
           } else { %>
            <tr>
                <td colspan="5" style="color:red;">No leave requests found.</td>
            </tr>
        <% } %>
    </tbody>
</table>

<a href="<%=request.getContextPath()%>/employee">Back</a>

</body>
</html>
