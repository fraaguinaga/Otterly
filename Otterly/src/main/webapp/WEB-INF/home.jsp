<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Otterly</title>
	</head>
	
	<body>
		<div class=container>
			<div class="head">
				<h1>Welcome ${user.username}</h1>
				<a href="/logout">Logout</a>
				<form id="search" action="/home" method="POST">
				<input type="text" name="searchstr" value="Search Otterly"/>
				<input type="submit" value="Go"/>
				</form>
			</div>
		</div>	
	</body>
</html>