# Leave Request Application

A modern, web-based Leave Management System built with Java Servlets, JSP, and JSTL.

## 🚀 Features

- **Modern UI/UX**: Beautiful, responsive design with animations and modern styling
- **User Authentication**: Secure login system with role-based access control
- **Role Management**: Separate interfaces for Employees and Admins
- **Dashboard**: Comprehensive dashboards for both user types
- **Session Management**: Secure session handling and logout functionality
- **Responsive Design**: Mobile-friendly interface with sidebar navigation

## 🛠️ Technology Stack

- **Backend**: Java Servlets, JSP
- **Frontend**: HTML5, CSS3, JavaScript, JSTL (Core & Functions)
- **Database**: MySQL
- **Server**: Apache Tomcat
- **Architecture**: MVC Pattern
- **Icons**: Font Awesome 6.0

## 📁 Project Structure

```
LeaveRequestApp/
├── src/main/
│   ├── java/com/tss/
│   │   ├── controller/     # Servlets
│   │   │   ├── LoginController.java
│   │   │   ├── RegisterController.java
│   │   │   └── LogoutController.java
│   │   ├── service/        # Business Logic
│   │   │   └── UserService.java
│   │   ├── dao/           # Data Access Objects
│   │   │   └── UserDao.java
│   │   ├── model/         # POJOs
│   │   │   └── User.java
│   │   └── db/            # Database Connection
│   │       └── DBConnection.java
│   └── webapp/
│       ├── WEB-INF/       # Configuration
│       │   ├── web.xml
│       │   └── lib/       # JSTL Libraries
│       ├── css/           # Stylesheets
│       │   ├── style.css      # Login & Registration
│       │   └── dashboard.css  # Dashboard Styles
│       ├── js/            # JavaScript
│       │   ├── login.js       # Login Page
│       │   ├── register.js    # Registration Page
│       │   └── dashboard.js   # Dashboard Functionality
│       ├── admin/         # Admin Pages
│       │   └── dashboard.jsp
│       ├── employee/      # Employee Pages
│       │   └── dashboard.jsp
│       ├── login.jsp      # Login Page
│       ├── register.jsp   # Registration Page
│       └── index.jsp      # Entry Point
```

## 🗄️ Database Setup

### 1. Create Database
```sql
CREATE DATABASE leaveapplication;
USE leaveapplication;
```

### 2. Create Tables
```sql
-- Users table (Employees + Admins)
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    role ENUM('EMPLOYEE', 'ADMIN') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Leave Types
CREATE TABLE leave_types (
    leave_type_id INT AUTO_INCREMENT PRIMARY KEY,
    leave_name VARCHAR(50) NOT NULL,
    max_days INT NOT NULL,
    duration_in_days DECIMAL(4,2) NOT NULL DEFAULT 1.00
);

-- Leave Balance (per employee, per leave type)
CREATE TABLE leave_balance (
    balance_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    leave_type_id INT NOT NULL,
    remaining_days INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (leave_type_id) REFERENCES leave_types(leave_type_id) ON DELETE CASCADE
);

-- Leave Requests
CREATE TABLE leave_requests (
    request_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    leave_type_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    reason TEXT,
    status ENUM('PENDING', 'APPROVED', 'REJECTED') DEFAULT 'PENDING',
    admin_id INT NULL,
    decision_date TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (leave_type_id) REFERENCES leave_types(leave_type_id) ON DELETE CASCADE,
    FOREIGN KEY (admin_id) REFERENCES users(user_id) ON DELETE SET NULL
);
```

### 3. Insert Sample Data
```sql
-- Insert leave types
INSERT INTO leave_types (leave_name, max_days, duration_in_days) VALUES 
('Casual Leave', 12, 1.00),
('Sick Leave', 10, 1.00),
('Earned Leave', 15, 1.00),
('Maternity Leave', 90, 1.00),
('Paternity Leave', 15, 1.00),
('Bereavement Leave', 5, 1.00),
('Unpaid Leave', 0, 1.00),
('Half Day Leave', 24, 0.50),
('Partial Leave (2 Hours)', 48, 0.25),
('Other Leave', 5, 1.00);

-- Insert sample admin user
INSERT INTO users (username, password, full_name, email, role) VALUES 
('admin', 'admin123', 'System Administrator', 'admin@company.com', 'ADMIN');

-- Insert sample employee user
INSERT INTO users (username, password, full_name, email, role) VALUES 
('employee', 'employee123', 'John Doe', 'john.doe@company.com', 'EMPLOYEE');
```

## 🚀 Setup Instructions

### Prerequisites
- Java JDK 8 or higher
- Apache Tomcat 9.0 or higher
- MySQL 5.7 or higher
- Maven (optional, for dependency management)

### 1. Database Setup
1. Create the database using the SQL scripts above
2. Update database connection details in `DBConnection.java` if needed

### 2. Deploy to Tomcat
1. Copy the project to Tomcat's `webapps` directory
2. Start Tomcat server
3. Access the application at `http://localhost:8080/LeaveRequestApp`

### 3. Default Login Credentials
- **Admin**: username: `admin`, password: `admin123`
- **Employee**: username: `employee`, password: `employee123`

## ✨ Current Features

### ✅ Completed
- **Login System**: User authentication with role-based redirection
- **Registration**: New user registration with validation
- **Employee Dashboard**: Overview, leave balance, recent requests, quick actions
- **Admin Dashboard**: Employee overview, pending approvals, statistics
- **Session Management**: Secure login/logout with session validation
- **Responsive Design**: Mobile-friendly interface with sidebar navigation
- **Modern UI**: Beautiful design with animations and hover effects

### 🔄 In Progress
- Leave application forms
- Leave approval workflow
- Employee management
- Reports and analytics

### 📋 Planned Features
- Leave balance management
- Email notifications
- Calendar integration
- Advanced reporting
- Bulk operations
- Audit logging

## 🎨 UI/UX Features

- **Modern Design**: Clean, professional interface with gradients and shadows
- **Responsive Layout**: Adapts to all screen sizes
- **Interactive Elements**: Hover effects, animations, and smooth transitions
- **Color-coded Status**: Visual indicators for different leave types and statuses
- **Icon Integration**: Font Awesome icons for better visual communication
- **Sidebar Navigation**: Collapsible navigation for mobile devices

## 🔒 Security Features

- **Session Validation**: All protected pages check for valid sessions
- **Role-based Access**: Different interfaces for employees and admins
- **Input Validation**: Client-side and server-side validation
- **SQL Injection Prevention**: Prepared statements in DAO layer
- **Password Security**: Secure password handling (ready for hashing)

## 🧪 Testing

### Test Scenarios
1. **Login Flow**: Test with valid/invalid credentials
2. **Role-based Access**: Verify employee vs admin access
3. **Session Management**: Test logout and session timeout
4. **Responsive Design**: Test on different screen sizes
5. **Navigation**: Test sidebar and menu functionality

## 🚀 Next Steps

1. **Leave Application**: Create forms for employees to apply for leave
2. **Approval Workflow**: Implement leave approval/rejection system
3. **Leave Balance**: Dynamic leave balance calculation and display
4. **Employee Management**: Admin tools for managing employees
5. **Reports**: Generate leave statistics and reports

## 📱 Browser Support

- Chrome 80+
- Firefox 75+
- Safari 13+
- Edge 80+

## 🤝 Contributing

This is a learning project demonstrating:
- JSP and Servlet development
- JSTL implementation
- Modern web UI/UX design
- Database integration with MySQL
- MVC architecture patterns

## 📄 License

This project is created for educational purposes and demonstrates best practices in Java web development.

---

**Note**: This application is currently in development. Some features may be incomplete or subject to change.
