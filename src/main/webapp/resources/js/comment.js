const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");

$(function() {

	$("#commentContent").keydown(function (key) {
		if (key.keyCode == 13) {
			$("#commentInsert").click();
		}
	});

	$(document).on("keydown", "#commentReplyContent", function (key) {

		if (key.keyCode == 13) {
			$("#commentReplyInsert").click();
		}

	});

	$("#commentInsert").click(function(){
		const commentContent = {
			commentContent : $("#commentContent").val(),
			boardNo : $("#boardNo").val(),
			imageNo : $("#imageNo").val(),
		}

		if(commentContent.commentContent == ""){
			$("#commentContent").focus();
		}else{

			const str = JSON.stringify(commentContent);

			$.ajax({
				url: "/comment/commentInsert",
				type: "post",
				data: str,
				contentType: "application/json; charset=UTF-8",
				beforeSend: function(xhr){
					xhr.setRequestHeader(header, token);
				},
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
	});

	function commentPaging(obj){
		const boardNo = $("#boardNo").val();
		const imageNo = $("#imageNo").val();
		const pageNum = obj;

		if(boardNo != null)
			hierarchicalBoardComment(boardNo, pageNum);

		if(imageNo != null)
			imageBoardComment(imageNo, pageNum);
	}
})

function hierarchicalBoardComment(boardNo, pageNum){
	$.getJSON('/comment/boardComment/' + boardNo + "/" + pageNum, function(arr){

		commentEachParsing(arr.commentList, arr.uid);

		commentPagingParsing(arr);

	})
}

function imageBoardComment(imageNo, pageNum){
	$.getJSON('/comment/imageComment/' + imageNo + "/" + pageNum, function(arr){
		commentEachParsing(arr.commentList, arr.uid);

		commentPagingParsing(arr);
	})
}

function commentEachParsing(arr, uid){
	const comment_area = $(".comment-area");
	comment_area.empty();

	let commentStr = "";

	$(arr).each(function(i, res){
		commentStr += "<div class=\"comment-box\" value=\"" + res.commentNo + "\">" +
						"<table class=\"table table-hover\">" +
							"<tr>" +
								"<td>";

		let commentIndentClassName = '';

		if(res.commentIndent == 1)
			commentIndentClassName = ' indent_size_1';
		else if(res.commentIndent == 2)
			commentIndentClassName = ' indent_size_2';
		else if(res.commentIndent == 3)
			commentIndentClassName = ' indent_size_3';
		else if(res.commentIndent == 4)
			commentIndentClassName = ' indent_size_4';

		commentStr += "<span class=\"comment_userId" + commentIndentClassName + "\">" + res.userId + "</span>" +
						"<span class=\"comment_date" + commentIndentClassName + "\">" + formatDate(res.commentDate) + "</span>" +
						"<p class=\"comment_content" + commentIndentClassName + "\">" + res.commentContent + "</p>";

		if(uid != null && res.commentIndent != 4){
			commentStr += "<button class=\"btn btn-outline-info btn-sm\" type=\"button\"" +
							"onclick=\"cReply(this)\" value=\"" + res.commentNo + "\">답글</button>";
		}

		if(res.userId == uid){
			commentStr += "<button class=\"btn btn-outline-info btn-sm\" type=\"button\" " +
							"onclick=\"delComment(this)\" value=\"" + res.commentNo + "\">삭제</button>";
		}

		commentStr += "</p>" +
					"</td>" +
					"<input type=\"hidden\" class=\"commentNo\" value=\"" + res.commentNo + "\">" +
					"<input type=\"hidden\" class=\"commentUpperNo\" value=\"" + res.commentUpperNo + "\">" +
					"<input type=\"hidden\" class=\"commentGroupNo\" value=\"" + res.commentGroupNo + "\">" +
					"<input type=\"hidden\" class=\"commentIndent\" value=\"" + res.commentIndent + "\">" +
				"</tr>" +
			"</table>" +
		"</div>";
	})

	comment_area.append(commentStr);
}

function commentPagingParsing(res){
	const comment_paging = $(".comment-paging");
	comment_paging.empty();

	let cpStr = "";
	const startPage = res.pageDTO.startPage;
	const endPage = res.pageDTO.endPage;
	const pageNum = res.pageDTO.cri.pageNum;
	const prev = res.pageDTO.prev;
	const next = res.pageDTO.next;

	if(prev){
		cpStr += "<ul>" +
			"<li>" +
			"<a href=\"#\" onclick=\"commentPaging(" + (startPage - 1) + ")\">이전</a>" +
			"</li>";
	}else {
		cpStr += "<ul>";
	}

	for(let i = startPage; i <= endPage; i++){
		if(startPage != endPage){
			if(i == pageNum){
				cpStr += "<li>" +
					"<a href=\"#\" style=\"font-weight: bold; color: black;\" onclick=\"commentPaging(" + i + ")\">" + i + "</a>" +
					"</li>"
			}else {
				cpStr += "<li>" +
					"<a href=\"#\" onclick=\"commentPaging(" + i + ")\">" + i + "</a>" +
					"</li>"
			}
		}
	}

	if(next){
		cpStr += "<li>" +
			"<a href=\"#\" onclick=\"commentPaging(" + (endPage + 1) + ")\">다음</a>" +
			"</li>" +
			"</ul>";
	}else{
		cpStr += "</ul>";
	}

	comment_paging.append(cpStr);

}

function commentPaging(obj){
	const boardNo = $("#boardNo").val();
	const imageNo = $("#imageNo").val();
	const pageNum = obj;

	if(boardNo != null){
		hierarchicalBoardComment(boardNo, pageNum);
	}

	if(imageNo != null){
		imageBoardComment(imageNo, pageNum);
	}
}

function formatDate(date){
	const d = new Date(date);
	let month = '' + (d.getMonth() + 1);
	let day = '' + d.getDate();
	const year = d.getFullYear();

	if(month.length < 2)
		month = '0' + month;
	if(day.length < 2)
		day = '0' + day;

	return [year, month, day].join('-');
}

$(document).on("click", "#commentReplyInsert", function(){

	const commentData = {
			commentNo : $("#commentReplyInsert").val(),
			commentGroupNo : $("#commentGroupNo").val(),
			commentIndent : $("#commentIndent").val(),
			commentContent : $("#commentReplyContent").val(),
			boardNo : $("#boardNo").val(),
			imageNo : $("#imageNo").val(),
	};
	
	if(commentData.commentContent == ""){
		alert("댓글을 입력해주세요");
		$("#commentReplyContent").focus();
	}else{

		const str = JSON.stringify(commentData)
	
		$.ajax({
			url: "/comment/commentReply",
			method: 'post',
			data: str,
			contentType: "application/json; charset=UTF-8",
			beforeSend: function(xhr){
				xhr.setRequestHeader(header, token);
			},
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
	$("#replyComment").remove();

	const RInput = $("#commentReplyContent").val();
	const Num = obj.attributes['value'].value;
	const GNum = $(".comment-box[value="+Num+"] .CommentGroupNo").val();
	const INum = $(".comment-box[value="+Num+"] .CommentIndent").val()

	if(RInput == null){
		$(".comment-box[value="+Num+"]").append(
				"<div id=\"replyComment\">" +
				"<input type=\"text\" id=\"commentReplyContent\" name=\"commentReplyContent\">" +
				"<button class=\"btn btn-outline-info btn-sm\" type=\"button\" id=\"commentReplyInsert\" value=\""+ Num +"\">"+"작성"+"</button>" +
				"<input type=\"hidden\" id=\"commentGroupNo\" value=\""+GNum+"\">"+
				"<input type=\"hidden\" id=\"commentIndent\" value=\""+INum+"\">"+
				"</div>"
		)
		$("#CommentReplyContent").focus();
	}
}


function delComment(obj){
	const commentNo = obj.attributes['value'].value;

	$.ajax({
		url:"/comment/commentDelete/" + commentNo,
		type:"delete",
		beforeSend: function(xhr){
			xhr.setRequestHeader(header, token);
		},
		success : function(data){
			if(data == 1) {
				location.reload();
			}else if(data == 0){
				alert('오류가 발생했습니다.\n문제가 계속되면 관리자에게 문의해주세요.');
			}else if(data == 1){
				alert('잘못된 요청입니다.\n문제가 계속되면 관리자에게 문의해주세요.');
			}
		},
		error : function(request, status, error){
			alert("code : "+request.status + "\n"
					+"message : "+request.responseText + "\n"
					+"error : "+error);
			
		}
	})
}