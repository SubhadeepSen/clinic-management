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


			<c:forEach var="consultation" items="${patientInfo.consultations}"
				varStatus="status">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#${consultation.dateOfVisit}_${status.index}">${consultation.dateOfVisit}</a>
							</h4>
						</div>
						<div id="${consultation.dateOfVisit}_${status.index}"
							class="panel-collapse collapse">
							<div class="panel-body">
								<label>Chief Complaint (C/C)</label>
								<ul>
									<c:forEach var="chiefComplaint"
										items="${consultation.chiefComplaints}">
										<li>${chiefComplaint}</li>
									</c:forEach>
								</ul>
								<label>On Examination (O/E)</label>
								<ul>
									<c:forEach var="onExamination"
										items="${consultation.onExaminations}">
										<li>${onExamination}</li>
									</c:forEach>
								</ul>
								<label>Medical History</label>
								<ul>
									<c:forEach var="medicalHistorie"
										items="${consultation.medicalHistories}">
										<li>${medicalHistorie}</li>
									</c:forEach>
								</ul>
								<label>Investigation</label>
								<ul>
									<c:forEach var="investigation"
										items="${consultation.investigations}">
										<li>${investigation}</li>
									</c:forEach>
								</ul>
								<label>Advice</label>
								<ul>
									<c:forEach var="advice" items="${consultation.advices}">
										<li>${advice}</li>
									</c:forEach>
								</ul>
								<label>Work Done</label>
								<ul>
									<c:forEach var="workDone" items="${consultation.workDones}">
										<li>${workDone}</li>
									</c:forEach>
								</ul>
								<label>Medicine</label>
								<ul>
									<c:forEach var="medicine" items="${consultation.medicines}">
										<li>${medicine}</li>
									</c:forEach>
								</ul>
								<label>Next Appointment Date</label>
								<p>${consultation.nextAppointmentDate}</p>
								<div class="margin-top-30px">
									<a
										href="/downloadInvoice?patientId=${patientInfo.patientId}&invoiceId=${consultation.invoice.invoiceId}&dateOfVisit=${consultation.dateOfVisit}"
										target="_blank" class="text-decoration-none"><span
										class="glyphicon glyphicon-new-window"></span> Download
										Prescription &amp; Invoice</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>