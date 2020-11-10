<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>${deatil[0].imageTitle}</title>
<style type="text/css">
li {
	list-style: none;
	float: left;
	padding: 6px;
}
</style>
</head>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/ImageBoard.js"></script>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/top.jsp" flush="false" />
	</div>
	<div>
		<c:set var="name" value="${sessionScope.userId}"/>
		<c:set var="id" value="${detail[0].userId}"/>
		<c:if test="${name eq id}">
			<button type="button" id="Modify">수정</button>
			<button type="button" id="Delete">삭제</button>
		</c:if>
	</div>
	<div>
		<h2>제목 : ${detail[0].imageTitle}</h2>
	</div>
	<div>
		<h3>작성자 : ${detail[0].userId }</h3>
	</div>
	<div>
		<p>내용</p>
		<h4>${detail[0].imageContent}</h4>
	</div>
	<br>
	<br>
	<div>
		<h5>이미지</h5>
		<c:forEach var="image" items="${detail}">
			<div>
				<img id="boardImg" src="IMG/${image.imageData}"	style="width: 300px; height: 300px;" />
			</div>
			<br>
			<br>
			<br>
			<br>
			<br>
		</c:forEach>
	</div>
	<input type="hidden" id="ImageNo" name="ImageNo" value="${detail[0].imageNo}">
	<div>	
		<jsp:include page="/WEB-INF/views/comment.jsp" flush="false" />
	</div>		
	
</body>
</html>
