<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Learner Academy</title>
</head>
<body>
 <%@ include file="index-nav.jsp"%>
 
 
<% if(request.getSession(false).getAttribute("userName")== null){
	response.sendRedirect("login.jsp");
}
%>
 

 <section
			class="main-content d-flex flex-column align-items-start justify-content-start"
		>
			<h1>Class Details</h1>

			<table
				class="table table-responsive table-striped text-center table-hover table-bordered caption-top"
			>
				<caption class="m-2 p-2">
					<!-- Button trigger modal -->
					<button
						type="button"
						class="btn btn-primary"
						data-bs-toggle="modal"
						data-bs-target="#form-modal"
					>
						Add Class
					</button>
					
				</caption>
				<tr class="table-primary">
					<th>S No</th>
					<th>Class Name</th>
					<th>Action</th>
				</tr>
					<c:forEach var="myClass" varStatus="item" items="${classList }">
				<tr>
					<td>${item.count }</td>
					<td>${myClass.className}</td>
					<td>
						<a href="<%request.getContextPath(); %>ClassController?action=deleteClass&id=${myClass.id}" class="btn btn-success rounded-pill">Delete</a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</section>

		<!-- Modals -->
			<!-- Form Modal -->
		<div
			class="modal fade"
			id="form-modal"
			data-bs-backdrop="static"
			data-bs-keyboard="false"
			tabindex="-1"
		>
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="staticBackdropLabel">
							Class form
						</h1>
						<button
							type="button"
							class="btn-close"
							data-bs-dismiss="modal"
							aria-label="Close"
						></button>
					</div>
					<div class="modal-body">
						<form action="<%request.getContextPath(); %>ClassController?action=addClass" id="classForm" method="post">
							<div class="mb-3">
								<label for="class Name" class="form-label"
									>Enter Class Name</label
								>
								<input
									type="text"
									class="form-control"
									id="className"
									name="className"
								/>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" form="classForm"	data-bs-dismiss="modal">Submit</button>
						<button
							type="button"
							class="btn btn-secondary"
							data-bs-dismiss="modal"
						>
							Cancel
						</button>
					</div>
				</div>
			</div>
		</div>
 

 <%@ include file="footer.jsp"%>
</body>
</html>