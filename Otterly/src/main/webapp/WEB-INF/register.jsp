<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Register</title>
	</head>
	
	<body>
		<div>
			<form:form action="/" method="POST" modelAttribute="user">
				<p>
					<form:label path="email">Email</form:label>
					<form:errors path="email"/>
					<form:input type="email" path="email"/>
				</p>
				<p><form:errors path="username"/></p>
				<p>
					<form:label path="username">Username</form:label>
					<form:input path="username"/>
				</p>
				<p>
					<form:label path="name">Name</form:label>
					<form:errors path="name"/>
					<form:input path="name"/>
				</p>
				<p>
					<form:label path="password">Password</form:label>
					<form:errors path="password"/>
					<form:input type="password" path="password"/>
				</p>
				<p>
					<form:label path="confirmPw">Confirm Password</form:label>
					<form:errors path="confirmPw"/>
					<form:input type="password" path="confirmPw"/>
				</p>
				<button>Register</button>
			</form:form>
		</div>
	</body>
</html>