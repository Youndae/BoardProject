package com.board.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.project.vo.CommentVO;
import com.board.project.vo.Criteria;

@Mapper
public interface CommentMapper {
	List<CommentVO> bCommentList(Criteria cri, int BoardNo) throws Exception;
	
	List<CommentVO> iCommentList(int ImageNo) throws Exception;
	
	void commentInsert(CommentVO commentVO) throws Exception;
	
	void commentDelete(int CommentNo) throws Exception;
	
	void commentReply(CommentVO commentVO) throws Exception;
	
	void commentDeleteBoard(CommentVO commentVO) throws Exception;
	
	int cListCount(int BoardNo) throws Exception;
}
