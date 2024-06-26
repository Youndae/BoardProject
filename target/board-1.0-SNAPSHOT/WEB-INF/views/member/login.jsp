<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/member.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/js/member.js"></script>
<body class="text-center">
	<div class="container">
		<div>
			<jsp:include page="/WEB-INF/views/navbar.jsp" flush="false" />
		</div>
		<div class="layer">
			<h1 style="margin-left: 60px; margin-bottom: 30px;">Login</h1>
			<form action="/login" id="loginForm" method="post">
				<div class="mb-2">
					<label style="margin-left:40px;">아이디</label>
					<input style="margin-left:40px;" type="text" id="userId" name="username" class="form-control" placeholder="아이디" required autofocus>
					<span style="margin-left:40px; color: red; font-size: 10pt;" id="NullId" ></span>
				</div>
				<div class="mb-2">
					<label style="margin-left:40px;">비밀번호</label>
					<input style="margin-left:40px;" type="password" id="userPw" name="password" class="form-control" placeholder="비밀번호" required>
					<span style="margin-left:40px; color: red; font-size: 10pt;" class="mb-5" id="nullPw"></span>
				</div>
				<div style="margin-left:50px;">
				<button class="btn btn-outline-info btn-sm" type="button" id="userLogin" name="userLogin">로그인</button>
				<button class="btn btn-outline-info btn-sm" onclick="location.href='/member/join'">회원가입</button>
				</div>
				<div>
					<c:set var="stat" value="${login}" />
					<c:if test="${stat == false}">
						<p style="margin-left:40px; color: red; font-size: 10pt;">로그인 실패!<br>아이디와 비밀번호를 확인해주세요.</p>
					</c:if>
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
		</div>
	</div>
			
			
		<%-- 	
			<div>
				<input type="text" id="UserId" name="UserId" placeholder="아이디">
				<div>
					<span id="NullId" style="color: red; font-size: 10pt;"></span>
				</div>
			</div>
			<div>
				<input type="password" id="UserPw" name="UserPw" placeholder="비밀번호">
				<div>
					<span id="NullPw" style="color: red; font-size: 10pt;"></span>
				</div>
			</div>
			<button class="btn btn-outline-info btn-sm" type="button" id="UserLogin" name="UserLogin">로그인</button>
		</form>
		<button class="btn btn-outline-info btn-sm" onclick="location.href='/board/Join'">회원가입</button>
		<div>
			<c:set var="stat" value="${login}" />
			<c:if test="${stat == false}">
				<span style="color: red; font-size: 10pt;">로그인 실패! 아이디와 비밀번호를 확인하세요.</span>
			</c:if>
		</div>
	</div> --%>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>