$(function(){
	$("#Modify").click(function(){
		var BoardNo = $("#BoardNo").val();

		location.href = "/board/BoardModify?BoardNo="+BoardNo;
	})
})

$(function(){
	$("#DeleteBoard").click(function(){
		var BoardNo = $("#BoardNo").val();

		location.href = "/board/BoardDelete?BoardNo="+BoardNo;
	})
})

$(function(){
	$("#Reply").click(function(){
		var Indent = $("#BoardIndent").val();
		if(Indent >= 4){
			alert("더이상 답글을 작성할 수 없습니다.");
		}else{
			var BoardNo = $("#BoardNo").val();
			location.href= "/board/BoardReply?BoardNo="+BoardNo;	
		}
	})
})

$(function(){
	$("#insertBoard").click(function(){
		var form = $("#insertBoardFrm");
		
		form.action = "<c:url value='/BoardInsertproc'/>";
		form.submit();
	})
})

$(function(){
	$("#ModifyProc").click(function(){
		var form = document.getElementById("insertBoardFrm");
		
		form.action = "/board/BoardModifyProc";
		form.submit();
	})
})

$(function(){
	$("#ReplyProc").click(function(){
		var form = document.getElementById("ReplyFrm");
		
		form.action = "/board/BoardReplyProc";
		form.submit();
	})
})

