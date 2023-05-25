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
	
			<tr class="table-primary ">
				<th>S No</th>
				<th>Student First Name</th>
				<th>Student Last Name</th>
		
		
			</tr>
			<c:forEach var="student" varStatus="item" items="${studentList}">
				
			
				<tr>
					<td>${item.count }</td>
					<td>${student.firstName}</td>
					<td>${student.lastName}</td>
				
				</tr>
			</c:forEach>
		
		</table>
	</section>
	
	
	
	
	
	
	
		<%@ include file="footer.jsp"%>
</body>
</html>