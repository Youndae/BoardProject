package com.board.project.service;

import java.io.File;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.board.project.domain.ResultProperties;
import com.board.project.domain.dto.ImageBoardModifyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.board.project.mapper.ImageBoardMapper;
import com.board.project.domain.entity.ImageBoard;
import com.board.project.domain.entity.ImageData;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageBoardServiceImpl implements ImageBoardService{
	

	private final ImageBoardMapper imageBoardMapper;

	private final PrincipalService principalService;

	private static final long LIMIT_SIZE = 10 * 1024 * 1024;

	//file size check. max 10MB
	public boolean imageSizeCheck(List<MultipartFile> images) {

		log.info("sizeCheck");
		for(MultipartFile image : images) {

			if(image.getSize() >= LIMIT_SIZE) {
				log.info("over size");
				return false;
			}
		}

		log.info("size check End");
		return true;
	}

	// imageBoard insert
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertImage(List<MultipartFile> images
							, String imageTitle
						    , String imageContent
							, Principal principal) {

		log.info("imageBoard Insert");

		if(principal == null)
			return ResultProperties.FAIL;

		if(imageSizeCheck(images) == false)
			return ResultProperties.EXCEED_SIZE;


		ImageBoard imageBoard = ImageBoard.builder()
						.imageTitle(imageTitle)
						.userId(principal.getName())
						.imageContent(imageContent)
						.build();

		imageBoardMapper.imageBoardInsertProc(imageBoard);

		log.info("imageNo : {}", imageBoard.getImageNo());

		log.info("imageBoard save success");

		saveImageFile(images, 1, imageBoard.getImageNo());

		return ResultProperties.SUCCESS;

	}

	//image file save
	@Transactional(rollbackFor = Exception.class)
	public int saveImageFile(List<MultipartFile> images
			, int step
			, int imageNo) {

		log.info("file save");
		String filePath = "E:\\upload\\boardProject\\";


		for (MultipartFile image : images) {
			String originalName = image.getOriginalFilename();

			// 저장
			StringBuffer sb = new StringBuffer();
			String saveName = sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()))
					.append(UUID.randomUUID().toString())
					.append(originalName.substring(originalName.lastIndexOf("."))).toString();
			String saveFile = filePath + saveName;

			try{
				image.transferTo(new File(saveFile));
			}catch (Exception e){
				return 0;
			}

			ImageData imageData = ImageData.builder()
					.imageName(saveName)
					.oldName(originalName)
					.imageStep(step)
					.imageNo(imageNo)
					.build();

			imageBoardMapper.imageDataInsert(imageData);
			step++;
		}

		return ResultProperties.SUCCESS;
	}

	//delete ImageFile
	@Transactional(rollbackFor = Exception.class)
	public void deleteFiles(List<String> deleteFiles) {
		String filePath = "E:\\upload\\boardProject\\";

		if(deleteFiles.size() == 0) {
			log.info("deleteFiles is null");
		}else {
			for(int i = 0; i < deleteFiles.size(); i++) {
				String delFileName = deleteFiles.get(i);
				imageBoardMapper.deleteImageFiles(delFileName);
				File file = new File(filePath+delFileName);
				if(file.exists()) {
					if(file.delete()) {
						log.info("file delete success");
					}else {
						log.info("file delete fail");
					}
				}else {
					log.info("file is null");
				}
		 	}
		}
		
	}

	//Modify imageBoard
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int modifyCheck(List<MultipartFile> images
							, List<String> deleteFiles
						    , int imageNo
						    , String imageTitle
						    , String imageContent
							, Principal principal) {

		if(!principalService.checkWriter(imageNo, "image", principal)) {
			return ResultProperties.FAIL;
		}

		if(images.size() != 0) {
			if(imageSizeCheck(images) == false)
				return ResultProperties.EXCEED_SIZE;

			int step = imageBoardMapper.countStep(imageNo) + 1;
			saveImageFile(images, step, imageNo);
		}

		ImageBoard imageBoard = ImageBoard.builder()
				.imageNo(imageNo)
				.imageTitle(imageTitle)
				.imageContent(imageContent)
				.build();

		imageBoardMapper.imageBoardModifyProc(imageBoard);

		if(deleteFiles != null) {
			deleteFiles(deleteFiles);
		}

		return ResultProperties.SUCCESS;

	}

	//delete imageBoard
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteBoard(int imageNo, Principal principal) {

		if(!principalService.checkWriter(imageNo, "image", principal))
			return ResultProperties.FAIL;

		List<String> dt = imageBoardMapper.deleteImageFileName(imageNo);

		deleteFiles(dt);

		imageBoardMapper.imageBoardDelete(imageNo);

		return ResultProperties.SUCCESS;

	}

	@Override
	public ImageBoardModifyDTO getModifyImageData(int imageNo, Principal principal) {

		try{
			if(!principalService.checkWriter(imageNo, "image", principal))
				return null;
			else
				return imageBoardMapper.getImageBoardModifyData(imageNo);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}

	}

}
