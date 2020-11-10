<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>게시판 글 작성</title>
</head>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/HierarchicalBoard.js"></script>
<body>
	<div>
		<div>
			<jsp:include page="/WEB-INF/views/top.jsp" flush="false" />
		</div>
		<form method="post" id="insertBoardFrm" action="BoardInsertProc">
			<div>
				<label for="boardTitle">제목</label> <input type="text" id="boardTitle" name="boardTitle" placeholder="제목을 입력하세요">
			</div>
			<div>
				<label for="boardContent">내용</label>
				<textarea id="boardContent" name="boardContent"	placeholder="내용을 입력하세요" style="width: 300px; height: 300px;"></textarea>
			</div>
			<button type="button" id="insertBoard" name="insertBoard">등록</button>
		</form>
	</div>
</body>
</html>
