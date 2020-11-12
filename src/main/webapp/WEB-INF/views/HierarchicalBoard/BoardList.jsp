<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Title</title>
<style type="text/css">
li {
	list-style: none;
	float: left;
	padding: 6px;
}
</style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/HierarchicalBoard.js"></script>
<body>
	<div class="container">
		<div>
			<jsp:include page="/WEB-INF/views/navbar.jsp" flush="false" />
		</div>
		<div class="form-row float-right mb-1">
			<c:set var="name" value="${sessionScope.userId}"/>
			<c:if test="${name eq null }">
				<button class="btn btn-outline-info btn-sm" onclick="location.href='/board/Login'">글작성</button>
			</c:if>
			<c:if test="${name ne null}">
				<button class="btn btn-outline-info btn-sm" onclick="location.href='/board/BoardInsert'">글작성</button>
			</c:if>
		</div>
		<form role="form" method="get">
			<table class="table table-hover" border="1">
				<tr>
					<th>글번호</th>
					<th>글제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
				<c:forEach var="board" items="${list}">
					<tr>
						<td>${board.boardNo}</td>
						<c:choose>
							<c:when test="${board.boardIndent == 1}">
								<td>
									<span style="text-align:left; margin-left: 12px;"> 
										<a href="/board/BoardDetail?BoardNo=${board.boardNo}">${board.boardTitle}</a>
									</span>
								</td>
							</c:when>
							<c:when test="${board.boardIndent == 2}">
								<td>	
									<span style="text-align:left; margin-left: 24px;"> 
										<a href="/board/BoardDetail?BoardNo=${board.boardNo}">${board.boardTitle}</a>
									</span>
								</td>
							</c:when>
							<c:when test="${board.boardIndent == 3}">
								<td>
									<span style="text-align:left; margin-left: 36px;"> 
										<a href="/board/BoardDetail?BoardNo=${board.boardNo}">${board.boardTitle}</a>
									</span>
								</td>
							</c:when>
							<c:when test="${board.boardIndent == 4}">
								<td>
									<span style="text-align:left; margin-left: 48px;"> 
										<a href="/board/BoardDetail?BoardNo=${board.boardNo}">${board.boardTitle}</a>
									</span>
								</td>
							</c:when>
							<c:otherwise>
								<td>
									<span style="text-align:left; margin-left: 0px;"> 
										<a href="/board/BoardDetail?BoardNo=${board.boardNo}">${board.boardTitle}</a>
									</span>
								</td>
							</c:otherwise>
						</c:choose>
						<td>${board.userId}</td>
						<td>${board.boardDate}</td>
					</tr>
				</c:forEach>
			</table>
			
			<div class="search">
				<select name="searchType">
					<option value="t"
						<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
					<option value="c"
						<c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
					<option value="w"
						<c:out value="${scri.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
					<option value="tc"
						<c:out value="${scri.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>
				</select> 
				<input type="text" name="keyword" id="keywordInput" value="${scri.keyword}" />
				<button class="btn btn-outline-info btn-sm" id="searchBtn" type="button">검색</button>
				<script>
					$(function() {
						$('#searchBtn').click(function() {
									self.location = "/board/BoardList"
											+ '${pageMaker.makeQuery(1)}'
											+ "&searchType="
											+ $("select option:selected").val()
											+ "&keyword="
											+ encodeURIComponent($(
													'#keywordInput').val());
						});
					});
				</script>	
			</div>
			<div>
				<ul class="pagination justify-content-center">
					<c:if test="${pageMaker.prev}">
						<li>
							<a href="/board/BoardList${pageMaker.makeSearch(pageMaker.startPage - 1)}">◀</a>
						</li>
					</c:if>
					<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
						<li>
							<a href="/board/BoardList${pageMaker.makeSearch(idx)}">${idx}</a>
						</li>
					</c:forEach>
					<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
						<li>
							<a href="/board/BoardList${pageMaker.makeSearch(pageMaker.endPage + 1)}">▶</a>
						</li>
					</c:if>
				</ul>
			</div>
		</form>
	</div>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>
