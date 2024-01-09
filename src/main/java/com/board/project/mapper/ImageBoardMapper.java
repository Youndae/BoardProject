package com.board.project.mapper;

import java.util.List;

import com.board.project.domain.dto.*;
import org.apache.ibatis.annotations.Mapper;

import com.board.project.domain.entity.ImageBoard;
import com.board.project.domain.entity.ImageData;

@Mapper
public interface ImageBoardMapper {
	//
	List<ImageBoardListDTO> imageBoardList(Criteria cri);

	int listCount(Criteria cri);

	//
	public List<ImageDataDTO> getAttachList(int ImageNo);

	//
	List<ImageBoardDetailDTO> imageBoardDetail(int ImageNo);

	//
	public int imageBoardInsertProc(ImageBoard imageBoard);

	//
	void imageDataInsert(ImageData imageDataVO);

	//
	public ImageBoardModifyDTO getImageBoardModifyData(int ImageNo);

	//
	void imageBoardModifyProc(ImageBoard imageBoard);

	//
	int countStep(int ImageNo);

	//
	void deleteImageFiles(String ImageName);

	//
	void imageBoardDelete(int ImageNo);

	//
	List<String> deleteImageFileName(int ImageNo);

	String checkWriter(int imageNo);
	
}
