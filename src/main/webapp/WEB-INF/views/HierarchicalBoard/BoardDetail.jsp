<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
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
    <button id="boardModify" name="boardModify">수정</button>
    <button id="boardDelete" name="boardDelete">삭제</button>
</body>
</html>
