package com.board.project.controller;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
	public void CommentReply(@RequestBody Map<String, Object> commentData, HttpSession session, Model model, CommentVO commentVO) throws Exception{
		System.out.println("Data : "+commentData);
		System.out.println("BoardNo : "+commentData.get("BoardNo"));
		System.out.println("CommentNo : "+commentData.get("CommentNo"));
		System.out.println("CommentGroupNo : "+commentData.get("CommentGroupNo"));
		System.out.println("CommentIndent : "+commentData.get("CommentIndent"));
		System.out.println("CommentContent : "+commentData.get("CommentContent"));
		
		String id = (String) session.getAttribute("userId");
		
		commentVO.setUserId(id);
		commentVO.setCommentContent(commentData.get("CommentContent").toString());
		commentVO.setBoardNo(Integer.parseInt(commentData.get("BoardNo").toString()));
		commentVO.setCommentGroupNo(Integer.parseInt(commentData.get("CommentGroupNo").toString()));
		commentVO.setCommentIndent(Integer.parseInt(commentData.get("CommentIndent").toString())+1);
		commentVO.setCommentUpperNo(Integer.parseInt(commentData.get("CommentNo").toString()));
		
		commentMapper.commentReply(commentVO);
		
	}
}

