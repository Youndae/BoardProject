$(document).ready(function(){
	
	$("#CommentContent").keydown(function(key) {
		if (key.keyCode == 13) {
			$("#CommentInsert").click();
		}
	});
	
	$(document).on("keydown", "#CommentReplyContent", function(key){
		
		if(key.keyCode == 13){
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
	
	if(commentData.CommentContent == ""){
		alert("댓글을 입력해주세요");
		$("#CommentReplyContent").focus();
	}else{
	
	var str = JSON.stringify(commentData)
	
	$.ajax({
		url: "/board/CommentReply",
		method: 'POST',
		data: str,
		contentType: "application/json; charset=UTF-8",
		success: function(data){
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
				"<button class=\"btn btn-outline-info btn-sm\" type=\"button\" id=\"CommentReplyInsert\" value=\""+ Num +"\">"+"작성"+"</button>" +
				"<input type=\"hidden\" id=\"CommentGroupNo\" value=\""+GNum+"\">"+
				"<input type=\"hidden\" id=\"CommentIndent\" value=\""+INum+"\">"+
				"</div>"
		)
		$("#CommentReplyContent").focus();
	}
	
	
}

$(function(){
	$("#CommentInsert").click(function(){
		var CommentContent = {
				CommentContent : $("#CommentContent").val(),
				BoardNo : $("#BoardNo").val(),
				ImageNo : $("#ImageNo").val(),
		}
		
		if(CommentContent.CommentContent == ""){
			$("#CommentContent").focus();
		}else{
		
			var str = JSON.stringify(CommentContent);
			
		$.ajax({
			url: "/board/CommentInsert",
			type: "post",
			data: str,
			contentType: "application/json; charset=UTF-8",
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
})


function DelComment(obj){
	var CommentNo = {
			CommentNo : obj.attributes['value'].value,
	}
	
	$.ajax({
		url:"/board/CommentDelete",
		type:"post",
		data: CommentNo,
		success : function(data){
			location.reload();
		},
		error : function(request, status, error){
			alert("code : "+request.status + "\n"
					+"message : "+request.responseText + "\n"
					+"error : "+error);
			
		}
	})
	
	
}