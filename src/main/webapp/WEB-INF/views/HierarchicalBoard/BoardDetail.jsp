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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<div class="container">
	<div>
		<jsp:include page="/WEB-INF/views/top.jsp" flush="false" />
	</div>
	<div>
		<div class="form-row float-right mb-3">
			<button class="btn btn-outline-info btn-sm" id="Reply" value=1>답글</button>
			<c:set var="name" value="${sessionScope.userId}" />
			<c:set var="id" value="${boardDetail.userId}" />
			<c:if test="${name eq id }">
				<button class="btn btn-outline-info btn-sm" id="Modify">수정</button>
				<button class="btn btn-outline-info btn-sm" id="DeleteBoard">삭제</button>
			</c:if>
		</div>
		<div class="form-group">
			<label>제목</label>
			<p>${boardDetail.boardTitle}</p>
			
		</div>
		<div class="form-group">
			<label>작성자</label>
			<p>${boardDetail.userId}</p>
		</div>
		<div class="form-group">
			<label>작성일</label>
			<p>${boardDetail.boardDate}</p>
		</div>
		<div class="form-group">
			<label>내용</label>
			<p>${boardDetail.boardContent}</p> 
		</div>
	</div>
	
	<input type="hidden" id="BoardNo" name="BoardNo" value="${boardDetail.boardNo}">
	<input type="hidden" id="BoardIndent" value="${boardDetail.boardIndent}">
		
	<form method="post" id="CommentFrm">
		<div>
			<input type="text" id="CommentContent" name="CommentContent">
			<c:if test="${name ne null}">
			<button class="btn btn-outline-info btn-sm" type="button" id="CommentInsert">작성</button>
			</c:if>
			<c:if test="${name eq null}">
			<button class="btn btn-outline-info btn-sm" type="button" id="CommentInsert" disabled="disabled">작성</button>
			</c:if>
		</div>
	</form>	
		
	<c:forEach var="clist" items="${comment}">
		<div id="comment">
			<div class="comment-box" id="comment-box" value="${clist.commentNo}">
				<c:set var="cId" value="${clist.userId}"/>
				<table class="table table-hover">
					<tr>			
						<c:choose>
							<c:when test="${clist.commentIndent == 0}">
								<td>
									<p style="color:gray;">
										${clist.userId}<br>
										<span style="margin-left:36px; font-size: 20px;color:black;">
											${clist.commentContent} 
										</span>
										<button type="button" id="cReply" value="${clist.commentNo}" onclick="cReply(this)">답글</button>
										<c:if test="${name eq cId }">
											<button type="button" id="DeleteComment" value="${clist.commentNo}" onclick="DelComment(this)">삭제</button>				
										</c:if>
									</p>
							</c:when>
							<c:otherwise>
								<td>
									<p style="margin-left:24px; color:gray;">
										┖ ${clist.userId}<br>
										<span style="margin-left:60px; font-size:20px; color:black;">
											 ${clist.commentContent}
										</span>
										<c:if test="${name eq cId }">
											<button type="button" id="DeleteComment" value="${clist.commentNo}" onclick="DelComment(this)">삭제</button>			
										</c:if>
									</p>	
								</td>
							</c:otherwise>
						</c:choose>
						<input type="hidden" id="CommentNo" value="${clist.commentNo}">
						<input type="hidden" class="CommentGroupNo" value="${clist.commentGroupNo}">
						<input type="hidden" class="CommentIndent" value="${clist.commentIndent}">
					</tr>
				</table>
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
</div>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>