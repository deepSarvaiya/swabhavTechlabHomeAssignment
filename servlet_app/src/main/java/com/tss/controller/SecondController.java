package com.tss.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SecondController
 */
@WebServlet("/SecondController")
public class SecondController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecondController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 response.setContentType("text/html");
	        PrintWriter writer = response.getWriter();

	        // Retrieve first name from cookie
	        String firstName = "";
//	        Cookie[] cookies = request.getCookies();
//	        if (cookies != null) {
//	            for (Cookie c : cookies) {
//	                if ("firstName".equals(c.getName())) {
//	                    firstName = c.getValue();
//	                    break;
//	                }
//	            }
//	        }
	        
	        HttpSession session = request.getSession(false); // false means don't create new if none exists
	        firstName = (session != null) ? (String) session.getAttribute("firstName") : "Unknown";


	        // Retrieve last name from form
	        String lastName = request.getParameter("lastNameTxt");

	        writer.print("<html><body>");
	        writer.print("<h2>First Name: " + firstName + "</h2>");
	        writer.print("<h2>Last Name: " + lastName + "</h2>");
	        writer.print("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
