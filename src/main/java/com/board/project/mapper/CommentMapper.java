package com.board.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.project.vo.CommentVO;

@Mapper
public interface CommentMapper {
	//
	List<CommentVO> commentList(CommentVO commentVO) throws Exception;
	//
	int cListCount(CommentVO commentVO) throws Exception;
	//
	void commentInsert(CommentVO commentVO) throws Exception;
	//
	void commentDelete(int CommentNo) throws Exception;
	//
	void commentReply(CommentVO commentVO) throws Exception;
	//
	void commentDeleteBoard(CommentVO commentVO) throws Exception;
	
}
