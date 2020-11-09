<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Otterly</title>
	<link rel="stylesheet" href="<c:url value="/css/indexstyle.css" />">
	</head>
	
	<body>
		<div class="bg">
			<div class="split">
				<form action="/login" method="POST">
					<div>
						<p class="login"><label for="email">Email</label></p>
						<p class="login"><label for="pword">Password</label></p>
					</div>
					<div>
						<input type="email" name="email"/>
						<input type="password" name="pword"/>
						<p id="error">${loginError}</p>
					</div>
					<button id="login-btn">Log In</button>
				</form>
				<form class="mid" action="/register">
					<p id="join">Join Otterly Today!</p>
					<button id="signup-btn">Sign Up!</button>
				</form>
				<p class="mid" id="slogan">See your interests Stay in the know Speak your mind</p>
			</div>
			<div class="split">
				<img class="home-img" src="<c:url value="/images/otters-home.jpg"/>">	
			</div>
		</div>		
	</body>
</html>