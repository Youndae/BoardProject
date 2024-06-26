<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>이미지 수정</title>
	<meta name="_csrf" content="${_csrf.token}">
	<meta name="_csrf_header" content="${_csrf.headerName}">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/imageBoard.js"></script>
<link rel="stylesheet" href="/css/imageBoard.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<div class="container">
	<div>
		<jsp:include page="/WEB-INF/views/navbar.jsp" flush="false" />
	</div>

	<div class="wrapper">
		<div class="header">
			<h1>사진 수정</h1>
		</div>
		<div class="body">
			<form id="uploadForm">
				<div>
					<label for="imageTitle">제목</label>
					<input type="text" id="imageTitle" name="imageTitle" value="${list.imageTitle}" />
				</div>
				<div>
					<label for="imageContent">내용</label>
					<textarea id="imageContent" name="imageContent" style="width: 300px; height: 300px">${list.imageContent}</textarea>
				</div>
				<input type="hidden" id="imageModifyNo" name="imageNo" value="${list.imageNo}" />
			</form>
			<div id="attach">
				<input id="uploadInputBox" type="file" name="fileData" value="사진 첨부" multiple />
			</div>
			<div id="preview" class="content"></div>
		</div>
		<div class="footer">
			<button class="btn btn-outline-info btn-sm" id="imageModify">등록</button>
		</div>
	</div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</html>