package com.tss.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.exception.DBException;
import com.tss.model.Feedback;
import com.tss.service.feedbackService;

/**
 * Servlet implementation class FeedbackServlet
 */
@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            Feedback feedback = new Feedback();
            feedback.setName(request.getParameter("name"));
            feedback.setSessionDate(LocalDate.parse(request.getParameter("sessionDate")));
            feedback.setSessionContent(Integer.parseInt(request.getParameter("sessionContent")));
            feedback.setQueryResolution(Integer.parseInt(request.getParameter("queryResolution")));
            feedback.setInteractivity(Integer.parseInt(request.getParameter("interactivity")));
            feedback.setImpactfulLearning(Integer.parseInt(request.getParameter("impactfulLearning")));
            feedback.setContentDelivery(Integer.parseInt(request.getParameter("contentDelivery")));

            feedbackService feedbackservice = new feedbackService();
            boolean saved = feedbackservice.saveFeedback(feedback);

            if (saved) {
                request.setAttribute("name", feedback.getName());
                request.setAttribute("date", feedback.getSessionDate());
                request.getRequestDispatcher("feedback_success.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("feedback_error.jsp").forward(request, response);
            }
        } catch (DBException e) {
            e.printStackTrace();
            request.getRequestDispatcher("feedback_error.jsp").forward(request, response);
        }	}

}
