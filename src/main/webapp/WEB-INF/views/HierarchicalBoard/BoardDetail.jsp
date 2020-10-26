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
 <h1>아이디 : ${sessionScope.userId}</h1>
</div>
    <div>
    	<div>
    		<h2>${boardDetail.boardTitle}</h2>
    	</div>
		<div>
			<h3>${boardDetail.userId }</h3>
		</div>
    	<div>
    		${boardDetail.boardContent }
    	</div>
    </div>
    
    <button href='#' onclick="Reply()" value=1>답글</button>
    <c:set var="name" value="${sessionScope.userId}"/>
    <c:set var="id" value="${boardDetail.userId}"/>
    <c:if test="${name eq id }">
    	<button href='#' onclick="Modify()">수정</button>
    	<button href='#' onclick="DeleteBoard()">삭제</button>
    </c:if>
    <input type="hidden" id="boardNo" name="boardNo" value="${boardDetail.boardNo}">
</body>
</html>
