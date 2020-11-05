package com.board.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.project.mapper.CommentMapper;
import com.board.project.vo.CommentVO;

@Controller
public class CommentController {
	
	@Autowired
	CommentMapper commentMapper;
	
	@RequestMapping("/CommentInsert")
	@ResponseBody
	public void CommentInsert(@RequestParam("CommentContent") String CommentContent, @RequestParam(value="BoardNo", required = false) Integer BoardNo, @RequestParam(value="ImageNo", required = false) Integer ImageNo , HttpServletRequest request, HttpSession session, CommentVO commentVO) throws Exception{
		String id = (String) session.getAttribute("userId");
		System.out.println("id : "+id);
		System.out.println("Content : "+CommentContent);
		System.out.println("BoardNo : "+BoardNo);
		System.out.println("ImageNo : "+ImageNo);
		commentVO.setUserId(id);
		commentVO.setBoardNo(BoardNo);
		commentVO.setImageNo(ImageNo); 
		commentVO.setCommentContent(CommentContent);
		
		 commentMapper.commentInsert(commentVO); 
	}
	
	
	@RequestMapping("/CommentReply")
	@ResponseBody
	public void CommentReply(@RequestParam("commentData") List<String> commentData, HttpSession session, Model model) throws Exception{
		System.out.println("Data : "+commentData);
		
		
		
	}
}

