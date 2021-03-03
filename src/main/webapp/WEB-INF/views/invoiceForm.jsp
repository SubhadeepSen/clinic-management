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
		<div class="row" id="errorMessageDiv" class="login-error-message text-center">
			<c:if test="${not empty workDoneError}">
				${workDoneError}
			</c:if>
			<c:if test="${not empty amountError}">
				${amountError}
			</c:if>
			<c:if test="${not empty totalAmountError}">
				${totalAmountError}
			</c:if>
		</div>
		<form:form action="/reviewInvoiceForm" method="POST"
			class="form-horizontal" modelAttribute="invoiceForm" id="invoiceform">
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
				<c:choose>
					<c:when test="${invoiceForm.workDoneAmounts.size() eq 0}">
						<div class="row invoiceRow margin-top-10px">
							<div class="col-sm-6 col-md-6 col-lg-6">
								<form:input path="workDoneAmounts['0'].workDone" type="text"
									name="workDoneAmounts0" class="form-control"
									id="workDoneAmounts" />
								<span
									class="custom-tootip glyphicon glyphicon-info-sign error-glyphicon hidden"
									data-toggle="tooltip"
									title="Work done information must be entered"
									data-placement="left"></span>
							</div>
							<div class="col-sm-5 col-md-5 col-lg-5">
								<form:input path="workDoneAmounts['0'].amount" type="text"
									name="workDoneAmounts0" class="form-control text-center"
									id="workDoneAmounts" value="0.0" />
								<span
									class="custom-tootip glyphicon glyphicon-info-sign error-glyphicon hidden"
									data-toggle="tooltip"
									title="Charged amount for this work must be entered"
									data-placement="left"></span>
							</div>
							<div class="col-sm-1 col-md-1 col-lg-1">
								<label class="invoice-add-row" id="add-invoice-row">+</label>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<c:forEach var="workDoneAmount"
							items="${invoiceForm.workDoneAmounts}" varStatus="status">
							<div class="row invoiceRow margin-top-10px">
								<div class="col-sm-6 col-md-6 col-lg-6">
									<form:input path="workDoneAmounts[${status.index}].workDone"
										type="text" name="workDone" class="form-control" id="workDone"
										value="${workDoneAmount.workDone}" />
									<span
										class="custom-tootip glyphicon glyphicon-info-sign error-glyphicon hidden"
										data-toggle="tooltip"
										title="Work done information must be entered"
										data-placement="left"></span>
								</div>
								<div class="col-sm-5 col-md-5 col-lg-5">
									<form:input path="workDoneAmounts[${status.index}].amount"
										type="text" name="amount" class="form-control text-center"
										id="amount" value="${workDoneAmount.amount}" />
									<span
										class="custom-tootip glyphicon glyphicon-info-sign error-glyphicon hidden"
										data-toggle="tooltip"
										title="Charged amount for this work must be entered"
										data-placement="left"></span>
								</div>
								<c:choose>
									<c:when
										test="${status.index eq invoiceForm.workDoneAmounts.size() - 1}">
										<div class="col-sm-1 col-md-1 col-lg-1">
											<label class="invoice-add-row" id="add-invoice-row">+</label>
											<c:if test="${status.index > 0}">
												<label class="invoice-remove-row" id="remove-invoice-row">-</label>
											</c:if>
										</div>
									</c:when>
									<c:otherwise>
										<c:if test="${status.index > 0}">
											<div class="col-sm-1 col-md-1 col-lg-1">
												<label class="invoice-remove-row" id="remove-invoice-row">-</label>
											</div>
										</c:if>
									</c:otherwise>
								</c:choose>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="row text-center margin-top-30px"
				id="invoiceTotalFooterDiv">
				<div class="col-sm-6 col-md-6 col-lg-6">
					<label>Total Amount in rupees:</label>
				</div>
				<div class="col-sm-5 col-md-5 col-lg-5">
					<form:input path="totalAmount" type="text" name="totalAmount"
						class="form-control text-center" id="totalAmount"
						value="${totalAmount}" readonly="true" />
				</div>
			</div>

			<div
				class="form-group margin-top-30px margin-bottom-100px margin-right-16per">
				<button type="submit" id="next"
					class="btn btn-primary float-right button-150px">Next</button>
				<a href="/" id="cancel"
					class="btn btn-primary float-right button-150px margin-right-30px">cancel</a>
			</div>
		</form:form>
	</div>
	<script type="text/javascript" src="js/invoice-form.js"></script>
</body>
</html>