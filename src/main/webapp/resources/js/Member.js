$(document).ready(function(){
	
	$("#UserPw").keydown(function(key){
		if(key.keyCode == 13){
			$("#UserLogin").click();
		}
	})
})




$(function(){
	$('#UserLogin').click(function(){
		var id = $('#UserId').val();
		var pw = $('#UserPw').val();
		var form = document.getElementById('LoginForm');
		
		if(id == ""){
			$("#NullId").text("아이디를 입력하세요");
			$("#UserId").focus();
		}else if(pw == ""){
			$("#NullPw").text("비밀번호를 입력하세요");
			$("#UserPw").focus();
		}else{
			  form.action = "/board/LoginProc"; 
			 $("#LoginForm").submit();
		}
		})
});


$(function(){
	$("#IdCheck").click(function(){
		var UserId ={ 
			UserId : $("#UserId").val(), 
		};
		
		if(UserId.UserId == ""){
			$("#overlap").text("아이디를 입력하세요");
		}else if(UserId.UserId != "" && (UserId.UserId < "0" || UserId.UserId > "9") && (UserId.UserId < "A" || UserId.UserId > "Z") && (UserId.UserId < "a" || UserId.UserId > "z")){
			$("#overlap").text("한글 및 특수문자는 사용하실 수 없습니다."); 
			
		}else{
			alert("else");
			$.ajaxSettings.traditional = true;
			$.ajax({
	            url: "/board/CheckUserId",
	            type: "post",
	            data: UserId,
	            success : function(data){
	                if(data == 1){
	                	$("#overlap").text("사용중인 아이디입니다.");
	                }else{
	                	$("#Check").val("Check");
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
	$("#Join").click(function(){

	var id = $("#UserId").val();
	var pw = $("#UserPw").val();
	var Name = $("#UserName").val();
	var Check = $("#Check").val();
	
	if(id == ""){
		alert("아이디를 입력하세요")
	}else if(Check == ""){
		alerty("아이디 중복체크를 해주세요")
	}else if(pw == ""){
		alert("비밀번호를 입력해주세요")
	}else if(Name == ""){
		alert("이름을 입력해주세요")
	}else{
		
		$("#JoinForm").submit();
	}
	})
});

