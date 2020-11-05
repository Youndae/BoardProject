<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>글 수정</title>
</head>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/HierarchicalBoard.js"></script>
<body>
    <div>
		<form method="post" id="insertBoardFrm">
			<div>
				<label for="boardTitle">제목</label> 
				<input type="text" id="BoardTitle" name="BoardTitle" placeholder="제목을 입력하세요" value="${boardModify.boardTitle}">
			</div>
			<div>
				<label for="boardContent">내용</label>
				<textarea  id="BoardContent" name="BoardContent" placeholder="내용을 입력하세요" style="width: 300px; height: 300px;">${boardModify.boardContent}</textarea>
			</div>
			<!-- <button href='#' onclick="ModifyProc()" id="insertButton" name="insertButton">등록</button> -->
			<button type="button" id="ModifyProc" name="ModifyProc">등록</button>
			<input type="hidden" name="BoardNo" value="${boardModify.boardNo}">
		</form>
	</div>
</body>
<!-- <script>
	function ModifyProc(){
		var form = document.getElementById("insertBoardFrm");
		
		form.action = "<c:url value='/BoardModifyProc' />";
		form.submit();
	}
</script> -->

</html>
