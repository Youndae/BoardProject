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
					<c:set var="cId" value="${clist.userId}"/>
					<c:set var="name" value="${sessionScope.userId}" />
					<c:choose>
						<c:when test="${clist.commentIndent == 0}">
							<p>
								${clist.userId} &nbsp&nbsp ${clist.commentContent} &nbsp&nbsp 
								<button type="button" id="cReply" value="${clist.commentNo}" onclick="cReply(this)">답글</button>
								<c:if test="${name eq cId }">
									<button type="button" id="DeleteComment" value="${clist.commentNo}" onclick="DelComment(this)">삭제</button>				
								</c:if>
							</p>					
						</c:when>
						<c:otherwise>
							<p>
								<span style="margin-left:24px;">
								${clist.userId} &nbsp&nbsp ${clist.commentContent} &nbsp&nbsp 
								<button type="button" id="cReply" value="${clist.commentNo}" onclick="cReply(this)">답글</button>
								<c:if test="${name eq cId }">
									<button type="button" id="DeleteComment" value="${clist.commentNo}" onclick="DelComment(this)">삭제</button>				
								</c:if>
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
		<div>
			<ul>
				<c:if test="${pageMaker.prev}">
					<li>
						<a href="/board/ImageDetail${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a>
					</li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
					<li>
						<a href="/board/ImageDetail${pageMaker.makeQuery(idx)}&ImageNo=${detail[0].imageNo}">${idx}</a>
					</li>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
					<li>
						<a href="/board/ImageDetail${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</body>
</html>