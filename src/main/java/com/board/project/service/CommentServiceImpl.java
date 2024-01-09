package com.board.project.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.board.project.domain.ResultProperties;
import com.board.project.domain.dto.CommentDTO;
import com.board.project.domain.dto.CommentDeleteDTO;
import com.board.project.domain.dto.Criteria;
import com.board.project.domain.dto.PageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.board.project.mapper.CommentMapper;
import com.board.project.domain.entity.Comment;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {


	private final CommentMapper commentMapper;

	private final PrincipalService principalService;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int commentReply(Map<String, Object> commentData
								, Principal principal) {
		

		Comment comment;

		if(commentData.get("boardNo") == null) {
			comment = Comment.builder()
					.userId(principal.getName())
					.commentContent(commentData.get("commentContent").toString())
					.commentGroupNo(Integer.parseInt(commentData.get("commentGroupNo").toString()))
					.commentIndent(Integer.parseInt(commentData.get("commentIndent").toString())+1)
					.commentUpperNo(Integer.parseInt(commentData.get("commentNo").toString()))
					.imageNo(Integer.parseInt(commentData.get("imageNo").toString()))
					.build();
		}else {
			comment = Comment.builder()
					.userId(principal.getName())
					.commentContent(commentData.get("commentContent").toString())
					.commentGroupNo(Integer.parseInt(commentData.get("commentGroupNo").toString()))
					.commentIndent(Integer.parseInt(commentData.get("commentIndent").toString())+1)
					.commentUpperNo(Integer.parseInt(commentData.get("commentNo").toString()))
					.boardNo(Integer.parseInt(commentData.get("boardNo").toString()))
					.build();
		}

		commentMapper.commentReply(comment);

		return ResultProperties.SUCCESS;

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int commentInsert(Map<String, Object> CommentContent
								, Principal principal) {


		log.info("commentInsert");
		Comment comment;

		if(CommentContent.get("boardNo") == null) {
			comment = Comment.builder()
					.userId(principal.getName())
					.commentContent(CommentContent.get("commentContent").toString())
					.imageNo(Integer.parseInt(CommentContent.get("imageNo").toString()))
					.build();
		}else {
			comment = Comment.builder()
					.userId(principal.getName())
					.commentContent(CommentContent.get("commentContent").toString())
					.boardNo(Integer.parseInt(CommentContent.get("boardNo").toString()))
					.build();
		}

		commentMapper.commentInsert(comment);

		return ResultProperties.SUCCESS;

	}

	//댓글 하나를 삭제
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteComment(int commentNo, Principal principal) {


		if(!principalService.checkWriter(commentNo, "comment", principal))
			return ResultProperties.FAIL;

		commentMapper.commentDelete(commentNo);

		return ResultProperties.SUCCESS;

	}

	@Override
	public CommentDTO boardCommentList(int boardNo, int pageNum, Principal principal) throws Exception{

		Criteria cri = new Criteria();
		cri.setPageNum(pageNum);
		Comment comment = Comment.builder().boardNo(boardNo).build();

		return commentList(comment, cri, principal);
	}

	@Override
	public CommentDTO imageCommentList(int imageNo, int pageNum, Principal principal) throws Exception{
		Criteria cri = new Criteria();
		cri.setPageNum(pageNum);
		Comment comment = Comment.builder().imageNo(imageNo).build();

		return commentList(comment, cri, principal);
	}

	public CommentDTO commentList(Comment comment, Criteria cri, Principal principal) throws Exception{

		int totalPages = (int) (Math.ceil((commentMapper.cListCount(comment) * 1.0) / cri.getBoardAmount()));

		String uid = null;
		if(principal != null)
			uid = principal.getName();

		return CommentDTO.builder()
				.commentList(commentMapper.commentList(comment, cri))
				.pageDTO(new PageDTO(cri, totalPages))
				.uid(uid)
				.build();
	}
}
