<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Feedback Submitted</title>
</head>
<body style="font-family: Arial, sans-serif;">
    <h2 style="color:green;">
        Name: <%= request.getAttribute("name") %> <br>
        Your Feedback is <b>submitted successfully</b> on Date: <%= request.getAttribute("date") %>
    </h2>
</body>
</html>
