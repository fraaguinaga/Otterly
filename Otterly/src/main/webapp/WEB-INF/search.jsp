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
					<input id="searchbar" type="text" name="searchstr" value="${searched}"/>
					<input id="searchbtn" type="submit" value="Go"/>
				</form>
				
				<form action="/logout">
					<button id="logout-btn">Logout</button>
				</form>
				<div class="hozline"></div>
			</div>
			
			<div class="col sides left">
				<form class="menu" action="/_${user.username}">
					<button class="menu">Profile</button>
				</form>
				<form class="menu" action="/settings">
					<button class="menu">Settings</button>
				</form>
				<form class="menu" action="/">
					<button class="menu">Home</button>
				</form>
			</div>
			
			<div class="col mid">
				<table class="center">
					<c:forEach items="${results}" var="result">
						<tr>
							<td  class="result-bar">
								<img class="user-pic" src="/images/otters-home.jpg">
								<div class="user-info">
									<p class="name"><a href="/_${result.username}">${result.name}</a></p>
									<p class="username">@${result.username}</p>
								</div>
							
								<c:choose>
									<c:when test="${user.following.contains(result)}">
										<form class="search-btn" action="/_${result.username}/uf" method="POST">
											<button class="profile-btn search-btn">Unfollow</button>
										</form>
									</c:when>
									<c:otherwise>
										<form class="search-btn" action="/_${result.username}/f" method="POST">
											<button class="profile-btn search-btn">Follow</button>
										</form>
									</c:otherwise>
								</c:choose>
								
								<p class="postcont name ">${result.bio}</p>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
			<div class="col sides right">
				<form class="menu" action="/create">
					<button class="menu">Create Post</button>
				</form>
			</div>
			
		</div>	
	</body>
</html>