package com.board.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.project.mapper.ImageBoardMapper;

@Controller
public class ImageBoardController {
	
	@Autowired
	ImageBoardMapper imageBoardMapper;
	
	@RequestMapping("/imageList")
	public String imageList(Model model) throws Exception{
		
		
		
		return "ImageBoard/ImageList";
	}
	
	@RequestMapping("/imageDetail/{bno}")
	public String imageDetail(Model model) throws Exception{
		
		return "ImageBoard/ImageDetail";
	}
	
	@RequestMapping("/imageModify/{bno}")
	public String imageModify(Model model) throws Exception{
		
		return "ImageBoard/ImageModify";
	}
	
	@RequestMapping("/imageModifyProc")
	public String imageModifyProc() throws Exception{
		
		return "redirect:imageDetail/{bno}";
	}
	
	@RequestMapping("/imageInsert")
	public String imageInsert() throws Exception{
		
		return "ImageBoard/ImageInsert";
	}
	
	@RequestMapping("/imageInsertProc")
	public String imageInsertProc() throws Exception{
		
		return "redirect:imageList";
	}

}
