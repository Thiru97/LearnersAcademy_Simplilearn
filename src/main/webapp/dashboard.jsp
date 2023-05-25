<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	<div class="mycontent">
		<div class="mycards container-fluid">
			<div class="">
				<div class="row">
					<div
						class="col-lg-auto col-md-6 card text-bg-light border-black border-2 m-1 p-1"
						style="max-width: 18rem">
						<div class="card-header h3 text-center text-white"
							style="background-color: #27374d">Class</div>
						<div class="card-body text-center">
							<h3 class="card-text">Manage Class related details here</h3>
							<a href="<%request.getContextPath(); %>ClassController"
								class="btn btn-dark">Manage</a>
						</div>
					</div>
					<div
						class="col-lg-auto col-md-6 card border-danger border-2 m-1 p-1"
						style="max-width: 18rem">
						<div class="card-header text-bg-danger h3 text-center">
							Student</div>
						<div class="card-body text-center">
							<h3 class="card-text">Manage student details here</h3>
							<a href="<%request.getContextPath(); %>StudentController"
								class="btn btn-danger">Manage</a>
						</div>
					</div>
					<div
						class="col-lg-auto col-md-6 card border-success border-2 m-1 p-1"
						style="max-width: 18rem">
						<div class="card-header text-bg-success h3 text-center">
							Teachers</div>
						<div class="card-body text-center">
							<h3 class="card-text">Manage Teacher details here</h3>
							<a href="<%request.getContextPath(); %>TeacherController"
								class="btn btn-success">Manage</a>
						</div>
					</div>
					<div class="col-lg-auto col-md-6 card border-info border-2 m-1 p-1"
						style="max-width: 18rem">
						<div class="card-header text-bg-info h3 text-center">Subject</div>
						<div class="card-body text-center">
							<h3 class="card-text">Manage Subject details here</h3>
							<a href="<%request.getContextPath(); %>SubjectController"
								class="btn btn-info">Manage</a>
						</div>
					</div>

					<div
						class="col-lg-auto col-md-6 card border-warning border-2 m-1 p-1"
						style="max-width: 18rem">
						<div class="card-header text-bg-warning h3 text-center">
							Class Duties</div>
						<div class="card-body text-center">
							<h3 class="card-text">Assign Subject and Staff Here</h3>
							<a href="<%request.getContextPath(); %>ClassController?action=showDuties" class="btn btn-warning">Manage</a>
						</div>
					</div>




				</div>
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>