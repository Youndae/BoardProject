package com.board.project.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.board.project.vo.CommentVO;


public interface CommentService {
	
	public void commentReply(Map<String, Object> commentData, CommentVO commentVO, HttpSession session) throws Exception;
	
	public void commentInsert(Map<String, Object> CommentContent, HttpSession session, CommentVO commentVO) throws Exception;
	
	public void commentDeleteBoard(Integer ImageNo, Integer BoardNo) throws Exception;
	
	
}
