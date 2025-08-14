<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Portal</title>
</head>
<body>
    <jsp:include page="header.jsp" />

    <h2>Enter Student Details</h2>
    <form action="processStudent.jsp" method="post">
        Name: <input type="text" name="name" required><br><br>
        Course: <input type="text" name="course" required><br><br>
        Marks: <input type="number" name="marks" required><br><br>
        <input type="submit" value="Submit">
    </form>

    <jsp:include page="footer.jsp" />
</body>
</html>
