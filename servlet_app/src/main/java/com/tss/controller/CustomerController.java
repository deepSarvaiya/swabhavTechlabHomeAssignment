package com.tss.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        String theme = (String) session.getAttribute("theme");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body style='color:" + theme + "'>");
        out.println("<h1>Welcome Customer: " + username + "</h1>");
        out.println("<p>This is the Customer Dashboard.</p>");
        out.println("</body></html>");
    }
}
