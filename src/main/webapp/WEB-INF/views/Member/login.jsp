<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="/board/LoginProc" name="LoginForm" method="post">
			<div>
				<input type="text" name="userId" placeholder="아이디">
			</div>
			<div>
				<input type="password" name="userPw" placeholder="비밀번호">
			</div>
			<button type="submit">로그인</button>
		</form>
		<button onclick="location.href='/board/Join'">회원가입</button>
	</div>
</body>
</html>