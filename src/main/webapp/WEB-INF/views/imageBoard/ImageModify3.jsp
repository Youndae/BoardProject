<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>이미지 수정</title>
 <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
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
                <input id="uploadInputBox"  type="file" name="filedata" value="사진 첨부" multiple/>
            </div>
            
            <!-- 미리보기 영역 -->
            <div id="preview" class="content">
            	
            </div>
            
            <!-- multipart 업로드시 영역 -->
            <form id="uploadForm" style="display: none;" />
        </div>
        <div class="footer">
            <button class="submit"><a href="#" title="등록" class="btnlink">등록</a></button>
        </div>
    </div>

	
	
</body>
</html>