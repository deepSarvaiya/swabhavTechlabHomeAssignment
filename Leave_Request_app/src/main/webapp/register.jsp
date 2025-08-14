<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Register Employee - Leave Management System</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    
    <!-- Font Awesome for Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet" />

    <!-- Custom Styles -->
    <style>
        :root {
            --primary-bg: #0f172a;
            --secondary-bg: #1e293b;
            --card-bg: #334155;
            --accent-color: #10b981;
            --accent-hover: #059669;
            --text-primary: #f8fafc;
            --text-secondary: #cbd5e1;
            --border-color: #475569;
            --success-color: #10b981;
            --error-color: #ef4444;
            --gradient-primary: linear-gradient(135deg, #10b981, #059669);
            --gradient-secondary: linear-gradient(135deg, #1e293b, #334155);
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            background: var(--primary-bg);
            font-family: 'Inter', sans-serif;
            color: var(--text-primary);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 2rem;
            position: relative;
            overflow-x: hidden;
        }

        /* Animated background */
        body::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: 
                radial-gradient(circle at 20% 80%, rgba(16, 185, 129, 0.1) 0%, transparent 50%),
                radial-gradient(circle at 80% 20%, rgba(59, 130, 246, 0.1) 0%, transparent 50%),
                radial-gradient(circle at 40% 40%, rgba(139, 92, 246, 0.05) 0%, transparent 50%);
            z-index: -1;
        }

        .register-container {
            width: 100%;
            max-width: 480px;
            position: relative;
            z-index: 1;
        }

        .register-card {
            background: var(--gradient-secondary);
            border: 1px solid var(--border-color);
            border-radius: 24px;
            box-shadow: 
                0 25px 50px -12px rgba(0, 0, 0, 0.5),
                0 0 0 1px rgba(255, 255, 255, 0.05);
            backdrop-filter: blur(20px);
            overflow: hidden;
            transition: all 0.4s cubic-bezier(0.4, 0, 0.2, 1);
        }

        .register-card:hover {
            transform: translateY(-8px);
            box-shadow: 
                0 32px 64px -12px rgba(0, 0, 0, 0.6),
                0 0 0 1px rgba(255, 255, 255, 0.1);
        }

        .card-header {
            background: var(--gradient-primary);
            padding: 2.5rem 2rem 2rem;
            text-align: center;
            position: relative;
            overflow: hidden;
        }

        .card-header::before {
            content: '';
            position: absolute;
            top: -50%;
            left: -50%;
            width: 200%;
            height: 200%;
            background: radial-gradient(circle, rgba(255, 255, 255, 0.1) 0%, transparent 70%);
            animation: float 6s ease-in-out infinite;
        }

        @keyframes float {
            0%, 100% { transform: translateY(0px) rotate(0deg); }
            50% { transform: translateY(-20px) rotate(180deg); }
        }

        .card-header h2 {
            font-weight: 700;
            font-size: 1.75rem;
            color: var(--text-primary);
            margin: 0;
            position: relative;
            z-index: 1;
        }

        .card-header .icon {
            font-size: 2.5rem;
            color: var(--text-primary);
            margin-bottom: 1rem;
            display: block;
            position: relative;
            z-index: 1;
        }

        .card-body {
            padding: 2.5rem 2rem;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        .form-label {
            font-weight: 500;
            color: var(--text-secondary);
            margin-bottom: 0.5rem;
            font-size: 0.9rem;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .input-group {
            position: relative;
            border-radius: 12px;
            overflow: hidden;
            background: var(--secondary-bg);
            border: 1px solid var(--border-color);
            transition: all 0.3s ease;
        }

        .input-group:focus-within {
            border-color: var(--accent-color);
            box-shadow: 0 0 0 3px rgba(16, 185, 129, 0.2);
            transform: translateY(-2px);
        }

        .input-group-text {
            background: transparent;
            border: none;
            color: var(--text-secondary);
            padding: 0 1rem;
            font-size: 1.1rem;
        }

        .form-control {
            background: transparent;
            border: none;
            color: var(--text-primary);
            padding: 1rem 1rem 1rem 0;
            font-size: 1rem;
            font-weight: 400;
        }

        .form-control::placeholder {
            color: var(--text-secondary);
            opacity: 0.7;
        }

        .form-control:focus {
            background: transparent;
            border: none;
            box-shadow: none;
            color: var(--text-primary);
        }

        .form-control.is-invalid {
            border-color: var(--error-color);
        }

        .btn-register {
            background: var(--gradient-primary);
            border: none;
            color: var(--text-primary);
            font-weight: 600;
            padding: 1rem 2rem;
            border-radius: 12px;
            font-size: 1rem;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
            position: relative;
            overflow: hidden;
        }

        .btn-register::before {
            content: '';
            position: absolute;
            top: 0;
            left: -100%;
            width: 100%;
            height: 100%;
            background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.2), transparent);
            transition: left 0.5s;
        }

        .btn-register:hover::before {
            left: 100%;
        }

        .btn-register:hover {
            transform: translateY(-3px);
            box-shadow: 0 20px 40px rgba(16, 185, 129, 0.4);
        }

        .card-footer {
            background: var(--secondary-bg);
            padding: 1.5rem 2rem;
            text-align: center;
            border-top: 1px solid var(--border-color);
        }

        .card-footer a {
            color: var(--accent-color);
            text-decoration: none;
            font-weight: 500;
            transition: color 0.3s ease;
        }

        .card-footer a:hover {
            color: var(--accent-hover);
        }

        /* Responsive design */
        @media (max-width: 576px) {
            body {
                padding: 1rem;
            }
            
            .register-card {
                border-radius: 20px;
            }
            
            .card-header, .card-body, .card-footer {
                padding: 1.5rem 1.5rem;
            }
            
            .card-header h2 {
                font-size: 1.5rem;
            }
        }

        /* Loading animation */
        .btn-register:active {
            transform: scale(0.98);
        }

        /* Custom scrollbar */
        ::-webkit-scrollbar {
            width: 8px;
        }

        ::-webkit-scrollbar-track {
            background: var(--secondary-bg);
        }

        ::-webkit-scrollbar-thumb {
            background: var(--border-color);
            border-radius: 4px;
        }

        ::-webkit-scrollbar-thumb:hover {
            background: var(--accent-color);
        }
    </style>
</head>
<body>

<div class="register-container">
    <div class="register-card">
        <div class="card-header">
            <i class="fas fa-user-plus icon"></i>
            <h2>Create Account</h2>
            <p class="text-light mb-0">Join our team today</p>
        </div>
        
        <div class="card-body">
            <form method="post" action="RegisterController" class="needs-validation" novalidate>

                <!-- Name -->
                <div class="form-group">
                    <label for="name" class="form-label">Full Name</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-id-card"></i></span>
                        <input type="text" id="name" name="name" class="form-control" placeholder="Enter your full name" required />
                    </div>
                    <div class="invalid-feedback">Please enter a name.</div>
                </div>

                <!-- Username -->
                <div class="form-group">
                    <label for="username" class="form-label">Username</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                        <input type="text" id="username" name="username" class="form-control" placeholder="Choose a unique username" required minlength="3" />
                    </div>
                    <div class="invalid-feedback">Username must be at least 3 characters.</div>
                </div>

                <!-- Password -->
                <div class="form-group">
                    <label for="password" class="form-label">Password</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-lock"></i></span>
                        <input type="password" id="password" name="password" class="form-control" placeholder="Create a strong password" required minlength="5" />
                    </div>
                    <div class="invalid-feedback">Password must be at least 5 characters.</div>
                </div>

         

                <button type="submit" class="btn btn-register w-100">Create Account</button>
            </form>
        </div>
        
        <div class="card-footer">
            Already have an account? <a href="login.jsp">Sign In Here</a>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- Custom JS for Validation -->
<script>
    (function () {
        'use strict';
        
        // Bootstrap 5 Form Validation
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

        // Add loading state to form submission
        const form = document.querySelector('form');
        const submitBtn = document.querySelector('.btn-register');
        
        form.addEventListener('submit', function() {
            submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin me-2"></i>Creating Account...';
            submitBtn.disabled = true;
        });
    })();
</script>

</body>
</html>
