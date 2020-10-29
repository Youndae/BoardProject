<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2020-10-17
  Time: 오후 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>번호</th>
        <th>사진</th>
    </tr>
    <c:forEach var="testboard" items="${ImgList}">
        <tr>
            <td>${testboard.boardNo}</td>
            <td>${testboard.image1}</td>
            <img src="${testboard.image2}">
        </tr>
    </c:forEach>
    </table>
</body>
</html>
