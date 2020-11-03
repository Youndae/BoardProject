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
            <form id="uploadForm" >
            	<div>
	            	<label for="ImageTitle">제목</label>
	            	<input type="text" id="ImageTitle" name="ImageTitle" value="${list.imageTitle}"/>
            	</div>
            	<div>
            		<label for="ImageContent">내용</label>
            		<textarea id="ImageContent" name="ImageContent" style="width:300px; height:300px">${list.imageContent}</textarea>
            	</div>
            	<input type="hidden" id="ImageNo" name="ImageNo" value="${list.imageNo}"/>
            </form>
        </div>
        <div class="footer">
            <button class="submit"><a href="#" title="등록" class="btnlink">등록</a></button>
        </div>
    </div>

	
	
	
	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js"></script> -->
	<script>
		//임의의 file object영역
		var files = {};
		var previewIndex = 0;
		var deletefiles = {};
		var step = 0; //테스트필요.
		

		// image preview 기능 구현
		// input = file object[]
		function addPreview(input) {
			
            if (input[0].files.length <= (5-$('.preview-box').length)) {
                //파일 선택이 여러개였을 시의 대응
                for (var fileIndex = 0; fileIndex < input[0].files.length; fileIndex++) {
                    var file = input[0].files[fileIndex];
                    if(validation(file.name)) continue;
                    setPreviewForm(file);
                }
            } else
                alert('5장만 올려.'); // 첨부클릭 후 취소시의 대응책은 세우지 않았다.
        }
        
        function setPreviewForm(file, img){
            var reader = new FileReader();
            
            //div id="preview" 내에 동적코드추가.
            //이 부분을 수정해서 이미지 링크 외 파일명, 사이즈 등의 부가설명을 할 수 있을 것이다.
            reader.onload = function(img) {
            	var length = $('.preview-box').length
    			alert("length : "+length);
            	var imgNum = previewIndex++;
            	
                if(length < 5){
	                	$("#preview").append(
		                        "<div class=\"preview-box\" value=\"" + imgNum +"\">" +
		                        "<img class=\"thumbnail\" src=\"" + img.target.result + "\"\/>" +
		                        "<p>" + file.name + "</p>" +
		                        "<a href=\"#\" value=\"" + imgNum + "\" onclick=\"deletePreview(this)\">" +
		                        "삭제" + "</a>"
		                        + "</div>"
		                );
		                /* resizeHeight(); */
		                files[imgNum] = file;  
		                /* <div class=\"preview-box\" value=\"imgNum\">
		                		<img class=\"tunbnail\" src=\"img.target.result\"\/>
		                		<p>file.name</p>
		                		<a href=\"#\" value=\"imgNum\" onclick=\"deletePreview(this)\">삭제</a>
		               		</div> */
		                
            	}else{
            	alert("이미지 업로드는 5장까지만 가능합니다.");
            	return;
            }
		            };
		            
		            reader.readAsDataURL(file);
        }


		//preview 영역에서 삭제 버튼 클릭시 해당 미리보기이미지 영역 삭제
		//기존파일과 수정파일의 구분이 필요함.
		function deletePreview(obj) {
			var imgNum = obj.attributes['value'].value;
			alert("value : "+imgNum);
			/* var fileName = obj.attributes['value'].value; */
			
			/* deletefiles[imgNum] =  */
			$("#preview .preview-box[value=" + imgNum + "]").remove();
			/* delete files[imgNum]; */
			resizeHeight();
		}
		//넘겨서 해야할것.
		//DB에서 해당 imgNum을 가진 것을 찾아서 삭제.
		//저장 디렉토리에서 해당 이름을 가진 파일을 찾아서 삭제.
		

		//client-side validation
		//always server-side validation required
		function validation(fileName) {
			fileName = fileName + "";
			var fileNameExtensionIndex = fileName.lastIndexOf('.') + 1;
			var fileNameExtension = fileName.toLowerCase().substring(
					fileNameExtensionIndex, fileName.length);
			if (!((fileNameExtension === 'jpg')
					|| (fileNameExtension === 'gif') || (fileNameExtension === 'png') || (fileNameExtension === 'jpeg'))) {
				alert('jpg, gif, png 확장자만 업로드 가능합니다.');
				return true;
			} else {
				return false;
			}
		}
		
		
		/*  $(document).ready(function(){
			(function(){
				var arr = {};
				var Num = 0;
				var DataNum = Num++;
				var str = "$";
					str += "{list.image";
					str += DataNum;
					str += "}";
				alert("str : "+str);
				for(DataNum; DataNum <= 5; DataNum++){
					
					
					if(array != null){
						$("#preview").append(
		                        "<div class=\"preview-box\" value=\"" + DataNum +"\">" +
		                        "<img class=\"thumbnail\" src=\"" + array + "\"\/>" +
		                        "<a href=\"#\" value=\"" + DataNum + "\" onclick=\"deletePreview(this)\">" +
		                        "삭제" + "</a>"
		                        + "</div>"
		                );
					}else{
						break;
					}
					}
			});
		})  */
		
		
		

		$(document).ready(function() {
			/* var ImageNo = '<c:out value="${list.imageNo}"/>'; */
			var ImageNo = $("#ImageNo").val();
            alert("imageNo : "+ImageNo+"::"+typeof ImageNo);
          	 $.getJSON("AttachList", {ImageNo: ImageNo}, function(arr){
            	
            	alert("arr : "+arr);
            	
            	$(arr).each(function(i, attach){
            		
            		$("#preview").append(
            			"<div class\"preview-box\" value=\"" + attach.imageStep + "\">" +
            			"<img class=\"thumbnail\" src=\"IMG/" + attach.imageData + "\"\/>" +
            			"<p>" + attach.oldName + "</p>" +
            			"<a href=\"#\" value=\"" + attach.imageStep +"\" onclick=\"deletePreview(this)\">" +
            			"삭제" + "</a>" +
            			"</div>"
            		);
            		step = attach.imageStep;
            	});
            })
            .fail(function(err){
            	alert(err.responseText);
            })
            
            
            
            
            /* <li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-fileName='"+attach.fileName"' data-type='"+attach.fileType+"'>
	            <div>
	            	<img src='/display?fileName="+fileCallPath+"'>
	            </div>
            </li>
            
            
            <li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"'>
            	<div>
            		<span "+attach.fileName+"</span><br/>
            		<img src='/resources/img/attach.png'>
            	</div>
            </li> */   
       		
            
            /* $('.submit a').on('click',function() {                        
                var form = $('#uploadForm')[0];
                var formData = new FormData(form);
    
                for (var index = 0; index < Object.keys(files).length; index++) {
                    //formData 공간에 files라는 이름으로 파일을 추가한다.
                    //동일명으로 계속 추가할 수 있다.
                    formData.append('files',files[index]);
                }
 
                //ajax 통신으로 multipart form을 전송한다.
                $.ajax({
                    type : 'POST',
                    enctype : 'multipart/form-data',
                    processData : false,
                    contentType : false,
                    cache : false,
                    timeout : 600000,
                    url : '/board/imageupload',
                    dataType : 'JSON',
                    data : formData,
                    success : function(result) {
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
                            alert('이미지 수정 성공');
                            location.href="ImageList";
                            // 이후 동작 ...
                        }
                    }
                    //전송실패에대한 핸들링은 고려하지 않음
                });
            });
            // <input type=file> 태그 기능 구현
            $('#attach input[type=file]').change(function() {
                addPreview($(this)); //preview form 추가하기
            }); */
        });
	</script>
</body>
</html>