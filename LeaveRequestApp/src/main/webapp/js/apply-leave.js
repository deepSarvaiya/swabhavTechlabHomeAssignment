document.addEventListener('DOMContentLoaded', function() {
    // Form elements
    const form = document.querySelector('.apply-leave-form');
    const startDateInput = document.getElementById('startDate');
    const endDateInput = document.getElementById('endDate');
    const leaveTypeSelect = document.getElementById('leaveTypeId');
    const reasonTextarea = document.getElementById('reason');
    
    // Set minimum date to today
    const today = new Date().toISOString().split('T')[0];
    startDateInput.min = today;
    endDateInput.min = today;
    
    // Date validation
    startDateInput.addEventListener('change', function() {
        endDateInput.min = this.value;
        if (endDateInput.value && endDateInput.value < this.value) {
            endDateInput.value = this.value;
        }
        calculateLeaveDays();
    });
    
    endDateInput.addEventListener('change', function() {
        if (startDateInput.value && this.value < startDateInput.value) {
            this.value = startDateInput.value;
        }
        calculateLeaveDays();
    });
    
    // Calculate leave days
    function calculateLeaveDays() {
        if (startDateInput.value && endDateInput.value) {
            const startDate = new Date(startDateInput.value);
            const endDate = new Date(endDateInput.value);
            const timeDiff = endDate.getTime() - startDate.getTime();
            const daysDiff = Math.ceil(timeDiff / (1000 * 3600 * 24)) + 1;
            
            // Update leave type info if available
            if (leaveTypeSelect.value) {
                const selectedOption = leaveTypeSelect.options[leaveTypeSelect.selectedIndex];
                const maxDaysMatch = selectedOption.text.match(/Max: (\d+) days/);
                if (maxDaysMatch) {
                    const maxDays = parseInt(maxDaysMatch[1]);
                    if (daysDiff > maxDays) {
                        showWarning(`Warning: You're requesting ${daysDiff} days, but the maximum for this leave type is ${maxDays} days.`);
                    } else {
                        hideWarning();
                    }
                }
            }
        }
    }
    
    // Show warning message
    function showWarning(message) {
        let warningDiv = document.querySelector('.warning-message');
        if (!warningDiv) {
            warningDiv = document.createElement('div');
            warningDiv.className = 'warning-message';
            warningDiv.innerHTML = `<i class="warning-icon">⚠️</i><span>${message}</span>`;
            form.insertBefore(warningDiv, form.querySelector('.form-actions'));
        } else {
            warningDiv.innerHTML = `<i class="warning-icon">⚠️</i><span>${message}</span>`;
        }
        warningDiv.style.display = 'block';
    }
    
    // Hide warning message
    function hideWarning() {
        const warningDiv = document.querySelector('.warning-message');
        if (warningDiv) {
            warningDiv.style.display = 'none';
        }
    }
    
    // Form validation
    form.addEventListener('submit', function(e) {
        if (!validateForm()) {
            e.preventDefault();
        }
    });
    
    function validateForm() {
        let isValid = true;
        
        // Clear previous error styles
        clearErrorStyles();
        
        // Validate leave type
        if (!leaveTypeSelect.value) {
            showFieldError(leaveTypeSelect, 'Please select a leave type');
            isValid = false;
        }
        
        // Validate start date
        if (!startDateInput.value) {
            showFieldError(startDateInput, 'Please select a start date');
            isValid = false;
        } else if (new Date(startDateInput.value) < new Date(today)) {
            showFieldError(startDateInput, 'Start date cannot be in the past');
            isValid = false;
        }
        
        // Validate end date
        if (!endDateInput.value) {
            showFieldError(endDateInput, 'Please select an end date');
            isValid = false;
        } else if (new Date(endDateInput.value) < new Date(startDateInput.value)) {
            showFieldError(endDateInput, 'End date cannot be before start date');
            isValid = false;
        }
        
        // Validate reason
        if (!reasonTextarea.value.trim()) {
            showFieldError(reasonTextarea, 'Please provide a reason for leave');
            isValid = false;
        } else if (reasonTextarea.value.trim().length < 10) {
            showFieldError(reasonTextarea, 'Reason must be at least 10 characters long');
            isValid = false;
        }
        
        return isValid;
    }
    
    // Show field error
    function showFieldError(field, message) {
        field.classList.add('error');
        const errorDiv = document.createElement('div');
        errorDiv.className = 'field-error';
        errorDiv.textContent = message;
        field.parentNode.appendChild(errorDiv);
    }
    
    // Clear error styles
    function clearErrorStyles() {
        document.querySelectorAll('.error').forEach(field => {
            field.classList.remove('error');
        });
        document.querySelectorAll('.field-error').forEach(error => {
            error.remove();
        });
    }
    
    // Real-time validation
    leaveTypeSelect.addEventListener('change', function() {
        if (this.value) {
            this.classList.remove('error');
            const errorDiv = this.parentNode.querySelector('.field-error');
            if (errorDiv) errorDiv.remove();
        }
        calculateLeaveDays();
    });
    
    startDateInput.addEventListener('input', function() {
        this.classList.remove('error');
        const errorDiv = this.parentNode.querySelector('.field-error');
        if (errorDiv) errorDiv.remove();
    });
    
    endDateInput.addEventListener('input', function() {
        this.classList.remove('error');
        const errorDiv = this.parentNode.querySelector('.field-error');
        if (errorDiv) errorDiv.remove();
    });
    
    reasonTextarea.addEventListener('input', function() {
        this.classList.remove('error');
        const errorDiv = this.parentNode.querySelector('.field-error');
        if (errorDiv) errorDiv.remove();
    });
    
    // Character counter for reason
    reasonTextarea.addEventListener('input', function() {
        const charCount = this.value.length;
        const minChars = 10;
        const remaining = minChars - charCount;
        
        let counter = this.parentNode.querySelector('.char-counter');
        if (!counter) {
            counter = document.createElement('div');
            counter.className = 'char-counter';
            this.parentNode.appendChild(counter);
        }
        
        if (remaining > 0) {
            counter.textContent = `${remaining} more characters required`;
            counter.className = 'char-counter warning';
        } else {
            counter.textContent = 'Minimum character requirement met';
            counter.className = 'char-counter success';
        }
    });
    
    // Form submission feedback
    form.addEventListener('submit', function() {
        const submitBtn = this.querySelector('button[type="submit"]');
        const originalText = submitBtn.innerHTML;
        
        submitBtn.innerHTML = '<i class="loading-spinner">⏳</i><span>Submitting...</span>';
        submitBtn.disabled = true;
        
        // Re-enable button after 5 seconds (fallback)
        setTimeout(() => {
            submitBtn.innerHTML = originalText;
            submitBtn.disabled = false;
        }, 5000);
    });
    
    // Keyboard shortcuts
    document.addEventListener('keydown', function(e) {
        if (e.ctrlKey || e.metaKey) {
            switch(e.key) {
                case 'Enter':
                    e.preventDefault();
                    form.submit();
                    break;
                case 's':
                    e.preventDefault();
                    form.submit();
                    break;
            }
        }
    });
    
    // Auto-save draft (localStorage)
    const formData = {
        leaveTypeId: '',
        startDate: '',
        endDate: '',
        reason: ''
    };
    
    // Save form data
    function saveFormData() {
        formData.leaveTypeId = leaveTypeSelect.value;
        formData.startDate = startDateInput.value;
        formData.endDate = endDateInput.value;
        formData.reason = reasonTextarea.value;
        localStorage.setItem('leaveRequestDraft', JSON.stringify(formData));
    }
    
    // Load form data
    function loadFormData() {
        const saved = localStorage.getItem('leaveRequestDraft');
        if (saved) {
            const data = JSON.parse(saved);
            if (data.leaveTypeId) leaveTypeSelect.value = data.leaveTypeId;
            if (data.startDate) startDateInput.value = data.startDate;
            if (data.endDate) endDateInput.value = data.endDate;
            if (data.reason) reasonTextarea.value = data.reason;
            
            // Trigger change events
            leaveTypeSelect.dispatchEvent(new Event('change'));
            startDateInput.dispatchEvent(new Event('change'));
            endDateInput.dispatchEvent(new Event('change'));
            reasonTextarea.dispatchEvent(new Event('input'));
        }
    }
    
    // Auto-save every 5 seconds
    setInterval(saveFormData, 5000);
    
    // Load saved data on page load
    loadFormData();
    
    // Clear draft on successful submission
    form.addEventListener('submit', function() {
        localStorage.removeItem('leaveRequestDraft');
    });
});
