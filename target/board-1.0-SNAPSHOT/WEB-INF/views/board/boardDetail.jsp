<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>${boardDetail.boardTitle}</title>
	<meta name="_csrf" content="${_csrf.token}">
	<meta name="_csrf_header" content="${_csrf.headerName}">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/js/hierarchicalBoard.js"></script>
<script type="text/javascript" src="/js/comment.js"></script>
<link rel="stylesheet" href="/css/comment.css">
<body>
<div class="container">
	<div>
		<jsp:include page="/WEB-INF/views/navbar.jsp" flush="false" />
	</div>
	<div>
		<div class="form-row float-right mb-3">
			<sec:authorize access="isAuthenticated()">
				<c:set var="id" value="${boardDetail.userId}" />
				<button class="btn btn-outline-info btn-sm" id="reply">답글</button>
				<sec:authentication property="principal.username" var="username"/>
				<c:if test="${username eq id}">
					<button class="btn btn-outline-info btn-sm" id="modify">수정</button>
					<button class="btn btn-outline-info btn-sm" id="deleteBoard">삭제</button>
				</c:if>
			</sec:authorize>
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
			<p><c:out value="${boardDetail.boardDate}"/></p>
		</div>
		<div class="form-group">
			<label>내용</label>
			<p>${boardDetail.boardContent}</p> 
		</div>
	</div>
	
	<input type="hidden" id="boardNo" name="boardNo" value="${boardDetail.boardNo}">
		
	<form method="post" id="commentFrm">
		<div>
			<sec:authorize access="isAuthenticated()">
				<input type="text" id="commentContent" name="commentContent" placeholder="댓글을 작성해주세요">
				<button class="btn btn-outline-info btn-sm" type="button" id="commentInsert">작성</button>
			</sec:authorize>
			<sec:authorize access="isAnonymous()">
				<input type="text" id="commentContent" name="commentContent" placeholder="댓글을 작성하시려면 로그인해주세요">
				<button class="btn btn-outline-info btn-sm" type="button" id="commentInsert" disabled="disabled">작성</button>
			</sec:authorize>
		</div>
		<sec:csrfInput/>
	</form>	
		
	<div class="comment-area">
		<c:forEach var="clist" items="${comment}">
			<div id="comment">
				<div class="comment-box" id="comment-box" value="${clist.commentNo}">
					<c:set var="cId" value="${clist.userId}"/>
					<table class="table table-hover">
						<tr>
							<c:choose>
								<c:when test="${clist.commentIndent == 0}">
									<td>
									<span class="comment_userId">${clist.userId}</span>
									<span class="comment_date"><c:out value="${clist.commentDate}"/></span>
									<c:choose>
										<c:when test="${clist.commentContent == null}">
											<p class="comment_content_delete">삭제된 댓글입니다.</p>
										</c:when>
										<c:otherwise>
											<p class="comment_content">${clist.commentContent}</p>
										</c:otherwise>
									</c:choose>
									<sec:authorize access="isAuthenticated()">
										<button class="btn btn-outline-info btn-sm" type="button" id="cReply" value="${clist.commentNo}" onclick="cReply(this)">답글</button>
									</sec:authorize>
									<c:if test="${username eq cId }">
										<button class="btn btn-outline-info btn-sm" type="button" id="deleteComment" value="${clist.commentNo}" onclick="delComment(this)">삭제</button>
									</c:if>
									</td>
								</c:when>
								<c:when test="${clist.commentIndent == 1}">
									<td>
										<span class="comment_userId indent_size_1">${clist.userId}</span>
										<span class="comment_date"><c:out value="${clist.commentDate}"/></span>
										<c:choose>
											<c:when test="${clist.commentContent == null}">
												<p class="comment_content_delete indent_size_1">삭제된 댓글입니다.</p>
											</c:when>
											<c:otherwise>
												<p class="comment_content indent_size_1">${clist.commentContent}</p>
											</c:otherwise>
										</c:choose>
										<sec:authorize access="isAuthenticated()">
											<button class="btn btn-outline-info btn-sm indent_size_1" type="button" id="cReply" value="${clist.commentNo}" onclick="cReply(this)">답글</button>
										</sec:authorize>
										<c:if test="${username eq cId }">
											<button class="btn btn-outline-info btn-sm" type="button" id="deleteComment" value="${clist.commentNo}" onclick="delComment(this)">삭제</button>
										</c:if>
									</td>
								</c:when>
								<c:when test="${clist.commentIndent == 2}">
									<td>
										<span class="comment_userId indent_size_2">${clist.userId}</span>
										<span class="comment_date"><c:out value="${clist.commentDate}"/></span>
										<c:choose>
											<c:when test="${clist.commentContent == null}">
												<p class="comment_content_delete indent_size_2">삭제된 댓글입니다.</p>
											</c:when>
											<c:otherwise>
												<p class="comment_content indent_size_2">${clist.commentContent}</p>
											</c:otherwise>
										</c:choose>
										<sec:authorize access="isAuthenticated()">
											<button class="btn btn-outline-info btn-sm indent_size_2" type="button" id="cReply" value="${clist.commentNo}" onclick="cReply(this)">답글</button>
										</sec:authorize>
										<c:if test="${username eq cId }">
											<button class="btn btn-outline-info btn-sm" type="button" id="deleteComment" value="${clist.commentNo}" onclick="delComment(this)">삭제</button>
										</c:if>
									</td>
								</c:when>
								<c:when test="${clist.commentIndent == 3}">
									<td>
										<span class="comment_userId indent_size_3">${clist.userId}</span>
										<span class="comment_date"><c:out value="${clist.commentDate}"/></span>
										<c:choose>
											<c:when test="${clist.commentContent == null}">
												<p class="comment_content_delete indent_size_3">삭제된 댓글입니다.</p>
											</c:when>
											<c:otherwise>
												<p class="comment_content indent_size_3">${clist.commentContent}</p>
											</c:otherwise>
										</c:choose>
										<sec:authorize access="isAuthenticated()">
											<button class="btn btn-outline-info btn-sm indent_size_3" type="button" id="cReply" value="${clist.commentNo}" onclick="cReply(this)">답글</button>
										</sec:authorize>
										<c:if test="${username eq cId }">
											<button class="btn btn-outline-info btn-sm" type="button" id="deleteComment" value="${clist.commentNo}" onclick="delComment(this)">삭제</button>
										</c:if>
									</td>
								</c:when>
								<c:when test="${clist.commentIndent == 4}">
									<td>
										<span class="comment_userId indent_size_4">${clist.userId}</span>
										<span class="comment_date"><c:out value="${clist.commentDate}"/></span>
										<c:choose>
											<c:when test="${clist.commentContent == null}">
												<p class="comment_content_delete indent_size_4">삭제된 댓글입니다.</p>
											</c:when>
											<c:otherwise>
												<p class="comment_content indent_size_4">${clist.commentContent}</p>
											</c:otherwise>
										</c:choose>
										<sec:authorize access="isAuthenticated()">
											<button class="btn btn-outline-info btn-sm indent_size_4" type="button" id="cReply" value="${clist.commentNo}" onclick="cReply(this)">답글</button>
										</sec:authorize>
										<c:if test="${username eq cId }">
											<button class="btn btn-outline-info btn-sm" type="button" id="deleteComment" value="${clist.commentNo}" onclick="delComment(this)">삭제</button>
										</c:if>
									</td>
								</c:when>
							</c:choose>
							<input type="hidden" id="commentNo" value="${clist.commentNo}">
							<input type="hidden" class="commentGroupNo" value="${clist.commentGroupNo}">
							<input type="hidden" class="commentIndent" value="${clist.commentIndent}">
						</tr>
					</table>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="comment-paging">
		<ul>
			<c:if test="${pageMaker.prev}">
				<li>
					<a href="#" onclick="commentPaging(${pageMaker.startPage} - 1)">이전</a>
				</li>
			</c:if>
			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
				<li>
					<a href="#" onclick="commentPaging(${idx})">${idx}</a>
				</li>
			</c:forEach>
			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<li>
					<a href="#" onclick="commentPaging(${pageMaker.endPage} + 1)">다음</a>
				</li>
			</c:if>
		</ul>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>