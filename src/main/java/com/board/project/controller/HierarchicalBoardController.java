package com.board.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.project.mapper.CommentMapper;
import com.board.project.mapper.HierarchicalBoardMapper;
import com.board.project.service.CommentService;
import com.board.project.service.HierarchicalBoardService;
import com.board.project.vo.CommentVO;
import com.board.project.vo.HierarchicalBoardVO;
import com.board.project.vo.PageMaker;
import com.board.project.vo.SearchCriteria;

@Controller
public class HierarchicalBoardController {

	@Autowired
	HierarchicalBoardMapper boardMapper;
	
	@Autowired
	CommentMapper commentMapper;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	HierarchicalBoardService boardService;
	
	@RequestMapping(value = "/BoardList", method = RequestMethod.GET)
	public String BoardList(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception{
	
		model.addAttribute("list", boardMapper.searchPage(scri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(boardMapper.listCount(scri));
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "HierarchicalBoard/BoardList";
	}
	
	@RequestMapping("/BoardDetail")
	public String BoardDetail(@ModelAttribute("commentVO")CommentVO commentVO , @RequestParam("BoardNo") int BoardNo , Model model) throws Exception{
		
		commentVO.setBoardNo(BoardNo);
			
		model.addAttribute("boardDetail", boardMapper.boardDetail(BoardNo));
		model.addAttribute("comment", commentMapper.commentList(commentVO)); 
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(commentVO);
		pageMaker.setTotalCount(commentMapper.cListCount(commentVO));
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "HierarchicalBoard/BoardDetail";
	}
	
	@RequestMapping("/BoardModify")
	public String BoardModify(Model model, @RequestParam("BoardNo") int BoardNo) throws Exception{
		
		model.addAttribute("boardModify", boardMapper.boardDetail(BoardNo));
		
		return "HierarchicalBoard/BoardModify";
	}
	
	@RequestMapping("/BoardModifyProc")
	public String BoardModifyProc(HierarchicalBoardVO boardVO, HttpServletRequest request) throws Exception{
		
		
		boardVO.setBoardTitle(request.getParameter("BoardTitle"));
		boardVO.setBoardNo(Integer.parseInt(request.getParameter("BoardNo")));
		boardVO.setBoardContent(request.getParameter("BoardContent"));
		
		
		boardMapper.boardModifyProc(boardVO);
		
		return "redirect:/BoardDetail?BoardNo="+request.getParameter("BoardNo");
	}
	
	@RequestMapping("/BoardDelete")
	public String BoardDelete(@RequestParam("BoardNo") int BoardNo) throws Exception{
		
		commentService.commentDeleteBoard(null, BoardNo);
		
		boardService.BoardDelete(BoardNo);
		
		
		return "redirect:BoardList";
	}
	
	@RequestMapping("/BoardInsert")
	public String BoardInsert() throws Exception{
		
		return "HierarchicalBoard/BoardInsert";
	}
	
	@RequestMapping("/BoardInsertProc")
	public String BoardInsertProc(HierarchicalBoardVO boardVO, HttpServletRequest request, HttpSession session) throws Exception{
		String id = (String) session.getAttribute("userId");
		
		boardVO.setUserId(id);
		boardVO.setBoardTitle(request.getParameter("boardTitle"));
		boardVO.setBoardContent(request.getParameter("boardContent"));
		
		boardMapper.boardInsertProc(boardVO);
		
		return "redirect:BoardList";
	}
	
	@RequestMapping("/BoardReply")
	public String BoardReply(Model model, @RequestParam("BoardNo") int BoardNo) throws Exception{
		
		model.addAttribute("boardReply", boardMapper.boardDetail(BoardNo));
		
		return "HierarchicalBoard/BoardReply";
	}
	
	@RequestMapping("/BoardReplyProc")
	public String BoardReplyProc(HierarchicalBoardVO boardVO, HttpSession session, HttpServletRequest request) throws Exception{
		
		String id = (String) session.getAttribute("userId");
		
		boardVO.setUserId(id);
		boardVO.setBoardTitle(request.getParameter("BoardTitle"));
		boardVO.setBoardContent(request.getParameter("BoardContent"));
		boardVO.setBoardUpperNo(Integer.parseInt(request.getParameter("BoardNo")));
		boardVO.setBoardGroupNo(Integer.parseInt(request.getParameter("BoardGroupNo")));
		boardVO.setBoardIndent(Integer.parseInt(request.getParameter("BoardIndent"))+1);
		
		boardMapper.boardReplyProc(boardVO);
		
		return "redirect:BoardList";
	}
}
