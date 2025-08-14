<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Login Portal Preview</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    
    <!-- Font Awesome for Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet" />

    <!-- Custom Styles -->
    <style>
        body {
            background: #f8fafc; /* Light gray background */
            font-family: 'Poppins', sans-serif;
            color: #334155; /* Dark slate text */
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            padding: 1rem;
        }
        .login-card {
            background-color: #ffffff; /* White background */
            border: 1px solid #cbd5e1; /* Light border */
            border-radius: 16px;
            box-shadow: 0 8px 32px rgba(100, 116, 139, 0.1);
            width: 100%;
            max-width: 400px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        .login-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 12px 40px rgba(100, 116, 139, 0.15);
        }
        .card-header {
            background-color: transparent;
            border-bottom: 1px solid #e2e8f0;
            text-align: center;
            padding: 1.5rem 1rem;
        }
        .card-header h2 {
            font-weight: 600;
            color: #1e293b; /* Darker text */
        }
        .form-label {
            font-weight: 500;
            color: #64748b; /* Medium gray */
        }
        .input-group-text {
            background-color: #e2e8f0; /* Light background */
            border: 1px solid #cbd5e1;
            color: #64748b;
        }
        .form-control, .form-select {
            background-color: #f1f5f9; /* Light input bg */
            border: 1px solid #cbd5e1;
            color: #334155;
            height: 48px;
        }
        .form-control:focus, .form-select:focus {
            background-color: #ffffff;
            border-color: #2563eb; /* Bootstrap blue */
            box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.25);
            color: #1e293b;
        }
        /* Style for valid/invalid states from Bootstrap validation */
        .form-control.is-invalid, .form-select.is-invalid {
            border-color: #ef4444; /* red-500 */
        }
        .form-control.is-valid, .form-select.is-valid {
            border-color: #22c55e; /* green-500 */
        }
        .btn-custom-login {
            background: linear-gradient(90deg, #2563eb, #3b82f6);
            border: none;
            transition: all 0.3s ease;
            color: white;
            font-weight: 600;
            padding: 12px 0;
            border-radius: 8px;
        }
        .btn-custom-login:hover {
            background: linear-gradient(90deg, #3b82f6, #2563eb);
            box-shadow: 0 5px 15px rgba(59, 130, 246, 0.4);
            transform: translateY(-2px);
        }
        .form-check-label {
             color: #64748b;
        }
        .footer-link a {
            text-decoration: none;
            color: #2563eb;
            font-size: 0.9rem;
            transition: color 0.3s ease;
        }
        .footer-link a:hover {
            color: #60a5fa;
            text-decoration: underline;
        }
        .card-footer {
            background-color: transparent;
            border-top: 1px solid #e2e8f0;
            font-size: 0.9rem;
            color: #475569;
        }
        #password-toggle {
            cursor: pointer;
            color: #64748b;
        }
    </style>
</head>
<body>

<div class="login-card">
    <div class="card-header">
        <h2><i class="fas fa-sign-in-alt me-2"></i>Secure Login</h2>
    </div>
    <div class="card-body p-4">
        

        <form method="post" action="LoginController" class="needs-validation" novalidate>
            
            <!-- Username -->
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                    <input type="text" id="username" name="username" class="form-control" placeholder="Enter your username" required minlength="3" />
                    <div class="invalid-feedback">Username must be at least 3 characters.</div>
                </div>
            </div>

            <!-- Password -->
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <div class="input-group">
                     <span class="input-group-text"><i class="fas fa-lock"></i></span>
                    <input type="password" id="password" name="password" class="form-control" placeholder="Enter your password" required minlength="5" />
                    <span class="input-group-text" id="password-toggle"><i class="fas fa-eye"></i></span>
                    <div class="invalid-feedback">Password must be at least 5 characters.</div>
                </div>
            </div>


            <div class="mb-4">
                <label for="role" class="form-label">Login as</label>
                <select id="role" name="role" class="form-select">
                    <option value="employee">Employee</option>
                    <option value="admin">Admin</option>
                </select>
            </div>
            
            <!-- Remember Me & Forgot Password -->
            <div class="d-flex justify-content-between align-items-center mb-4">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="rememberMe" />
                    <label class="form-check-label" for="rememberMe">Remember Me</label>
                </div>
                <div class="footer-link">
                    <a href="#">Forgot Password?</a>
                </div>
            </div>

            <button type="submit" class="btn btn-custom-login w-100">Login</button>
        </form>
    </div>
    <div class="card-footer text-center py-3 footer-link">
        New Employee? <a href="register.jsp">Create an account</a>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    (function () {
        'use strict';

        // --- Bootstrap 5 Form Validation ---
        var forms = document.querySelectorAll('.needs-validation');
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });

        const passwordInput = document.getElementById('password');
        const passwordToggle = document.getElementById('password-toggle');
        
        if (passwordToggle) {
            const toggleIcon = passwordToggle.querySelector('i');
            passwordToggle.addEventListener('click', function() {
                const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
                passwordInput.setAttribute('type', type);
                toggleIcon.classList.toggle('fa-eye');
                toggleIcon.classList.toggle('fa-eye-slash');
            });
        }
    })();
</script>

</body>
</html>
