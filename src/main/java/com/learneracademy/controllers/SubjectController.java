package com.learneracademy.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learneracademy.dao.SubjectDAO;
import com.learneracademy.entity.Subject;

/**
 * Servlet implementation class SubjectController
 */
@WebServlet("/SubjectController")
public class SubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	SubjectDAO subjectDAO = null;

	public SubjectController() {
		subjectDAO = new SubjectDAO();
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
			action = "subjectList";
		}
		switch (action) {
		case "subjectList":
			showSubjectList(request, response);
			break;
		case "deleteSubject":
			deleteSubject(request, response);
			break;

		default:
			request.getRequestDispatcher("subject-list.jsp").forward(request, response);
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
		case "addSubject":
			addSubject(request, response);
			break;

		}
	}

	/* Controller Methods */

	public void addSubject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Subject subject = new Subject();
		String subjectName = request.getParameter("subjectName");
		String subjectCode = subjectName.substring(0, 3);

		subject.setSubjectName(subjectName);
		subject.setSubjectCode(subjectCode);

		boolean status = subjectDAO.addSubject(subject);
		if (status) {
			response.sendRedirect("SubjectController?action=subjectList");
		} else {
			String message = "Operation Failed";
			request.setAttribute("message", message);
			request.getRequestDispatcher("SubjectController?action=subjectList").forward(request, response);
		}
	}

	public void showSubjectList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Subject> subjectList = null;
		subjectList = subjectDAO.getSubjectList();

		request.setAttribute("subjectList", subjectList);
		request.getRequestDispatcher("subject-list.jsp").forward(request, response);

	}

	public void deleteSubject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		boolean status = subjectDAO.deleteSubject(id);
		if (status) {
			response.sendRedirect("SubjectController?action=subjectList");
		} else {
			String message = "Operation Failed";
			request.setAttribute("message", message);
			request.getRequestDispatcher("SubjectController?action=subjectList").forward(request, response);
		}
	}

}
