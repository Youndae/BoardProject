<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2020-10-17
  Time: 오후 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Title</title>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/top.jsp" flush="false" />
	</div>
	<table>
		<tr>
			<th>번호</th>
			<th>사진</th>
			<th>작성자</th>
		</tr>
		<c:forEach var="list" items="${ImgList}">
			<tr>
				<td>${list.imageNo}</td>
				<td><img id="ImageData" src="IMG/${list.imageData}"
					style="width: 100px; height: 100px;" /></td>
				<td>${list.userId}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
