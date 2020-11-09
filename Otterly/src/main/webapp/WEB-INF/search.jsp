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
		<div class=container>
			<div class="head">
				<a href="/logout">Logout</a>
				<form id="search" action="/home" method="POST">
				<input type="text" name="searchstr" value="${searched}"/>
				<input type="submit" value="Go"/>
				</form>
			</div>
			
			<div>
				<table>
					<c:forEach items="${results}" var="result">
						<tr>
							<td><a href="/_${result.username}">${result.name}</a></td>
							<td>@${result.username}</td>
							<c:choose>
								<c:when test="${user.following.contains(result)}">
									<button class="unfollow">Unfollow</button>
								</c:when>
								<c:otherwise>
									<button class="follow">Follow</button>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>	
	</body>
</html>