<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>글 수정</title>
</head>
<body>
    <div>
		<form method="post" id="insertBoardFrm">
			<div>
				<label for="boardTitle">제목</label> 
				<input type="text" id="boardTitle" name="boardTitle" placeholder="제목을 입력하세요" value="${boardModify.boardTitle}">
			</div>
			<div>
				<label for="boardContent">내용</label>
				<textarea  id="boardContent" name="boardContent" placeholder="내용을 입력하세요" style="width: 300px; height: 300px;">${boardModify.boardTitle}</textarea>
			</div>
			<button href='#' onclick="ModifyProc()" id="insertButton" name="insertButton">등록</button>
			<input type="hidden" name="boardNo" value="${boardModify.boardNo}">
		</form>
	</div>
</body>
<script>
	function ModifyProc(){
		var form = document.getElementById("insertBoardFrm");
		
		form.action = "<c:url value='/BoardModifyProc' />";
		form.submit();
	}
</script>

</html>
