function UserLogin(){
	
	
	var id = $("#userId").val();
	var pw = $("#userPw").val();
	var form = document.getElementById("LoginForm"); 
	
	alert("userId : "+id+", pw : "+pw);
	
	
	if(id == ""){
		alert("아이디를 입력하세요");
		$("#NullId").text("아이디를 입력하세요");
		$("#userId").focus();
	}else if(pw == ""){
		alert("비밀번호를 입력하세요");
		$("#NullPw").text("비밀번호를 입력하세요");
		$("#userPw").focus();
	}else{
		  form.action = "/board/LoginProc"; 
		 $("#LoginForm").submit();
	}
}



function IdCheck(){
	var userId ={ userId : $("#userId").val(), };
	
	alert("userId : "+userId.userId);
	
	if(userId.userId == ""){
		$("#overlap").text("아이디를 입력하세요");
	}else if(userId.userId != "" && (userId.userId < "0" || userId.userId > "9") && (userId.userId < "A" || userId.userId > "Z") && (userId.userId < "a" || userId.userId > "z")){
		$("#overlap").text("한글 및 특수문자는 사용하실 수 없습니다."); 
		
	}else{
		alert("else");
		$.ajaxSettings.traditional = true;
		$.ajax({
            url: "/board/CheckUserId",
            type: "post",
            data: userId,
            success : function(data){
                if(data == 1){
                	$("#overlap").text("사용중인 아이디입니다.");
                }else{
                	$("#check").val("check");
                	var test = $("#check").val();
                	alert("test : "+test)
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
};


function Join(){
	alert("Join");
	var id = $("#userId").val();
	var pw = $("#userPw").val();
	var Name = $("#userName").val();
	var check = $("#check").val();
	
	
	if(id == ""){
		alert("아이디를 입력하세요")
	}else if(check == ""){
		alerty("아이디 중복체크를 해주세요")
	}else if(pw == ""){
		alert("비밀번호를 입력해주세요")
	}else if(Name == ""){
		alert("이름을 입력해주세요")
	}else{
		
		$("#JoinForm").submit();
	}
}
