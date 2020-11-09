package com.board.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.project.mapper.CommentMapper;
import com.board.project.mapper.HierarchicalBoardMapper;
import com.board.project.service.CommentService;
import com.board.project.vo.CommentVO;
import com.board.project.vo.Criteria;
import com.board.project.vo.HierarchicalBoardVO;
import com.board.project.vo.PageMaker;
import com.board.project.vo.PagingVO;
import com.board.project.vo.SearchCriteria;

@Controller
public class HierarchicalBoardController {

	@Autowired
	HierarchicalBoardMapper boardMapper;
	
	@Autowired
	CommentMapper commentMapper;
	
	@Autowired
	CommentService commentService;
	
	@RequestMapping(value = "/BoardList3", method = RequestMethod.GET)
	public String BoardList3(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception{
		//완성.
		model.addAttribute("list", boardMapper.SearchPage(scri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(boardMapper.ListCount(scri));
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "HierarchicalBoard/BoardList3";
	}
	
	@RequestMapping(value = "/BoardList4", method = RequestMethod.GET)
	public String BoardList4(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception{
		// Test
		/* model.addAttribute("list", boardMapper.SearchPage(scri)); */
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(boardMapper.ListCount(scri));
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "HierarchicalBoard/BoardList4";
	}
	
	@RequestMapping("/BAttachList")
	@ResponseBody
	public ResponseEntity<List<HierarchicalBoardVO>> BAttachList(@ModelAttribute("scri") SearchCriteria scri) throws Exception{
		
		return new ResponseEntity<>(boardMapper.SearchPage(scri), HttpStatus.OK);
	}
	
	
	
	@RequestMapping("/BoardList2")
	public String BoardList2(PagingVO vo, Model model
			,@RequestParam(value="nowPage", required = false) String nowPage
			,@RequestParam(value="cntPerPage", required = false) String cntPerPage) throws Exception{
		
		int total = boardMapper.countBoard();
		System.out.println("total: "+total);
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		}else if(nowPage == null) {
			nowPage = "1";
		}else if(cntPerPage == null) {
			cntPerPage = "5";
		}
		vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		model.addAttribute("paging", vo);
		model.addAttribute("viewAll", boardMapper.selectBoard(vo));
		
		return "HierarchicalBoard/BoardList2";
	}
	
	
	
	
	
	@RequestMapping("/BoardList")
	public String BoardList(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception{
		
		model.addAttribute("boardList", boardMapper.SearchPage(scri));
		System.out.println("boardList : "+boardMapper.SearchPage(scri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(boardMapper.ListCount(scri));
		
		model.addAttribute("pageMaker", pageMaker);
		

		return "HierarchicalBoard/BoardList";
	}
	
	@RequestMapping("/BoardDetail")
	public String BoardDetail(Criteria cri, Model model, @RequestParam("boardNo") int boardNo, CommentVO commentVO) throws Exception{
		System.out.println("boardNo : "+boardNo);
		commentVO.setBoardNo(boardNo);
		System.out.println("RowStart : "+cri.getRowStart());
		System.out.println("RowEnd : "+cri.getRowEnd());
		
		model.addAttribute("boardDetail", boardMapper.BoardDetail(boardNo));
		model.addAttribute("comment", commentMapper.bCommentList(cri, boardNo));
		
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(commentMapper.cListCount(boardNo));
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "HierarchicalBoard/BoardDetail";
	}
	
	@RequestMapping("/BoardModify")
	public String BoardModify(Model model, @RequestParam("boardNo") int boardNo) throws Exception{
		
		model.addAttribute("boardModify", boardMapper.BoardDetail(boardNo));
		
		return "HierarchicalBoard/BoardModify";
	}
	
	@RequestMapping("/BoardModifyProc")
	public String BoardModifyProc(HierarchicalBoardVO boardVO, HttpServletRequest request) throws Exception{
		
		
		boardVO.setBoardTitle(request.getParameter("BoardTitle"));
		System.out.println("title : "+boardVO.getBoardTitle());
		boardVO.setBoardNo(Integer.parseInt(request.getParameter("BoardNo")));
		System.out.println("boardNo : "+boardVO.getBoardNo());
		boardVO.setBoardContent(request.getParameter("BoardContent"));
		System.out.println("content : "+boardVO.getBoardContent());
		
		boardMapper.BoardModifyProc(boardVO);
		
		return "redirect:/BoardDetail?boardNo="+request.getParameter("BoardNo");
	}
	
	@RequestMapping("/BoardDelete")
	public String BoardDelete(@RequestParam("boardNo") int boardNo) throws Exception{
		System.out.println("boardNo : "+boardNo);
		boardMapper.BoardDelete(boardNo);
		
		commentService.commentDeleteBoard(null, boardNo);
		
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
		System.out.println("id : "+id);
		boardVO.setUserId(id);
		boardVO.setBoardTitle(request.getParameter("BoardTitle"));
		System.out.println("title : "+boardVO.getBoardTitle());
		boardVO.setBoardContent(request.getParameter("BoardContent"));
		System.out.println("content : "+boardVO.getBoardContent());
		boardVO.setBoardUpperNo(Integer.parseInt(request.getParameter("BoardNo")));
		System.out.println("boardNo : "+boardVO.getBoardUpperNo());
		boardVO.setBoardGroupNo(Integer.parseInt(request.getParameter("BoardGroupNo")));
		System.out.println("groupNo : "+boardVO.getBoardGroupNo());
		boardVO.setBoardIndent(Integer.parseInt(request.getParameter("BoardIndent"))+1);
		System.out.println("indent : "+boardVO.getBoardIndent());
		boardMapper.BoardReplyProc(boardVO);
		
		return "redirect:BoardList";
	}
}
