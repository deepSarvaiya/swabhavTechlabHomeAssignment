<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Feedback App</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f6f9; }
        .container { max-width: 500px; margin: 50px auto; background: white; padding: 40px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1);}
        h2 { text-align: center; color: #2c3e50; }
        label { display: block; margin-top: 10px; }
        input, textarea { width: 100%; padding: 8px; margin-top: 4px; border-radius: 5px; border: 1px solid #ccc; }
        input[type="submit"] { background: #27ae60; color: white; border: none; cursor: pointer; margin-top: 15px; }
        input[type="submit"]:hover { background: #219150; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Feedback App</h2>
        <form action="FeedbackServlet" method="post">
            <label>Name:</label>
            <input type="text" name="name" required>

            <label>Session Date:</label>
            <input type="date" name="sessionDate" required>

            <h3>Feedback</h3>
            <label>Session Content:</label>
            <input type="number" name="sessionContent" min="1" max="10" required>

            <label>Query Resolution/Feedback:</label>
            <input type="number" name="queryResolution" min="1" max="10" required>

            <label>Interactivity/Engagement:</label>
            <input type="number" name="interactivity" min="1" max="10" required>

            <label>Impactful Learning:</label>
            <input type="number" name="impactfulLearning" min="1" max="10" required>

            <label>Content Delivery Skills:</label>
            <input type="number" name="contentDelivery" min="1" max="10" required>

            <input type="submit" value="Submit Feedback">
        </form>
    </div>
</body>
</html>
