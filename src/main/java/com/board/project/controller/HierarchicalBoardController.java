package com.board.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.project.mapper.HierarchicalBoardMapper;
import com.board.project.vo.HierarchicalBoardVO;
import com.board.project.vo.MemberVO;

@Controller
public class HierarchicalBoardController {

	@Autowired
	HierarchicalBoardMapper boardMapper;
	
	@RequestMapping("/BoardList")
	public String BoardList(Model model, HttpSession session, MemberVO memberVO) throws Exception{
		
		model.addAttribute("boardList", boardMapper.BoardList());

		return "HierarchicalBoard/BoardList";
	}
	
	@RequestMapping("/BoardDetail")
	public String BoardDetail(Model model, @RequestParam("boardNo") int boardNo) throws Exception{
		
		model.addAttribute("boardDetail", boardMapper.BoardDetail(boardNo));
		
		return "HierarchicalBoard/BoardDetail";
	}
	
	@RequestMapping("/BoardModify")
	public String BoardModify(Model model, @RequestParam("boardNo") int boardNo) throws Exception{
		
		model.addAttribute("boardModify", boardMapper.BoardDetail(boardNo));
		
		return "HierarchicalBoard/BoardModify";
	}
	
	@RequestMapping("/BoardModifyProc")
	public String BoardModifyProc(HierarchicalBoardVO boardVO, HttpServletRequest request) throws Exception{
		
		
		boardVO.setBoardTitle(request.getParameter("boardTitle"));
		boardVO.setBoardNo(Integer.parseInt(request.getParameter("boardNo")));
		boardVO.setBoardContent(request.getParameter("boardContent"));
		
		boardMapper.BoardModifyProc(boardVO);
		
		return "redirect:/BoardDetail?boardNo="+request.getParameter("boardNo");
	}
	
	@RequestMapping("/BoardDelete")
	public String BoardDelete(@RequestParam("boardNo") int boardNo) throws Exception{
		System.out.println("boardNo : "+boardNo);
		boardMapper.BoardDelete(boardNo);
		
		return "redirect:BoardList";
	}
	
	@RequestMapping("/BoardInsert")
	public String BoardInsert() throws Exception{
		
		return "HierarchicalBoard/BoardInsert";
	}
	
	@RequestMapping("/BoardInsertProc")
	public String BoardInsertProc(HierarchicalBoardVO boardVO, HttpServletRequest request, HttpSession session) throws Exception{
		String id = (String) session.getAttribute("userId");
		System.out.println("Insert id : "+id);
		boardVO.setUserId(id);
		boardVO.setBoardTitle(request.getParameter("boardTitle"));
		boardVO.setBoardContent(request.getParameter("boardContent"));
		
		boardMapper.BoardInsertProc(boardVO);
		
		return "redirect:BoardList";
	}
	
	@RequestMapping("/BoardReply")
	public String BoardReply(Model model, @RequestParam("boardNo") int boardNo) throws Exception{
		
		model.addAttribute("boardReply", boardMapper.BoardDetail(boardNo));
		
		return "HierarchicalBoard/BoardReply";
	}
	
	@RequestMapping("/BoardReplyProc")
	public String BoardReplyProc(HierarchicalBoardVO boardVO, HttpSession session, HttpServletRequest request) throws Exception{
		
		String id = (String) session.getAttribute("userId");
		
		boardVO.setUserId(id);
		boardVO.setBoardTitle(request.getParameter("boardTitle"));
		boardVO.setBoardContent(request.getParameter("boardContent"));
		boardVO.setBoardUpperNo(Integer.parseInt(request.getParameter("boardNo")));
		boardVO.setBoardGroupNo(Integer.parseInt(request.getParameter("boardGroupNo")));
		boardVO.setBoardIndent(Integer.parseInt(request.getParameter("boardIndent")+1));
		
		boardMapper.BoardReplyProc(boardVO);
		
		return "redirect:BoardList";
	}
}
