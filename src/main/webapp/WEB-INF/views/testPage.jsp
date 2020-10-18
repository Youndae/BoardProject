<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>이름</th>
			<th>나이</th>
		</tr>
		<c:forEach var="test" items="${testBoard}">
			<tr>
				<td>${test.name}</td>
				<td>${test.age}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>