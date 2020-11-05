<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>답글작성</title>
</head>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/HierarchicalBoard.js"></script>
<body>
    <div>
		<form method="post" id="ReplyFrm">
			<div>
				<label for="BoardTitle">제목</label> 
				<input type="text" id="BoardTitle" name="BoardTitle" value="RE: ${boardReply.boardTitle}" readonly="readonly"> 
			</div>
			<div>
				<label for="BoardContent">내용</label>
				<textarea  id="BoardContent" name="BoardContent" placeholder="내용을 입력하세요" style="width: 300px; height: 300px;"></textarea>
			</div>
			<!-- <button href='#' onclick="ReplyProc()" id="insertButton" name="insertButton">등록</button> -->
			<button type="button" id="ReplyProc" name="ReplyProc">등록</button>
			<input type="hidden" name="BoardNo" value="${boardReply.boardNo}">
			<input type="hidden" name="BoardGroupNo" value="${boardReply.boardGroupNo}">
			<input type="hidden" name="BoardIndent" value="${boardReply.boardIndent}">
		</form>
	</div>
</body>

<!-- <script>
	function ReplyProc(){
		var form = document.getElementById("ReplyFrm");
		
		form.action = "<c:url value='/BoardReplyProc'/>";
		form.submit();
	}
</script> -->
</html>
