<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');

        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #f0f4f5, #e0ecef);
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            min-height: 100vh;
            color: #1f2933;
        }

        h2 {
            margin-top: 60px;
            font-size: 2rem;
            color: #1e3a3a;
            font-weight: 600;
            letter-spacing: 0.5px;
        }

        .nav {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            margin-top: 40px;
            gap: 16px;
        }

        .nav a {
            text-decoration: none;
            background: #38b2ac;  /* Softer teal */
            color: white;
            padding: 14px 28px;
            border-radius: 10px;
            font-weight: 600;
            font-size: 1rem;
            box-shadow: 0 4px 12px rgba(0,0,0,0.08);
            transition: all 0.25s ease-in-out;
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .nav a:hover {
            transform: translateY(-3px) scale(1.03);
            box-shadow: 0 8px 20px rgba(0,0,0,0.12);
            background: #319795; /* Slightly darker teal on hover */
        }

        footer {
            margin-top: auto;
            padding: 20px 0;
            font-size: 0.9rem;
            color: #1e3a3a;
            text-align: center;
        }

        /* Icons style */
        .nav a i {
            font-size: 1.1rem;
        }

        @media (max-width: 600px) {
            .nav {
                flex-direction: column;
            }
            .nav a {
                width: 80%;
                justify-content: center;
            }
        }
    </style>

    <script>
        function confirmLogout(e) {
            if (!confirm("Are you sure you want to log out?")) {
                e.preventDefault();
            }
        }
    </script>
</head>
<body>

<h2>Admin Dashboard</h2>

<div class="nav">
    <a href="<%=request.getContextPath()%>/admin?action=view"><i class="fas fa-file-alt"></i> View All Requests</a>
    <a href="<%=request.getContextPath()%>/logout" onclick="confirmLogout(event)"><i class="fas fa-sign-out-alt"></i> Logout</a>
</div>

<footer>
    &copy; <%= java.time.Year.now() %> Admin Panel. All Rights Reserved.
</footer>

<!-- Font Awesome Icons -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/js/all.min.js"></script>

</body>
</html>
