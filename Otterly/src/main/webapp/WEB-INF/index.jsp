<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Otterly</title>
	</head>
	
	<body>
		<p>${loginError}</p>
		<form action="/login" method="POST">
			<label for="email">Email</label>
			<input type="email" name="email"/>
			
			<label for="pword">Password</label>
			<input type="password" name="pword"/>
			
			<input type="submit" value="Log In"/>
		</form>
		<button><a href="/register">Sign Up</a></button>
	</body>
</html>