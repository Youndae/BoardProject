package com.board.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.project.vo.Criteria;
import com.board.project.vo.HierarchicalBoardVO;
import com.board.project.vo.PagingVO;
import com.board.project.vo.SearchCriteria;

@Mapper
public interface HierarchicalBoardMapper {
	
	
	int ListCount(SearchCriteria scri) throws Exception;
	public List<HierarchicalBoardVO> SearchPage(SearchCriteria scri) throws Exception;
	
	
	int countBoard();
	public List<HierarchicalBoardVO> selectBoard(PagingVO vo);
	
	
	
	
	List<HierarchicalBoardVO> BoardList() throws Exception;
	
	public HierarchicalBoardVO BoardDetail(int boardNo) throws Exception;
	
	public void BoardDelete(int boardNo) throws Exception;
	
	public void BoardModifyProc(HierarchicalBoardVO boardVO) throws Exception;
	
	public void BoardInsertProc(HierarchicalBoardVO boardVO) throws Exception;
	
	public void BoardReplyProc(HierarchicalBoardVO boardVO) throws Exception;
}
