<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%-- <script src="<c:url value="/resources/js/HierarchicalBoard.js" />"></script> --%>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/HierarchicalBoard.js"></script>
<script type="text/javascript" src="js/Comment.js"></script>
<title>${boardDetai.boardTitle}</title>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/top.jsp" flush="false" />
	</div>
	<div>
		<div>
			<h2>${boardDetail.boardTitle}</h2>
		</div>
		<div>
			<h3>${boardDetail.userId }</h3>
		</div>
		<div>${boardDetail.boardContent }</div>
	</div>

	<!-- <button href='#' onclick="Reply()" value=1>답글</button> -->
	<button href='#' id="Reply" value=1>답글</button>
	<c:set var="name" value="${sessionScope.userId}" />
	<c:set var="id" value="${boardDetail.userId}" />
	<c:if test="${name eq id }">
		<!-- <button href='#' onclick="Modify()">수정</button> -->
		<button href='#' id="Modify">수정</button>
		<!-- <button href='#' onclick="DeleteBoard()">삭제</button> -->
		<button href='#' id="DeleteBoard">삭제</button>
	</c:if>
	<input type="hidden" id="BoardNo" name="BoardNo"
		value="${boardDetail.boardNo}">
		
	<form method="post" id="CommentFrm">
		<div>
			<input type="text" id="CommentContent" name="CommentContent">
			<button type="button" id="BCommentInsert">작성</button>
		</div>
	</form>	
		
	<c:forEach var="clist" items="${comment}">
		<div id="comment">
			<div class="comment-box" value="${clist.commentNo}">
				<p>${clist.userId} &nbsp&nbsp ${clist.commentContent} &nbsp&nbsp <button type="button" id="cReply">답글</button></p>
				<input type="hidden" id="CommentNo" value="${clist.commentNo}">
				<input type="hidden" id="CommentGroupNo" value="${clist.commentGroupNo}">
				<input type="hidden" id="CommentIndent" value="${clist.commentIndent}">
				<div id="ReplyComment">
					
					
				</div>
			</div>
		</div>
	</c:forEach>
</body>
</html>
