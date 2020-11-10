package com.board.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	private static final int RESULT_EXCEED_SIZE = -2;
	private static final int RESULT_UNACCEPTED_EXTENSION = -1;
	private static final int RESULT_SUCCESS = 1;
	private static final long LIMIT_SIZE = 10 * 1024 * 1024;
	private static int result = 0;

	@RequestMapping("/ImageModify")
	public String imageModify(@RequestParam("ImageNo") int ImageNo , Model model) throws Exception {
		//완

		model.addAttribute("list", imageBoardMapper.Modify(ImageNo));
		
		return "ImageBoard/ImageModify";
	}

	@RequestMapping("/AttachList")
	@ResponseBody
	public ResponseEntity<List<ImageDataVO>> getAttachList(Integer ImageNo) throws Exception {
		System.out.println("ImageNo : " + ImageNo);
		System.out.println("result : " + new ResponseEntity<>(imageBoardMapper.getAttachList(ImageNo), HttpStatus.OK));

		return new ResponseEntity<>(imageBoardMapper.getAttachList(ImageNo), HttpStatus.OK);
	}

	@RequestMapping("/ImageList")
	public String imageList(Model model) throws Exception {

		model.addAttribute("ImgList", imageBoardMapper.imageList());

		return "ImageBoard/ImageList";
	}

	@RequestMapping("/ImageDetail")
	public String imageDetail(Model model, @RequestParam("ImageNo") int ImageNo, @ModelAttribute("commentVO") CommentVO commentVO) throws Exception {
			
			commentVO.setImageNo(ImageNo);
			model.addAttribute("detail", imageBoardMapper.imageDetail(ImageNo));
			model.addAttribute("comment", commentMapper.CommentList(commentVO)); 
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(commentVO);
			pageMaker.setTotalCount(commentMapper.cListCount(commentVO));
			
			model.addAttribute("pageMaker", pageMaker);
			
		return "ImageBoard/Imagedetail";
	}

	@RequestMapping("/ImageInsert")
	public String imageInsert() throws Exception {

		return "ImageBoard/ImageInsert";
	}

	@RequestMapping("/ImageModifyProc")
	@ResponseBody
	public int ImageModifyProc(@RequestParam("files") List<MultipartFile> images,
			@RequestParam(value = "deletefiles", required = false) List<String> deletefiles, HttpServletRequest request,
			ImageBoardVO imageBoardVO, ImageDataVO imageDataVO) throws Exception {
		

		imageBoardVO.setImageNo(Integer.parseInt(request.getParameter("ImageNo")));
		imageBoardVO.setImageTitle(request.getParameter("ImageTitle"));
		imageBoardVO.setImageContent(request.getParameter("ImageContent"));
		imageBoardMapper.ModifyProc(imageBoardVO);
		//체크. 왜 ImageBoardVO에서 Step체크?
		int step = imageBoardMapper.CountStep(imageBoardVO.getImageNo());
		

		if (deletefiles != null) {
			imageBoardService.deletefiles(deletefiles, request);
		}

		if (images.size() != 0) {
			result = imageBoardService.imageModify(images, request, step, imageDataVO);
		}


		return result;
	}

	@RequestMapping("/imageupload")
	@ResponseBody
	public int multiImageUpload(@RequestParam("files") List<MultipartFile> images, HttpServletRequest request,
			ImageBoardVO imageBoardVO, ImageDataVO imageDataVO) throws Exception {
		
		imageBoardVO.setImageTitle(request.getParameter("ImageTitle"));
		imageBoardVO.setUserId("coco");
		imageBoardVO.setImageContent(request.getParameter("ImageContent"));
		
		imageBoardMapper.imageInsertProc(imageBoardVO);
		
		result = imageBoardService.imageInsert(images, request, imageDataVO);

		return result;
	}
	
	@RequestMapping("/ImageDelete")
	public String ImageDelete(@RequestParam("ImageNo") int ImageNo, HttpServletRequest request)throws Exception{

		commentService.commentDeleteBoard(ImageNo, null);
		imageBoardService.deleteList(ImageNo, request);
		imageBoardMapper.imageDataDelete(ImageNo);	  
		imageBoardMapper.imageDelete(ImageNo);
		
		return "redirect:/ImageList";
	}

}
