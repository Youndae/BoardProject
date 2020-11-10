package com.board.project.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.project.mapper.CommentMapper;
import com.board.project.vo.CommentVO;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentMapper commentMapper;
	
	@Override
	public void commentReply(Map<String, Object> commentData, CommentVO commentVO, HttpSession session) throws Exception {
		
		System.out.println("impl Hi!");
		
		String id = (String) session.getAttribute("userId");
		
		commentVO.setUserId(id);
		System.out.println("id : "+id);
		commentVO.setCommentContent(commentData.get("CommentContent").toString());
		System.out.println("content : "+commentVO.getCommentContent());
		commentVO.setCommentGroupNo(Integer.parseInt(commentData.get("CommentGroupNo").toString()));
		System.out.println("groupNO : "+commentVO.getCommentGroupNo());
		commentVO.setCommentIndent(Integer.parseInt(commentData.get("CommentIndent").toString())+1);
		System.out.println("Indent : "+commentVO.getCommentIndent());
		commentVO.setCommentUpperNo(Integer.parseInt(commentData.get("CommentNo").toString()));
		System.out.println("commentNo : "+commentVO.getCommentNo());
		
		if(commentData.get("BoardNo") == null) {
			commentVO.setImageNo(Integer.parseInt(commentData.get("ImageNo").toString()));
			System.out.println("ImageNo"+commentVO.getImageNo()+"'s In!");
		}else {
			commentVO.setBoardNo(Integer.parseInt(commentData.get("BoardNo").toString()));
			System.out.println("BoardNo"+commentVO.getBoardNo()+"'s In!");
		}
		
		commentMapper.commentReply(commentVO);
		
		return;
	}

	@Override
	public void commentInsert(Map<String, Object> CommentContent, HttpSession session, CommentVO commentVO)
			throws Exception {
		
		System.out.println("impl Insert!");
		String id = (String) session.getAttribute("userId");
		
		commentVO.setUserId(id);
		commentVO.setCommentContent(CommentContent.get("CommentContent").toString());
		
		if(CommentContent.get("BoardNo") == null) {
			commentVO.setImageNo(Integer.parseInt(CommentContent.get("ImageNo").toString()));
		}else {
			commentVO.setBoardNo(Integer.parseInt(CommentContent.get("BoardNo").toString()));
		}
		
		commentMapper.commentInsert(commentVO);
		return;
	}

	@Override
	public void commentDeleteBoard(Integer ImageNo, Integer BoardNo) throws Exception {
		
		CommentVO commentVO = new CommentVO();
		
		if(BoardNo == null) {
		
		commentVO.setImageNo(ImageNo);
		System.out.println("commentDelte "+commentVO.getImageNo()+"'s Delete");
		}else {
			commentVO.setBoardNo(BoardNo);
			System.out.println("commentDelte "+commentVO.getBoardNo()+"'s Delete");
		}
		
		commentMapper.commentDeleteBoard(commentVO);
		
		
		
	}

	
	


}
