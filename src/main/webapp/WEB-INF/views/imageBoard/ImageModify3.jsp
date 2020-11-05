<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>이미지 수정</title> <script
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
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
			<h3>Title : ${list[0].imageTitle}</h3>
			<h3>Writer : ${list[0].userId}</h3>
			<c:forEach var="data" items="${list}">
				<h3>IMG : ${data.imageData}</h3>
				<h3>OName : ${data.OName}</h3>

			</c:forEach>


		</div>
		<div class="body">
			<!-- 첨부 버튼 -->
			<div id="attach">
				<!-- <label class="waves-effect waves-teal btn-flat" for="uploadInputBox">사진첨부</label> -->
				<input id="uploadInputBox" type="file" name="filedata" value="사진 첨부"
					multiple />
			</div>

			<!-- 미리보기 영역 -->
			<div id="preview" class="content"></div>

			<!-- multipart 업로드시 영역 -->
			<form id="uploadForm" style="display: none;" />
		</div>
		<div class="footer">
			<button class="submit">
				<a href="#" title="등록" class="btnlink">등록</a>
			</button>
		</div>
	</div>



</body>
<script>
	$(document)
			.ready(
					function() {
						var arr = new Array();

						alert("0 : " + arr[0].image + " 1 : " + arr[1].image
								+ " 2 : " + arr[2].image + " 3 : "
								+ arr[3].image + " 4 : " + arr[4].image);

						for (var i = 0; i < 5; i++) {
							if (arr[i].image != null) {
								$("#preview")
										.append(
												"<div class=\"preview-box\" value=\"" + i +"\">"
														+ "<img class=\"thumbnail\" src=\"" + arr[i].image + "\"\/>"
														+ "<a href=\"#\" value=\""
														+ i
														+ "\" onclick=\"deletePreview(this)\">"
														+ "삭제" + "</a>"
														+ "</div>");
							} else {
								break;
							}
						}

						$('.submit a')
								.on(
										'click',
										function() {
											var form = $('#uploadForm')[0];
											var formData = new FormData(form);

											for (var index = 0; index < Object
													.keys(files).length; index++) {
												//formData 공간에 files라는 이름으로 파일을 추가한다.
												//동일명으로 계속 추가할 수 있다.
												formData.append('files',
														files[index]);
											}

											//ajax 통신으로 multipart form을 전송한다.
											$
													.ajax({
														type : 'POST',
														enctype : 'multipart/form-data',
														processData : false,
														contentType : false,
														cache : false,
														timeout : 600000,
														url : '/board/imageupload',
														dataType : 'JSON',
														data : formData,
														success : function(
																result) {
															//이 부분을 수정해서 다양한 행동을 할 수 있으며,
															//여기서는 데이터를 전송받았다면 순수하게 OK 만을 보내기로 하였다.
															//-1 = 잘못된 확장자 업로드, -2 = 용량초과, 그외 = 성공(1)
															if (result === -1) {
																alert('jpg, gif, png, bmp 확장자만 업로드 가능합니다.');
																// 이후 동작 ...
															} else if (result === -2) {
																alert('파일이 10MB를 초과하였습니다.');
																// 이후 동작 ...
															} else {
																alert('이미지 업로드 성공');
																location.href = "ImageList";
																// 이후 동작 ...
															}
														}
													//전송실패에대한 핸들링은 고려하지 않음
													});
										});
						// <input type=file> 태그 기능 구현
						$('#attach input[type=file]').change(function() {
							addPreview($(this)); //preview form 추가하기
						});
					});
</script>
</html>