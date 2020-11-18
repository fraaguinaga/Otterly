<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>${profileUser.name}</title>
		<link rel=stylesheet href="<c:url value="/css/homestyle.css"/>">
	</head>
	
	<body>
		<img id="banner" src="/images/otters-home.jpg">
		<div class="container">
			<div class="head">
				<img class="user-pic" src="/images/otters-home.jpg">
				<div class="user-info">
					<p class="name">${profileUser.name}</p>
					<p class="username">@${profileUser.username}</p>
				</div>
				<div class="user-info">
					<c:choose>
						<c:when test="${profileUser.following.contains(user) }">
							<p class="name">|follows you</p>
						</c:when>
						<c:otherwise>
							<p class="name">|</p>
						</c:otherwise>
					</c:choose>
					<p class="username">|${profileUser.following.size()} following ${profileUser.followers.size()} followers</p>
				</div>
			
				<c:choose>
					<%--If user is looking own profile shows edit button --%>
					<c:when test="${user.equals(profileUser)}">
						<form action="/_${user.username}/edit">
							<button class="profile-btn profile-btn-edit">Edit</button>
						</form>
					</c:when>
					<c:otherwise>
						<%-- Decides to display follow or unfollow button --%>
						<c:choose>
							<c:when test="${user.following.contains(profileUser)}">
								<form action="/_${profileUser.username}/uf" method="POST">
									<button class="profile-btn profile-btn-right">Unfollow</button>
								</form>
							</c:when>
							<c:otherwise>
								<form action="/_${profileUser.username}/f" method="POST">
									<button class="profile-btn profile-btn-right">Follow</button>
								</form>
							</c:otherwise>
						</c:choose>
						<button class="profile-btn">Message</button>
					</c:otherwise>
				</c:choose>
				<p class="bio">${profileUser.bio}</p>
			</div>
			
			<div class="col sides left">
				<form class="menu" action="/_${profileUser.username}">
					<button class="menu">Posts</button>
				</form>
				<form class="menu" action="/_${profileUser.username}/likes">
					<button class="menu">Likes</button>
				</form>
				<form class="menu" action="/">
					<button class="menu">Home</button>
				</form>
			</div>
			
			<div class="col mid">
				
			</div>
			
			<div class="col sides right">
			
			</div>
		</div>
	</body>
</html>