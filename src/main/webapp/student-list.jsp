<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Learner's Academy</title>
</head>
<body>

<% if(request.getSession(false).getAttribute("userName")== null){
	response.sendRedirect("login.jsp");
}
%>

	<%@ include file="index-nav.jsp"%>

	<section
		class="main-content d-flex flex-column align-items-start justify-content-start">
		<h1>Student Details</h1>

		<table
			class="table table-responsive table-striped text-center table-hover table-bordered caption-top">
			<caption class="m-2 p-2">
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-primary" data-bs-toggle="modal"
					data-bs-target="#form-modal">Add Student</button>
				<button type="button" class="btn btn-danger" data-bs-toggle="modal"
					data-bs-target="#form-2-modal">Assign Class</button>
			</caption>
			<tr class="table-primary t">
				<th>S No</th>
				<th>Student First Name</th>
				<th>Student Last Name</th>
				<th>Class </th>
				<th>Action</th>
			</tr>
			<c:forEach var="student" varStatus="item" items="${studentList}">
				
			
				<tr>
					<td>${item.count }</td>
					<td>${student.firstName}</td>
					<td>${student.lastName}</td>
					<td>${student.myClass.className}</td>
					<td><a
						href="<%request.getContextPath(); %>StudentController?action=deleteStudent&id=${student.id}"
						class="btn btn-success rounded-pill">Delete</a></td>
				</tr>
			</c:forEach>
		
		</table>
	</section>

	<!-- Modals -->
	<div class="modal fade" id="form-modal" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="staticBackdropLabel">Class
						form</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="studentForm"
						action="<%request.getContextPath(); %>StudentController?action=addStudent"
						method="post">
						<div class="mb-3">
							<label for="firstName" class="form-label">Enter First
								Name</label> <input type="text" class="form-control" id="lastName"
								name="firstName" placeholder="John" /> <label for="lastName"
								class="form-label">Enter Last Name</label> <input type="text"
								class="form-control" id="lastName" name="lastName"
								placeholder="Doe" />
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="submit" form="studentForm" class="btn btn-primary"
						data-bs-dismiss="modal">Submit</button>
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="form-2-modal" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="staticBackdropLabel">Class
						form</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="addClassForm" action="<%request.getContextPath(); %>ClassController?action=addStudentToAClass" method="post">
						<div class="mb-3">
							<label for="Student">Student Name:</label> 
							<select
								class="form-control" name="studentName" id="studentName">
								<option value="">Select</option>
								<c:forEach var ="student" items="${studentList }">
								<option value="${student.firstName}">${student.firstName}</option>
								</c:forEach>
							</select> <label for="Class">Class Name:</label> 
							<select
								class="form-control" name="className" id="className">
									<option value="">Select</option>
								<c:forEach var ="myClass" items="${classList }">
								<option value="${myClass.className}">${myClass.className}</option>
								</c:forEach>
							</select>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary"
						data-bs-dismiss="modal" form="addClassForm">Add</button>
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>





	<%@ include file="footer.jsp"%>
</body>
</html>