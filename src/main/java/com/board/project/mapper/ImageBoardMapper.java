package com.board.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.project.vo.ImageBoardVO;

@Mapper
public interface ImageBoardMapper {
	List<ImageBoardVO> imageList() throws Exception;
	
	public String imageDetail() throws Exception;
	
	public String imageModify() throws Exception;
	
	void imageInsertProc(ImageBoardVO imageBoardVO) throws Exception;
}
