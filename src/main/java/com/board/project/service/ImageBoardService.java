package com.board.project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.board.project.vo.ImageDataVO;

public interface ImageBoardService {
	
	public void deleteList(int ImageNo, HttpServletRequest request) throws Exception;
	
	public void deletefiles(List<String> deletefiles, HttpServletRequest request) throws Exception;
	
	public int imageModify(List<MultipartFile> images, HttpServletRequest request, int step, ImageDataVO imageDataVO) throws Exception;
	
	public int imageInsert(List<MultipartFile> images, HttpServletRequest request, ImageDataVO imageDataVO) throws Exception;
	
	
}
