package com.board.project.controller;

import javax.servlet.http.HttpServletRequest;

import com.board.project.domain.dto.Criteria;
import com.board.project.domain.dto.HierarchicalBoardModifyDTO;
import com.board.project.domain.dto.HierarchicalBoardReplyDTO;
import com.board.project.domain.dto.PageDTO;
import com.board.project.domain.entity.Comment;
import com.board.project.domain.entity.HierarchicalBoard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.board.project.mapper.CommentMapper;
import com.board.project.mapper.HierarchicalBoardMapper;
import com.board.project.service.HierarchicalBoardService;

import java.security.Principal;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class HierarchicalBoardController {

	private final HierarchicalBoardMapper boardMapper;

	private final CommentMapper commentMapper;

	private final HierarchicalBoardService boardService;

	//계층형 게시판 리스트
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public String boardList(Model model, Criteria cri) throws Exception{

		model.addAttribute("list", boardMapper.searchPage(cri));

		log.info("search Page");

		int totalPages = (int) (Math.ceil((boardMapper.listCount(cri) * 1.0) / cri.getBoardAmount()));

		log.info("total : " + totalPages);

		model.addAttribute("pageMaker", new PageDTO(cri, totalPages));

		log.info("pageMaker");

		log.info("cri : " + cri.getKeyword() + ", type : " + cri.getSearchType());

		return "board/boardList";
	}

	//계층형 게시판 상세페이지
	@RequestMapping(value = "/boardDetail", method = RequestMethod.GET)
	public String boardDetail(@RequestParam("boardNo") int boardNo , Model model, Criteria cri) throws Exception{

		Comment comment = Comment.builder().boardNo(boardNo).build();
			
		model.addAttribute("boardDetail", boardMapper.boardDetail(boardNo));
		model.addAttribute("comment", commentMapper.commentList(comment, cri));

		int totalPages = (int) (Math.ceil((commentMapper.cListCount(comment) * 1.0) / cri.getBoardAmount()));

		model.addAttribute("pageMaker", new PageDTO(cri, totalPages));
		
		return "board/boardDetail";
	}

	//계층형 게시판 게시글 수정 페이지
	@RequestMapping(value = "/boardModify", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public String boardModify(Model model
								, @RequestParam("boardNo") int boardNo
								, Principal principal) {

		HierarchicalBoardModifyDTO dto = boardService.getModifyBoardData(boardNo, principal);

		if(dto == null)
			return "/accessError";

		model.addAttribute("boardModify", dto);
		
		return "board/boardModify";
	}

	//계층형 게시판 게시글 수정 처리
	@RequestMapping(value = "/boardModifyProc", method = RequestMethod.PATCH)
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public String boardModifyProc(HttpServletRequest request
									, Principal principal) {

		int result = boardService.boardModifyProc(request, principal);

		if(result <= 0)
			return "/accessError";
		else
			return "redirect:/board/boardDetail?boardNo=" + result;
		
	}

	//계층형 게시판 게시글 삭제
	@RequestMapping(value = "/boardDelete/{boardNo}", method = RequestMethod.DELETE)
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public int boardDelete(@PathVariable("boardNo") int boardNo, Principal principal) throws Exception{


		return boardService.boardDelete(boardNo, principal);
	}

	//계층형 게시판 작성 페이지
	@RequestMapping(value = "/boardInsert", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public String boardInsert() throws Exception{
		
		return "board/boardInsert";
	}

	//계층형 게시판 작성 처리
	@RequestMapping(value = "/boardInsertProc", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public String boardInsertProc(HttpServletRequest request, Principal principal) throws Exception{

		if(boardService.boardInsertProc(request, principal) == 1)
			return "redirect:/board/boardList";
		else
			return "/accessError";

	}

	//계층형 게시판 답글 페이지
	@RequestMapping(value = "/boardReply", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public String boardReply(Model model, @RequestParam("boardNo") int boardNo) throws Exception{

		HierarchicalBoardReplyDTO dto = boardService.getBoardReplyData(boardNo);

		if(dto == null)
			return "/accessError";

		model.addAttribute("boardReply", dto);
		
		return "board/boardReply";
	}

	//계층형 게시판 답글 처리
	@RequestMapping(value = "/boardReplyProc", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public String boardReplyProc(HttpServletRequest request
									, Principal principal) {
		
		if(boardService.boardReplyProc(request, principal) == 1)
			return "redirect:/board/boardList";
		else
			return "/accessError";

	}
}
