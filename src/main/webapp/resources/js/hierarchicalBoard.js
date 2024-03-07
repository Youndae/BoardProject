const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function(){
	
	$("#keywordInput").keydown(function(key){
		if(key.keyCode == 13){
			$("#searchBtn").click();
		}
	})
})


$(function(){
	$("#modify").click(function(){
		const boardNo = $("#boardNo").val();

		location.href = "/board/boardModify?boardNo=" + boardNo;
	})
})

$(function() {
	$("#insertBtn").click(function() {
		location.href = "/board/boardInsert";
	})
})

$(function(){
	$("#deleteBoard").click(function(){
		const boardNo = $("#boardNo").val();

		$.ajax({
			url: '/board/boardDelete/' + boardNo,
			method: 'delete',
			beforeSend: function (xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(result){

				if(result == 1)
					location.href = "/board/boardList";
				else if(result == 0)
					alert('오류가 발생했습니다.\n문제가 계속되면 관리자에게 문의해주세요.');
				else if(result == -1)
					location.href = "/accessError";

			},
			error: function (request, status, error) {
				alert("code:" + request.status + "\n"
					+ "message : " + request.responseText
					+ "\n" + "error : " + error);
			}
		});
	})
})

$(function(){
	$("#reply").click(function(){
		const indent = $("#boardIndent").val();
		if(indent == 4){
			alert("더이상 답글을 작성할 수 없습니다.");
		}else{
			const boardNo = $("#boardNo").val();
			location.href= "/board/boardReply?boardNo=" + boardNo;
		}
	})
})

$(function(){
	$("#insertBoard").click(function(){
		const form = $("#insertBoardFrm");
		
		form.action = "<c:url value='/boardInsertproc'/>";
		form.submit();
	})
})

$(function(){
	$("#modifyProc").click(function(){
		const form = document.getElementById("insertBoardFrm");
		
		form.action = "/board/boardModifyProc";
		form.submit();
	})
})

$(function(){
	$("#replyProc").click(function(){
		const form = document.getElementById("replyFrm");
		
		form.action = "/board/boardReplyProc";
		form.submit();
	})
})


$(function() {
	$('#searchBtn').click(function() {
		const sType = $("select option:selected").val();

		console.log("sType : " + sType);

		$("#searchType").val(sType);
	});
});


$(function(){
	$('.pagination a').on('click', function(e) {
		e.preventDefault();

		const page_form = $('#page_action');

		page_form.find("input[name='pageNum']").val($(this).attr("href"));
		page_form.submit();
	});
})

