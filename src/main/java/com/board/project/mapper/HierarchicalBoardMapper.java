package com.board.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.project.vo.HierarchicalBoardVO;
import com.board.project.vo.SearchCriteria;

@Mapper
public interface HierarchicalBoardMapper {
	
	//
	public List<HierarchicalBoardVO> SearchPage(SearchCriteria scri) throws Exception;
	int ListCount(SearchCriteria scri) throws Exception;
	//
	public HierarchicalBoardVO BoardDetail(int BoardNo) throws Exception;
	//
	public void BoardDelete(int BoardNo) throws Exception;
	//
	public int BoardUpperCount(int BoardNo) throws Exception;
	//
	public void BoardUpperUpdate(int BoardUpperNo) throws Exception;
	//
	public void BoardModifyProc(HierarchicalBoardVO boardVO) throws Exception;
	//
	public void BoardInsertProc(HierarchicalBoardVO boardVO) throws Exception;
	//
	public void BoardReplyProc(HierarchicalBoardVO boardVO) throws Exception;
}
