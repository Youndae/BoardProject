$(function(){
	$("#Modify").click(function(){
		var boardNo = $("#boardNo").val();
		alert("boardNo : "+boardNo);
		location.href = "/board/BoardModify?boardNo="+boardNo;
	})
})

/*function Modify(){
	var boardNo = document.getElementById("boardNo").value;
	console.log(boardNo);
	alert("boardNo : "+boardNo);
	location.href = "/board/BoardModify?boardNo="+boardNo;
}*/

$(function(){
	$("#DeleteBoard").click(function(){
		var boardNo = $("#boardNo").val();
		alert("boardNo : "+boardNo);
		location.href = "/board/BoardDelete?boardNo="+boardNo;
	})
})

/*function DeleteBoard(){
	var boardNo = document.getElementById("boardNo").value;
	alert("boardNo : "+boardNo);
	location.href = "/board/BoardDelete?boardNo="+boardNo;
}*/

$(function(){
	$("#Reply").click(function(){
		var boardNo = $("#boardNo").val();
		location.href= "/board/BoardReply?boardNo="+boardNo;
	})
})

/*function Reply(){
	var boardNo = document.getElementById("boardNo").value;
	location.href= "/board/BoardReply?boardNo="+boardNo;
}
*/


$(function(){
	$("#insertBoard").click(function(){
		var form = $("#insertBoardFrm");
		
		form.action = "<c:url value='/BoardInsertproc'/>";
		form.submit();
	})
})


$(function(){
	$("#searchBtn").click(function(){
		self.location = "/board/BoardList"
			+ '${pageMaker.makeQuery(1)}'
			+ "&searchType="
			+ $("select option:selected").val()
			+ "&keyword="
			+ encodeURIComponent($('#keywordInput').val());
	});
});

$(function(){
	$("#ModifyProc").click(function(){
		var form = document.getElementById("insertBoardFrm");
		alert("Modify hi?");
		form.action = "/board/BoardModifyProc";
		form.submit();
	})
})

$(function(){
	$("#ReplyProc").click(function(){
		alert("Reply hi?");
		var form = document.getElementById("ReplyFrm");
		alert("Reply form OK");
		form.action = "/board/BoardReplyProc";
		form.submit();
	})
})

