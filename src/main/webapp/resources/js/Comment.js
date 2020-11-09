$(document).ready(function(){
	
	$("#CommentContent").keydown(function(key) {
		if (key.keyCode == 13) {
			alert("엔터!");
			$("#CommentInsert").click();
		}
	});
	
	$(document).on("keydown", "#CommentReplyContent", function(key){
		
		if(key.keyCode == 13){
			alert("Reply Enter!");
			$("#CommentReplyInsert").click();
		}
		
	})
})
	



$(document).on("click", "#CommentReplyInsert", function(){
	
	var commentData = {
			CommentNo : $("#CommentReplyInsert").val(),
			CommentGroupNo : $("#CommentGroupNo").val(),
			CommentIndent : $("#CommentIndent").val(),
			CommentContent : $("#CommentReplyContent").val(),
			BoardNo : $("#BoardNo").val(),
			ImageNo : $("#ImageNo").val(),
	};
	
	alert("Content : "+commentData.CommentContent);
	
	if(commentData.CommentContent == ""){
		alert("댓글을 입력해주세요");
		$("#CommentReplyContent").focus();
	}else{
	alert("CommentData : "+commentData);
	
	alert("CommentNo : "+commentData.CommentNo + ", CommentGroupNo : "+commentData.CommentGroupNo
			+ ", CommentIndent : "+commentData.CommentIndent+", CommentContent : "+commentData.CommentContent
			+ ", BoardNo : "+commentData.BoardNo);
	
	alert("Type : "+typeof(commentData));
	
	var str = JSON.stringify(commentData)
	
	
	$.ajax({
		url: "/board/CommentReply",
		method: 'POST',
		data: str,
		contentType: "application/json; charset=UTF-8",
		success: function(data){
			alert("댓글 OK!");
			
			location.reload();
		},
		 error : function(request, status, error){
				alert("code:" + request.status + "\n"
						+ "message : " + request.responseText
						+ "\n" + "error : " +error);
			}
	})
	}
})

/*function cReply(obj){
	var RInput = $("#CommentReplyContent").val();
	var Num = obj.attributes['value'].value;
	var GNum = $(".comment-box[value="+Num+"] .CommentGroupNo").val();
	var INum = $(".comment-box[value="+Num+"] .CommentIndent").val()
	
	$("#ReplyComment").remove();
	
	alert("Num : "+Num+", GNum : "+GNum+", INum : "+INum);
	
	if(RInput == null){
		$(".comment-box[value="+Num+"]").append(
				"<div id=\"ReplyComment\">" +
				"<input type=\"text\" id=\"CommentReplyContent\" name=\"CommentReplyContent\">" +
				"<button type=\"button\" id=\"CommentReplyInsert\" value=\""+ Num +"\">"+"작성"+"</button>" +
				"<input type=\"hidden\" id=\"CommentGroupNo\" value=\""+GNum+"\">"+
				"<input type=\"hidden\" id=\"CommentIndent\" value=\""+INum+"\">"+
				"</div>"
		)
	}
}*/

function cReply(obj){
	$("#ReplyComment").remove();
	
	
	var RInput = $("#CommentReplyContent").val();
	var Num = obj.attributes['value'].value;
	var GNum = $(".comment-box[value="+Num+"] .CommentGroupNo").val();
	var INum = $(".comment-box[value="+Num+"] .CommentIndent").val()

	
	
	if(RInput == null){
		$(".comment-box[value="+Num+"]").append(
				"<div id=\"ReplyComment\">" +
				"<input type=\"text\" id=\"CommentReplyContent\" name=\"CommentReplyContent\">" +
				"<button type=\"button\" id=\"CommentReplyInsert\" value=\""+ Num +"\">"+"작성"+"</button>" +
				"<input type=\"hidden\" id=\"CommentGroupNo\" value=\""+GNum+"\">"+
				"<input type=\"hidden\" id=\"CommentIndent\" value=\""+INum+"\">"+
				"</div>"
		)
		$("#CommentReplyContent").focus();
	}
	
	
}

/*function addInput(obj){
	var RInput = $("#CommentReplyContent").val();
	var Num = obj.attributes['value'].value;
	var GNum = $(".comment-box[value="+Num+"] .CommentGroupNo").val();
	var INum = $(".comment-box[value="+Num+"] .CommentIndent").val()

	alert("Num : "+Num+", GNum : "+GNum+", INum : "+INum);
	
	if(RInput == null){
		$(".comment-box[value="+Num+"]").append(
				"<div id=\"ReplyComment\">" +
				"<input type=\"text\" id=\"CommentReplyContent\" name=\"CommentReplyContent\">" +
				"<button type=\"button\" id=\"CommentReplyInsert\" value=\""+ Num +"\">"+"작성"+"</button>" +
				"<input type=\"hidden\" id=\"CommentGroupNo\" value=\""+GNum+"\">"+
				"<input type=\"hidden\" id=\"CommentIndent\" value=\""+INum+"\">"+
				"</div>"
		)
	}
}*/


/*$(document).on("click", "#cReply", function(){
	var RInput = $("#CommentReplyContent").val();
	var Num = $("#CommentNo").val();
	alert("Num : "+Num);
		
		if(RInput == null){
			
			$(".comment-box[value="+Num+"]").append(
					"<div id=\"ReplyComment\">" +
					"<input type=\"text\" id=\"CommentReplyContent\" name=\"CommentReplyContent\">" +
					"<button type=\"button\" id=\"CommentReplyInsert\">"+"작성"+"</button>" +
					"</div>"
			)
			
			$("#ReplyComment").append(
					"<input type=\"text\" id=\"CommentReplyContent\" name=\"CommentReplyContent\">" +
					"<button type=\"button\" id=\"CommentReplyInsert\">"+"작성"+"</button>"
			)			
		}
})*/


/*$(function(){
	$("#cReply").click(function(){
		
		var RInput = $("#CommentReplyContent").val();
		
		if(RInput == null){
			$("#ReplyComment").append(
					"<input type=\"text\" id=\"CommentReplyContent\" name=\"CommentReplyContent\">" +
					"<button type=\"button\" id=\"CommentReplyInsert\">"+"작성"+"</button>"
			)			
		}
		
	})
})*/


$(function(){
	$("#CommentInsert").click(function(){
		var CommentContent = {
				CommentContent : $("#CommentContent").val(),
				BoardNo : $("#BoardNo").val(),
				ImageNo : $("#ImageNo").val(),
		}
		
		alert("Content : "+CommentContent.CommentContent+", BoardNo : "+CommentContent.BoardNo+", ImageNo : "+CommentContent);
		
		if(CommentContent.CommentContent == ""){
			alert("댓글을 입력해주세요");
			$("#CommentContent").focus();
		}else{
		
			var str = JSON.stringify(CommentContent);
			
		$.ajax({
			url: "/board/CommentInsert",
			type: "post",
			data: str,
			contentType: "application/json; charset=UTF-8",
			success : function(data){
				alert("Comment Complete")
				location.reload();
			},
			error : function(request, status, error){
				alert("code : "+request.status + "\n"
						+ "message : "+request.responseText + "\n"
						+ "error : "+error);
			}
		})
		}
	})
})


function DelComment(obj){
	alert("Del Start!");
	var commentNo = {
			commentNo : obj.attributes['value'].value,
	}
	
	alert("CommentNo : "+commentNo.commentNo);
	
	$.ajax({
		url:"/board/CommentDelete",
		type:"post",
		data: commentNo,
		success : function(data){
			alert("Delete Complete");
			location.reload();
		},
		error : function(request, status, error){
			alert("code : "+request.status + "\n"
					+"message : "+request.responseText + "\n"
					+"error : "+error);
			
		}
	})
	
	
}



/*$(function(){
	$("#ICommentInsert").click(function(){
		var CommentContent = {
				CommentContent : $("#CommentContent").val(),
				ImageNo : $("#ImageNo").val(),
		}
		
		alert("Content : "+CommentContent.CommentContent+", ImageNo : "+CommentContent.ImageNo);
		
		if(CommentContent.CommentContent == ""){
			alert("댓글을 입력해주세요");
			$("#CommentContent").focus();
		}else{
		
		$.ajax({
			url: "/board/CommentInsert",
			type: "post",
			data: CommentContent,
			success : function(data){
				location.reload();
			},
			error : function(request, status, error){
				alert("code : "+request.status + "\n"
						+ "message : "+request.responseText + "\n"
						+ "error : "+error);
			}
		})
		}
	})
})*/