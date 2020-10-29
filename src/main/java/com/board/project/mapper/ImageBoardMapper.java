package com.board.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.project.vo.HierarchicalBoardVO;
import com.board.project.vo.ImageBoardVO;
import com.board.project.vo.SearchCriteria;

@Mapper
public interface ImageBoardMapper {
	List<ImageBoardVO> imageList() throws Exception;
	
	public String imageDetail() throws Exception;
	
	public String imageModify() throws Exception;
	
	public void imageInsertProc(ImageBoardVO imageBoardVO) throws Exception;
	
	public String selectTest() throws Exception;
	
	
	
	public ImageBoardVO ModifyTest(int boardNo) throws Exception;
}
