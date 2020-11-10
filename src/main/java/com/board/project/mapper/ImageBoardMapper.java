package com.board.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.project.vo.ImageBoardVO;
import com.board.project.vo.ImageDataVO;

@Mapper
public interface ImageBoardMapper {
	//
	List<ImageBoardVO> imageList() throws Exception;
	//
	public List<ImageDataVO> getAttachList(int ImageNo) throws Exception;
	//
	List<ImageDataVO> imageDetail(int ImageNo) throws Exception;//Detail은 결국 ModifyTest와 getAttachList 의 합친 결과인데 이걸 그냥 사용할지, 아니면 지우고 저 두개를 똑같이 불러와서 사용할지 고민해볼것.
	//
	public void imageInsertProc(ImageBoardVO imageBoardVO) throws Exception;
	//
	void imageInsert(ImageDataVO imageDataVO) throws Exception;
	//변경
	public ImageBoardVO Modify(int ImageNo) throws Exception;
	//
	void ModifyProc(ImageBoardVO imageBoardVO) throws Exception;
	//
	void imageModfiy(ImageDataVO imageDataVO) throws Exception;
	//
	int CountStep(int ImageNo) throws Exception;
	//
	void deletefiles(String ImageData) throws Exception;
	//
	void imageDelete(int ImageNo) throws Exception;
	//
	void imageDataDelete(int ImageNo) throws Exception;
	//
	List<String> deleteImageFile(int ImageNo) throws Exception;
	
}
