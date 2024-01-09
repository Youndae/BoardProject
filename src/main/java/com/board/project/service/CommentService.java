package com.board.project.service;

import java.security.Principal;
import java.util.Map;

import com.board.project.domain.dto.CommentDTO;
import com.board.project.domain.entity.Comment;


public interface CommentService {
	
	public int commentReply(Map<String, Object> commentData, Principal principal);
	
	public int commentInsert(Map<String, Object> CommentContent, Principal principal);

	public int deleteComment(int commentNo, Principal principal);

	public CommentDTO boardCommentList(int boardNo, int pageNum, Principal principal) throws Exception;

	public CommentDTO imageCommentList(int imageNo, int pageNum, Principal principal) throws Exception;
	
	
}
