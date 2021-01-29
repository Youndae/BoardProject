package com.board.project.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.board.project.mapper.ImageBoardMapper;
import com.board.project.vo.ImageBoardVO;
import com.board.project.vo.ImageDataVO;

@Service("ImageBoardService")
public class ImageBoardServiceImpl implements ImageBoardService{
	
	@Autowired
	ImageBoardMapper imageBoardMapper;
	
	@Autowired
	ImageBoardService imageBoardService;

	private static final int RESULT_EXCEED_SIZE = -1;
	private static final int RESULT_SUCCESS = 1;
	private static final long LIMIT_SIZE = 10 * 1024 * 1024;
	private static long sizeSum = 0; 
	
	
	@Override
	public int imageSizeCheck(List<MultipartFile> images, HttpServletRequest request, ImageDataVO imageDataVO, 
			ImageBoardVO imageBoardVO, HttpSession session) throws Exception{
		
		for(MultipartFile image : images) {
			sizeSum += image.getSize();
			if(sizeSum >= LIMIT_SIZE) {
				return RESULT_EXCEED_SIZE;
			}
		}
		
		String id = (String)session.getAttribute("userId");
		  
		imageBoardVO.setImageTitle(request.getParameter("ImageTitle"));
		imageBoardVO.setUserId(id);
		imageBoardVO.setImageContent(request.getParameter("ImageContent"));
		  
		imageBoardMapper.imageInsertProc(imageBoardVO);
		
		imageBoardService.imageInsert(images, request, imageDataVO);
		return RESULT_SUCCESS;
	}
	
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
				  imageBoardMapper.deleteImageFiles(deletefiles.get(i)); 
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
	public int modifyCheck(List<MultipartFile> images,List<String> deletefiles, HttpServletRequest request, 
			ImageBoardVO imageBoardVO, ImageDataVO imageDataVO) throws Exception{
		
		imageBoardVO.setImageNo(Integer.parseInt(request.getParameter("ImageNo")));
		imageBoardVO.setImageTitle(request.getParameter("ImageTitle"));
		imageBoardVO.setImageContent(request.getParameter("ImageContent"));
		
		if(deletefiles != null) {
			imageBoardMapper.imageBoardModifyProc(imageBoardVO);
			imageBoardService.deletefiles(deletefiles, request);
			return RESULT_SUCCESS;
		}
		if(images.size() != 0) {
			
			for(MultipartFile image : images) {
				sizeSum += image.getSize();
				if(sizeSum >= LIMIT_SIZE) {
					return RESULT_EXCEED_SIZE;
				}
			}
		
			imageBoardMapper.imageBoardModifyProc(imageBoardVO);
			int step = imageBoardMapper.countStep(imageBoardVO.getImageNo());
			imageBoardService.imageModify(images, request, step, imageDataVO);
			
			return RESULT_SUCCESS;
		}else {
		
		imageBoardMapper.imageBoardModifyProc(imageBoardVO);
		
		return RESULT_SUCCESS;
		}
		
		
				
	}

	@Override
	public int imageModify(List<MultipartFile> images, HttpServletRequest request, int step, ImageDataVO imageDataVO) throws Exception {
		String filePath = request.getSession().getServletContext().getRealPath("IMG/");
		if(images.size() == 0) {
			return RESULT_SUCCESS;
		}else {	 
			for (MultipartFile image : images) {
				String originalName = image.getOriginalFilename();		  
				  
				  try { //저장
				  StringBuffer sb = new StringBuffer();
				  String saveName = sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()))
						  				.append(UUID.randomUUID().toString())
						  				.append(originalName.substring(originalName.lastIndexOf("."))).toString();
				  String saveFile = filePath + saveName; 
				  step++; 
				  image.transferTo(new File(saveFile)); 
				 
				  imageDataVO.setImageNo(Integer.parseInt(request.getParameter("ImageNo")));
				  imageDataVO.setImageName(saveName);
				  imageDataVO.setOldName(originalName);
				  imageDataVO.setImageStep(step); 
				  imageBoardMapper.imageModfiy(imageDataVO); 
				  
				  }catch(Exception e) { 
					  e.printStackTrace(); 
				  }
				 
			}
			return RESULT_SUCCESS;
		}
		
	}

	@Override
	public int imageInsert(List<MultipartFile> images, HttpServletRequest request, ImageDataVO imageDataVO) throws Exception {
		
		int step = 1;
		String filePath = request.getSession().getServletContext().getRealPath("IMG/");
		
		for (MultipartFile image : images) {
			String originalName = image.getOriginalFilename();
			  
			try {
				// 저장
				StringBuffer sb = new StringBuffer();
				String saveName = sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()))
						.append(UUID.randomUUID().toString())
						.append(originalName.substring(originalName.lastIndexOf("."))).toString();
				String saveFile = filePath + saveName;
			
				image.transferTo(new File(saveFile));
				System.out.println("save End");
				
				System.out.println("saveName : "+saveName+", OldName : "+originalName+", imageStep : "+step);
				imageDataVO.setImageName(saveName);
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
	
	

	@Override
	public void deleteList(int ImageNo, HttpServletRequest request) throws Exception {
		String filePath = request.getSession().getServletContext().getRealPath("IMG/");
		
		List<String> dt = imageBoardMapper.deleteImageFileName(ImageNo);
		
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
