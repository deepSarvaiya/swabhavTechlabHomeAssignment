package com.tss.controller;

import com.tss.dao.UserDao;
import com.tss.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String selectedUserType = request.getParameter("userType");
        String theme = request.getParameter("theme");

        UserDao dao = new UserDao();
        User user = dao.login(username, password);

        if (user != null && user.getUserType().equalsIgnoreCase(selectedUserType)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("theme", theme);
            session.setAttribute("userType", user.getUserType());

            if (user.getUserType().equalsIgnoreCase("Admin")) {
                response.sendRedirect("AdminController");
            } else {
                response.sendRedirect("CustomerController");
            }
        } else {
            response.getWriter().println("Invalid login credentials or user type mismatch.");
        }
    }
}
