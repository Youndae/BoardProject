var files = {};
var previewIndex = 0;

		function addPreview(input) {
            if (input[0].files.length <= 5) {
                for (var fileIndex = 0; fileIndex < input[0].files.length; fileIndex++) {
                    var file = input[0].files[fileIndex];
                    if(validation(file.name)) continue;
                    setPreviewForm(file);
                }
            } else
                alert('5장까지만 업로드 가능합니다.');
        }
        
        function setPreviewForm(file, img){
            var reader = new FileReader();
            var length = $('.preview-box').length
            reader.onload = function(img) {
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
		                files[imgNum] = file;  	                
            	 }else{
            	alert("이미지 업로드는 5장까지만 가능합니다.");
            	return;
            } 
		            };
		            reader.readAsDataURL(file);
        }

		function deletePreview(obj) {
			var imgNum = obj.attributes['value'].value;
			delete files[imgNum];
			$("#preview .preview-box[value=" + imgNum + "]").remove();
			resizeHeight();
		}

		function validation(fileName) {
			fileName = fileName + "";
			var fileNameExtensionIndex = fileName.lastIndexOf('.') + 1;
			var fileNameExtension = fileName.toLowerCase().substring(
					fileNameExtensionIndex, fileName.length);
			if (!((fileNameExtension === 'jpg')
					|| (fileNameExtension === 'gif') || (fileNameExtension === 'png') || (fileNameExtension === 'jpeg'))) {
				alert('jpg, gif, png, jpeg 확장자만 업로드 가능합니다.');
				return true;
			} else {
				return false;
			}
		}

		$(document).ready(function() {
            $("#ImageInsert").on('click',function() {                        
                var form = $('#uploadForm')[0];
                var formData = new FormData(form);
    
                for (var index = 0; index < Object.keys(files).length; index++) {
                    formData.append('files',files[index]);
                }
 
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

                        if (result === -1) {
                            alert('파일이 10MB를 초과하였습니다.');
                        } else {
                            
                            location.href="ImageList";
                        }
                    }
                });
            });
            
            $('#attach input[type=file]').change(function() {
                addPreview($(this));
            });
        });