// Leave Management System - Login Page JavaScript

document.addEventListener('DOMContentLoaded', function() {
    // Initialize the login page
    initializeLoginPage();
});

function initializeLoginPage() {
    // Add input focus effects
    setupInputEffects();
    
    // Add form validation
    setupFormValidation();
    
    // Add loading state for login button
    setupLoginButton();
    
    // Add keyboard shortcuts
    setupKeyboardShortcuts();
    
    // Add smooth animations
    addPageAnimations();
}

function setupInputEffects() {
    const inputs = document.querySelectorAll('.input-wrapper input');
    
    inputs.forEach(input => {
        // Add focus effect
        input.addEventListener('focus', function() {
            this.parentElement.style.transform = 'scale(1.02)';
        });
        
        // Remove focus effect
        input.addEventListener('blur', function() {
            this.parentElement.style.transform = 'scale(1)';
        });
        
        // Add typing effect
        input.addEventListener('input', function() {
            if (this.value.length > 0) {
                this.style.borderColor = '#667eea';
            } else {
                this.style.borderColor = '#e2e8f0';
            }
        });
    });
}

function setupFormValidation() {
    const form = document.querySelector('.login-form');
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    
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
    usernameInput.addEventListener('blur', function() {
        validateUsername(this);
    });
    
    passwordInput.addEventListener('blur', function() {
        validatePassword(this);
    });
}

function validateForm() {
    let isValid = true;
    
    // Validate username
    if (!validateUsername(document.getElementById('username'))) {
        isValid = false;
    }
    
    // Validate password
    if (!validatePassword(document.getElementById('password'))) {
        isValid = false;
    }
    
    return isValid;
}

function validateUsername(input) {
    const value = input.value.trim();
    const errorClass = 'input-error';
    
    // Remove existing error styling
    input.classList.remove(errorClass);
    removeErrorMessage(input);
    
    if (value.length === 0) {
        showInputError(input, 'Username is required');
        return false;
    }
    
    if (value.length < 3) {
        showInputError(input, 'Username must be at least 3 characters');
        return false;
    }
    
    return true;
}

function validatePassword(input) {
    const value = input.value;
    const errorClass = 'input-error';
    
    // Remove existing error styling
    input.classList.remove(errorClass);
    removeErrorMessage(input);
    
    if (value.length === 0) {
        showInputError(input, 'Password is required');
        return false;
    }
    
    if (value.length < 6) {
        showInputError(input, 'Password must be at least 6 characters');
        return false;
    }
    
    return true;
}

function showInputError(input, message) {
    input.classList.add('input-error');
    
    const errorDiv = document.createElement('div');
    errorDiv.className = 'error-message';
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

function removeErrorMessage(input) {
    const errorMessage = input.parentElement.querySelector('.error-message');
    if (errorMessage) {
        errorMessage.remove();
    }
}

function setupLoginButton() {
    const loginBtn = document.querySelector('.login-btn');
    
    loginBtn.addEventListener('click', function() {
        if (this.classList.contains('loading')) {
            return; // Prevent multiple clicks
        }
        
        this.classList.add('loading');
        this.innerHTML = '<span>Signing In...</span><i class="btn-icon">⏳</i>';
        
        // Reset button after 3 seconds if form doesn't submit
        setTimeout(() => {
            if (this.classList.contains('loading')) {
                this.classList.remove('loading');
                this.innerHTML = '<span>Sign In</span><i class="btn-icon">→</i>';
            }
        }, 3000);
    });
}

function showLoadingState() {
    const loginBtn = document.querySelector('.login-btn');
    loginBtn.innerHTML = '<span>Signing In...</span><i class="btn-icon">⏳</i>';
    loginBtn.style.background = 'linear-gradient(135deg, #48bb78, #38a169)';
}

function setupKeyboardShortcuts() {
    // Enter key to submit form
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Enter') {
            const activeElement = document.activeElement;
            if (activeElement.tagName === 'INPUT') {
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
            const form = document.querySelector('.login-form');
            if (form) {
                form.dispatchEvent(new Event('submit'));
            }
        }
    });
}

function addPageAnimations() {
    // Add staggered animation to form elements
    const formElements = document.querySelectorAll('.form-group, .form-options, .login-btn, .register-link, .features');
    
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
    
    .login-btn.loading {
        pointer-events: none;
        opacity: 0.8;
    }
    
    .form-group, .form-options, .login-btn, .register-link, .features {
        opacity: 0;
        transform: translateY(20px);
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
