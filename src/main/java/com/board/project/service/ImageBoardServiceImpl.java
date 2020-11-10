package com.board.project.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.board.project.mapper.ImageBoardMapper;
import com.board.project.vo.ImageDataVO;

@Service("ImageBoardService")
public class ImageBoardServiceImpl implements ImageBoardService{
	
	@Autowired
	ImageBoardMapper imageBoardMapper;
	
	
	
	private static final int RESULT_EXCEED_SIZE = -2;
	private static final int RESULT_UNACCEPTED_EXTENSION = -1;
	private static final int RESULT_SUCCESS = 1;
	private static final long LIMIT_SIZE = 10 * 1024 * 1024;
	private static long sizeSum = 0; 
	
	
	
	@Override
	public void deletefiles(List<String> deletefiles, HttpServletRequest request) throws Exception {
		String filePath = request.getSession().getServletContext().getRealPath("IMG/");
		if(deletefiles.size() == 0) {
			System.out.println("데이터없어");
			return;
		}else {
			System.out.println("데이터있어");
			
			for(int i = 0; i < deletefiles.size(); i++) {
				System.out.println(deletefiles.get(i));
				  imageBoardMapper.deletefiles(deletefiles.get(i)); 
				  File file = new File(filePath+deletefiles.get(i));
				  if(file.exists()) {
					  if(file.delete()) {
						  System.out.println("삭제성공");
					  }else {
						  System.out.println("삭제실패");
					  }
				  }else {
					  System.out.println("파일 없는데?");
				  }
			  }
			
			
			return;
		}
		
	}

	@Override
	public int imageModify(List<MultipartFile> images, HttpServletRequest request, int step, ImageDataVO imageDataVO) throws Exception {
		String filePath = request.getSession().getServletContext().getRealPath("IMG/");
		if(images.size() == 0) {
			System.out.println("사진없어");
			return RESULT_SUCCESS;
		}else {
			System.out.println("사진있어");
			
			 
			 System.out.println("step2 : "+step);
			 
			  System.out.println("filePath : "+filePath); 
			  
			 
			for (MultipartFile image : images) {
				String originalName = image.getOriginalFilename();
				
				  System.out.println("originalName: "+originalName); //확장자 검사
				  if(!isValidExtension(originalName)) { 
					  return RESULT_UNACCEPTED_EXTENSION; 
				  }
				  
				  //용량검사 
				  sizeSum += image.getSize(); 
				  if(sizeSum >= LIMIT_SIZE) { 
					  return RESULT_EXCEED_SIZE; 
				  } 
				  
				  try { //저장
					  System.out.println("save Start");
				  StringBuffer sb = new StringBuffer();
				  
				  String saveName = sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()))
						  				.append(UUID.randomUUID().toString())
						  				.append(originalName.substring(originalName.lastIndexOf("."))).toString();
				  
				  String saveFile = filePath + saveName; 
				  System.out.println("saveFile : "+ saveFile); 
				  
				  
				  
				  step++; 
				  image.transferTo(new File(saveFile)); 
				  System.out.println("save End");
				  
				  System.out.println("saveName : "+saveName+", oName : "+originalName+", step : "+step);
				  System.out.println("saveName : "+imageDataVO.getImageData());
				  imageDataVO.setImageNo(Integer.parseInt(request.getParameter("ImageNo")));
				  imageDataVO.setImageData(saveName);
				  imageDataVO.setOldName(originalName);
				  System.out.println("oldName : "+imageDataVO.getOldName());
				  imageDataVO.setImageStep(step); 
				  System.out.println("Step : "+imageDataVO.getImageStep());
				  imageBoardMapper.imageModfiy(imageDataVO); 
				  
				  }catch(Exception e) { 
					  e.printStackTrace(); 
				  }
				 
			}
			return RESULT_SUCCESS;
		}
		
	}

	@Override
	public int imageInsert(List<MultipartFile> images, HttpServletRequest request, ImageDataVO imageDataVO)
			throws Exception {
		long sizeSum = 0;
		int step = 1;
		String filePath = request.getSession().getServletContext().getRealPath("IMG/");
		System.out.println("filePath : " + filePath);
		

		for (MultipartFile image : images) {
			String originalName = image.getOriginalFilename();
			System.out.println("originalName: " + originalName);
			// 확장자 검사
			if (!isValidExtension(originalName)) {
				return RESULT_UNACCEPTED_EXTENSION;
			}

			// 용량검사
			sizeSum += image.getSize();
			if (sizeSum >= LIMIT_SIZE) {
				return RESULT_EXCEED_SIZE;
			}
			try {
				// 저장
				System.out.println("save Start");
				StringBuffer sb = new StringBuffer();

				String saveName = sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()))
						.append(UUID.randomUUID().toString())
						.append(originalName.substring(originalName.lastIndexOf("."))).toString();

				String saveFile = filePath + saveName;
				System.out.println("saveFile : " + saveFile);
				

				image.transferTo(new File(saveFile));
				System.out.println("save End");
				imageDataVO.setImageData(saveName);
				imageDataVO.setOldName(originalName);
				imageDataVO.setImageStep(step);
				imageBoardMapper.imageInsert(imageDataVO);
				step++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("END!");
		return RESULT_SUCCESS;
	}
	
	private boolean isValidExtension(String originalName) throws Exception {
		String originalNameExtension = originalName.substring(originalName.lastIndexOf(".") + 1);
		switch (originalNameExtension) {
		case "jpg":
		case "png":
		case "gif":
		case "jpeg":
			return true;
		}
		return false;
	}

	@Override
	public void deleteList(int ImageNo, HttpServletRequest request) throws Exception {
		String filePath = request.getSession().getServletContext().getRealPath("IMG/");
		
		List<String> dt = imageBoardMapper.deleteImageFile(ImageNo);
		
		for(int i = 0; i < dt.size(); i++) {
			System.out.println("file Name : "+dt.get(i));
			File file = new File(filePath+dt.get(i));
			if(file.exists()) {
				if(file.delete()) {
					System.out.println("삭제 성공!");
				}else {
					System.out.println("삭제 실패 ㅠㅠ");
				}
			}else {
				System.out.println("파일 없어");
			}
		}
		
		return;
	}



}
