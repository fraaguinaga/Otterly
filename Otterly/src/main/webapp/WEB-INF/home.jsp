<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Otterly</title>
	<link rel="stylesheet" href="<c:url value="/css/homestyle.css"/>">
	</head>
	
	<body>
		<div class=container>
			<div class="head">
				<img class="user-pic" src="/images/otters-home.jpg">
				<div class="user-info">
					<p class="name">${user.name}</p>
					<p class="username">@${user.username}</p>
				</div>
				<form id="search" action="/home" method="POST">
					<input id="searchbar" type="text" name="searchstr" value="Search Otterly"/>
					<input id="searchbtn" type="submit" value="Go"/>
				</form>
				
				<form action="/logout">
					<button id="logout-btn">Logout</button>
				</form>
				<div class="hozline"></div>
			</div>
			
			<div class="col sides left">
				<form action="/_${user.username}">
					<button class="menu">Profile</button>
				</form>
				<form action="/settings">
					<button class="menu">Settings</button>
				</form>
			</div>
			
			<div class="col mid">
				
			</div>
			
			<div class="col sides right">
			
			</div>
		</div>	
	</body>
</html>