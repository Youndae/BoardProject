package com.board.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.project.mapper.HierarchicalBoardMapper;

@Controller
public class HierarchicalBoardController {

	@Autowired
	HierarchicalBoardMapper boardMapper;
	
	@RequestMapping("/BoardList")
	public String BoardList(Model model) throws Exception{
		
		model.addAttribute("boardList", boardMapper.BoardList());
		
		System.out.println("List : "+boardMapper.BoardList());
		
		return "HierarchicalBoard/BoardList";
	}
	
	@RequestMapping("/BoardDetail")
	public String BoardDetail(Model model, @RequestParam("boardNo") int boardNo) throws Exception{
		System.out.println("boardNo : "+boardNo);
		model.addAttribute("boardDetail", boardMapper.BoardDetail(boardNo));
		
		System.out.println("data: "+boardMapper.BoardDetail(boardNo));
		
		return "HierarchicalBoard/BoardDetail";
	}
	
	@RequestMapping("/BoardModify")
	public String BoardModify(Model model, @RequestParam("boardNo") int boardNo) throws Exception{
		
		model.addAttribute("boardModify", boardMapper.BoardModify(boardNo));
		
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
	
	@RequestMapping("/BoardReply")
	public String BoardReply(Model model, @RequestParam("boardNo") int boardNo) throws Exception{
		
		model.addAttribute("boardReply", boardMapper.BoardDetail(boardNo));
		
		return "HierarchicalBoard/BoardReply";
	}
	
	@RequestMapping("/BoardReplyProc")
	public String BoardReplyProc() throws Exception{
		
		return "redirect:BoardList";
	}
}
