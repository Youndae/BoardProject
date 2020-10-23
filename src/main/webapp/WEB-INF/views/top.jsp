<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
</head>
<body>
<div>
	<div>
	<c:if test="${not empty sessionScope}">
		<ul>
			<li><span>${sessionScope.userName}님</span>
			<li><a href="/board/Logout">로그아웃</a>
		</ul>
	</c:if>

	<c:if test="${empty sessionScope}">
		<ul>
			<li><a href="/board/Login">로그인</a>
		</ul>
	</c:if>
	</div>
	<br><br>
	<div>
		<a href="/board/ImageList">사진</a>
		<a href="/board/BoardList">게시판</a>
	</div>
	
</div>
</body>
</html>