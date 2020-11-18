<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Register</title>
	<link rel="stylesheet" href="<c:url value="/css/registerstyle.css"/>">
	</head>
	
	<body>
		<div class="bg">
			<div class="split">
				<form:form class="regform" action="/_${user.username}/edit" method="POST" modelAttribute="user">
					<form:hidden path="id" value="${user.id}"></form:hidden>
					<form:hidden path="username" value="${user.username}"></form:hidden>
					<form:hidden path="password" value="${user.password}"></form:hidden>
					<form:hidden path="email" value="${user.email}"></form:hidden>
					<div class="formgrp">
						<p><form:errors path="name"/></p>
						<div class="label"><form:label path="name">Name</form:label></div>
						<form:input path="name"/>
					</div>
					<div class="formgrp">
						<p><form:errors path="bio"/></p>
						<div class="label"><form:label path="bio">Bio</form:label></div>
						<form:input path="bio"/>
					</div>
					<button id="reg-btn">Save</button>
				</form:form>
			</div>
			<div class="split">
				<img class="home-img" src="<c:url value="/images/otters-home.jpg"/>">	
			</div>
		</div>
	</body>
</html>