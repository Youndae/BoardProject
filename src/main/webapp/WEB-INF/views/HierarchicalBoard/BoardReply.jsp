<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>답글작성</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/HierarchicalBoard.js"></script>
<body>
	<div class="container">
		<div>
			<jsp:include page="/WEB-INF/views/navbar.jsp" flush="false" />
		</div>
		<form method="post" id="ReplyFrm">
			<div>
				<label for="BoardTitle">제목</label> 
				<input type="text" id="BoardTitle" name="BoardTitle" value="RE: ${boardReply.boardTitle}" readonly="readonly">
			</div>
			<div class="mb-4">
				<label for="BoardContent">내용</label>
				<textarea id="BoardContent" name="BoardContent"	placeholder="내용을 입력하세요" style="width: 300px; height: 300px;"></textarea>
			</div>
			<button class="btn btn-outline-info btn-sm" type="button" id="ReplyProc" name="ReplyProc">등록</button>
			<input type="hidden" name="BoardNo" value="${boardReply.boardNo}">
			<input type="hidden" name="BoardGroupNo" value="${boardReply.boardGroupNo}"> 
			<input type="hidden" name="BoardIndent" value="${boardReply.boardIndent}">
		</form>
	</div>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>
