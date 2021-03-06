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
	<div class="container">
		<form:form action="/searchRecords" method="get"
			class="form-horizontal" modelAttribute="queryContent">
			<div class="row">
				<div class="form-group">
					<label class="control-label col-sm-2" for="fullName">Full
						Name:</label>
					<div class="col-sm-3">
						<form:input path="fullName" type="text" class="form-control"
							id="fullName" />
					</div>
					<label class="control-label col-sm-2" for="patientId">Patient
						Id:</label>
					<div class="col-sm-3">
						<form:input path="patientId" type="text" class="form-control"
							id="patientId" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<label class="control-label col-sm-2" for="phoneNumber">Phone
						No.:</label>
					<div class="col-sm-3">
						<form:input path="phoneNumber" type="text" class="form-control"
							id="phoneNumber" />
					</div>
					<label class="control-label col-sm-2" for="invoiceId">Invoice
						Id:</label>
					<div class="col-sm-3">
						<form:input path="invoiceId" type="text" class="form-control"
							id="invoiceId" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<label class="control-label col-sm-2" for="dateOfVisit">Date
						Of Visit:</label>
					<div class="col-sm-3">
						<form:input path="dateOfVisit" type="date" class="form-control"
							id="dateOfVisit" />
					</div>
					<label class="control-label col-sm-2" for="nextAppointmentDate">Next
						Appointment Date:</label>
					<div class="col-sm-3">
						<form:input path="nextAppointmentDate" type="date"
							class="form-control" id="nextAppointmentDate" max="2099-12-31" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<label class="control-label col-sm-2" for="emailId">Email
						Id:</label>
					<div class="col-sm-3">
						<form:input path="emailId" type="text" class="form-control"
							id="emailId" />
					</div>
					<div class="control-label col-sm-2"></div>
					<div class="col-sm-3">
						<button type="submit" id="search"
							class="btn btn-primary float-right">
							<span class="glyphicon glyphicon-search"></span> Search
						</button>
					</div>
				</div>
			</div>
		</form:form>

		<c:if test="${patientSearchResults.size() > 0}">
			<table class="table table-hover margin-top-30px">
				<thead>
					<tr>
						<th>Full Name</th>
						<th>Age</th>
						<th>Gender</th>
						<th>Phone No.</th>
						<th>Email Id</th>
						<th>Date of Visit</th>
						<th>Next Appointment Date</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="patientSearchResult"
						items="${patientSearchResults}">
						<tr>
							<td>${patientSearchResult.fullName}</td>
							<td>${patientSearchResult.age}</td>
							<td>${patientSearchResult.gender}</td>
							<td>${patientSearchResult.phoneNumber}</td>
							<td>${patientSearchResult.emailId}</td>
							<td>${patientSearchResult.dateOfVisit}</td>
							<td>${patientSearchResult.nextAppointmentDate}</td>
							<td><a
								href="/patientDetails?patientId=${patientSearchResult.patientId}&dateOfVisit=${patientSearchResult.dateOfVisit}"
								target="_blank" class="text-decoration-none"><span
									class="glyphicon glyphicon-new-window"></span> more details</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>