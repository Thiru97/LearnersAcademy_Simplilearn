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
		<h1>Subject Details</h1>

		<table
			class="table table-responsive table-striped text-center table-hover table-bordered caption-top">
			<caption class="m-2 p-2">
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-primary" data-bs-toggle="modal"
					data-bs-target="#form-modal">Add Subject</button>
			

			</caption>
			<tr class="table-primary t">
				<th>S No</th>
				<th>Subject Name</th>
				<th>Subject Code</th>
				<th>Action</th>
			</tr>
			<c:forEach var="subject" varStatus="item" items="${subjectList}">
				<tr>
					<td>${item.count }</td>
					<td>${subject.subjectName }</td>
					<td>${subject.subjectCode }</td>
					<td><a
						href="<%request.getContextPath(); %>SubjectController?action=deleteSubject&id=${subject.id}"
						class="btn btn-success rounded-pill">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</section>

	<!-- Modals -->
	<!-- Form Modal -->
	<div class="modal fade" id="form-modal" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="staticBackdropLabel">Subject
						form</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="subjectForm"
						action="<%request.getContextPath(); %>SubjectController?action=addSubject"
						method="post">
						<div class="mb-3">
							<label for="subjectName" class="form-label">Enter Subject
								Name</label> <input type="text" class="form-control" id="subjectName"
								name="subjectName" placeholder="English" />
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary"
						data-bs-dismiss="modal" form="subjectForm">Submit</button>
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="assignClassModal" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="staticBackdropLabel">Assign
						Class</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="assignClassForm" action="" method="post">
						<div class="mb-3">
							<label for="Subject">Subject :</label> <select name="subjectName"
								id="Subject" class="form-control">
								<option value="English">English</option>
							</select> <label for="Teacher">Assign Teacher :</label> <select
								name="teacherName" id="Teacher" class="form-control">
								<option value="John Doe">John Doe</option>
								<option value="Jane Doe">Jane Doe</option>
							</select>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary"
						data-bs-dismiss="modal" form="assignClassForm">Submit</button>
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancel</button>
				</div>
			</div>
		</div>
	</div>
















	<%@ include file="footer.jsp"%>
</body>
</html>