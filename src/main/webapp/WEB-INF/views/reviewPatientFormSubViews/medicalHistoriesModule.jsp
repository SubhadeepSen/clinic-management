<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${patientForm.medicalHistories.size() eq 0}">
		<div class="row">
			<div class="form-group">
				<label class="control-label col-sm-2" for="medicalHistories">Medical
					History:</label>
				<div class="col-sm-8">
					<div class="form-control readonly"></div>
				</div>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<c:forEach var="medicalHistory"
			items="${patientForm.medicalHistories}" varStatus="counter">
			<div class="row">
				<div class="form-group">
					<c:choose>
						<c:when test="${counter.count eq 1}">
							<label class="control-label col-sm-2" for="medicalHistories">Medical
								History:</label>
						</c:when>
						<c:otherwise>
							<label class="control-label col-sm-2" for="medicalHistories"></label>
						</c:otherwise>
					</c:choose>
					<div class="col-sm-8">
						<div class="form-control readonly">${medicalHistory}</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</c:otherwise>
</c:choose>