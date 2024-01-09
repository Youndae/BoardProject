<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<meta name="_csrf" content="${_csrf.token}">
	<meta name="_csrf_header" content="${_csrf.headerName}">
<link rel="stylesheet" href="/css/member.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/js/member.js"></script>
<body>
	<div class="container">
		<div>
			<jsp:include page="/WEB-INF/views/navbar.jsp" flush="false" />
		</div>
		
		<div class="layer">
		
		<h1 style="margin-left: 100px; margin-bottom: 30px;">회원가입</h1>
		
		<form action="joinProc" id="joinForm" method="post">
			<div class="mb-2">
				<label >아이디</label>
				
				<input style="margin-left:30px;" type="text" id="userId" name="userId" placeholder="아이디">
				<button class="btn btn-outline-info btn-sm" type="button" id="idCheck">중복체크</button>
				<div class="check" id="overlap" style="color: red; font-size: 10pt;"></div>
			</div>
			<div class="mb-2">
				<label class="mr-3 mb-2">비밀번호</label>
				<input type="password" id="userPw" name="userPw" placeholder="비밀번호">
			</div>
			<div class="mb-2">
				<label class="mr-5 mb-2">이름</label>
				<input type="text" id="userName" name="userName" placeholder="이름">
			</div>
			<sec:csrfInput/>
		</form>
		<button style="margin-left: 130px;" class="btn btn-outline-info btn-sm" type="button" id="join">가입</button>
		<input type="hidden" id="check" name="check" value="">
		</div>
	</div>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>