package com.board.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.project.vo.HierarchicalBoardVO;
import com.board.project.vo.SearchCriteria;

@Mapper
public interface HierarchicalBoardMapper {
	
	//
	public List<HierarchicalBoardVO> searchPage(SearchCriteria scri) throws Exception;
	int listCount(SearchCriteria scri) throws Exception;
	//
	public HierarchicalBoardVO boardDetail(int BoardNo) throws Exception;
	//
	public void boardDelete(int BoardNo) throws Exception;
	//
	public int boardUpperCount(int BoardNo) throws Exception;
	//
	public void boardUpperUpdate(int BoardUpperNo) throws Exception;
	//
	public void boardModifyProc(HierarchicalBoardVO boardVO) throws Exception;
	//
	public void boardInsertProc(HierarchicalBoardVO boardVO) throws Exception;
	//
	public void boardReplyProc(HierarchicalBoardVO boardVO) throws Exception;
}
