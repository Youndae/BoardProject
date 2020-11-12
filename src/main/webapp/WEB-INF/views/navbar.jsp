<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
<style type="text/css">
li {
	list-style: none;
	float: left;
	padding: 6px;
}
</style>
</head>
<body>

	<nav class="navbar navbar-expand navbar-dark bg-dark mb-3">
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="/board/ImageList">사진
						<span class="sr-only"></span>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/board/BoardList">게시판</a>
				</li>
			</ul>
			<div class="form-inline my-2 my-md-0">
				<c:if test="${not empty sessionScope}">
				<ul>
					<li>
						<a class="nav-link" style="color:white;" href="/board/Logout">로그아웃</a>
					</li>
				</ul>
				</c:if>
				<c:if test="${empty sessionScope}">
					<li>
						<a class="nav-link" style="color:white;" href="/board/Login">로그인</a>
					</li>
			</c:if>
			</div>
		</div>
	</nav>
</body>
</html>