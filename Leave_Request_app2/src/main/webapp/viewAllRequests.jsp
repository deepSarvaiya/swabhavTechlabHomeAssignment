<%@ page import="model.LeaveRequest" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<LeaveRequest> list = (List<LeaveRequest>) request.getAttribute("requests");
%>
<!DOCTYPE html>
<html>
<head>
    <title>All Leave Requests</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0fdfa;
            margin: 0;
            padding: 20px;
            color: #064e3b;
        }
        h3 {
            text-align: center;
            color: #065f46;
            font-weight: 700;
            margin-bottom: 25px;
            font-size: 1.8rem;
        }
        .message {
            max-width: 600px;
            margin: 0 auto 20px auto;
            padding: 12px 20px;
            border-radius: 8px;
            font-weight: 600;
            font-size: 1rem;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        .success {
            background-color: #d1fae5;
            color: #065f46;
            border: 1px solid #10b981;
        }
        .error {
            background-color: #fee2e2;
            color: #991b1b;
            border: 1px solid #ef4444;
        }
        table {
            border-collapse: separate;
            border-spacing: 0 8px;
            width: 100%;
            max-width: 900px;
            margin: 0 auto;
            background: white;
            box-shadow: 0 8px 24px rgb(0 0 0 / 0.05);
            border-radius: 12px;
            overflow: hidden;
        }
        th, td {
            padding: 14px 20px;
            text-align: center;
            vertical-align: middle;
        }
        thead th {
            background-color: #06b6d4; /* cyan/teal header */
            color: white;
            font-weight: 600;
            text-transform: uppercase;
            font-size: 0.875rem;
            letter-spacing: 0.05em;
        }
        tbody tr {
            background-color: #f0fdfa;
            transition: background-color 0.25s ease;
            border-radius: 10px;
        }
        tbody tr:hover {
            background-color: #ccfbf1; /* lighter cyan on hover */
        }
        tbody tr td {
            border: none;
            font-size: 0.95rem;
            color: #065f46;
        }
        form {
            display: inline-block;
            margin: 0 4px;
        }
        button {
            padding: 8px 14px;
            border: none;
            border-radius: 8px;
            font-weight: 600;
            font-size: 0.9rem;
            cursor: pointer;
            transition: background-color 0.25s ease, box-shadow 0.25s ease;
            color: white;
            min-width: 90px;
        }
        .approve-btn {
            background-color: #10b981; /* green */
            box-shadow: 0 4px 10px rgb(16 185 129 / 0.4);
        }
        .approve-btn:hover {
            background-color: #059669;
            box-shadow: 0 6px 16px rgb(5 150 105 / 0.5);
        }
        .reject-btn {
            background-color: #ef4444;
            box-shadow: 0 4px 10px rgb(239 68 68 / 0.4);
        }
        .reject-btn:hover {
            background-color: #b91c1c;
            box-shadow: 0 6px 16px rgb(185 28 28 / 0.5);
        }
        td:last-child {
            font-weight: 600;
            color: #065f46;
        }
        @media (max-width: 720px) {
            table, thead, tbody, th, td, tr { display: block; }
            thead tr { display: none; }
            tbody tr {
                margin-bottom: 20px;
                background-color: white;
                box-shadow: 0 4px 12px rgba(0,0,0,0.1);
                border-radius: 12px;
                padding: 12px 20px;
            }
            tbody tr td {
                text-align: right;
                padding-left: 50%;
                position: relative;
                font-size: 0.9rem;
                border-bottom: 1px solid #e5e7eb;
            }
            tbody tr td::before {
                content: attr(data-label);
                position: absolute;
                left: 20px;
                width: 45%;
                padding-left: 0;
                font-weight: 600;
                color: #065f46;
                text-align: left;
            }
            td:last-child {
                border-bottom: none;
                text-align: center;
                font-weight: 700;
                color: #065f46;
                padding-top: 12px;
            }
            form { margin: 4px 4px 0 0; }
        }
        .back-link {
            display: block;
            margin: 30px auto 0 auto;
            text-align: center;
            max-width: 900px;
            font-weight: 600;
            font-size: 1rem;
            color: #06b6d4;
            text-decoration: none;
            transition: color 0.3s ease;
        }
        .back-link:hover {
            color: #0891b2;
            text-decoration: underline;
        }
    </style>
</head>
<body>

<h3>All Leave Requests</h3>

<% if (request.getAttribute("message") != null) { %>
    <div class="message success"><%= request.getAttribute("message") %></div>
<% } %>
<% if (request.getAttribute("error") != null) { %>
    <div class="message error"><%= request.getAttribute("error") %></div>
<% } %>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Employee ID</th>
            <th>Period</th>
            <th>Reason</th>
            <th>Status</th>
            <th>Applied On</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
    <% for (LeaveRequest r : list) { %>
        <tr>
            <td data-label="ID"><%= r.getId() %></td>
            <td data-label="Employee ID"><%= r.getEmployeeId() %></td>
            <td data-label="Period"><%= r.getStartDate() %> to <%= r.getEndDate() %></td>
            <td data-label="Reason"><%= r.getReason() %></td>
            <td data-label="Status"><%= r.getStatus() %></td>
            <td data-label="Applied On"><%= r.getAppliedOn() %></td>
            <td data-label="Action">
                <% if ("Pending".equalsIgnoreCase(r.getStatus())) { %>
                    <form method="post" action="<%=request.getContextPath()%>/admin">
                        <input type="hidden" name="requestId" value="<%=r.getId()%>" />
                        <input type="hidden" name="action" value="approve" />
                        <button type="submit" class="approve-btn">Approve</button>
                    </form>
                    <form method="post" action="<%=request.getContextPath()%>/admin">
                        <input type="hidden" name="requestId" value="<%=r.getId()%>" />
                        <input type="hidden" name="action" value="reject" />
                        <button type="submit" class="reject-btn">Reject</button>
                    </form>
                <% } else { %>
                    N/A
                <% } %>
            </td>
        </tr>
    <% } %>
    </tbody>
</table>

<a href="<%=request.getContextPath()%>/admin" class="back-link">⬅ Back to Dashboard</a>

</body>
</html>
