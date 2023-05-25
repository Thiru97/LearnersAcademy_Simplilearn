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

<% if(request.getSession(false).getAttribute("userName")== null){
	response.sendRedirect("login.jsp");
}
%>

 <%@ include file="index-nav.jsp"%>
 
 <section
		class="main-content d-flex flex-column align-items-start justify-content-start">
		<h1>Class Report</h1>
		

		<table
			class="table table-responsive table-striped text-center table-hover table-bordered caption-top">
			<caption class="m-2 p-2">
	
	
			</caption>
			<tr class="table-primary t">
				<th>Class Name</th>
				<th>Subject</th>
				<th>Teacher</th>
				<th>Student List</th>
			</tr>
			<c:forEach var="myClass" items="${classList }">
				<c:forEach var="subject" items="${myClass.subject }">
			
						<tr>
							<td>${myClass.className}</td>
							<td>${subject.subjectName}</td>
							<td>${subject.teacher.fullName }</td>
		
							<td><a href="<%request.getContextPath();%>StudentController?action=StudentOf&&className=${myClass.className}">List</a></td>
						</tr>
					</c:forEach>
				</c:forEach>

		</table>
	</section>
 
 
 
 
 
 
 
 
 
 
 
 <%@ include file="footer.jsp"%>
</body>
</html>