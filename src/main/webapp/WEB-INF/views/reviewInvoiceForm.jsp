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
	<div class="container">
		<form action="/confirmation" method="post">
			<div class="form-horizontal">
				<div class="row">
					<div class="form-group">
						<label class="control-label col-sm-2" for="fullName">Full
							Name:</label>
						<div class="col-sm-3">
							<div class="form-control readonly">${invoiceForm.fullName}</div>
						</div>
						<label class="control-label col-sm-1" for="age">Age:</label>
						<div class="col-sm-1">
							<div class="form-control readonly">${invoiceForm.age}</div>
						</div>
						<label class="control-label col-sm-1" for="gender">Gender:</label>
						<div class="col-sm-2">
							<label class="radio-inline"> <input type="radio"
								name="gender" value="male" disabled
								<c:if test = "${invoiceForm.gender eq 'male' }">checked="true"</c:if> />Male
							</label> <label class="radio-inline"> <input type="radio"
								name="gender" value="female" disabled
								<c:if test = "${invoiceForm.gender eq 'female' }">checked="true"</c:if> />Female
							</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="control-label col-sm-2" for="address">Address:</label>
						<div class="col-sm-8">
							<div class="form-control readonly">${invoiceForm.address}</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="control-label col-sm-2" for="phoneNumber">Phone
							No.:</label>
						<div class="col-sm-3">
							<div class="form-control readonly">${invoiceForm.phoneNumber}</div>
						</div>
						<label class="control-label col-sm-2" for="emailId">Email
							Id:</label>
						<div class="col-sm-3">
							<div class="form-control readonly">${invoiceForm.emailId}</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="control-label col-sm-2" for="occupation">Occupation:</label>
						<div class="col-sm-3">
							<div class="form-control readonly">${invoiceForm.occupation}</div>
						</div>
						<label class="control-label col-sm-2" for="dateOfVisit">Date
							Of Visit:</label>
						<div class="col-sm-3">
							<div class="form-control readonly">${invoiceForm.dateOfVisit}</div>
						</div>
					</div>
				</div>

				<div class="row text-center margin-top-30px"
					id="invoiceTableHeaderDiv">
					<div class="col-sm-6 col-md-6 col-lg-6">
						<label>Work Done</label>
					</div>
					<div class="col-sm-5 col-md-5 col-lg-5">
						<label>Amount (Rs./-)</label>
					</div>
				</div>
				<div id="invoiceParentDiv">
					<c:forEach var="workDoneAmount"
						items="${invoiceForm.workDoneAmounts}">
						<div class="row invoiceRow margin-top-10px">
							<div class="col-sm-6 col-md-6 col-lg-6">
								<div class="form-control readonly">${workDoneAmount.workDone}</div>
							</div>
							<div class="col-sm-5 col-md-5 col-lg-5">
								<div class="form-control readonly text-center">${workDoneAmount.amount}</div>
							</div>
						</div>
					</c:forEach>
				</div>

				<div class="row text-center margin-top-30px" id="invoiceTotalDiv">
					<div class="col-sm-6 col-md-6 col-lg-6">
						<label>Total Amount in rupees:</label>
					</div>
					<div class="col-sm-5 col-md-5 col-lg-5">
						<div class="form-control readonly">${invoiceForm.totalAmount}</div>
					</div>
				</div>

				<div
					class="form-group margin-top-30px margin-bottom-100px margin-right-16per">
					<button type="submit" id="submit"
						class="btn btn-primary float-right button-150px">Submit</button>
					<button type="button" id="back"
						class="btn btn-primary float-right button-150px margin-right-30px">Back</button>
					<a href="/" id="cancel"
						class="btn btn-primary float-right button-150px margin-right-30px">cancel</a>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="js/review-invoice-form.js"></script>
</body>
</html>