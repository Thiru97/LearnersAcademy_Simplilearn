<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>Learner's Academy</title>
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
			crossorigin="anonymous"
		/>
		<link rel="stylesheet" href="css/styles.css" />
	</head>
	
	
	<body class="d-flex flex-column  align-items-center justify-content-center">
	
	
		<form class="card p-3 bg-light" action="<%request.getContextPath();%>AdminController?action=login" method="post">
			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">User Name</label>
				<input
					type="email"
					name="userName"
					class="form-control"
					id="userName"
				/>
			</div>
			<div class="mb-3">
				<label for="exampleInputPassword1" class="form-label">Password</label>
				<input
					type="password"
					name="password"
					class="form-control"
					id="password"
				/>
			</div>

			<button type="submit" class="btn btn-primary">Submit</button>
		</form>

		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
			;
			integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
			;
			crossorigin="anonymous"
			;
		></script>
	</body>

</html>