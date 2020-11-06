$(document).on("click", "#CommentReplyInsert", function(){
	alert("button hi");
	
	var commentData = {
			CommentNo : $("#CommentReplyInsert").val(),
			CommentGroupNo : $("#CommentGroupNo").val(),
			CommentIndent : $("#CommentIndent").val(),
			CommentContent : $("#CommentReplyContent").val(),
			BoardNo : $("#BoardNo").val(),
	};
	/*commentData.CommentNo = $("#CommentReplyInsert").val();
	commentData.CommentGroupNo = $("#CommentGroupNo").val();
	commentData.CommentIndent = $("#CommentIndent").val();
	commentData.CommentContent = $("#CommentReplyContent").val();
	commentData.BoardNo = $("#BoardNo").val();*/
	alert("CommentData : "+commentData);
	
	alert("CommentNo : "+commentData.CommentNo + ", CommentGroupNo : "+commentData.CommentGroupNo
			+ ", CommentIndent : "+commentData.CommentIndent+", CommentContent : "+commentData.CommentContent
			+ ", BoardNo : "+commentData.BoardNo);
	
	var str = JSON.stringify(commentData)
	
	
	$.ajax({
		url: "/board/CommentReply",
		method: 'POST',
		dataType: 'json',
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
