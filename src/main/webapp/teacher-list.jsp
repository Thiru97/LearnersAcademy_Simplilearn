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
		<h1>Teacher's Details</h1>

		<table
			class="table table-responsive table-striped text-center table-hover table-bordered caption-top">
			<caption class="m-2 p-2">
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-primary" data-bs-toggle="modal"
					data-bs-target="#form-modal">Add Teacher</button>
			</caption>
			<tr class="table-primary t">
				<th>S No</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Qualification</th>
				<th>Action</th>
			</tr>
			<c:forEach var="teacher" varStatus="item" items="${teacherList}">
			<tr>
				<td>${item.count}</td>
				<td>${teacher.firstName }</td>
				<td>${teacher.lastName }</td>
				<td>${teacher.qualification }</td>
				<td><a href="<%request.getContextPath(); %>TeacherController?action=deleteTeacher&id=${teacher.id}" class="btn btn-success rounded-pill">Delete</a>
				</td>
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
					<h1 class="modal-title fs-5" id="staticBackdropLabel">
						Teacher's form</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="teacherForm" action="<%request.getContextPath(); %>TeacherController?action=addTeacher" method="post">
						<div class="mb-3">
							<label for="subjectName" class="form-label">Enter
								First Name</label> <input type="text" class="form-control"
								id="firstName" name="firstName" placeholder="John" />
							<label for="subjectName" class="form-label">Enter
								Last Name</label> <input type="text" class="form-control" id="lastName"
								name="lastName" placeholder="Doe" /> <label
								for="subjectName" class="form-label">Enter
								Qualification</label> <input type="text" class="form-control"
								id="qualification" name="qualification"
								placeholder="M.A English" />
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary"
						data-bs-dismiss="modal" form="teacherForm">
						Submit</button>
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>