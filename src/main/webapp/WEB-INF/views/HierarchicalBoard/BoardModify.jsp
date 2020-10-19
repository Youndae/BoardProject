<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2020-10-17
  Time: 오후 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
		<form action="/BoardInsertProc" method="post" id="insertBoardFrm">
			<div>
				<label for="boardTitle">제목</label> 
				<input type="text" id="boardTitle" name="boardTitle" placeholder="제목을 입력하세요" value="${boardModify.boardTitle}">
			</div>
			<div>
				<label for="boardContent">내용</label>
				<textarea  id="boardContent" name="boardContent" placeholder="내용을 입력하세요" style="width: 300px; height: 300px;">${boardModify.boardTitle}</textarea>
			</div>
			<button id="insertButton" name="insertButton">등록</button>
		</form>
	</div>
</body>
</html>
