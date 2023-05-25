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
import com.learneracademy.dao.SubjectDAO;
import com.learneracademy.dao.TeacherDAO;
import com.learneracademy.entity.MyClass;
import com.learneracademy.entity.Student;
import com.learneracademy.entity.Subject;
import com.learneracademy.entity.Teacher;

/**
 * Servlet implementation class ClassController
 */
@WebServlet("/ClassController")
public class ClassController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClassDAO classDAO = null;
	SubjectDAO subjectDAO = null;
	TeacherDAO teacherDAO = null;
	StudentDAO studentDAO = null;

	public ClassController() {
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
			action = "classList";
		}
		switch (action) {
		case "classList":
			showClassList(request, response);
			break;
		case "showDuties":
			showDuties(request, response);
			break;
		case "deleteClass":
			deleteClass(request, response);
			break;

		/*
		 * case "deleteDuty": deleteDuty(request, response); break;
		 */

		default:
			request.getRequestDispatcher("class-list.jsp").forward(request, response);
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
		System.out.println(action);

		switch (action) {
		case "addClass":
			addClass(request, response);
			break;

		case "addDuty":
			addDuty(request, response);
			break;

		/*
		 * case "assignSubject": assignSubject(request, response); break;
		 */

		case "addStudentToAClass":
			addStudentToAClass(request, response);
			break;

		default:
			request.getRequestDispatcher("class-list.jsp").forward(request, response);
			break;
		}
	}

	/* Controller Methods */
	public void addClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MyClass myClass = new MyClass();
		String className = request.getParameter("className");
		System.out.println(className);
		String message = null;

		myClass.setClassName(className);

		boolean status = classDAO.addClass(myClass);
		if (status) {
			response.sendRedirect("ClassController?action=classList");
		} else {
			message = "Operation Failed";
			request.setAttribute("message", message);
			request.getRequestDispatcher("ClassController?action=classList").forward(request, response);
		}

	}

	public void showClassList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<MyClass> classList = null;
		classList = classDAO.getClassList();

		request.setAttribute("classList", classList);
		request.getRequestDispatcher("class-list.jsp").forward(request, response);

	}

	public void deleteClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		boolean status = classDAO.deleteClass(id);
		if (status) {
			request.getRequestDispatcher("ClassController?action=classList").forward(request, response);
		} else {
			String message = "Operation failed";
			request.setAttribute("message", message);
			request.getRequestDispatcher("ClassController?action=classList").forward(request, response);
		}

	}

	/*
	 * public void assignSubject(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * 
	 * MyClass myClass = new MyClass(); Subject subject = new Subject();
	 * 
	 * String className = request.getParameter("className"); String subjectName =
	 * request.getParameter("subjectName");
	 * 
	 * myClass = classDAO.getClass(className);
	 * 
	 * subject = subjectDAO.getSubject(subjectName);
	 * 
	 * if (myClass != null) {
	 * 
	 * myClass.getSubject().add(subject); subject.getMyClass().add(myClass);
	 * 
	 * classDAO.updateClass(myClass); subjectDAO.updateSubject(subject);
	 * 
	 * response.sendRedirect("ClassController?action=showDuties"); } else { String
	 * message = "Operation failed"; request.setAttribute("message", message);
	 * request.getRequestDispatcher("ClassController?action=showDuties").forward(
	 * request, response); } }
	 */

	@SuppressWarnings("unused")
	public void addDuty(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyClass myClass = new MyClass();

		Teacher teacher = new Teacher();

		String className = request.getParameter("className");
		String subjectName = request.getParameter("subjectName");
		String teacherName = request.getParameter("teacherName");

		myClass = classDAO.getClass(className);
		teacher = teacherDAO.getTeacher(teacherName);
		Subject subject = subjectDAO.getSubject(subjectName);

		if (myClass != null) {

			myClass.getTeacher().add(teacher);
			teacher.getMyClass().add(myClass);

			subject.setTeacher(teacher);

			myClass.getSubject().add(subject);
			subject.getMyClass().add(myClass);

			classDAO.updateClass(myClass);
			teacherDAO.updateTeacher(teacher);
			subjectDAO.updateSubject(subject);
			response.sendRedirect("ClassController?action=showDuties");
		} else {
			String message = "Operation failed";
			request.setAttribute("message", message);
			request.getRequestDispatcher("validation.jsp").forward(request, response);
		}
	}

	public void showDuties(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<MyClass> classList = null;
		List<Subject> subjectList = null;
		List<Teacher> teacherList = null;

		classList = classDAO.getClassList();
		subjectList = subjectDAO.getSubjectList();
		teacherList = teacherDAO.getTeacherList();

		request.setAttribute("classList", classList);
		request.setAttribute("subjectList", subjectList);
		request.setAttribute("teacherList", teacherList);

		request.getRequestDispatcher("duty.jsp").forward(request, response);
	}

	/*
	 * public void deleteDuty(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { MyClass myClass = new
	 * MyClass(); Teacher teacher = new Teacher(); Subject subject = new Subject();
	 * 
	 * int classId = Integer.parseInt(request.getParameter("classId")); int
	 * subjectId = Integer.parseInt(request.getParameter("subjectId")); int
	 * teacherId = Integer.parseInt(request.getParameter("teacherId"));
	 * 
	 * System.out.println(classId); System.out.println(subjectId);
	 * System.out.println(teacherId);
	 * 
	 * myClass = classDAO.getClass(classId);
	 * 
	 * teacher = teacherDAO.getTeacher(teacherId); subject =
	 * subjectDAO.getSubject(subjectId);
	 * 
	 * if (myClass != null) {
	 * 
	 * myClass.getTeacher().remove(teacher); teacher.getMyClass().remove(myClass);
	 * 
	 * myClass.getSubject().remove(subject); subject.getMyClass().remove(myClass);
	 * 
	 * subject.setTeacher(null);
	 * 
	 * subjectDAO.updateSubject(subject); teacherDAO.updateTeacher(teacher);
	 * classDAO.updateClass(myClass);
	 * 
	 * response.sendRedirect("ClassController?action=showDuties"); } else { String
	 * message = "Operation failed"; request.setAttribute("message", message);
	 * request.getRequestDispatcher("ClassController?action=showDuties").forward(
	 * request, response); }
	 * 
	 * }
	 */

	public void addStudentToAClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MyClass myClass = new MyClass();
		Student student = new Student();

		String className = request.getParameter("className");
		String studentName = request.getParameter("studentName");

		myClass = classDAO.getClass(className);
		student = studentDAO.getStudent(studentName);

		if (myClass != null) {
			myClass.getStudent().add(student);
			student.setMyClass(myClass);

			classDAO.updateClass(myClass);
			studentDAO.updateStudent(student);

			response.sendRedirect("StudentController?action=studentList");
		}

	}
}
