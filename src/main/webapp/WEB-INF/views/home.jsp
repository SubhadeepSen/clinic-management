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
		<div class="row">
			<div class="col-md-2">
				<div class="card">
					<a href="/patientForm" class="card-body">
						<h1 class="card-title">New <br> Patient</h1>
					</a>
				</div>
			</div>
			<div class="col-md-2">
				<div class="card">
					<a href="/searchRecords" class="card-body">
						<h1 class="card-title">Patient <br> Records</h1>
					</a>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="js/home.js"></script>
</body>
</html>