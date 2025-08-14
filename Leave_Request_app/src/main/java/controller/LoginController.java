package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import service.EmployeeService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService = new EmployeeService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String role = req.getParameter("role");

		if ("admin".equalsIgnoreCase(role)) {
			boolean ok = false;
			try (Connection con = database.DBConnection.getConnection();
					PreparedStatement ps = con
							.prepareStatement("SELECT * FROM admins WHERE username=? AND password=?")) {
				ps.setString(1, username);
				ps.setString(2, password);
				try (ResultSet rs = ps.executeQuery()) {
					ok = rs.next();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (ok) {
				HttpSession s = req.getSession();
				s.setAttribute("admin", username);
				resp.sendRedirect(req.getContextPath() + "/AdminController"); // AdminServlet
				return;
			} else {
				req.setAttribute("error", "Invalid admin credentials");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
				return;
			}
		}
		Employee emp = employeeService.login(username, password);
		if (emp != null) {
			HttpSession s = req.getSession();
			s.setAttribute("employee", emp);
			resp.sendRedirect(req.getContextPath() + "/EmployeeController"); // EmployeeServlet
		} else {
			req.setAttribute("error", "Invalid username/password");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}

}
