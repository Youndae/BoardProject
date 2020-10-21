<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
    	<div>
    		<h2>아이디는 : ${sessionScope.userId}</h2>
    	</div>
        <table class="table table-hover" border="1">

            <tr>
                <th>글번호</th>
                <th>글제목</th>
                <th>작성자</th>
                <th>작성일</th>
            </tr>
            <c:forEach var="board" items="${boardList}">
            <tr>
                <td>${board.boardNo}</td><!-- 글번호 -->
                <td>
                    <a href="/board/BoardDetail?boardNo=${board.boardNo}">${board.boardTitle}</a>
                </td><!-- 글제목 -->
                <td>${board.userId}</td><!-- 작성자 -->
                <td>${board.boardDate}</td><!-- 작성일 -->
            </tr>
            </c:forEach>
        </table>
    </div>
    <button onclick="location.href='/board/BoardInsert'">글작성</button>
</body>
</html>
