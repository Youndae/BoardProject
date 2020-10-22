package com.board.project.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.board.project.vo.HierarchicalBoardVO;
import com.board.project.vo.PagingVO;

/*@Repository("boardService")*/
public interface HierarchicalBoardService {
	
	public int countBoard();
	public List<HierarchicalBoardVO> selectBoard(PagingVO vo);
}
