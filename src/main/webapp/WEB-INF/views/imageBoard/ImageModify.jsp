<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>이미지 수정</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/ImageBoard.js"></script>
<style>
#preview img {
	width: 100px;
	height: 100px;
}

#preview p {
	text-overflow: ellipsis;
	overflow: hidden;
}

.preview-box {
	border: 1px solid;
	padding: 5px;
	border-radius: 2px;
	margin-bottom: 10px;
}
</style>
</head>

<body>
	<div>
		<jsp:include page="/WEB-INF/views/top.jsp" flush="false" />
	</div>

	<div class="wrapper">
		<div class="header">
			<h1>사진 수정</h1>
		</div>
		<div class="body">
			<form id="uploadForm">
				<div>
					<label for="ImageTitle">제목</label> 
					<input type="text" id="ImageTitle" name="ImageTitle" value="${list.imageTitle}" />
				</div>
				<div>
					<label for="ImageContent">내용</label>
					<textarea id="ImageContent" name="ImageContent" style="width: 300px; height: 300px">${list.imageContent}</textarea>
				</div>
				<input type="hidden" id="ImageNo" name="ImageNo" value="${list.imageNo}" />
			</form>
			<div id="attach">
				<input id="uploadInputBox" type="file" name="filedata" value="사진 첨부" multiple />
			</div>
			<div id="preview" class="content"></div>
		</div>
		<div class="footer">
			<button class="submit">
				<a href="#" title="등록" class="btnlink">등록</a>
			</button>
		</div>
	</div>
</body>
</html>