package com.board.project.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.board.project.mapper.ImageBoardMapper;
import com.board.project.vo.ImageBoardVO;

@Controller
public class ImageBoardController {
	
	@Autowired
	ImageBoardMapper imageBoardMapper;
	
	
	private static final int RESULT_EXCEED_SIZE = -2;
	private static final int RESULT_UNACCEPTED_EXTENSION = -1;
	private static final int RESULT_SUCCESS = 1;
	private static final long LIMIT_SIZE = 10 * 1024 * 1024;
	
	@RequestMapping("/ImageTest")
	public String imageTest() throws Exception{
		return "ImageBoard/ImageTest2";
	}
	
	
	
	@RequestMapping("/imageupload2")
	@ResponseBody
	public String multiImageUpload2(HttpServletRequest request) throws Exception{
		System.out.println(request.getHeader("file-name"));
		StringBuffer sb = new StringBuffer();
		
		try {
			String oldName = request.getHeader("file-name");
			
			String filePath = request.getSession().getServletContext().getRealPath("resources/IMG");
			
			String saveName = sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()))
								.append(UUID.randomUUID().toString())
								.append(oldName.substring(oldName.lastIndexOf("."))).toString();
			
			InputStream is = request.getInputStream();
			
			OutputStream os = new FileOutputStream(filePath + saveName);
			
			int numRead;
			byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
			while((numRead = is.read(b, 0, b.length)) != -1) {
				os.write(b, 0, numRead);
			}
			os.flush();
			os.close();
			
			sb = new StringBuffer();
			sb.append("&bNewLine=true")
				.append("$sFileName=").append(oldName)
				.append("&sFileURL=").append("/IMG/").append(saveName);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return "/board/ImageTest";
	}
	
	
	
	
	
	@RequestMapping(value="/imageupload", method=RequestMethod.POST)
	@ResponseBody
	public int multiImageUpload(@RequestParam("files") List<MultipartFile> images, HttpServletRequest request, ImageBoardVO imageBoardVO) throws Exception{
		System.out.println("images : "+images.size());
		
		long sizeSum = 0;
		String filePath = request.getSession().getServletContext().getRealPath("resources/IMG/");
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
			ImageArray.add(saveName);
			
			image.transferTo(new File(saveFile));
			System.out.println("save End");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		int filesSize = ImageArray.size();
		if(filesSize != 0) {
			int i = 1;
			switch(filesSize) {
			case 5 :
				imageBoardVO.setImage5("/IMG/" + ImageArray.get(filesSize - i));
				i++;
			case 4 :
				imageBoardVO.setImage4("/IMG/" + ImageArray.get(filesSize - i));
				i++;
			case 3 :
				imageBoardVO.setImage3("/IMG/" + ImageArray.get(filesSize - i));
				i++;
			case 2 :
				imageBoardVO.setImage2("/IMG/" + ImageArray.get(filesSize - i));
				i++;
			case 1 :
				imageBoardVO.setImage1("/IMG/" + ImageArray.get(filesSize - i));
			}
		}
		
		int imageNo = 1;
		
		imageBoardVO.setImageNo(imageNo);
		System.out.println("img1 : "+imageBoardVO.getImage1());
		imageBoardMapper.imageInsertProc(imageBoardVO);
		System.out.println("END!");
		return RESULT_SUCCESS;
	}
	
	private boolean isValidExtension(String originalName) throws Exception{
		String originalNameExtension = originalName.substring(originalName.lastIndexOf(".") + 1);
		switch(originalNameExtension) {
		case "jpg" :
		case "png" :
		case "gif" :
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping("/ImageList")
	public String imageList(Model model) throws Exception{
		
		
		
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
	
	@RequestMapping("/imageInsertProc")
	public String imageInsertProc() throws Exception{
		
		return "redirect:imageList";
	}

}
