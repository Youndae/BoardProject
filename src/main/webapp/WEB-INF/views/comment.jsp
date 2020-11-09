<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
<script type="text/javascript" src="js/Comment.js"></script>
</head>
<body>
	<div>
		<form method="post" id="CommentFrm">
			<div>
				<input type="text" id="CommentContent" name="CommentContent">
				<button type="button" id="CommentInsert">작성</button>
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
	</div>
</body>
</html>