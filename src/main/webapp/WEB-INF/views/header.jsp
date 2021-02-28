<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/">Dr. Sens Dental Clinic</a>
		</div>
		<c:if test="${not empty username}">
			<ul class="nav navbar-nav float-right">
				<li><a href="/logout">Logout</a></li>
			</ul>
		</c:if>
	</div>
</nav>