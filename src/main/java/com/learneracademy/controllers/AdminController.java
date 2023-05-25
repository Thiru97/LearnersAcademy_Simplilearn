package com.learneracademy.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.learneracademy.dao.ClassDAO;
import com.learneracademy.dao.StudentDAO;
import com.learneracademy.dao.SubjectDAO;
import com.learneracademy.dao.TeacherDAO;
import com.learneracademy.entity.MyClass;
import com.learneracademy.entity.Student;
import com.learneracademy.entity.Teacher;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClassDAO classDAO = null;
	SubjectDAO subjectDAO = null;
	TeacherDAO teacherDAO = null;
	StudentDAO studentDAO = null;

	private static final String USERNAME = "admin@gmail.com";
	String defaultPassword = "admin";

	public AdminController() {
		super();
		classDAO = new ClassDAO();
		subjectDAO = new SubjectDAO();
		teacherDAO = new TeacherDAO();
		studentDAO = new StudentDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");

		if (action == null) {
			action = "home";
		}
		switch (action) {
		case "home":
			request.getRequestDispatcher("index-nav.jsp").forward(request, response);
			break;
		case "dashboard":
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
			break;
		case "logout":
			request.getSession().invalidate();
			request.getRequestDispatcher("login.jsp").forward(request, response);
			break;
		case "classMasterList":
			showClassMasterList(request, response);
			break;
		case "studentMasterList":
			showStudentMasterList(request, response);
			break;
		case "teacherMasterList":
			showTeacherMasterList(request, response);
			break;

		case "changePassword":
			request.getRequestDispatcher("change-password.jsp	").forward(request, response);
			break;

		case "report":
			showReport(request, response);
			break;
		default:
			request.getRequestDispatcher("index-nav.jsp").forward(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null) {
			action = "login";
		}
		switch (action) {
		case "login":
			authenticateAdmin(request, response);
			break;
		case "passwordChange":
			changePassword(request, response);
			break;

		}

	}

	public void showClassMasterList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<MyClass> classList = classDAO.getClassList();
		request.setAttribute("classList", classList);
		request.getRequestDispatcher("class-masterlist.jsp").forward(request, response);
	}

	public void showStudentMasterList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Student> studentList = studentDAO.getStudentList();
		request.setAttribute("studentList", studentList);
		request.getRequestDispatcher("student-masterlist.jsp").forward(request, response);
	}

	public void showTeacherMasterList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Teacher> teacherList = teacherDAO.getTeacherList();
		request.setAttribute("teacherList", teacherList);
		request.getRequestDispatcher("teacher-masterlist.jsp").forward(request, response);
	}

	public void showReport(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<MyClass> classList = classDAO.getClassList();
		request.setAttribute("classList", classList);
		request.getRequestDispatcher("report.jsp").forward(request, response);
	}

	public void authenticateAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		System.out.println(userName);

		if (userName.equals(USERNAME) && password.equals(defaultPassword)) {
			HttpSession session = request.getSession(true);
			session.setMaxInactiveInterval(300);
			session.setAttribute("userName", userName);
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		} else {
			String message = "Username or password is wrong";
			request.setAttribute("message", message);
			request.getRequestDispatcher("validation.jsp").forward(request, response);
		}
	}

	public void changePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		if (defaultPassword.equals(password) && userName.equals(USERNAME)) {
			defaultPassword = newPassword;
			response.sendRedirect("login.jsp");
		} else {
			String message = "old password is wrong";
			request.setAttribute("message", message);
			request.getRequestDispatcher("validation.jsp").forward(request, response);
		}

	}

}
