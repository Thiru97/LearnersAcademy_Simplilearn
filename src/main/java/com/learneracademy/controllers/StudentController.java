package com.learneracademy.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learneracademy.dao.ClassDAO;
import com.learneracademy.dao.StudentDAO;
import com.learneracademy.entity.MyClass;
import com.learneracademy.entity.Student;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	StudentDAO studentDAO = null;
	ClassDAO classDAO = null;

	public StudentController() {
		studentDAO = new StudentDAO();
		classDAO = new ClassDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);

		if (action == null) {
			action = "studentList";
		}
		switch (action) {
		case "studentList":
			showStudentList(request, response);
			break;
		case "deleteStudent":
			deleteStudent(request, response);
			break;
		case "StudentOf":
			showStudentOfClass(request, response);
			break;

		default:
			request.getRequestDispatcher("student-list.jsp").forward(request, response);
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

		switch (action) {
		case "addStudent":
			addStudent(request, response);
			break;

		}
	}

	/* Controller Methods */

	public void addStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Student student = new Student();
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");

		student.setFirstName(firstName);
		student.setLastName(lastName);

		boolean status = studentDAO.addStudent(student);
		if (status) {
			response.sendRedirect("StudentController?action=studentList");
		} else {
			String message = "Operation Failed";
			request.setAttribute("message", message);
			request.getRequestDispatcher("StudentController?action=studentList").forward(request, response);
		}
	}

	public void showStudentList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<MyClass> classList = null;
		List<Student> studentList = null;

		classList = classDAO.getClassList();
		studentList = studentDAO.getStudentList();

		request.setAttribute("classList", classList);
		request.setAttribute("studentList", studentList);
		request.getRequestDispatcher("student-list.jsp").forward(request, response);

	}

	public void deleteStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		boolean status = studentDAO.deleteStudent(id);
		if (status) {
			response.sendRedirect("StudentController?action=studentList");
		} else {
			String message = "Operation Failed";
			request.setAttribute("message", message);
			request.getRequestDispatcher("StudentController?action=studentList").forward(request, response);
		}
	}

	public void showStudentOfClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String className = request.getParameter("className");
		MyClass myClass = classDAO.getClass(className);

		List<Student> studentList = studentDAO.getStudent(myClass);
		request.setAttribute("studentList", studentList);
		request.getRequestDispatcher("students-class.jsp").forward(request, response);
	}
}
