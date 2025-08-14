package com.tss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.model.Question;
import com.tss.service.QuizService;

/**
 * Servlet implementation class QuizController
 */
@WebServlet("/QuizController")
public class QuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuizController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		QuizService quizService = new QuizService();
		List<Question> questions = quizService.getRandomQuestions(5);

		HttpSession session = request.getSession();
		session.setAttribute("questionList", questions);
		session.setAttribute("currentIndex", 0);
		session.setAttribute("userAnswers", new HashMap<Integer, String>());

		showQuestion(response, questions.get(0), 1);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		List<Question> questions = (List<Question>) session.getAttribute("questionList");
		Map<Integer, String> userAnswers = (Map<Integer, String>) session.getAttribute("userAnswers");

		int currentIndex = (int) session.getAttribute("currentIndex");

		String selectedAnswer = request.getParameter("answer");
		Question currentQuestion = questions.get(currentIndex);
		userAnswers.put(currentQuestion.getId(), selectedAnswer);

		currentIndex++;
		session.setAttribute("currentIndex", currentIndex);

		if (currentIndex < questions.size()) {
			Question nextQuestion = questions.get(currentIndex);
			showQuestion(response, nextQuestion, currentIndex + 1);
		} else {
			response.sendRedirect("ScoreController"); // all questions done
		}
	}

	private void showQuestion(HttpServletResponse response, Question q, int questionNumber) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html><head>");
		out.println("<title>Quiz - Question " + questionNumber + "</title>");
		out.println("<meta charset='UTF-8'>");
		out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		out.println("<link rel='stylesheet' href='style.css'>");
		out.println("</head><body>");
		
		out.println("<div class='quiz-container'>");
		out.println("<div class='question'>");
		out.println("<h3>Question " + questionNumber + "</h3>");
		out.println("<p>" + q.getQuestionText() + "</p>");
		out.println("</div>");
		
		out.println("<form method='post' action='QuizController'>");
		out.println("<div class='options'>");
		out.println("<div class='option'>");
		out.println("<input type='radio' name='answer' value='A' id='optionA' required>");
		out.println("<label for='optionA'>" + q.getOptionA() + "</label>");
		out.println("</div>");
		out.println("<div class='option'>");
		out.println("<input type='radio' name='answer' value='B' id='optionB'>");
		out.println("<label for='optionB'>" + q.getOptionB() + "</label>");
		out.println("</div>");
		out.println("<div class='option'>");
		out.println("<input type='radio' name='answer' value='C' id='optionC'>");
		out.println("<label for='optionC'>" + q.getOptionC() + "</label>");
		out.println("</div>");
		out.println("<div class='option'>");
		out.println("<input type='radio' name='answer' value='D' id='optionD'>");
		out.println("<label for='optionD'>" + q.getOptionD() + "</label>");
		out.println("</div>");
		out.println("</div>");
		out.println("<input type='submit' value='Next Question'>");
		out.println("</form>");
		out.println("</div>");
		out.println("</body></html>");
	}

}
