<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>답글작성</title>
</head>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/HierarchicalBoard.js"></script>
<body>
	<div>
		<div>
			<jsp:include page="/WEB-INF/views/top.jsp" flush="false" />
		</div>
		<form method="post" id="ReplyFrm">
			<div>
				<label for="BoardTitle">제목</label> 
				<input type="text" id="BoardTitle" name="BoardTitle" value="RE: ${boardReply.boardTitle}" readonly="readonly">
			</div>
			<div>
				<label for="BoardContent">내용</label>
				<textarea id="BoardContent" name="BoardContent"	placeholder="내용을 입력하세요" style="width: 300px; height: 300px;"></textarea>
			</div>
			<button type="button" id="ReplyProc" name="ReplyProc">등록</button>
			<input type="hidden" name="BoardNo" value="${boardReply.boardNo}">
			<input type="hidden" name="BoardGroupNo" value="${boardReply.boardGroupNo}"> 
			<input type="hidden" name="BoardIndent" value="${boardReply.boardIndent}">
		</form>
	</div>
</body>
</html>
