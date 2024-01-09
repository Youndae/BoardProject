<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
<link rel="stylesheet" href="/css/nav.css">
</head>
<body>
	<nav class="navbar navbar-expand navbar-dark bg-dark mb-3">
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="/imageBoard/imageList">사진
						<span class="sr-only"></span>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/board/boardList">게시판</a>
				</li>
			</ul>
			<div class="form-inline my-2 my-md-0">

				<sec:authorize access="isAnonymous()">
					<li>
						<a class="nav-link" style="color:white;" href="/member/login">로그인</a>
					</li>
				</sec:authorize>

				<sec:authorize access="isAuthenticated()">
					<form id="logoutForm" action="/logout" method="post">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<li><a class="nav-link" style="color: white"><button style="all: unset">로그아웃</button></a></li>
					</form>
				</sec:authorize>
			</div>
		</div>
	</nav>
</body>
</html>