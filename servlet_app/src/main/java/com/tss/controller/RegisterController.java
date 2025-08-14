package com.tss.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        String name = request.getParameter("name");
	        String address = request.getParameter("address");
	        String gender = request.getParameter("gender");
	        String city = request.getParameter("city");
	        String[] languages = request.getParameterValues("languages");
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String confirmPassword = request.getParameter("confirmPassword");

	        out.println("<html><body>");
	        out.println("<h2>Registration Details</h2>");
	        out.println("<p>Name: " + name + "</p>");
	        out.println("<p>Address: " + address + "</p>");
	        out.println("<p>Gender: " + gender + "</p>");
	        out.println("<p>City: " + city + "</p>");
	        out.print("<p>Languages Known: ");
	        if (languages != null) {
	            for (String lang : languages) {
	                out.print(lang + " ");
	            }
	        } else {
	            out.print("None");
	        }
	        out.println("</p>");
	        out.println("<p>Username: " + username + "</p>");

	        if (!password.equals(confirmPassword)) {
	            out.println("<p style='color:red;'>Password and Confirm Password do not match!</p>");
	        } else {
	            out.println("<p>Password Set Successfully.</p>");
	        }

	        out.println("</body></html>");
	    }
}
