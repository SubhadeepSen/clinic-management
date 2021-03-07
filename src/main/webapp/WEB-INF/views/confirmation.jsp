<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
	<div class="container text-center margin-top-100px">
		<p class="font-size-30px">Thank you.</p>
		<p class="font-size-20px">
			Record has been successfully processed with Patient ID <i><b>${patientId}</b></i><br>
			and Invoice ID <i><b>${invoiceId}</b></i>
		</p>
		<div
			class="form-group margin-top-30px margin-bottom-100px text-center">
			<a href="/" id="goToHome"
				class="btn btn-primary button-150px margin-right-30px">Go to
				home</a> <a href="#" id="download"
				class="btn btn-primary button-150px margin-right-30px">Download</a>
		</div>
		<p id="patientId" value="${patientId}" class="hidden"></p>
		<p id="invoiceId" value="${invoiceId}" class="hidden"></p>
		<p id="dateOfVisit" value="${dateOfVisit}" class="hidden"></p>
	</div>
	<script type="text/javascript" src="js/confirmation.js"></script>
</body>
</html>