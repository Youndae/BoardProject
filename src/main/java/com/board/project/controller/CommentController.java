package com.board.project.controller;

import java.security.Principal;
import java.util.Map;

import com.board.project.domain.ResultProperties;
import com.board.project.domain.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.board.project.service.CommentService;

@Controller
@RequestMapping("/comment")
@Slf4j
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	//댓글 insert
	@RequestMapping(value = "/commentInsert", method = RequestMethod.POST)
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public void commentInsert(@RequestBody Map<String, Object> commentContent
								, Principal principal){

		log.info("commentInsert");
		commentService.commentInsert(commentContent, principal);
	}

	// 대댓글 insert
	@RequestMapping(value = "/commentReply", method = RequestMethod.POST)
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public void commentReply(@RequestBody Map<String, Object> commentData
								, Principal principal){
		
		commentService.commentReply(commentData, principal);
		
	}

	//댓글 delete
	@RequestMapping(value = "/commentDelete/{commentNo}", method = RequestMethod.DELETE)
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public int commentDelete(@PathVariable("commentNo") int commentNo, Principal principal){

		return commentService.deleteComment(commentNo, principal);
	}

	//계층형 게시판 댓글 리스트 조회
	@RequestMapping(value = "/boardComment/{boardNo}/{pageNum}", method = RequestMethod.GET)
	public ResponseEntity<CommentDTO> boardCommentList(@PathVariable("boardNo") int boardNo
														, @PathVariable("pageNum") int pageNum
														, Principal principal) throws Exception {

		return new ResponseEntity<>(commentService.boardCommentList(boardNo, pageNum, principal), HttpStatus.OK);
	}

	//이미지 게시판 댓글 리스트 조회
	@RequestMapping(value = "/imageComment/{imageNo}/{pageNum}", method = RequestMethod.GET)
	public ResponseEntity<CommentDTO> imageCommentList(@PathVariable("imageNo") int imageNo
														, @PathVariable("pageNum") int pageNum
														, Principal principal) throws Exception {

		log.info("imageCommentList");

		return new ResponseEntity<>(commentService.imageCommentList(imageNo, pageNum, principal), HttpStatus.OK);
	}

	
}

