<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>Title</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/js/paging.js"></script>
<script type="text/javascript" src="/js/imageBoard.js"></script>
<body>
<div class="container">
	<div>
		<jsp:include page="/WEB-INF/views/navbar.jsp" flush="false" />
	</div>
	<div class="mb-4">
		<sec:authorize access="isAuthenticated()">
			<button class="btn btn-outline-info btn-sm" id="insertBtn">글작성</button>
		</sec:authorize>
		<sec:authorize access="isAnonymous()">
			<button class="btn btn-outline-info btn-sm" id="loginBtn">글작성</button>
		</sec:authorize>
	</div>
	<div class="row">
		<c:forEach var="list" items="${ImgList}">
			<div class="col-md-4">
				<a href="/imageBoard/imageDetail?imageNo=${list.imageNo}" class="thumbnail">
					<img id="imageData" src="/imageBoard/display?image=${list.imageName}" style="width: 150px; height: 150px;"/>
					<p>${list.imageTitle}</p>
				</a>
			</div>
		</c:forEach>
	</div>
	<div class="search">
		<form id="search_form" action="/imageBoard/imageList" method="get">
			<select name="searchType">
				<option value="t"
						<c:out value="${cri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
				<option value="c"
						<c:out value="${cri.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
				<option value="w"
						<c:out value="${cri.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
				<option value="tc"
						<c:out value="${cri.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>
			</select>
			<input type="text" name="keyword" id="keywordInput" value="${cri.keyword}" />
			<button class="btn btn-outline-info btn-sm" id="searchBtn">검색</button>
		</form>
	</div>
	<div>
		<ul class="pagination justify-content-center">
			<c:if test="${pageMaker.prev}">
				<li>
					<a href="${pageMaker.startPage - 1}">◀</a>
				</li>
			</c:if>
			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
				<li>
					<a href="${idx}">${idx}</a>
				</li>
			</c:forEach>
			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<li>
					<a href="${pageMaker.endPage + 1}">▶</a>
				</li>
			</c:if>
		</ul>
	</div>
	<form id="page_action" action="/imageBoard/imageList" method="get">
		<input type="hidden" id="searchType" name="searchType" value="<c:out value="${pageMaker.cri.searchType}"/>"/>
		<input type="hidden" name="pageNum" value="<c:out value="${pageMaker.cri.pageNum}"/>"/>
		<input type="hidden" name="amount" value="<c:out value="${pageMaker.cri.imageAmount}"/>"/>
		<input type="hidden" name="keyword" value="<c:out value="${pageMaker.cri.keyword}"/>"/>
	</form>
</div>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>
