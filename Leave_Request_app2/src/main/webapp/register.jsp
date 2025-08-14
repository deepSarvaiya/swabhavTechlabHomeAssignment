<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Register Employee Preview</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    
    <!-- Font Awesome for Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet" />

    <!-- Custom Styles (Lighter Theme) -->
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
        .register-card {
            background-color: #ffffff; /* White background */
            border: 1px solid #cbd5e1; /* Light border */
            border-radius: 16px;
            box-shadow: 0 8px 32px rgba(100, 116, 139, 0.1);
            width: 100%;
            max-width: 420px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        .register-card:hover {
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
            color: #1e293b; /* Dark text */
        }
        .form-label {
            font-weight: 500;
            color: #64748b; /* Medium gray */
        }
        .input-group-text {
            background-color: #e2e8f0; /* Light bg */
            border: 1px solid #cbd5e1;
            color: #64748b;
        }
        .form-control {
            background-color: #f1f5f9; /* Light input bg */
            border: 1px solid #cbd5e1;
            color: #334155;
            height: 48px;
        }
        .form-control:focus {
            background-color: #ffffff;
            border-color: #10b981; /* Tailwind green */
            box-shadow: 0 0 0 3px rgba(16, 185, 129, 0.25);
            color: #065f46;
        }
        .form-control.is-invalid {
            border-color: #ef4444; /* red-500 */
        }
        .btn-custom-register {
            background: linear-gradient(90deg, #10b981, #059669);
            border: none;
            transition: all 0.3s ease;
            color: white;
            font-weight: 600;
            padding: 12px 0;
            border-radius: 8px;
        }
        .btn-custom-register:hover {
            background: linear-gradient(90deg, #059669, #10b981);
            box-shadow: 0 5px 15px rgba(16, 185, 129, 0.4);
            transform: translateY(-2px);
        }
        .footer-link a {
            text-decoration: none;
            color: #2563eb; /* Blue link */
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
    </style>
</head>
<body>

<div class="register-card">
    <div class="card-header">
        <h2><i class="fas fa-user-plus me-2"></i>Create Employee Account</h2>
    </div>
    <div class="card-body p-4">

        <!-- Server-side messages would appear here -->

        <form method="post" action="RegisterController" class="needs-validation" novalidate>

            <!-- Name -->
            <div class="mb-3">
                <label for="name" class="form-label">Full Name</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-id-card"></i></span>
                    <input type="text" id="name" name="name" class="form-control" required />
                    <div class="invalid-feedback">Please enter a name.</div>
                </div>
            </div>

            <!-- Username -->
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                    <input type="text" id="username" name="username" class="form-control" placeholder="Must be unique" required minlength="3" />
                    <div class="invalid-feedback">Username must be at least 3 characters.</div>
                </div>
            </div>

            <!-- Password -->
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-lock"></i></span>
                    <input type="password" id="password" name="password" class="form-control" placeholder="At least 5 characters" required minlength="5" />
                    <div class="invalid-feedback">Password must be at least 5 characters.</div>
                </div>
            </div>

            <!-- Leave Balance -->
            <div class="mb-4">
                <label for="leaveBalance" class="form-label">Initial Leave Balance</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-calendar-check"></i></span>
                    <input type="number" id="leaveBalance" name="leaveBalance" class="form-control" placeholder="14" required min="0" />
                    <div class="invalid-feedback">Please enter a valid number (0 or more).</div>
                </div>
            </div>

            <button type="submit" class="btn btn-custom-register w-100">Register</button>
        </form>
    </div>
    <div class="card-footer text-center py-3 footer-link">
        Already have an account? <a href="login.jsp">Login Here</a>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- Custom JS for Validation -->
<script>
    (function () {
        'use strict';
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
    })();
</script>

</body>
</html>
