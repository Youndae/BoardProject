package com.board.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.board.project.mapper.CommentMapper;
import com.board.project.mapper.ImageBoardMapper;
import com.board.project.service.CommentService;
import com.board.project.service.ImageBoardService;
import com.board.project.vo.CommentVO;
import com.board.project.vo.ImageBoardVO;
import com.board.project.vo.ImageDataVO;
import com.board.project.vo.PageMaker;

@Controller
public class ImageBoardController {

	@Autowired
	ImageBoardMapper imageBoardMapper;

	@Autowired
	ImageBoardService imageBoardService;
	
	@Autowired
	CommentMapper commentMapper;
	
	@Autowired
	CommentService commentService;

	private static int result = 0;

	@RequestMapping("/ImageList")
	public String imageList(Model model) throws Exception {

		model.addAttribute("ImgList", imageBoardMapper.imageBoardList());

		return "ImageBoard/ImageList";
	}
	
	@RequestMapping("/AttachList")
	@ResponseBody
	public ResponseEntity<List<ImageDataVO>> getAttachList(Integer ImageNo) throws Exception {
		System.out.println("ImageNo : " + ImageNo);
		System.out.println("result : " + new ResponseEntity<>(imageBoardMapper.getAttachList(ImageNo), HttpStatus.OK));

		return new ResponseEntity<>(imageBoardMapper.getAttachList(ImageNo), HttpStatus.OK);
	}
	
	@RequestMapping("/ImageDetail")
	public String imageDetail(Model model, @RequestParam("ImageNo") int ImageNo, @ModelAttribute("commentVO") CommentVO commentVO) throws Exception {
			
			commentVO.setImageNo(ImageNo);
			model.addAttribute("detail", imageBoardMapper.imageBoardDetail(ImageNo));
			model.addAttribute("comment", commentMapper.commentList(commentVO)); 
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(commentVO);
			pageMaker.setTotalCount(commentMapper.cListCount(commentVO));
			
			model.addAttribute("pageMaker", pageMaker);
			
		return "ImageBoard/Imagedetail";
	}
	
	@RequestMapping("/ImageModify")
	public String imageModify(@RequestParam("ImageNo") int ImageNo , Model model) throws Exception {

		model.addAttribute("list", imageBoardMapper.imageBoardModify(ImageNo));
		
		return "ImageBoard/ImageModify";
	}
	
	@RequestMapping("/ImageModifyProc")
	@ResponseBody
	public int ImageModifyProc(@RequestParam("files") List<MultipartFile> images,
			@RequestParam(value = "deletefiles", required = false) List<String> deletefiles, HttpServletRequest request,
			ImageBoardVO imageBoardVO, ImageDataVO imageDataVO) throws Exception {
		
		result = imageBoardService.modifyCheck(images, deletefiles, request, imageBoardVO, imageDataVO);

		return result;
	}

	@RequestMapping("/ImageInsert")
	public String imageInsert() throws Exception {

		return "ImageBoard/ImageInsert";
	}

	
	 @RequestMapping("/imageupload")
	  @ResponseBody 
	  public int multiImageUpload(@RequestParam("files") List<MultipartFile> images, HttpServletRequest request, 
			  ImageBoardVO imageBoardVO, ImageDataVO imageDataVO, HttpSession session) throws Exception {
		 
		result = imageBoardService.imageSizeCheck(images, request, imageDataVO, imageBoardVO, session);
	
	  return result;
	  }
	 
	

	
	@RequestMapping("/ImageDelete")
	public String ImageDelete(@RequestParam("ImageNo") int ImageNo, HttpServletRequest request)throws Exception{

		commentService.commentDeleteBoard(ImageNo, null);
		imageBoardService.deleteList(ImageNo, request);
		imageBoardMapper.imageNameDelete(ImageNo);	  
		imageBoardMapper.imageBoardDelete(ImageNo);
		
		return "redirect:/ImageList";
	}

}
