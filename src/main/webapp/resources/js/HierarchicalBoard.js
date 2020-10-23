function Modify(){
	var boardNo = document.getElementById("boardNo").value;
	console.log(boardNo);
	alert("boardNo : "+boardNo);
	location.href = "/board/BoardModify?boardNo="+boardNo;
}

function DeleteBoard(){
	var boardNo = document.getElementById("boardNo").value;
	alert("boardNo : "+boardNo);
	location.href = "/board/BoardDelete?boardNo="+boardNo;
}

function Reply(){
	var boardNo = document.getElementById("boardNo").value;
	location.href= "/board/BoardReply?boardNo="+boardNo;
}


