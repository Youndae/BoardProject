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
		<h2>아이디는: ${sessionScope.userId}</h2>
	</div>
		<form action="/board/JoinProc" method="post">
			<div>
				<input type="text" name="userId" placeholder="아이디">
			</div>
			<div>
				<input type="password" name="userPw" placeholder="비밀번호">
			</div>
			<div>
				<input type="text" name="userName" placeholder="이름">
			</div>
			<button type="submit">가입</button>
		</form>
	</div>
</body>
</html>