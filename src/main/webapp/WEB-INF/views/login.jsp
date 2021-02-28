<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/dr.sens.dental.clinic.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<title>Dr. Sens Dental Clinic</title>
</head>
<body class="container-fluid">
	<%@include file="header.jsp"%>
	<form:form action="/login" method="POST" class="margin-top-100px"
		modelAttribute="loginForm">
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<c:if test="${not empty errorMessage}">
					<p class="login-error-message">${errorMessage}</p>
				</c:if>
				<div class="form-group">
					<label for="username">Username:</label>
					<form:input path="username" type="text" class="form-control"
						id="username" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<div class="form-group">
					<label for="pwd">Password:</label>
					<form:input path="password" type="password" class="form-control"
						id="password" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<button type="submit"
					class="btn btn-primary float-right lets-go-btn">Let's Go</button>
			</div>
		</div>
	</form:form>
</body>
</html>