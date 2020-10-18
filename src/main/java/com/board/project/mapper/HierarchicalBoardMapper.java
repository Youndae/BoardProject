package com.board.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.project.vo.HierarchicalBoardVO;

@Mapper
public interface HierarchicalBoardMapper {
	List<HierarchicalBoardVO> BoardList() throws Exception;
	
	public String BoardDetail() throws Exception;
	
	public String BoardModify() throws Exception;
	
	public void BoardModifyProc() throws Exception;
	
	public void BoardInsertProc() throws Exception;
	
	public String BoardReply() throws Exception;
	
	public void BoardReplyProc() throws Exception;
}
