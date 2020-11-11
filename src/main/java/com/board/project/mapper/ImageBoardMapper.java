package com.board.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.project.vo.ImageBoardVO;
import com.board.project.vo.ImageDataVO;

@Mapper
public interface ImageBoardMapper {
	//
	List<ImageBoardVO> imageBoardList() throws Exception;
	//
	public List<ImageDataVO> getAttachList(int ImageNo) throws Exception;
	//
	List<ImageDataVO> imageBoardDetail(int ImageNo) throws Exception;
	//
	public void imageInsertProc(ImageBoardVO imageBoardVO) throws Exception;
	//
	void imageInsert(ImageDataVO imageDataVO) throws Exception;
	//
	public ImageBoardVO imageBoardModify(int ImageNo) throws Exception;
	//
	void imageBoardModifyProc(ImageBoardVO imageBoardVO) throws Exception;
	//
	void imageModfiy(ImageDataVO imageDataVO) throws Exception;
	//
	int countStep(int ImageNo) throws Exception;
	//
	void deleteImageFiles(String ImageName) throws Exception;
	//
	void imageBoardDelete(int ImageNo) throws Exception;
	//
	void imageNameDelete(int ImageNo) throws Exception;
	//
	List<String> deleteImageFileName(int ImageNo) throws Exception;
	
}
