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
		
		String id = (String) session.getAttribute("userId");
		
		commentVO.setUserId(id);
		commentVO.setCommentContent(commentData.get("CommentContent").toString());
		commentVO.setCommentGroupNo(Integer.parseInt(commentData.get("CommentGroupNo").toString()));
		commentVO.setCommentIndent(Integer.parseInt(commentData.get("CommentIndent").toString())+1);
		commentVO.setCommentUpperNo(Integer.parseInt(commentData.get("CommentNo").toString()));
		
		if(commentData.get("BoardNo") == null) {
			commentVO.setImageNo(Integer.parseInt(commentData.get("ImageNo").toString()));
		}else {
			commentVO.setBoardNo(Integer.parseInt(commentData.get("BoardNo").toString()));
		}
		
		commentMapper.commentReply(commentVO);
		
		return;
	}

	@Override
	public void commentInsert(Map<String, Object> CommentContent, HttpSession session, CommentVO commentVO)
			throws Exception {
	
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
		}else {
			commentVO.setBoardNo(BoardNo);
		}
		
		commentMapper.commentDeleteBoard(commentVO);
		
		
		
	}

	
	


}
