package com.board.project.mapper;

import java.util.List;

import com.board.project.domain.dto.*;
import org.apache.ibatis.annotations.Mapper;

import com.board.project.domain.entity.HierarchicalBoard;

@Mapper
public interface HierarchicalBoardMapper {
	
	//
	public List<HierarchicalBoardListDTO> searchPage(Criteria cri) throws Exception;

	public int listCount(Criteria cri);

	//
	public HierarchicalBoardDetailDTO boardDetail(int boardNo);

	//
	public HierarchicalBoardModifyDTO getBoardModifyData(int boardNo);

	//
	public HierarchicalBoardReplyDTO getBoardReplyData(int boardNo);

	//
	public void boardModifyProc(HierarchicalBoard boardVO);

	//
	public void boardInsertProc(HierarchicalBoard boardVO);

	//
	public void boardReplyProc(HierarchicalBoard boardVO);

	//
	public String checkWriter(int boardNo);

	public HierarchicalBoardDeleteDTO checkDeleteNo(int boardNo);

	public List<HierarchicalBoardDeleteDTO> groupData(int boardGroupNo);

	public void deleteBoardGroup(int boardGroupNo);

	public void deleteHierarchicalList(List<Integer> deleteList);
}
