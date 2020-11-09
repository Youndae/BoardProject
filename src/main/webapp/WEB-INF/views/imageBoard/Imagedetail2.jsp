<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>${deatil[0].imageTitle}</title>
</head>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/ImageBoard.js"></script>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/top.jsp" flush="false" />
	</div>
	<div>
		<button type="button" id="Modify">수정</button>
		<button type="button" id="Delete">삭제</button>
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
				<img id="boardImg" src="IMG/${image.imageData}"
					style="width: 500px; height: 500px;" />
			</div>
			<br>
			<br>
			<br>
			<br>
			<br>
		</c:forEach>
	</div>
	<input type="hidden" id="ImageNo" name="ImageNo"
		value="${detail[0].imageNo}">
		
		<form method="post" id="CommentFrm">
		<div>
			<input type="text" id="CommentContent" name="CommentContent">
			<button type="button" id="BCommentInsert">작성</button>
		</div>
	</form>	
		
	<c:forEach var="clist" items="${comment}">
		<div id="comment">
			<div class="comment-box" id="comment-box" value="${clist.commentNo}">
				<c:choose>
				<c:when test="${clist.commentIndent == 0}">
				<p>${clist.userId} &nbsp&nbsp ${clist.commentContent} &nbsp&nbsp <button type="button" id="cReply" value="${clist.commentNo}" onclick="cReply(this)">답글</button></p>					
				</c:when>
				<c:otherwise>
				<p>
				<span style="margin-left:24px;">
				${clist.userId} &nbsp&nbsp ${clist.commentContent} &nbsp&nbsp <button type="button" id="cReply" value="${clist.commentNo}" onclick="cReply(this)">답글</button>
				</span>
				</p>
				</c:otherwise>
				</c:choose>
				<input type="hidden" id="CommentNo" value="${clist.commentNo}">
				<input type="hidden" class="CommentGroupNo" value="${clist.commentGroupNo}">
				<input type="hidden" class="CommentIndent" value="${clist.commentIndent}">
			</div>
		</div>
	</c:forEach>
</body>
</html>
