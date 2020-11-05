<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Title</title>
</head>
<script>
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href = "BoardList2?nowPage=${paging.nowPage}&cntPerPage="
				+ sel;
	}
</script>
<body>
	<div>
		<div>
			<jsp:include page="/WEB-INF/views/top.jsp" flush="false" />
		</div>
		<div>
			<select id="cntPerPage" name="sel" onchange="selChange()">
				<option value="5"
					<c:if test="${paging.cntPerPage == 5}">selected</c:if>>5줄보기</option>
				<option value="10"
					<c:if test="${paging.cntPerPage == 10}">selected</c:if>>10줄보기</option>
				<option value="15"
					<c:if test="${paging.cntPerPage == 15}">selected</c:if>>15줄보기</option>
				<option value="20"
					<c:if test="${paging.cntPerPage == 20}">selected</c:if>>20줄보기</option>
			</select>
		</div>

		<div>
			<h2>아이디는 : ${sessionScope.userId}</h2>
		</div>
		<table class="table table-hover" border="1">

			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>작성일</th>
			</tr>
			<c:forEach var="board" items="${viewAll}">
				<tr>
					<td>${board.boardNo}</td>
					<!-- 글번호 -->
					<td><a href="/board/BoardDetail?boardNo=${board.boardNo}">${board.boardTitle}</a>
					</td>
					<!-- 글제목 -->
					<td>${board.boardDate}</td>
					<!-- 작성일 -->
				</tr>
			</c:forEach>
		</table>
		<button onclick="location.href='/board/BoardInsert'">글작성</button>

		<div style="display: block; text-align: center;">
			<c:if test="${paging.startPage != 1 }">
				<a
					href="/board/BoardList2?nowPage=${paging.startpage - 1 }&cntPerPage=${paging.cntPerPage}">&lt</a>
			</c:if>
			<c:forEach begin="${paging.startPage}" end="${paging.endPage}"
				var="p">
				<c:choose>
					<c:when test="${p == paging.nowPage }">
						<b>${p }</b>
					</c:when>
					<c:when test="${p != paging.nowPage }">
						<a
							href="/board/BoardList2?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:if test="${paging.endPage != paging.lastPage}">
				<a
					href="/board/BoardList2?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}">&gt</a>
			</c:if>
		</div>
	</div>
</body>
</html>
