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
<title>Apply Leave</title>
<style>
body {
	font-family: Arial, sans-serif;
	background: linear-gradient(135deg, #eef2f3, #dfe9f3);
	margin: 0;
	padding: 0;
}

h3 {
	text-align: center;
	margin-top: 30px;
	color: #2c3e50;
}

.container {
	max-width: 400px;
	margin: 40px auto;
	background: white;
	padding: 25px;
	border-radius: 8px;
	box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
}

label {
	font-weight: bold;
	display: block;
	margin-top: 12px;
	color: #34495e;
}

input[type="date"], input[type="text"] {
	width: 100%;
	padding: 8px;
	margin-top: 5px;
	border-radius: 4px;
	border: 1px solid #ccc;
	font-size: 14px;
}

button {
	width: 100%;
	padding: 10px;
	margin-top: 20px;
	background-color: #27ae60;
	color: white;
	border: none;
	border-radius: 6px;
	font-size: 15px;
	font-weight: bold;
	cursor: pointer;
	transition: 0.3s ease;
}

button:hover {
	background-color: #219150;
}

.back-link {
	display: block;
	text-align: center;
	margin-top: 15px;
	color: #3498db;
	text-decoration: none;
	font-weight: bold;
}

.back-link:hover {
	text-decoration: underline;
}

.error {
	color: red;
	font-size: 14px;
	text-align: center;
	margin-top: 10px;
}
</style>

<script>
    const start = document.getElementById('startDate');
    const end = document.getElementById('endDate');

    start.addEventListener('change', () => {
        end.min = start.value;
    });
</script>

</head>
<body>

	<h3>
		Apply Leave (Balance:
		<%=emp.getLeaveBalance()%>
		days)
	</h3>

	<div class="container">
		<form method="post" action="<%=request.getContextPath()%>/employee"
			onsubmit="validateDates(event)">
			<%
			// Get today's date in yyyy-MM-dd format for min attribute
			java.time.LocalDate today = java.time.LocalDate.now();
			%>

			<label for="startDate">Start Date:</label> <input type="date"
				id="startDate" name="startDate" min="<%=today%>" required>

			<label for="endDate">End Date:</label> <input type="date"
				id="endDate" name="endDate" min="<%=today%>" required> <label>Reason:</label>
			<input type="text" name="reason" placeholder="Enter reason" />

			<button type="submit">Submit</button>
		</form>

		<a href="<%=request.getContextPath()%>/employee" class="back-link">⬅
			Back</a>

		<%
		if (request.getAttribute("error") != null) {
		%>
		<p class="error"><%=request.getAttribute("error")%></p>
		<%
		}
		%>
	</div>

</body>
</html>
