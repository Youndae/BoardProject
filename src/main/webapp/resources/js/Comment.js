$(document).on("click", "#CommentReplyInsert", function(){
	alert("button hi");
	
	var commentData = {};
	commentData.CommentNo = $("#CommentNo").val();
	commentData.CommentGroupNo = $("#CommentGroupNo").val();
	commentData.CommentIndent = $("#CommentIndent").val();
	commentData.CommentContent = $("#CommentReplyContent").val();
	commentData.BoardNo = $("#BoardNo").val();
	
	alert("CommentNo : "+commentData.CommentNo + ", CommentGroupNo : "+commentData.CommentGroupNo
			+ ", CommentIndent : "+commentData.CommentIndent+", CommentContent : "+commentData.CommentContent
			+ ", BoardNo : "+commentData.BoardNo);
	
	
	
	$.ajax({
		url: "/board/CommentReply",
		method: 'POST',
		Data: commentData,
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
	
})


$(function(){
	$("#cReply").click(function(){
		
		var RInput = $("#CommentReplyContent").val();
		
		if(RInput == null){
			$("#ReplyComment").append(
					"<input type=\"text\" id=\"CommentReplyContent\" name=\"CommentReplyContent\">" +
					"<button type=\"button\" id=\"CommentReplyInsert\">"+"작성"+"</button>"
			)			
		}
		
	})
})


$(function(){
	$("#BCommentInsert").click(function(){
		var CommentContent = {
				CommentContent : $("#CommentContent").val(),
				BoardNo : $("#BoardNo").val(),
		}
		
		alert("Content : "+CommentContent.CommentContent+", BoardNo : "+CommentContent.BoardNo);
		
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
		
	})
})
