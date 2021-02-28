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
		<div class="form-horizontal">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Patient Id</th>
						<th>Full Name</th>
						<th>Age</th>
						<th>Gender</th>
						<th>Address</th>
						<th>Phone No.</th>
						<th>Email Id</th>
						<th>Occupation</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${patientInfo.patientId}</td>
						<td>${patientInfo.personalInfo.fullName}</td>
						<td>${patientInfo.personalInfo.age}</td>
						<td>${patientInfo.personalInfo.gender.getValue()}</td>
						<td>${patientInfo.personalInfo.address}</td>
						<td>${patientInfo.personalInfo.phoneNumber}</td>
						<td>${patientInfo.personalInfo.emailId}</td>
						<td>${patientInfo.personalInfo.occupation}</td>
					</tr>
				</tbody>
			</table>


			<c:forEach var="consultation" items="${patientInfo.consultations}">
				<p>${consultation.dateOfVisit}</p>
				<c:forEach var="chiefComplaint"
					items="${consultation.chiefComplaints}">
					<p>${chiefComplaint}</p>
				</c:forEach>
				<c:forEach var="onExamination"
					items="${consultation.onExaminations}">
					<p>${onExamination}</p>
				</c:forEach>
				<c:forEach var="medicalHistorie"
					items="${consultation.medicalHistories}">
					<p>${medicalHistorie}</p>
				</c:forEach>
				<c:forEach var="investigation"
					items="${consultation.investigations}">
					<p>${investigation}</p>
				</c:forEach>
				<c:forEach var="advice" items="${consultation.advices}">
					<p>${advice}</p>
				</c:forEach>
				<c:forEach var="workDone" items="${consultation.workDones}">
					<p>${workDone}</p>
				</c:forEach>
				<c:forEach var="medicine" items="${consultation.medicines}">
					<p>${medicine}</p>
				</c:forEach>
				<p>${consultation.nextAppointmentDate}</p>
				<p>
					<a
						href="/downloadInvoice?invoiceId=${consultation.invoice.invoiceId}"
						target="_blank" class="text-decoration-none"><span class="glyphicon glyphicon-new-window"></span> Download
						Invoice</a>
				</p>
			</c:forEach>

		</div>
	</div>
</body>
</html>