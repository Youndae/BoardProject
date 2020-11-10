package com.board.project.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.project.mapper.CommentMapper;
import com.board.project.service.CommentService;
import com.board.project.vo.CommentVO;

@Controller
public class CommentController {
	
	@Autowired
	CommentService commentService;

	@Autowired
	CommentMapper commentMapper;
	
	
	@RequestMapping("/CommentInsert")
	@ResponseBody
	public void CommentInsert(@RequestBody Map<String, Object> CommentContent, HttpSession session, CommentVO commentVO) throws Exception{
		
		commentService.commentInsert(CommentContent, session, commentVO);
		
	}
	
	
	@RequestMapping("/CommentReply")
	@ResponseBody
	public void CommentReply(@RequestBody Map<String, Object> commentData, HttpSession session, CommentVO commentVO) throws Exception{
		
		commentService.commentReply(commentData, commentVO, session);
		
	}
	
	@RequestMapping("/CommentDelete")
	@ResponseBody
	public void ComentDelete(@RequestParam("CommentNo") String CommentNo)throws Exception{
		
		commentMapper.commentDelete(Integer.parseInt(CommentNo));
		
	}
	
}

