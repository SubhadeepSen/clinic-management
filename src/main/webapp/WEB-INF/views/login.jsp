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
<style type="text/css">
.error-glyphicon {
	top: 25px !important;
}
</style>
</head>
<body class="container-fluid">
	<%@include file="header.jsp"%>
	<form:form action="/login" method="POST" class="margin-top-100px"
		modelAttribute="loginForm" id="loginForm">
		<div class="row">
			<div class="col-sm-4"></div>
			<c:if test="${not empty errorMessage}">
				<div class="col-sm-4 login-error-message">${errorMessage}</div>
			</c:if>
		</div>
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<div class="form-group">
					<label for="username">Username:</label>
					<form:input path="username" type="text" class="form-control"
						id="username" />
					<span
						class="custom-tootip glyphicon glyphicon-info-sign error-glyphicon hidden"
						data-toggle="tooltip" title="Enter username" data-placement="left"></span>
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
					<span
						class="custom-tootip glyphicon glyphicon-info-sign error-glyphicon hidden"
						data-toggle="tooltip" title="Enter password" data-placement="left"></span>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<button type="submit"
					class="btn btn-primary float-right lets-go-btn" id="letsGo">Let's
					Go</button>
			</div>
		</div>
	</form:form>
	<script type="text/javascript" src="js/login.js"></script>
</body>
</html>