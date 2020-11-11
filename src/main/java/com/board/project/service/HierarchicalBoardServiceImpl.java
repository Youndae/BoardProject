package com.board.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.project.mapper.HierarchicalBoardMapper;

@Service
public class HierarchicalBoardServiceImpl implements HierarchicalBoardService{

	@Autowired
	HierarchicalBoardMapper boardMapper;
	
	@Override
	public void BoardDelete(int BoardNo) throws Exception {
		
		if(boardMapper.boardUpperCount(BoardNo) == 0) {
			boardMapper.boardDelete(BoardNo);
		}else {
			boardMapper.boardUpperUpdate(BoardNo);
			boardMapper.boardDelete(BoardNo);
		}
		
	}


}
