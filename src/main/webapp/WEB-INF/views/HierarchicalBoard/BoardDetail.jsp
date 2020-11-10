<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/HierarchicalBoard.js"></script>
<script type="text/javascript" src="js/Comment.js"></script>
<title>${boardDetail.boardTitle}</title>
<style type="text/css">
li {
	list-style: none;
	float: left;
	padding: 6px;
}
</style>
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
		<div>
			<textarea style="width:1000px; height:1000px;">${boardDetail.boardContent}</textarea> 
		</div>
	</div>
	<button href='#' id="Reply" value=1>답글</button>
	<c:set var="name" value="${sessionScope.userId}" />
	<c:set var="id" value="${boardDetail.userId}" />
	<c:if test="${name eq id }">
		<button href='#' id="Modify">수정</button>
		<button href='#' id="DeleteBoard">삭제</button>
	</c:if>
	<input type="hidden" id="BoardNo" name="BoardNo" value="${boardDetail.boardNo}">
	<input type="hidden" id="BoardIndent" value="${boardDetail.boardIndent}">
		
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
				<c:choose>
					<c:when test="${clist.commentIndent == 0}">
						<p>${clist.userId} &nbsp&nbsp ${clist.commentContent} &nbsp&nbsp 
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
					<a href="/board/BoardDetail${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a>
				</li>
			</c:if>
			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
				<li>
					<a href="/board/BoardDetail${pageMaker.makeQuery(idx)}&BoardNo=${boardDetail.boardNo}">${idx}</a>
				</li>
			</c:forEach>
			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<li>
					<a href="/board/BoardDetail${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a>
				</li>
			</c:if>
		</ul>
	</div>
</body>
</html>