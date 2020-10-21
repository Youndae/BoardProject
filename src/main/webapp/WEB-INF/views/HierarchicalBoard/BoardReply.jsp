<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>답글작성</title>
</head>
<body>
    <div>
		<form method="post" id="ReplyFrm">
			<div>
				<label for="boardTitle">제목</label> 
				<input type="text" id="boardTitle" name="boardTitle" value="RE: ${boardReply.boardTitle}" readonly="readonly"> 
			</div>
			<div>
				<label for="boardContent">내용</label>
				<textarea  id="boardContent" name="boardContent" placeholder="내용을 입력하세요" style="width: 300px; height: 300px;"></textarea>
			</div>
			<button href='#' onclick="ReplyProc()" id="insertButton" name="insertButton">등록</button>
			<input type="hidden" name="boardNo" value="${boardReply.boardNo}">
			<input type="hidden" name="boardGroupNo" value="${boardReply.boardGroupNo}">
			<input type="hidden" name="boardIndent" value="${boardReply.boardIndent}">
		</form>
	</div>
</body>

<script>
	function ReplyProc(){
		var form = document.getElementById("ReplyFrm");
		
		form.action = "<c:url value='/BoardReplyProc'/>";
		form.submit();
	}
</script>
</html>
