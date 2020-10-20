<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<%-- <script src="<c:url value="/resources/js/HierarchicalBoard.js" />"></script> --%>
	<script type="text/javascript" src="./js/HierarchicalBoard.js"></script>
	
    <title>${boardDetai.boardTitle}</title>
</head>
<body>
    <div>
    	<h2>${boardDetail.boardTitle}</h2>
    	<br>
    	<br>
    	<h3>${boardDetail.userId }</h3>
    	<br>
    	<br>
    	${boardDetail.boardContent }
    	<br>
    	<br>
    </div>
    
    <button href='#' onclick="Reply()">답글</button>
    <button href='#' onclick="Modify()">수정</button>
    <button href='#' onclick="DeleteBoard()">삭제</button>
    <input type="hidden" id="boardNo" name="boardNo" value="${boardDetail.boardNo}">
</body>
</html>
