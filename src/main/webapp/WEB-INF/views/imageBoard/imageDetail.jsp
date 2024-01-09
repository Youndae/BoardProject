<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>${deatil[0].imageTitle}</title>
	<meta name="_csrf" content="${_csrf.token}">
	<meta name="_csrf_header" content="${_csrf.headerName}">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/js/imageBoard.js"></script>
<body>
<div class="container">
	<div>
		<jsp:include page="/WEB-INF/views/navbar.jsp" flush="false" />
	</div>
	<div class="form-row float-right mb-3">
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.username" var="username"/>
			<c:set var="id" value="${detail[0].userId}"/>
			<c:if test="${username eq id}">
				<button class="btn btn-outline-info btn-sm" type="button" id="modify">수정</button>
				<button class="btn btn-outline-info btn-sm" type="button" id="delete">삭제</button>
			</c:if>
		</sec:authorize>
	</div>
	<div class="form-group">
		<label>제목</label>
		<p>${detail[0].imageTitle}</p>
	</div>
	<div class="form-group">
		<label>작성자</label>
		<p>${detail[0].userId}</p>
	</div>
	<div class="form-group mb-5">
		<label>작성일</label>
		<p>${detail[0].imageDate}</p>
	</div>
	<div class="form-group mb-5">
		<label>내용</label>
		<div class="mt-4">
			<c:forEach var="image" items="${detail}">
				<div class="mb-4">
					<img id="boardImg" src="/imageBoard/display?image=${image.imageName}"	style="width: 300px; height: 300px;" />
				</div>
			</c:forEach>
		</div>
		<p>${detail[0].imageContent}</p>
	</div>
	
	<input type="hidden" id="imageNo" name="imageNo" value="${detail[0].imageNo}">
	<div>	
		<jsp:include page="/WEB-INF/views/comment.jsp" flush="false" />
	</div>	
</div>	
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>
