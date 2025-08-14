package com.tss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.dao.QuizDao;
import com.tss.model.Question;

/**
 * Servlet implementation class ScoreController
 */
@WebServlet("/ScoreController")
public class ScoreController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private QuizDao quizDao;

    @Override
    public void init() throws ServletException {
        quizDao = new QuizDao();
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		List<Question> questionList = (List<Question>) session.getAttribute("questionList");
		@SuppressWarnings("unchecked")
		Map<Integer, String> userAnswers = (Map<Integer, String>) session.getAttribute("userAnswers");
		String username = (String) session.getAttribute("username");

		int score = 0;
		int total = questionList != null ? questionList.size() : 0;

		if (questionList != null && userAnswers != null) {
			for (Question q : questionList) {
				String correct = q.getCorrectOption();
				String userAnswer = userAnswers.get(q.getId());
				if (correct != null && correct.equalsIgnoreCase(userAnswer)) {
					score++;
				}
			}
		}

		int userId = (int) session.getAttribute("userId");
		boolean saved = quizDao.saveResult(userId, score, 5);

		// Enhanced score display with modern styling and detailed review
		out.println("<!DOCTYPE html>");
		out.println("<html><head>");
		out.println("<title>Quiz Result</title>");
		out.println("<meta charset='UTF-8'>");
		out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		out.println("<link rel='stylesheet' href='style.css'>");
		out.println("<style>");
		out.println(".review-section { margin-top: 40px; }");
		out.println(".question-review {");
		out.println("    background: #cad9ff;");
		out.println("    border-radius: 16px;");
		out.println("    padding: 24px;");
		out.println("    margin-bottom: 20px;");
		out.println("    border-left: 4px solid #667eea;");
		out.println("    position: relative;");
		out.println("}");
		out.println(".question-review.correct { border-left-color: #38a169; }");
		out.println(".question-review.incorrect { border-left-color: #e53e3e; }");
		out.println(".question-review h4 {");
		out.println("    color: #2d3748;");
		out.println("    margin-bottom: 12px;");
		out.println("    font-weight: 600;");
		out.println("}");
		out.println(".answer-status {");
		out.println("    display: inline-block;");
		out.println("    padding: 4px 12px;");
		out.println("    border-radius: 20px;");
		out.println("    font-size: 12px;");
		out.println("    font-weight: 600;");
		out.println("    text-transform: uppercase;");
		out.println("    letter-spacing: 0.5px;");
		out.println("    position: absolute;");
		out.println("    top: 20px;");
		out.println("    right: 20px;");
		out.println("}");
		out.println(".answer-status.correct {");
		out.println("    background: #c6f6d5;");
		out.println("    color: #22543d;");
		out.println("}");
		out.println(".answer-status.incorrect {");
		out.println("    background: #fed7d7;");
		out.println("    color: #742a2a;");
		out.println("}");
		out.println(".answer-details {");
		out.println("    margin-top: 16px;");
		out.println("    padding: 16px;");
		out.println("    background: white;");
		out.println("    border-radius: 8px;");
		out.println("    border: 1px solid #e2e8f0;");
		out.println("}");
		out.println(".answer-details p {");
		out.println("    margin: 8px 0;");
		out.println("    color: #4a5568;");
		out.println("}");
		out.println(".correct-answer {");
		out.println("    color: #38a169;");
		out.println("    font-weight: 600;");
		out.println("}");
		out.println(".user-answer {");
		out.println("    color: #e53e3e;");
		out.println("    font-weight: 600;");
		out.println("}");
		out.println(".options-list {");
		out.println("    margin-top: 12px;");
		out.println("}");
		out.println(".option-item {");
		out.println("    padding: 8px 12px;");
		out.println("    margin: 4px 0;");
		out.println("    border-radius: 6px;");
		out.println("    background: #f7fafc;");
		out.println("    border: 1px solid #e2e8f0;");
		out.println("}");
		out.println(".option-item.correct {");
		out.println("    background: #c6f6d5;");
		out.println("    border-color: #38a169;");
		out.println("    color: #22543d;");
		out.println("}");
		out.println(".option-item.incorrect {");
		out.println("    background: #fed7d7;");
		out.println("    border-color: #e53e3e;");
		out.println("    color: #742a2a;");
		out.println("}");
		out.println("</style>");
		out.println("</head><body>");
		
		out.println("<div class='score-container'>");
		out.println("<h2>Quiz Completed!</h2>");
		out.println("<div class='score-display'>" + score + " / " + total + "</div>");
		out.println("<p style='font-size: 18px; color: #4a5568; margin: 20px 0;'>");
		out.println("Congratulations! You've completed the quiz.");
		out.println("</p>");
		
		if (saved) {
			out.println("<p style='color: #38a169; font-weight: 500;'> Result saved successfully!</p>");
		} else {
			out.println("<p style='color: #e53e3e; font-weight: 500;'> Failed to save result.</p>");
		}
		
		// Detailed Review Section
		if (questionList != null && userAnswers != null) {
			out.println("<div class='review-section'>");
			out.println("<h3 style='text-align: center; color: #2d3748; margin-bottom: 24px;'>Question Review</h3>");
			
			for (int i = 0; i < questionList.size(); i++) {
				Question q = questionList.get(i);
				String correctAnswer = q.getCorrectOption();
				String userAnswer = userAnswers.get(q.getId());
				boolean isCorrect = correctAnswer != null && correctAnswer.equalsIgnoreCase(userAnswer);
				
				out.println("<div class='question-review " + (isCorrect ? "correct" : "incorrect") + "'>");
				out.println("<span class='answer-status " + (isCorrect ? "correct" : "incorrect") + "'>");
				out.println(isCorrect ? " Correct" : " Incorrect");
				out.println("</span>");
				
				out.println("<h4>Question " + (i + 1) + "</h4>");
				out.println("<p>" + q.getQuestionText() + "</p>");
				
				out.println("<div class='answer-details'>");
				out.println("<p><strong>Your Answer:</strong> <span class='user-answer'>" + 
					(userAnswer != null ? getOptionText(q, userAnswer) : "Not answered") + "</span></p>");
				out.println("<p><strong>Correct Answer:</strong> <span class='correct-answer'>" + 
					getOptionText(q, correctAnswer) + "</span></p>");
				
				out.println("<div class='options-list'>");
				out.println("<p><strong>All Options:</strong></p>");
				out.println("<div class='option-item " + ("A".equals(correctAnswer) ? "correct" : "") + "'>A. " + q.getOptionA() + "</div>");
				out.println("<div class='option-item " + ("B".equals(correctAnswer) ? "correct" : "") + "'>B. " + q.getOptionB() + "</div>");
				out.println("<div class='option-item " + ("C".equals(correctAnswer) ? "correct" : "") + "'>C. " + q.getOptionC() + "</div>");
				out.println("<div class='option-item " + ("D".equals(correctAnswer) ? "correct" : "") + "'>D. " + q.getOptionD() + "</div>");
				out.println("</div>");
				out.println("</div>");
				out.println("</div>");
			}
			out.println("</div>");
		}
		
		out.println("<form action='LogoutController' method='post' style='margin-top: 32px;'>");
		out.println("<input type='submit' value='Logout'/>");
		out.println("</form>");
		out.println("</div>");
		out.println("</body></html>");

		// Clear session after showing score
		session.invalidate();
	}
	
	// Helper method to get option text based on option letter
	private String getOptionText(Question q, String option) {
		if (option == null) return "Not answered";
		switch (option.toUpperCase()) {
			case "A": return q.getOptionA();
			case "B": return q.getOptionB();
			case "C": return q.getOptionC();
			case "D": return q.getOptionD();
			default: return "Invalid option";
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	


}
