<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>이미지 첨부</title>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/ImageBoardInsert.js"></script>
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
			<h1>사진 첨부</h1>
		</div>
		<div class="body">
			<form id="uploadForm">
				<div>
					<label for="ImageTitle">제목</label> 
					<input type="text" id="ImageTitle" name="ImageTitle" placeholder="제목을 입력하세요">
				</div>
				<div>
					<label for="ImageContent">내용</label>
					<textarea id="ImageContent" name="ImageContent"	placeholder="사진을 설명해주세요" style="width: 300px; height: 300px;"></textarea>
				</div>
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