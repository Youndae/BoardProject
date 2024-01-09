<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
<script type="text/javascript" src="/js/comment.js"></script>
<link rel="stylesheet" href="/css/comment.css">
</head>
<body>
	<div class="container">
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
</body>
</html>