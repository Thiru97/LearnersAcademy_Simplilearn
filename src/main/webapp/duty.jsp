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
		<h1>Duty Details</h1>

		<table
			class="table table-responsive table-striped text-center table-hover table-bordered caption-top">
			<caption class="m-2 p-2">
		
				<button type="button" class="btn btn-danger" data-bs-toggle="modal"
					data-bs-target="#teacherForm">Assign Teacher</button>
			</caption>
			<tr class="table-primary t">
				<th>Class Name</th>
				<th>Subject</th>
				<th>Teacher</th>
				
			</tr>
			<c:forEach var="myClass" items="${classList }">
				<c:forEach var="subject" items="${myClass.subject }">
			


						<tr>
							<td>${myClass.className}</td>
							<td>${subject.subjectName}</td>
							<td>${subject.teacher.fullName }</td>
							
						</tr>
					</c:forEach>
				</c:forEach>


		</table>
	</section>

	<!-- Modals -->
	<!-- Assign Subject form -->
	<div class="modal fade" id="subjectForm" data-bs-backdrop="static"
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
					<form id="assignSubject"
						action="<%request.getContextPath();%>ClassController?action=assignSubject"
						method="post">
						<div class="mb-3">
							<label for="class">Select Class:</label> <select
								class="form-control" name="className" id="className">
								<option value="Select">Select</option>
								<c:forEach var="myClass" items="${classList }">
									<option value="${myClass.className}">${myClass.className}</option>
								</c:forEach>
							</select>
						</div>
						<div class="mb-3">
							<label for="class">Select Subject:</label> <select
								class="form-control" name="subjectName" id="subjectName">
								<option value="Select">Select</option>
								<c:forEach var="subject" items="${subjectList }">
									<option value="${subject.subjectName}">${subject.subjectName}</option>
								</c:forEach>
							</select>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary"
						data-bs-dismiss="modal" form="assignSubject">Assign</button>
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Add Duty form -->
	<div class="modal fade" id="teacherForm" data-bs-backdrop="static"
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
					<form id="assignTeacher"
						action="<%request.getContextPath();%>ClassController?action=addDuty"
						method="post">
						<div class="mb-3">
							<label for="class">Select Class:</label> <select
								class="form-control" name="className" id="className">
								<option value="Select">Select</option>
								<c:forEach var="myClass" items="${classList }">
									<option value="${myClass.className}">${myClass.className}</option>
								</c:forEach>
							</select>
						</div>
						<div class="mb-3">
							<label for="class">Select Subject:</label> <select
								class="form-control" name="subjectName" id="subjectName">
								<option value="Select">Select</option>
								<c:forEach var="subject" items="${subjectList }">
									<option value="${subject.subjectName}">${subject.subjectName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="mb-3">
							<label for="class">Select Teacher:</label> <select
								class="form-control" name="teacherName" id="teacherName">
								<option value="Select">Select</option>
								<c:forEach var="teacher" items="${teacherList }">
									<option value="${teacher.fullName}">${teacher.fullName}</option>
								</c:forEach>
							</select>
						</div>

					</form>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary"
						data-bs-dismiss="modal" form="assignTeacher">Assign</button>
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>




	<%@ include file="footer.jsp"%>
</body>
</html>