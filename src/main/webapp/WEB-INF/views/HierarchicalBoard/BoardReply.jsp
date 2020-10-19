<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>답글작성</title>
</head>
<body>
    <div>
		<form action="/BoardInsertProc" method="post" id="insertBoardFrm">
			<div>
				<label for="boardTitle">RE: ${boardReply.boardTitle}</label> 
				<input type="text" id="boardTitle" name="boardTitle" placeholder="제목을 입력하세요">
			</div>
			<div>
				<label for="boardContent">내용</label>
				<textarea  id="boardContent" name="boardContent" placeholder="내용을 입력하세요" style="width: 300px; height: 300px;"></textarea>
			</div>
			<button id="insertButton" name="insertButton">등록</button>
		</form>
	</div>
</body>
</html>
