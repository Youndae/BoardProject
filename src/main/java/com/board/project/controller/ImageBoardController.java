package com.board.project.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.board.project.mapper.ImageBoardMapper;
import com.board.project.vo.ImageBoardVO;
import com.board.project.vo.ImageDataVO;

@Controller
public class ImageBoardController {
	
	@Autowired
	ImageBoardMapper imageBoardMapper;
	
	
	
	private static final int RESULT_EXCEED_SIZE = -2;
	private static final int RESULT_UNACCEPTED_EXTENSION = -1;
	private static final int RESULT_SUCCESS = 1;
	private static final long LIMIT_SIZE = 10 * 1024 * 1024;

	
	
	@RequestMapping("/ImageModifyTest")
	public String imageModifyTest(Model model) throws Exception{
		
		int boardNo = 9;
		
		model.addAttribute("list", imageBoardMapper.ModifyTest(boardNo));		
		System.out.println("img : "+imageBoardMapper.ModifyTest(boardNo));
		return"ImageBoard/ImageModify2";
	}
	
	
	
	
	
	
	@RequestMapping(value = "/AttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<ImageDataVO>> getAttachList(int ImageNo) throws Exception{
		System.out.println("ImageNo : " + ImageNo);
		System.out.println("result : "+new ResponseEntity<>(imageBoardMapper.getAttachList(ImageNo), HttpStatus.OK));
		
		return new ResponseEntity<>(imageBoardMapper.getAttachList(ImageNo), HttpStatus.OK);
	}
	
	
	
	
	
	@RequestMapping("/ImageList")
	public String imageList(Model model) throws Exception{
		
		model.addAttribute("ImgList", imageBoardMapper.imageList());
		
		return "ImageBoard/ImageList";
	}
	
	@RequestMapping("/imageDetail/{bno}")
	public String imageDetail(Model model) throws Exception{
		
		return "ImageBoard/ImageDetail";
	}
	
	@RequestMapping("/imageModify/{bno}")
	public String imageModify(Model model) throws Exception{
		
		return "ImageBoard/ImageModify";
	}
	
	@RequestMapping("/imageModifyProc")
	public String imageModifyProc() throws Exception{
		
		return "redirect:imageDetail/{bno}";
	}
	
	@RequestMapping("/imageInsert")
	public String imageInsert() throws Exception{
		
		return "ImageBoard/ImageInsert";
	}
	
	@RequestMapping("/imageupload")
	@ResponseBody
	public int multiImageUpload(@RequestParam("files") List<MultipartFile> images, HttpServletRequest request, ImageBoardVO imageBoardVO, ImageDataVO imageDataVO) throws Exception{
		System.out.println("images : "+images.size());
		/*
		 * int imageNo = 8; imageBoardVO.setBoardNo(imageNo);//seq.nextval로 사용.
		 */		
		imageBoardVO.setImageTitle(request.getParameter("ImageTitle"));
		imageBoardVO.setUserId("coco");
		imageBoardVO.setImageContent(request.getParameter("ImageContent"));
		System.out.println("board Upload Go");
		imageBoardMapper.imageInsertProc(imageBoardVO);
		System.out.println("board Upload Complete");
		int step = 1;
		//step은 service로 넘겨서 같은 그룹중 가장 큰 값을 가져오도록 만들자.
		long sizeSum = 0;
		String filePath = request.getSession().getServletContext().getRealPath("IMG/");
		System.out.println("filePath : "+filePath);
		List<String> ImageArray = new ArrayList<>();
		
		for(MultipartFile image : images) {
			String originalName = image.getOriginalFilename();
			System.out.println("originalName: "+originalName);
			//확장자 검사
			if(!isValidExtension(originalName)) {
				return RESULT_UNACCEPTED_EXTENSION;
			}
			
			//용량검사
			sizeSum += image.getSize();
			if(sizeSum >= LIMIT_SIZE) {
				return RESULT_EXCEED_SIZE;
			}
			try {
			//저장
			System.out.println("save Start");
			StringBuffer sb = new StringBuffer();
			
			String saveName = sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()))
								.append(UUID.randomUUID().toString())
								.append(originalName.substring(originalName.lastIndexOf("."))).toString();
			
			String saveFile = filePath + saveName;
			System.out.println("saveFile : "+ saveFile);
			/*ImageArray.add(saveName);*/
			
			
			image.transferTo(new File(saveFile));
			System.out.println("save End");
			imageDataVO.setImageData(saveName);
			imageDataVO.setOldName(originalName);
			imageDataVO.setImageStep(step);
			imageBoardMapper.imageInsert(imageDataVO);
			step++;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		/*
		 * int filesSize = ImageArray.size(); if(filesSize != 0) { int i = 1;
		 * switch(filesSize) { case 5 : imageBoardVO.setImage5("IMG/" +
		 * ImageArray.get(filesSize - i)); i++; case 4 : imageBoardVO.setImage4("IMG/" +
		 * ImageArray.get(filesSize - i)); i++; case 3 : imageBoardVO.setImage3("IMG/" +
		 * ImageArray.get(filesSize - i)); i++; case 2 : imageBoardVO.setImage2("IMG/" +
		 * ImageArray.get(filesSize - i)); i++; case 1 : imageBoardVO.setImage1("IMG/" +
		 * ImageArray.get(filesSize - i)); } }
		 */
		
		
		
		
		System.out.println(imageBoardVO);
		
		/*
		 * System.out.println("img1 : "+imageBoardVO.getImage1());
		 * System.out.println("img2 : "+imageBoardVO.getImage2());
		 * System.out.println("img3 : "+imageBoardVO.getImage3());
		 * System.out.println("img4 : "+imageBoardVO.getImage4());
		 * System.out.println("img5 : "+imageBoardVO.getImage5());
		 */
		 
		
		
		System.out.println("END!");
		return RESULT_SUCCESS;
	}
	
	private boolean isValidExtension(String originalName) throws Exception{
		String originalNameExtension = originalName.substring(originalName.lastIndexOf(".") + 1);
		switch(originalNameExtension) {
		case "jpg" :
		case "png" :
		case "gif" :
		case "jpeg" :
			return true;
		}
		return false;
	}

}
