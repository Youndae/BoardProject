<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<form action="JoinProc" id="JoinForm" method="post">
			<div>
				<input type="text" id="UserId" name="UserId" placeholder="아이디">
				<!-- <button type="button" onclick="IdCheck()">중복체크</button> -->
				<button type="button" id="IdCheck">중복체크</button>
				<div class="check" id="overlap" style="color: red; font-size: 10pt;"></div>
			</div>
			<div>
				<input type="password" id="UserPw" name="UserPw" placeholder="비밀번호">
			</div>
			<div>
				<input type="text" id="UserName" name="UserName" placeholder="이름">
			</div>
		</form>
		<!-- <button type="button" onclick="Join()">가입</button> -->
		<button type="button" id="Join">가입</button>
		<input type="hidden" id="check" name="check" value="">
	</div>
</body>
</html>