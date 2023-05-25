package com.learneracademy.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learneracademy.dao.TeacherDAO;
import com.learneracademy.entity.Teacher;

/**
 * Servlet implementation class TeacherController
 */
@WebServlet("/TeacherController")
public class TeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TeacherDAO teacherDAO = null;

	public TeacherController() {

		teacherDAO = new TeacherDAO();
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
			action = "teacherList";
		}
		switch (action) {
		case "teacherList":
			showTeacherList(request, response);
			break;
		case "deleteTeacher":
			deleteTeacher(request, response);
			break;

		default:
			request.getRequestDispatcher("teacher-list.jsp").forward(request, response);
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
		case "addTeacher":
			addTeacher(request, response);
			break;

		}
	}

	/* Controller Methods */

	public void addTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Teacher teacher = new Teacher();
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String qualification = request.getParameter("qualification");

		String fullName = firstName + " " + lastName;

		teacher.setFirstName(firstName);
		teacher.setLastName(lastName);
		teacher.setFullName(fullName);
		teacher.setQualification(qualification);

		boolean status = teacherDAO.addTeacher(teacher);
		if (status) {
			response.sendRedirect("TeacherController?action=teacherList");
		} else {
			String message = "Operation Failed";
			request.setAttribute("message", message);
			request.getRequestDispatcher("TeacherController?action=teacherList").forward(request, response);
		}
	}

	public void showTeacherList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Teacher> teacherList = null;
		teacherList = teacherDAO.getTeacherList();

		request.setAttribute("teacherList", teacherList);
		request.getRequestDispatcher("teacher-list.jsp").forward(request, response);

	}

	public void deleteTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		boolean status = teacherDAO.deleteTeacher(id);
		if (status) {
			response.sendRedirect("TeacherController?action=teacherList");
		} else {
			String message = "Operation Failed";
			request.setAttribute("message", message);
			request.getRequestDispatcher("TeacherController?action=teacherList").forward(request, response);
		}
	}

}
