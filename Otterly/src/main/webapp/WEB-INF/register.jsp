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
				<form:form class="regform" action="/" method="POST" modelAttribute="user">
					<div class="formgrp">
						<p><form:errors path="email"/></p>
						<div class="label"><form:label path="email">Email</form:label></div>
						<form:input type="email" path="email"/>
					</div>
					<div class="formgrp">
						<p><form:errors path="username"/></p>
						<div class="label"><form:label path="username">Username</form:label></div>
						<form:input path="username"/>
					</div>
					<div class="formgrp">
						<p><form:errors path="name"/></p>
						<div class="label"><form:label path="name">Name</form:label></div>
						<form:input path="name"/>
					</div>
					<div class="formgrp">
						<p><form:errors path="password"/></p>
						<div class="label"><form:label path="password">Password</form:label></div>
						<form:input type="password" path="password"/>
					</div>
					<div class="formgrp">
						<p><form:errors path="confirmPw"/></p>
						<div class="label"><form:label path="confirmPw">Confirm Password</form:label></div>
						<form:input type="password" path="confirmPw"/>
					</div>
					<button id="reg-btn">Register</button>
				</form:form>
			</div>
			<div class="split">
				<img class="home-img" src="<c:url value="/images/otters-home.jpg"/>">	
			</div>
		</div>
	</body>
</html>