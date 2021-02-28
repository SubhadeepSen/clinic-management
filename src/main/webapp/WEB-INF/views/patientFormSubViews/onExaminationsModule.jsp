<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${patientForm.onExaminations.size() eq 0}">
		<div class="row">
			<div class="form-group">
				<label class="control-label col-sm-2" for="onExaminations">On
					Examination (O/E):</label>
				<div class="col-sm-8">
					<form:input path="onExaminations" type="text" class="form-control"
						id="onExaminations" value="" />
				</div>
				<div>
					<label class="add-row" id="add-on-examination-row">+</label>
				</div>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<c:forEach var="onExamination" items="${patientForm.onExaminations}"
			varStatus="counter">
			<div class="row">
				<div class="form-group">
					<c:choose>
						<c:when test="${counter.count eq 1}">
							<label class="control-label col-sm-2" for="onExaminations">On
								Examination (O/E):</label>
						</c:when>
						<c:otherwise>
							<label class="control-label col-sm-2" for="onExaminations"></label>
						</c:otherwise>
					</c:choose>
					<div class="col-sm-8">
						<form:input path="onExaminations" type="text" class="form-control"
							id="onExaminations" value="${onExamination}" />
					</div>
					<c:choose>
						<c:when
							test="${counter.count eq patientForm.onExaminations.size()}">
							<div>
								<label class="add-row" id="add-on-examination-row">+</label>
								<c:if test="${counter.count > 1}">
									<label class="remove-row" id="remove-on-examination-row">-</label>
								</c:if>
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<c:if test="${counter.count > 1}">
									<label class="remove-row" id="remove-on-examination-row">-</label>
								</c:if>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</c:forEach>
	</c:otherwise>
</c:choose>