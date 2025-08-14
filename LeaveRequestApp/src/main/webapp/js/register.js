// Leave Management System - Registration Page JavaScript

document.addEventListener('DOMContentLoaded', function() {
    // Initialize the registration page
    initializeRegistrationPage();
});

function initializeRegistrationPage() {
    // Add input focus effects
    setupInputEffects();
    
    // Add form validation
    setupFormValidation();
    
    // Add password toggle functionality
    setupPasswordToggles();
    
    // Add password strength indicator
    setupPasswordStrength();
    
    // Add loading state for register button
    setupRegisterButton();
    
    // Add keyboard shortcuts
    setupKeyboardShortcuts();
    
    // Add smooth animations
    addPageAnimations();
}

function setupInputEffects() {
    const inputs = document.querySelectorAll('.input-wrapper input, .input-wrapper select');
    
    inputs.forEach(input => {
        // Add focus effect
        input.addEventListener('focus', function() {
            this.parentElement.style.transform = 'scale(1.02)';
        });
        
        // Remove focus effect
        input.addEventListener('blur', function() {
            this.parentElement.style.transform = 'scale(1)';
        });
        
        // Add typing effect for text inputs
        if (input.type !== 'select-one') {
            input.addEventListener('input', function() {
                if (this.value.length > 0) {
                    this.style.borderColor = '#667eea';
                } else {
                    this.style.borderColor = '#e2e8f0';
                }
            });
        }
    });
}

function setupFormValidation() {
    const form = document.getElementById('registerForm');
    const inputs = form.querySelectorAll('input[required], select[required]');
    
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        if (validateForm()) {
            // Show loading state
            showLoadingState();
            
            // Submit form after validation
            setTimeout(() => {
                form.submit();
            }, 1000);
        }
    });
    
    // Real-time validation
    inputs.forEach(input => {
        input.addEventListener('blur', function() {
            validateField(this);
        });
        
        input.addEventListener('input', function() {
            clearFieldError(this);
        });
    });
}

function validateForm() {
    let isValid = true;
    const inputs = document.querySelectorAll('input[required], select[required]');
    
    inputs.forEach(input => {
        if (!validateField(input)) {
            isValid = false;
        }
    });
    
    // Check password confirmation
    const password = document.getElementById('password');
    const confirmPassword = document.getElementById('confirmPassword');
    
    if (password.value !== confirmPassword.value) {
        showFieldError(confirmPassword, 'Passwords do not match');
        isValid = false;
    }
    
    // Check terms and privacy checkboxes
    const terms = document.getElementById('terms');
    const privacy = document.getElementById('privacy');
    
    if (!terms.checked) {
        showFieldError(terms, 'You must agree to the Terms & Conditions');
        isValid = false;
    }
    
    if (!privacy.checked) {
        showFieldError(privacy, 'You must agree to the Privacy Policy');
        isValid = false;
    }
    
    return isValid;
}

function validateField(input) {
    const value = input.value.trim();
    const fieldName = input.name;
    
    clearFieldError(input);
    
    // Required field validation
    if (value.length === 0) {
        showFieldError(input, `${getFieldLabel(fieldName)} is required`);
        return false;
    }
    
    // Specific field validations
    switch (fieldName) {
        case 'fullName':
            if (value.length < 2) {
                showFieldError(input, 'Full name must be at least 2 characters');
                return false;
            }
            if (!/^[a-zA-Z\s]+$/.test(value)) {
                showFieldError(input, 'Full name can only contain letters and spaces');
                return false;
            }
            break;
            
        case 'username':
            if (value.length < 3) {
                showFieldError(input, 'Username must be at least 3 characters');
                return false;
            }
            if (!/^[a-zA-Z0-9_]+$/.test(value)) {
                showFieldError(input, 'Username can only contain letters, numbers, and underscores');
                return false;
            }
            break;
            
        case 'email':
            if (!isValidEmail(value)) {
                showFieldError(input, 'Please enter a valid email address');
                return false;
            }
            break;
            
        case 'password':
            if (value.length < 6) {
                showFieldError(input, 'Password must be at least 6 characters');
                return false;
            }
            break;
            
        case 'confirmPassword':
            const password = document.getElementById('password');
            if (value !== password.value) {
                showFieldError(input, 'Passwords do not match');
                return false;
            }
            break;
            
        case 'role':
            if (value === '') {
                showFieldError(input, 'Please select a role');
                return false;
            }
            break;
    }
    
    return true;
}

function getFieldLabel(fieldName) {
    const labels = {
        'fullName': 'Full Name',
        'username': 'Username',
        'email': 'Email Address',
        'role': 'Role',
        'password': 'Password',
        'confirmPassword': 'Confirm Password'
    };
    return labels[fieldName] || fieldName;
}

function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

function showFieldError(input, message) {
    input.classList.add('input-error');
    
    const errorDiv = document.createElement('div');
    errorDiv.className = 'field-error-message';
    errorDiv.textContent = message;
    errorDiv.style.cssText = `
        color: #e53e3e;
        font-size: 12px;
        margin-top: 4px;
        margin-left: 4px;
        animation: slideDown 0.3s ease;
    `;
    
    input.parentElement.appendChild(errorDiv);
    
    // Add shake animation
    input.style.animation = 'shake 0.5s ease';
    setTimeout(() => {
        input.style.animation = '';
    }, 500);
}

function clearFieldError(input) {
    input.classList.remove('input-error');
    const errorMessage = input.parentElement.querySelector('.field-error-message');
    if (errorMessage) {
        errorMessage.remove();
    }
}

function setupPasswordToggles() {
    const passwordToggle = document.getElementById('passwordToggle');
    const confirmPasswordToggle = document.getElementById('confirmPasswordToggle');
    const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('confirmPassword');
    
    passwordToggle.addEventListener('click', function() {
        togglePasswordVisibility(passwordInput, this);
    });
    
    confirmPasswordToggle.addEventListener('click', function() {
        togglePasswordVisibility(confirmPasswordInput, this);
    });
}

function togglePasswordVisibility(input, toggleButton) {
    if (input.type === 'password') {
        input.type = 'text';
        toggleButton.querySelector('.toggle-icon').textContent = '🙈';
        toggleButton.setAttribute('title', 'Hide password');
    } else {
        input.type = 'password';
        toggleButton.querySelector('.toggle-icon').textContent = '👁️';
        toggleButton.setAttribute('title', 'Show password');
    }
}

function setupPasswordStrength() {
    const passwordInput = document.getElementById('password');
    const strengthFill = document.getElementById('strengthFill');
    const strengthText = document.getElementById('strengthText');
    
    passwordInput.addEventListener('input', function() {
        const strength = calculatePasswordStrength(this.value);
        updatePasswordStrengthIndicator(strength, strengthFill, strengthText);
    });
}

function calculatePasswordStrength(password) {
    let score = 0;
    
    if (password.length >= 8) score += 1;
    if (/[a-z]/.test(password)) score += 1;
    if (/[A-Z]/.test(password)) score += 1;
    if (/[0-9]/.test(password)) score += 1;
    if (/[^A-Za-z0-9]/.test(password)) score += 1;
    
    if (score <= 2) return 'weak';
    if (score <= 3) return 'medium';
    return 'strong';
}

function updatePasswordStrengthIndicator(strength, strengthFill, strengthText) {
    strengthFill.className = `strength-fill ${strength}`;
    
    const strengthLabels = {
        'weak': 'Weak password',
        'medium': 'Medium strength password',
        'strong': 'Strong password'
    };
    
    strengthText.textContent = strengthLabels[strength];
    strengthText.style.color = strength === 'weak' ? '#e53e3e' : 
                               strength === 'medium' ? '#d69e2e' : '#38a169';
}

function setupRegisterButton() {
    const registerBtn = document.querySelector('.register-btn');
    
    registerBtn.addEventListener('click', function() {
        if (this.classList.contains('loading')) {
            return; // Prevent multiple clicks
        }
        
        this.classList.add('loading');
        this.innerHTML = '<span>Creating Account...</span><i class="btn-icon">⏳</i>';
        
        // Reset button after 3 seconds if form doesn't submit
        setTimeout(() => {
            if (this.classList.contains('loading')) {
                this.classList.remove('loading');
                this.innerHTML = '<span>Create Account</span><i class="btn-icon">→</i>';
            }
        }, 3000);
    });
}

function showLoadingState() {
    const registerBtn = document.querySelector('.register-btn');
    registerBtn.innerHTML = '<span>Creating Account...</span><i class="btn-icon">⏳</i>';
    registerBtn.style.background = 'linear-gradient(135deg, #48bb78, #38a169)';
}

function setupKeyboardShortcuts() {
    // Enter key to submit form
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Enter') {
            const activeElement = document.activeElement;
            if (activeElement.tagName === 'INPUT' || activeElement.tagName === 'SELECT') {
                const form = activeElement.closest('form');
                if (form) {
                    form.dispatchEvent(new Event('submit'));
                }
            }
        }
    });
    
    // Ctrl + Enter to submit form
    document.addEventListener('keydown', function(e) {
        if (e.ctrlKey && e.key === 'Enter') {
            const form = document.getElementById('registerForm');
            if (form) {
                form.dispatchEvent(new Event('submit'));
            }
        }
    });
}

function addPageAnimations() {
    // Add staggered animation to form elements
    const formElements = document.querySelectorAll('.form-group, .form-options, .register-btn, .login-link, .features');
    
    formElements.forEach((element, index) => {
        element.style.opacity = '0';
        element.style.transform = 'translateY(20px)';
        
        setTimeout(() => {
            element.style.transition = 'all 0.6s ease';
            element.style.opacity = '1';
            element.style.transform = 'translateY(0)';
        }, 200 + (index * 100));
    });
}

// Add CSS animations
const style = document.createElement('style');
style.textContent = `
    @keyframes shake {
        0%, 100% { transform: translateX(0); }
        25% { transform: translateX(-5px); }
        75% { transform: translateX(5px); }
    }
    
    @keyframes slideDown {
        from {
            opacity: 0;
            transform: translateY(-10px);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }
    
    .input-error {
        border-color: #e53e3e !important;
        box-shadow: 0 0 0 3px rgba(229, 62, 62, 0.1) !important;
    }
    
    .register-btn.loading {
        pointer-events: none;
        opacity: 0.8;
    }
    
    .form-group, .form-options, .register-btn, .login-link, .features {
        opacity: 0;
        transform: translateY(20px);
    }
    
    .field-error-message {
        color: #e53e3e;
        font-size: 12px;
        margin-top: 4px;
        margin-left: 4px;
        animation: slideDown 0.3s ease;
    }
`;

document.head.appendChild(style);

// Add success message function
function showSuccessMessage(message) {
    const successDiv = document.createElement('div');
    successDiv.className = 'success-message';
    successDiv.textContent = message;
    successDiv.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        background: #48bb78;
        color: white;
        padding: 16px 24px;
        border-radius: 8px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.15);
        z-index: 1000;
        animation: slideInRight 0.5s ease;
    `;
    
    document.body.appendChild(successDiv);
    
    setTimeout(() => {
        successDiv.style.animation = 'slideOutRight 0.5s ease';
        setTimeout(() => {
            successDiv.remove();
        }, 500);
    }, 3000);
}

// Add more CSS animations
const additionalStyle = document.createElement('style');
additionalStyle.textContent = `
    @keyframes slideInRight {
        from {
            transform: translateX(100%);
            opacity: 0;
        }
        to {
            transform: translateX(0);
            opacity: 1;
        }
    }
    
    @keyframes slideOutRight {
        from {
            transform: translateX(0);
            opacity: 1;
        }
        to {
            transform: translateX(100%);
            opacity: 0;
        }
    }
`;

document.head.appendChild(additionalStyle);
