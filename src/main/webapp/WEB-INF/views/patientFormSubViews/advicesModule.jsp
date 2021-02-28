<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${patientForm.advices.size() eq 0}">
		<div class="row">
			<div class="form-group">
				<label class="control-label col-sm-2" for="advices">Advice:</label>
				<div class="col-sm-8">
					<form:input path="advices" type="text" class="form-control"
						id="advices" value="" />
				</div>
				<div>
					<label class="add-row" id="add-advice-row">+</label>
				</div>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<c:forEach var="advice" items="${patientForm.advices}"
			varStatus="counter">
			<div class="row">
				<div class="form-group">
					<c:choose>
						<c:when test="${counter.count eq 1}">
							<label class="control-label col-sm-2" for="advices">Advice:</label>
						</c:when>
						<c:otherwise>
							<label class="control-label col-sm-2" for="advices"></label>
						</c:otherwise>
					</c:choose>
					<div class="col-sm-8">
						<form:input path="advices" type="text"
							class="form-control" id="advices"
							value="${advice}" />
					</div>
					<c:choose>
						<c:when
							test="${counter.count eq patientForm.advices.size()}">
							<div>
								<label class="add-row" id="add-advice-row">+</label>
								<c:if test="${counter.count > 1}">
									<label class="remove-row" id="remove-advice-row">-</label>
								</c:if>
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<c:if test="${counter.count > 1}">
									<label class="remove-row" id="remove-advice-row">-</label>
								</c:if>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</c:forEach>
	</c:otherwise>
</c:choose>