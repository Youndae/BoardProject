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
	<div class="container">
		<form method="post" id="CommentFrm">
			<div class="mb-4">
				<c:set var="name" value="${sessionScope.userId}" />
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
											<button class="btn btn-outline-info btn-sm" type="button" id="cReply" value="${clist.commentNo}" onclick="cReply(this)">답글</button>
											<c:if test="${name eq cId }">
												<button class="btn btn-outline-info btn-sm" type="button" id="DeleteComment" value="${clist.commentNo}" onclick="DelComment(this)">삭제</button>				
											</c:if>
										</p>		
									</td>			
								</c:when>
								<c:otherwise>
									<td>
										<p style="margin-left:24px; color:gray; ">
											┖ ${clist.userId} <br>
											<span style="margin-left:60px; font-size:20px;color:black;">
													${clist.commentContent}
											</span>
											<c:if test="${name eq cId }">
												<button class="btn btn-outline-info btn-sm" type="button" id="DeleteComment" value="${clist.commentNo}" onclick="DelComment(this)">삭제</button>				
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