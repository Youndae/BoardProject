function Modify(){
	var boardNo = document.getElementById("boardNo").value;
	console.log(boardNo);
	alert("boardNo : "+boardNo);
	location.href = "/controller/BoardModify?boardNo="+boardNo;
}

function DeleteBoard(){
	var boardNo = docuemnt.getElementById("boardNo").value;
	location.href = "/controller/BoardDelete?boardNo="+boardNo;
}

function Reply(){
	var boardNo = document.getElementById("boardNo").value;
	location.href= "/controller/BoardReply?boardNo="+boardNo;
}

