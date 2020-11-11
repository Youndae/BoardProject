<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Title</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<div class="container">
	<div>
		<jsp:include page="/WEB-INF/views/top.jsp" flush="false" />
	</div>
	<div class="mb-4">
		<button class="btn btn-outline-info btn-sm" onclick="location.href='/board/ImageInsert'">글작성</button>
	</div>
	<div class="row">
		<c:forEach var="list" items="${ImgList}">
			<div class="col-md-4">
				<a href="/board/ImageDetail?ImageNo=${list.imageNo}" class="thumbnail">
					<img id="ImageData" src="IMG/${list.imageName}"	style="width: 150px; height: 150px;" />
					<p>${list.imageTitle}</p>
				</a>
			</div>
		</c:forEach>
	</div>
	
	<%-- 
	
	
	
	<table>
		<tr>
			<th>번호</th>
			<th>사진</th>
			<th>작성자</th>
		</tr>
		<c:forEach var="list" items="${ImgList}">
			<tr>
				<td>${list.imageNo}</td>
				<td>
					<img id="ImageData" src="IMG/${list.imageName}"	style="width: 100px; height: 100px;" />
					<a href="/board/ImageDetail?ImageNo=${list.imageNo}">${list.imageTitle}</a>
				</td>
				<td>${list.userId}</td>
			</tr>
		</c:forEach>
	</table> --%>
</div>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>
