<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <tr>
        <th>이름</th>
        <th>나이</th>
    </tr>
    <c:forEach var="testboard" items="${test}">
        <tr>
            <td>${testboard.name}</td>
            <td>${testboard.age}</td>
        </tr>
    </c:forEach>
</body>
</html>
