<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
	<div class="container form-horizontal">
		<form action="/invoiceForm" method="get">
			<div class="row">
				<div class="form-group">
					<label class="control-label col-sm-2" for="fullName">Full
						Name:</label>
					<div class="col-sm-3">
						<div class="form-control readonly">${patientForm.fullName}</div>
					</div>
					<label class="control-label col-sm-1" for="age">Age:</label>
					<div class="col-sm-1">
						<div class="form-control readonly">${patientForm.age}</div>
					</div>
					<label class="control-label col-sm-1" for="gender">Gender:</label>
					<div class="col-sm-2">
						<label class="radio-inline"> <input type="radio"
							name="gender" value="male" disabled
							<c:if test = "${patientForm.gender eq 'male' }">checked="true"</c:if> />Male
						</label> <label class="radio-inline"> <input type="radio"
							name="gender" value="female" disabled
							<c:if test = "${patientForm.gender eq 'female' }">checked="true"</c:if> />Female
						</label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<label class="control-label col-sm-2" for="address">Address:</label>
					<div class="col-sm-8">
						<div class="form-control readonly">${patientForm.address}</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<label class="control-label col-sm-2" for="phoneNumber">Phone
						No.:</label>
					<div class="col-sm-3">
						<div class="form-control readonly">${patientForm.phoneNumber}</div>
					</div>
					<label class="control-label col-sm-2" for="emailId">Email
						Id:</label>
					<div class="col-sm-3">
						<div class="form-control readonly">${patientForm.emailId}</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<label class="control-label col-sm-2" for="occupation">Occupation:</label>
					<div class="col-sm-3">
						<div class="form-control readonly">${patientForm.occupation}</div>
					</div>
					<label class="control-label col-sm-2" for="dateOfVisit">Date
						Of Visit:</label>
					<div class="col-sm-3">
						<div class="form-control readonly">${patientForm.dateOfVisit}</div>
					</div>
				</div>
			</div>
			<div id="chiefComplaintsParentDiv">
				<%@include
					file="reviewPatientFormSubViews/chiefComplaintsModule.jsp"%>
			</div>
			<div id="onExaminationsParentDiv">
				<%@include file="reviewPatientFormSubViews/onExaminationsModule.jsp"%>
			</div>
			<div id="medicalHistoriesParentDiv">
				<%@include
					file="reviewPatientFormSubViews/medicalHistoriesModule.jsp"%>
			</div>
			<div id="investigationsParentDiv">
				<%@include file="reviewPatientFormSubViews/investigationsModule.jsp"%>
			</div>
			<div id="advicesParentDiv">
				<%@include file="reviewPatientFormSubViews/advicesModule.jsp"%>
			</div>
			<div id="workDonesParentDiv">
				<%@include file="reviewPatientFormSubViews/workDonesModule.jsp"%>
			</div>
			<div id="medicinesParentDiv">
				<%@include file="reviewPatientFormSubViews/medicinesModule.jsp"%>
			</div>
			<div class="row">
				<div class="form-group">
					<label class="control-label col-sm-2" for="nextAppointmentDate">Next
						Appointment Date:</label>
					<div class="col-sm-3">
						<div class="form-control readonly">${patientForm.nextAppointmentDate}</div>
					</div>
				</div>
			</div>
			<div
				class="form-group margin-top-30px margin-bottom-100px margin-right-16per">
				<button type="submit" id="next"
					class="btn btn-primary float-right button-150px">Next</button>
				<button type="button" id="back"
					class="btn btn-primary float-right button-150px margin-right-30px">Back</button>
				<a href="/" id="cancel"
					class="btn btn-primary float-right button-150px margin-right-30px">cancel</a>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="js/review-patient-form.js"></script>
</body>
</html>