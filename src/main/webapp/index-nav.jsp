<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Learner Academy</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous" />
<link rel="stylesheet" href="css/styles.css" />
<body>

<% if(request.getSession(false).getAttribute("userName")== null){
	response.sendRedirect("login.jsp");
}
%>






	<div class="navbar navbar-expand-lg" style="background-color: #526d82">
		<div class="container d-flex align-items-end justify-content-end">
			<div class="">
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav">
						<li class="nav-item dropdown mx-3 px-4">
							<button class="nav-link dropdown-toggle text-bg-light rounded"
								href="#" role="button" data-bs-toggle="dropdown"
								aria-expanded="false">
								<span class="mb-0 h4">Admin</span>
							</button>

							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="<%request.getContextPath();%>AdminController?action=changePassword">Change Password </a>
								</li>
								<li><a class="dropdown-item" href="<%request.getContextPath();%>AdminController?action=logout">Logout</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<section class="sidebar">
		<div class="sidebar__logo">
			<h1>Learner's Academy</h1>
		</div>
		<div class="sidebar__nav">
			<a href="<%request.getContextPath(); %>AdminController?action=dashboard" class="border-bottom">Dashboard</a>
			<div class="dropdown">
				<a href="#masterlist" class="btn btn-lg border-bottom"
					data-bs-toggle="collapse" role="button" aria-expanded="false"
					data-target="#masterlist"> Master list </a>

				<ul class="collapse" id="masterlist">
					<li><a class="dropdown-item" href="<%request.getContextPath();%>AdminController?action=classMasterList">Class list</a></li>
					<li><a class="dropdown-item" href="<%request.getContextPath();%>AdminController?action=teacherMasterList">Teachers list</a></li>
					<li><a class="dropdown-item" href="<%request.getContextPath();%>AdminController?action=studentMasterList">Student list</a></li>
				</ul>
			</div>
			<a href="<%request.getContextPath();%>AdminController?action=report" class="border-bottom">Report</a>
		</div>
	</section>
	 <%@ include file="footer.jsp"%>
	 	<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
			crossorigin="anonymous"
		></script>
		
		 <%@ include file="footer.jsp"%>
</body>
</html>