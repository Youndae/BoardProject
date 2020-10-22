package com.board.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.project.vo.HierarchicalBoardVO;
import com.board.project.vo.PagingVO;

@Mapper
public interface HierarchicalBoardMapper {
	
	
	int countBoard();
	public List<HierarchicalBoardVO> selectBoard(PagingVO vo);
	
	
	
	
	List<HierarchicalBoardVO> BoardList() throws Exception;
	
	public HierarchicalBoardVO BoardDetail(int boardNo) throws Exception;
	
	public void BoardDelete(int boardNo) throws Exception;
	
	public void BoardModifyProc(HierarchicalBoardVO boardVO) throws Exception;
	
	public void BoardInsertProc(HierarchicalBoardVO boardVO) throws Exception;
	
	public void BoardReplyProc(HierarchicalBoardVO boardVO) throws Exception;
}
