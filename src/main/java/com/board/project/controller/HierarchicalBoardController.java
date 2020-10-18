package com.board.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.project.mapper.HierarchicalBoardMapper;

@Controller
public class HierarchicalBoardController {

	@Autowired
	HierarchicalBoardMapper boardMapper;
	
	@RequestMapping("/BoardList")
	public String BoardList(Model model) throws Exception{
		
		model.addAttribute("boardList", boardMapper.BoardList());
		
		return "HierarchicalBoard/BoardList";
	}
	
	@RequestMapping("/BoardDetail/{bno}")
	public String BoardDetail(Model model) throws Exception{
		
		model.addAttribute("boardDetail", boardMapper.BoardDetail());
		
		return "HierarchicalBoard/BoardDetail";
	}
	
	@RequestMapping("/BoardModify/{bno}")
	public String BoardModify(Model model) throws Exception{
		
		model.addAttribute("boardModify", boardMapper.BoardModify());
		
		return "HierarchicalBoard/BoardModify";
	}
	
	@RequestMapping("/BoardModifyProc")
	public String BoardModifyProc() throws Exception{
		
		return "redirect:BoardDetail/{bno}";
	}
	
	@RequestMapping("/BoardInsert")
	public String BoardInsert() throws Exception{
		
		return "HierarchicalBoard/BoardInsert";
	}
	
	@RequestMapping("/BoardInsertProc")
	public String BoardInsertProc() throws Exception{
		
		return "redirect:BoardList";
	}
	
	@RequestMapping("/BoardReply/{bno}")
	public String BoardReply(Model model) throws Exception{
		
		model.addAttribute("boardReply", boardMapper.BoardReply());
		
		return "HierarchicalBoard/BoardReply";
	}
	
	@RequestMapping("/BoardReplyProc")
	public String BoardReplyProc() throws Exception{
		
		return "redirect:BoardList";
	}
}
