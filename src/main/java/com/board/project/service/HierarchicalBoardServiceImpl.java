package com.board.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.board.project.mapper.HierarchicalBoardMapper;
import com.board.project.vo.HierarchicalBoardVO;
import com.board.project.vo.PagingVO;

public class HierarchicalBoardServiceImpl implements HierarchicalBoardService {

	@Autowired
	HierarchicalBoardMapper boardMapper;
	
	@Override
	public int countBoard() {
		// TODO Auto-generated method stub
		return boardMapper.countBoard();
	}

	@Override
	public List<HierarchicalBoardVO> selectBoard(PagingVO vo) {
		// TODO Auto-generated method stub
		return boardMapper.selectBoard(vo);
	}

}
