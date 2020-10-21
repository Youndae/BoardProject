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
	<div>
		<h2>아이디는 : ${sessionScope.userId}</h2>
	</div>
		<form action="/board/LoginProc" name="LoginForm" method="post">
			<div>
				<input type="text" name="userId" placeholder="아이디">
			</div>
			<div>
				<input type="password" name="userPw" placeholder="비밀번호">
			</div>
			<button type="submit">로그인</button>
		</form>
	</div>
</body>
</html>