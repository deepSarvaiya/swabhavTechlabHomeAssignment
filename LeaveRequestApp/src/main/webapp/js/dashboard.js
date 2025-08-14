// Dashboard JavaScript - Leave Management System

document.addEventListener('DOMContentLoaded', function() {
    // Initialize the dashboard
    initializeDashboard();
});

function initializeDashboard() {
    // Setup sidebar toggle
    setupSidebarToggle();
    
    // Setup interactive elements
    setupInteractiveElements();
    
    // Setup notifications
    setupNotifications();
    
    // Setup user menu
    setupUserMenu();
    
    // Add page animations
    addPageAnimations();
    
    // Setup table interactions
    setupTableInteractions();
}

function setupSidebarToggle() {
    const sidebarToggle = document.getElementById('sidebarToggle');
    const sidebar = document.querySelector('.sidebar');
    const mainContent = document.querySelector('.main-content');
    
    if (sidebarToggle && sidebar) {
        sidebarToggle.addEventListener('click', function() {
            sidebar.classList.toggle('active');
            
            // Update main content margin on mobile
            if (window.innerWidth <= 1024) {
                if (sidebar.classList.contains('active')) {
                    mainContent.style.marginLeft = '0';
                } else {
                    mainContent.style.marginLeft = '0';
                }
            }
        });
        
        // Close sidebar when clicking outside on mobile
        document.addEventListener('click', function(e) {
            if (window.innerWidth <= 1024 && 
                !sidebar.contains(e.target) && 
                !sidebarToggle.contains(e.target) &&
                sidebar.classList.contains('active')) {
                sidebar.classList.remove('active');
            }
        });
    }
}

function setupInteractiveElements() {
    // Setup stat cards hover effects
    const statCards = document.querySelectorAll('.stat-card');
    statCards.forEach(card => {
        card.addEventListener('mouseenter', function() {
            this.style.transform = 'translateY(-8px)';
        });
        
        card.addEventListener('mouseleave', function() {
            this.style.transform = 'translateY(0)';
        });
    });
    
    // Setup leave type cards
    const leaveTypeCards = document.querySelectorAll('.leave-type-card');
    leaveTypeCards.forEach(card => {
        card.addEventListener('mouseenter', function() {
            this.style.transform = 'translateY(-4px)';
        });
        
        card.addEventListener('mouseleave', function() {
            this.style.transform = 'translateY(0)';
        });
    });
    
    // Setup quick action cards
    const quickActionCards = document.querySelectorAll('.quick-action-card');
    quickActionCards.forEach(card => {
        card.addEventListener('mouseenter', function() {
            this.style.transform = 'translateY(-6px)';
        });
        
        card.addEventListener('mouseleave', function() {
            this.style.transform = 'translateY(0)';
        });
    });
}

function setupNotifications() {
    const notifications = document.querySelector('.notifications');
    if (notifications) {
        notifications.addEventListener('click', function() {
            showNotificationPanel();
        });
    }
}

function showNotificationPanel() {
    // Create notification panel
    const notificationPanel = document.createElement('div');
    notificationPanel.className = 'notification-panel';
    notificationPanel.innerHTML = `
        <div class="notification-header">
            <h3>Notifications</h3>
            <button class="close-notifications" onclick="this.parentElement.parentElement.remove()">
                <i class="fas fa-times"></i>
            </button>
        </div>
        <div class="notification-list">
            <div class="notification-item">
                <div class="notification-icon">
                    <i class="fas fa-check-circle"></i>
                </div>
                <div class="notification-content">
                    <p>Your leave request for Dec 15-16 has been approved!</p>
                    <span class="notification-time">2 hours ago</span>
                </div>
            </div>
            <div class="notification-item">
                <div class="notification-icon">
                    <i class="fas fa-clock"></i>
                </div>
                <div class="notification-content">
                    <p>New leave request from John Doe is pending approval</p>
                    <span class="notification-time">1 day ago</span>
                </div>
            </div>
            <div class="notification-item">
                <div class="notification-icon">
                    <i class="fas fa-info-circle"></i>
                </div>
                <div class="notification-content">
                    <p>Your leave balance has been updated</p>
                    <span class="notification-time">3 days ago</span>
                </div>
            </div>
        </div>
    `;
    
    // Add styles
    notificationPanel.style.cssText = `
        position: fixed;
        top: 80px;
        right: 20px;
        width: 350px;
        background: white;
        border-radius: 12px;
        box-shadow: 0 10px 40px rgba(0, 0, 0, 0.15);
        z-index: 1000;
        border: 1px solid #e2e8f0;
        animation: slideInRight 0.3s ease;
    `;
    
    document.body.appendChild(notificationPanel);
    
    // Add notification panel styles
    addNotificationStyles();
}

function addNotificationStyles() {
    const style = document.createElement('style');
    style.textContent = `
        .notification-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            border-bottom: 1px solid #e2e8f0;
        }
        
        .notification-header h3 {
            font-size: 18px;
            font-weight: 600;
            color: #2d3748;
        }
        
        .close-notifications {
            background: none;
            border: none;
            font-size: 18px;
            color: #a0aec0;
            cursor: pointer;
            padding: 4px;
            border-radius: 4px;
            transition: all 0.3s ease;
        }
        
        .close-notifications:hover {
            background: #f7fafc;
            color: #4a5568;
        }
        
        .notification-list {
            max-height: 400px;
            overflow-y: auto;
        }
        
        .notification-item {
            display: flex;
            align-items: flex-start;
            gap: 16px;
            padding: 16px 20px;
            border-bottom: 1px solid #f7fafc;
            transition: background 0.3s ease;
        }
        
        .notification-item:hover {
            background: #f8fafc;
        }
        
        .notification-item:last-child {
            border-bottom: none;
        }
        
        .notification-icon {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 16px;
            flex-shrink: 0;
        }
        
        .notification-item:nth-child(1) .notification-icon {
            background: #e6fffa;
            color: #22543d;
        }
        
        .notification-item:nth-child(2) .notification-icon {
            background: #fef5e7;
            color: #744210;
        }
        
        .notification-item:nth-child(3) .notification-icon {
            background: #e6fffa;
            color: #22543d;
        }
        
        .notification-content p {
            font-size: 14px;
            color: #2d3748;
            margin-bottom: 4px;
            line-height: 1.4;
        }
        
        .notification-time {
            font-size: 12px;
            color: #718096;
        }
        
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
    `;
    
    document.head.appendChild(style);
}

function setupUserMenu() {
    const userMenu = document.querySelector('.user-menu');
    if (userMenu) {
        userMenu.addEventListener('click', function() {
            showUserDropdown();
        });
    }
}

function showUserDropdown() {
    // Remove existing dropdown
    const existingDropdown = document.querySelector('.user-dropdown');
    if (existingDropdown) {
        existingDropdown.remove();
        return;
    }
    
    // Create user dropdown
    const userDropdown = document.createElement('div');
    userDropdown.className = 'user-dropdown';
    userDropdown.innerHTML = `
        <div class="dropdown-item">
            <i class="fas fa-user"></i>
            <span>My Profile</span>
        </div>
        <div class="dropdown-item">
            <i class="fas fa-cog"></i>
            <span>Settings</span>
        </div>
        <div class="dropdown-item">
            <i class="fas fa-question-circle"></i>
            <span>Help</span>
        </div>
        <div class="dropdown-divider"></div>
        <div class="dropdown-item">
            <i class="fas fa-sign-out-alt"></i>
            <span>Logout</span>
        </div>
    `;
    
    // Position the dropdown
    const userMenu = document.querySelector('.user-menu');
    const rect = userMenu.getBoundingClientRect();
    
    userDropdown.style.cssText = `
        position: fixed;
        top: ${rect.bottom + 8}px;
        right: ${rect.right - 200}px;
        width: 200px;
        background: white;
        border-radius: 12px;
        box-shadow: 0 10px 40px rgba(0, 0, 0, 0.15);
        z-index: 1000;
        border: 1px solid #e2e8f0;
        animation: slideInDown 0.3s ease;
    `;
    
    document.body.appendChild(userDropdown);
    
    // Add dropdown styles
    addDropdownStyles();
    
    // Close dropdown when clicking outside
    document.addEventListener('click', function(e) {
        if (!userMenu.contains(e.target) && !userDropdown.contains(e.target)) {
            userDropdown.remove();
        }
    });
}

function addDropdownStyles() {
    const style = document.createElement('style');
    style.textContent = `
        .dropdown-item {
            display: flex;
            align-items: center;
            gap: 12px;
            padding: 12px 16px;
            cursor: pointer;
            transition: background 0.3s ease;
            font-size: 14px;
            color: #2d3748;
        }
        
        .dropdown-item:hover {
            background: #f8fafc;
        }
        
        .dropdown-item i {
            width: 16px;
            color: #718096;
        }
        
        .dropdown-divider {
            height: 1px;
            background: #e2e8f0;
            margin: 8px 0;
        }
        
        @keyframes slideInDown {
            from {
                transform: translateY(-10px);
                opacity: 0;
            }
            to {
                transform: translateY(0);
                opacity: 1;
            }
        }
    `;
    
    document.head.appendChild(style);
}

function setupTableInteractions() {
    // Setup table row hover effects
    const tableRows = document.querySelectorAll('.leave-requests-table tbody tr');
    tableRows.forEach(row => {
        row.addEventListener('mouseenter', function() {
            this.style.backgroundColor = '#f8fafc';
        });
        
        row.addEventListener('mouseleave', function() {
            this.style.backgroundColor = '';
        });
    });
    
    // Setup action buttons
    const actionButtons = document.querySelectorAll('.btn-edit, .btn-cancel, .btn-view');
    actionButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            handleActionButton(this);
        });
    });
}

function handleActionButton(button) {
    const action = button.classList.contains('btn-edit') ? 'edit' :
                   button.classList.contains('btn-cancel') ? 'cancel' : 'view';
    
    const row = button.closest('tr');
    const leaveType = row.querySelector('.leave-type-badge').textContent.trim();
    const status = row.querySelector('.status-badge').textContent.trim();
    
    switch (action) {
        case 'edit':
            if (status === 'Pending') {
                showMessage('Edit functionality will be implemented soon!', 'info');
            } else {
                showMessage('Cannot edit non-pending leave requests', 'warning');
            }
            break;
            
        case 'cancel':
            if (status === 'Pending') {
                if (confirm('Are you sure you want to cancel this leave request?')) {
                    showMessage('Leave request cancelled successfully!', 'success');
                    row.remove();
                }
            } else {
                showMessage('Cannot cancel non-pending leave requests', 'warning');
            }
            break;
            
        case 'view':
            showMessage('View functionality will be implemented soon!', 'info');
            break;
    }
}

function showMessage(message, type = 'info') {
    const messageDiv = document.createElement('div');
    messageDiv.className = `message-toast ${type}`;
    messageDiv.textContent = message;
    
    messageDiv.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        padding: 16px 24px;
        border-radius: 8px;
        color: white;
        font-weight: 500;
        z-index: 10000;
        animation: slideInRight 0.3s ease;
        max-width: 300px;
    `;
    
    // Set background color based on type
    switch (type) {
        case 'success':
            messageDiv.style.background = '#48bb78';
            break;
        case 'warning':
            messageDiv.style.background = '#ed8936';
            break;
        case 'error':
            messageDiv.style.background = '#e53e3e';
            break;
        default:
            messageDiv.style.background = '#667eea';
    }
    
    document.body.appendChild(messageDiv);
    
    // Remove message after 3 seconds
    setTimeout(() => {
        messageDiv.style.animation = 'slideOutRight 0.3s ease';
        setTimeout(() => {
            messageDiv.remove();
        }, 300);
    }, 3000);
}

function addPageAnimations() {
    // Add staggered animation to dashboard elements
    const elements = document.querySelectorAll('.stat-card, .dashboard-section, .quick-action-card');
    
    elements.forEach((element, index) => {
        element.style.opacity = '0';
        element.style.transform = 'translateY(20px)';
        
        setTimeout(() => {
            element.style.transition = 'all 0.6s ease';
            element.style.opacity = '1';
            element.style.transform = 'translateY(0)';
        }, 100 + (index * 100));
    });
}

// Add slideOutRight animation
const slideOutStyle = document.createElement('style');
slideOutStyle.textContent = `
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
document.head.appendChild(slideOutStyle);

// Handle window resize
window.addEventListener('resize', function() {
    const sidebar = document.querySelector('.sidebar');
    const mainContent = document.querySelector('.main-content');
    
    if (window.innerWidth > 1024) {
        sidebar.classList.remove('active');
        mainContent.style.marginLeft = '280px';
    } else {
        mainContent.style.marginLeft = '0';
    }
});

// Add keyboard shortcuts
document.addEventListener('keydown', function(e) {
    // Escape key to close dropdowns and panels
    if (e.key === 'Escape') {
        const notificationPanel = document.querySelector('.notification-panel');
        const userDropdown = document.querySelector('.user-dropdown');
        
        if (notificationPanel) notificationPanel.remove();
        if (userDropdown) userDropdown.remove();
    }
    
    // Ctrl + M to toggle sidebar on mobile
    if (e.ctrlKey && e.key === 'm') {
        const sidebarToggle = document.getElementById('sidebarToggle');
        if (sidebarToggle && window.innerWidth <= 1024) {
            sidebarToggle.click();
        }
    }
});
