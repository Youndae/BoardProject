<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/Member.js"></script>
<body>
	<div>
		<div>
			<jsp:include page="/WEB-INF/views/top.jsp" flush="false" />
		</div>
		<form action="LoginProc" id="LoginForm" method="post">
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
			<button type="button" id="UserLogin" name="UserLogin">로그인</button>
		</form>
		<button onclick="location.href='/board/Join'">회원가입</button>
		<div>
			<c:set var="stat" value="${login}" />
			<c:if test="${stat == false}">
				<span style="color: red; font-size: 10pt;">로그인 실패! 아이디와 비밀번호를 확인하세요.</span>
			</c:if>
		</div>
	</div>
</body>
</html>