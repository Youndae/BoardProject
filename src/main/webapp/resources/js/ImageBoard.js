var files = {};
var previewIndex = 0;
var deletefiles = {};
var step = 0; 
var deleteNo = 0;
		
		function addPreview(input) {			
            if (input[0].files.length <= (5-($('.preview-box').length))) {
                for (var fileIndex = 0; fileIndex < input[0].files.length; fileIndex++) {
                    var file = input[0].files[fileIndex];
                    if(validation(file.name)) continue;
                    setPreviewForm(file);
                }
            } else
                alert('5장만 업로드 가능합니다.'); 
        }
        
        function setPreviewForm(file, img){
            var reader = new FileReader();
            reader.onload = function(img) {
            	var length = $('.preview-box').length
            	var imgNum = ++step;
            	var fileNum = 0;
            	
	                	$("#preview").append(
		                        "<div class=\"preview-box\" id=\"newImg\" value=\"" + imgNum +"\">" +
		                        "<img class=\"thumbnail\" id=\"imgName\" src=\"" + img.target.result + "\"\/>" +
		                        "<p>" + file.name + "</p>" +
		                        "<a href=\"#\" value=\"" + imgNum + "\" onclick=\"deletePreview(this)\">" +
		                        "삭제" + "</a>"
		                        + "</div>"
		                );	                
		                files[fileNum] = file; 
		                fileNum++;                
		            };	            
		            reader.readAsDataURL(file);
        }

        function deleteOldPreview(obj){
        	var imgNum = obj.attributes['value'].value;
        	var imgName = jQuery('#imgName').attr("src");
			var idx = imgName.lastIndexOf('/');
			var deleteImg = imgName.substring(idx+1);
			
			deletefiles[deleteNo] = deleteImg;
			deleteNo++;
			
			$("#preview .preview-box[value=" + imgNum + "]").remove();
			resizeHeight();
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
				alert('jpg, gif, png 확장자만 업로드 가능합니다.');
				return true;
			} else {
				return false;
			}
		}

		$(document).ready(function() {
			var ImageNo = $("#ImageNo").val();
            
          	 $.getJSON("AttachList", {ImageNo: ImageNo}, function(arr){
            	
            	$(arr).each(function(i, attach){
            		$("#preview").append(
            			"<div class=\"preview-box\" value=\"" + attach.imageStep + "\">" +
            			"<img class=\"thumbnail\" id=\"imgName\" src=\"IMG/" + attach.imageData + "\"\/>" +
            			"<p>" + attach.oldName + "</p>" +
            			"<a href=\"#\" value=\"" + attach.imageStep +"\" onclick=\"deleteOldPreview(this)\">" +
            			"삭제" + "</a>" +
            			"</div>"
            		);
            		step = attach.imageStep;
            	});
            })
            .fail(function(err){
            	alert(err.responseText);
            })
            
       		
            
             $('.submit a').on('click',function() {                        
                var form = $('#uploadForm')[0];
                var formData = new FormData(form);
    			
                for (var index = 0; index < Object.keys(files).length; index++) {

                    formData.append('files',files[index]);
                }
                
                for(var index = 0; index < Object.keys(deletefiles).length; index++){
                	formData.append('deletefiles', deletefiles[index]);
                }
 					
                $.ajax({
                    type : 'POST',
                    enctype : 'multipart/form-data',
                    processData : false,
                    contentType : false,
                    cache : false,
                    timeout : 600000,
                    url : '/board/ImageModifyProc',
                    dataType : 'JSON',
                    data : formData, 
                    success : function(result) {
                        if (result === -1) {
                            alert('jpg, gif, png, bmp 확장자만 업로드 가능합니다.');
                        } else if (result === -2) {
                            alert('파일이 10MB를 초과하였습니다.');
                        } else {
                            location.href="ImageList";
                        }
                    },
                    error : function(request, status, error){
                    	alert("code : "+request.status + "\n"
                    			+ "message : "+request.responseText + "\n"
                    			+ "error : "+error);
                    }
                });
            });
          	 
            $('#attach input[type=file]').change(function() {
                addPreview($(this)); 
            }); 
        });



$(function(){
	$("#Modify").click(function(){
		var ImageNo = $("#ImageNo").val();
		
		location.href = "/board/ImageModify?ImageNo="+ImageNo;
	})
});

$(function(){
	$("#Delete").click(function(){
		var ImageNo = $("#ImageNo").val();
		
		location.href = "/board/ImageDelete?ImageNo="+ImageNo;
	})
});

