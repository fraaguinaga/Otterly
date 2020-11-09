<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>${profileUser.name}</title>
	</head>
	
	<body>
		<h1>${profileUser.name}</h1>
		<c:choose>
			<c:when test="${user.following.contains(profileUser)}">
				<form action="/_${profileUser.username}/uf" method="POST">
					<button class="unfollow">Unfollow</button>
				</form>
			</c:when>
			<c:otherwise>
				<form action="/_${profileUser.username}/f" method="POST">
					<button class="follow">Follow</button>
				</form>
			</c:otherwise>
		</c:choose>
	</body>
</html>