package com.board.project.service;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.board.project.domain.dto.ImageBoardModifyDTO;
import org.springframework.web.multipart.MultipartFile;

import com.board.project.domain.entity.ImageBoard;
import com.board.project.domain.entity.ImageData;

public interface ImageBoardService {
	
	public int deleteBoard(int imageNo, Principal principal);
	
	public int insertImage(List<MultipartFile> images, String imageTitle, String imageContent, Principal principal);
	
	public int modifyCheck(List<MultipartFile> images, List<String> deleteFiles, int imageNo, String imageTitle, String imageContent, Principal principal);

	public ImageBoardModifyDTO getModifyImageData(int imageNo, Principal principal);
}
