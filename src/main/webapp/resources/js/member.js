const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function(){
	
	$("#userPw").keydown(function(key){
		if(key.keyCode == 13){
			$("#userLogin").click();
		}
	})
})

$(function(){
	$('#userLogin').click(function(e){
		e.preventDefault();
		const id = $('#userId').val();
		const pw = $('#userPw').val();
		
		if(id == ""){
			$("#nullId").text("아이디를 입력하세요");
			$("#userId").focus();
		}else if(pw == ""){
			$("#nullPw").text("비밀번호를 입력하세요");
			$("#userPw").focus();
		}else{
			 $("#loginForm").submit();
		}
	})
});


$(function(){
	$("#idCheck").click(function(){
		const userId = {
			userId : $("#userId").val(),
		};

		if(userId.userId == "")
			$("#overlap").text("아이디를 입력하세요");
		else if(userId.userId != "" && (userId.userId < "0" || userId.userId > "9") && (userId.userId < "A" || userId.userId > "Z") && (userId.userId < "a" || userId.userId > "z"))
			$("#overlap").text("한글 및 특수문자는 사용하실 수 없습니다.");
		else{
			$.ajaxSettings.traditional = true;
			$.ajax({
	            url: "/member/checkUserId",
	            type: "get",
	            data: userId,
				beforeSend: function(xhr){
					xhr.setRequestHeader(header, token);
				},
	            success : function(data){
	                if(data == 1){
	                	$("#overlap").text("사용중인 아이디입니다.");
	                }else{
	                	$("#check").val("Check");
	                	$("#overlap").text("사용가능한 아이디입니다.");
	                }
	            },
	            error : function(request, status, error){
					alert("code:" + request.status + "\n"
							+ "message : " + request.responseText
							+ "\n" + "error : " +error);
				}
	        });
		}
		})
});


$(function(){
	$("#join").click(function(){
		const id = $("#userId").val();
		const pw = $("#userPw").val();
		const Name = $("#userName").val();
		const Check = $("#check").val();
	
		if(id == "")
			alert("아이디를 입력하세요")
		else if(Check == "")
			alert("아이디 중복체크를 해주세요")
		else if(pw == "")
			alert("비밀번호를 입력해주세요")
		else if(Name == "")
			alert("이름을 입력해주세요")
		else
			$("#joinForm").submit();
	})
});

