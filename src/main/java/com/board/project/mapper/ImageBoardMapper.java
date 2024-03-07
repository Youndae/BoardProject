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
	List<ImageDataDTO> getAttachList(int ImageNo);

	//
	List<ImageBoardDetailDTO> imageBoardDetail(int ImageNo);

	//
	int imageBoardInsertProc(ImageBoard imageBoard);

	//
	void imageDataInsert(List<ImageData> imageDataList);

	//
	ImageBoardModifyDTO getImageBoardModifyData(int ImageNo);

	//
	void imageBoardModifyProc(ImageBoard imageBoard);

	//
	int countStep(int ImageNo);

	//
	void imageBoardDelete(int ImageNo);

	//
	List<String> deleteImageFileName(int ImageNo);

	void deleteImageFileList(List<String> deleteImageList);
}
