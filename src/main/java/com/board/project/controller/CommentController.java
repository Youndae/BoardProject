package com.board.project.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
		/*
		 * String id = (String) session.getAttribute("userId"); 
		 * commentVO.setUserId(id);
		 * System.out.println("id : "+id); 
		 * commentVO.setBoardNo(BoardNo);
		 * System.out.println("BoardNo : "+BoardNo); 
		 * commentVO.setImageNo(ImageNo);
		 * System.out.println("ImageNo : "+ImageNo);
		 * commentVO.setCommentContent(CommentContent);
		 * System.out.println("Content : "+CommentContent);
		 */
		
		/* commentMapper.commentInsert(commentVO); */
		
		commentService.commentInsert(CommentContent, session, commentVO);
	}
	
	
	@RequestMapping("/CommentReply")
	@ResponseBody
	public void CommentReply(@RequestBody Map<String, Object> commentData, HttpSession session, CommentVO commentVO) throws Exception{
		/*
		 * System.out.println("Data : "+commentData);
		 * System.out.println("BoardNo : "+commentData.get("BoardNo"));
		 * System.out.println("CommentNo : "+commentData.get("CommentNo"));
		 * System.out.println("CommentGroupNo : "+commentData.get("CommentGroupNo"));
		 * System.out.println("CommentIndent : "+commentData.get("CommentIndent"));
		 * System.out.println("CommentContent : "+commentData.get("CommentContent"));
		 * System.out.println("ImageNo : "+commentData.get("ImageNo"));
		 * 
		 * String id = (String) session.getAttribute("userId");
		 * 
		 * commentVO.setUserId(id);
		 * commentVO.setCommentContent(commentData.get("CommentContent").toString());
		 * commentVO.setBoardNo(Integer.parseInt(commentData.get("BoardNo").toString()))
		 * ;
		 * commentVO.setBoardNo(Integer.parseInt(commentData.get("ImageNo").toString()))
		 * ;
		 * commentVO.setCommentGroupNo(Integer.parseInt(commentData.get("CommentGroupNo"
		 * ).toString()));
		 * commentVO.setCommentIndent(Integer.parseInt(commentData.get("CommentIndent").
		 * toString())+1);
		 * commentVO.setCommentUpperNo(Integer.parseInt(commentData.get("CommentNo").
		 * toString()));
		 * 
		 * commentMapper.commentReply(commentVO);
		 */
		System.out.println("hihihihi");
		commentService.commentReply(commentData, commentVO, session);
		
	}
	
	@RequestMapping("/CommentDelete")
	@ResponseBody
	public void ComentDelete(@RequestParam("commentNo") String CommentNo)throws Exception{
		
		commentMapper.commentDelete(Integer.parseInt(CommentNo));
		
	}
}

