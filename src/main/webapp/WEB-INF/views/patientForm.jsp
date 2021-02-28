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
		<form:form action="/reviewPatientForm" method="POST"
			class="form-horizontal" modelAttribute="patientForm">
			<div class="row">
				<div class="form-group">
					<label class="control-label col-sm-2" for="fullName">Full
						Name:</label>
					<div class="col-sm-3">
						<c:choose>
							<c:when test="${ not empty fullNameError}">
								<form:input path="fullName" type="text"
									class="form-control field-error-border" id="fullName"
									required="true" />
								<span
									class="custom-tootip glyphicon glyphicon-info-sign error-glyphicon"
									data-toggle="tooltip" title="${fullNameError}"
									data-placement="left"></span>
							</c:when>
							<c:otherwise>
								<form:input path="fullName" type="text" class="form-control"
									id="fullName" required="true" />
							</c:otherwise>
						</c:choose>
					</div>
					<label class="control-label col-sm-1" for="age">Age:</label>
					<div class="col-sm-1">
						<c:choose>
							<c:when test="${ not empty ageError}">
								<form:input path="age" type="text"
									class="form-control field-error-border" id="age"
									required="true" />
								<span
									class="custom-tootip glyphicon glyphicon-info-sign error-glyphicon"
									data-toggle="tooltip" title="${ageError}"
									data-placement="right"></span>
							</c:when>
							<c:otherwise>
								<form:input path="age" type="text" class="form-control" id="age"
									required="true" />
							</c:otherwise>
						</c:choose>
					</div>
					<label class="control-label col-sm-1" for="gender">Gender:</label>
					<div class="col-sm-2">
						<label class="radio-inline"> <form:radiobutton
								path="gender" value="male" name="gender" checked="true" />Male
						</label> <label class="radio-inline"> <form:radiobutton
								path="gender" value="female" name="gender" />Female
						</label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<label class="control-label col-sm-2" for="address">Address:</label>
					<div class="col-sm-8">
						<form:input path="address" type="text" class="form-control"
							id="address" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<label class="control-label col-sm-2" for="phoneNumber">Phone
						No.:</label>
					<div class="col-sm-3">
						<c:choose>
							<c:when test="${ not empty phoneNumberError}">
								<form:input path="phoneNumber" type="text"
									class="form-control field-error-border" id="phoneNumber"
									required="true" />
								<span
									class="custom-tootip glyphicon glyphicon-info-sign error-glyphicon"
									data-toggle="tooltip" title="${phoneNumberError}"
									data-placement="left"></span>
							</c:when>
							<c:otherwise>
								<form:input path="phoneNumber" type="text" class="form-control"
									id="phoneNumber" required="true" />
							</c:otherwise>
						</c:choose>
						<span class="glyphicon glyphicon-search search-glyphicon" id="populate"></span>
					</div>
					<label class="control-label col-sm-2" for="emailId">Email
						Id:</label>
					<div class="col-sm-3">
						<c:choose>
							<c:when test="${ not empty emailIdError}">
								<form:input path="emailId" type="text"
									class="form-control field-error-border" id="emailId"
									required="true" />
								<span
									class="custom-tootip glyphicon glyphicon-info-sign error-glyphicon"
									data-toggle="tooltip" title="${emailIdError}"
									data-placement="left"></span>
							</c:when>
							<c:otherwise>
								<form:input path="emailId" type="text" class="form-control"
									id="emailId" />
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<label class="control-label col-sm-2" for="occupation">Occupation:</label>
					<div class="col-sm-3">
						<form:input path="occupation" type="text" class="form-control"
							id="occupation" />
					</div>
					<label class="control-label col-sm-2" for="dateOfVisit">Date
						Of Visit:</label>
					<div class="col-sm-3">
						<c:choose>
							<c:when test="${ not empty dateOfVisitError}">
								<form:input path="dateOfVisit" type="date"
									class="form-control field-error-border" id="dateOfVisit"
									required="true"
									min="<%=(java.time.LocalDate.now().toString())%>"
									max="<%=(java.time.LocalDate.now().toString())%>"
									value="<%=(java.time.LocalDate.now().toString())%>" />
								<span
									class="custom-tootip glyphicon glyphicon-info-sign error-glyphicon"
									data-toggle="tooltip" title="${dateOfVisitError}"
									data-placement="left"></span>
							</c:when>
							<c:otherwise>
								<form:input path="dateOfVisit" type="date" class="form-control"
									id="dateOfVisit" required="true"
									min="<%=(java.time.LocalDate.now().toString())%>"
									max="<%=(java.time.LocalDate.now().toString())%>"
									value="<%=(java.time.LocalDate.now().toString())%>" />
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<div id="chiefComplaintsParentDiv">
				<%@include file="patientFormSubViews/chiefComplaintsModule.jsp"%>
			</div>
			<div id="onExaminationsParentDiv">
				<%@include file="patientFormSubViews/onExaminationsModule.jsp"%>
			</div>
			<div id="medicalHistoriesParentDiv">
				<%@include file="patientFormSubViews/medicalHistoriesModule.jsp"%>
			</div>
			<div id="investigationsParentDiv">
				<%@include file="patientFormSubViews/investigationsModule.jsp"%>
			</div>
			<div id="advicesParentDiv">
				<%@include file="patientFormSubViews/advicesModule.jsp"%>
			</div>
			<div id="workDonesParentDiv">
				<%@include file="patientFormSubViews/workDonesModule.jsp"%>
			</div>
			<div id="medicinesParentDiv">
				<%@include file="patientFormSubViews/medicinesModule.jsp"%>
			</div>
			<div class="row">
				<div class="form-group">
					<label class="control-label col-sm-2" for="nextAppointmentDate">Next
						Appointment Date:</label>
					<div class="col-sm-3">
						<c:choose>
							<c:when test="${ not empty nextAppointmentDateError}">
								<form:input path="nextAppointmentDate" type="date"
									class="form-control field-error-border"
									id="nextAppointmentDate"
									min="<%=(java.time.LocalDate.now().toString())%>"
									max="2099-12-31" />
								<span
									class="custom-tootip glyphicon glyphicon-info-sign error-glyphicon"
									data-toggle="tooltip" title="${nextAppointmentDateError}"
									data-placement="left"></span>
							</c:when>
							<c:otherwise>
								<form:input path="nextAppointmentDate" type="date"
									class="form-control" id="nextAppointmentDate"
									min="<%=(java.time.LocalDate.now().toString())%>"
									max="2099-12-31" />
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<div
				class="form-group margin-top-30px margin-bottom-100px margin-right-16per">
				<button type="submit" id="preview"
					class="btn btn-primary float-right button-150px">Preview</button>
				<a href="/" id="cancel"
					class="btn btn-primary float-right button-150px margin-right-30px">cancel</a>
			</div>
		</form:form>
	</div>
	<script type="text/javascript" src="js/patient-form.js"></script>
</body>
</html>