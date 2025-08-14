<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Portal</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f9;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            background: #ffffff;
            padding: 20px 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 10px;
        }
        h2 {
            color: #34495e;
            margin-top: 20px;
        }
        p {
            font-size: 16px;
            margin: 6px 0;
        }
        strong {
            color: #2c3e50;
        }
        .date {
            text-align: center;
            font-size: 14px;
            color: #7f8c8d;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- Title using expression tag -->
        <h1><%= "Welcome to Student Portal" %></h1>

        <!-- Current Date & Time -->
        <p class="date"><strong>Current Date & Time:</strong> <%= new java.util.Date() %></p>

        <%!
            String studentName = "Deep Sarvaiya";
            String course = "Information Technology";
            int marks = 95;
            String grade;
        %>

        <%
            if (marks >= 90) {
                grade = "A";
            } else if (marks >= 75 && marks < 90) {
                grade = "B";
            } else if (marks >= 50 && marks < 75) {
                grade = "C";
            } else {
                grade = "F";
            }
        %>

        <h2>Student Information</h2>
        <p><strong>Name:</strong> <%= studentName %></p>
        <p><strong>Course:</strong> <%= course %></p>
        <p><strong>Marks:</strong> <%= marks %></p>
        <p><strong>Grade:</strong> <%= grade %></p>
    </div>
</body>
</html>
