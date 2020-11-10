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
		
		if(boardMapper.BoardUpperCount(BoardNo) == 0) {
			boardMapper.BoardDelete(BoardNo);
		}else {
			boardMapper.BoardUpperUpdate(BoardNo);
			boardMapper.BoardDelete(BoardNo);
		}
		
	}


}
