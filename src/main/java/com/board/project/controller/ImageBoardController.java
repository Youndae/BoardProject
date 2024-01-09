package com.board.project.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.board.project.domain.dto.Criteria;
import com.board.project.domain.dto.ImageBoardModifyDTO;
import com.board.project.domain.dto.ImageDataDTO;
import com.board.project.domain.dto.PageDTO;
import com.board.project.domain.entity.Comment;
import com.board.project.domain.entity.ImageBoard;
import com.board.project.domain.entity.ImageData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.board.project.mapper.CommentMapper;
import com.board.project.mapper.ImageBoardMapper;
import com.board.project.service.ImageBoardService;

@Controller
@RequestMapping("/imageBoard")
@RequiredArgsConstructor
@Slf4j
public class ImageBoardController {

	private final ImageBoardMapper imageBoardMapper;

	private final ImageBoardService imageBoardService;

	private final CommentMapper commentMapper;

	//이미지 게시판 리스트
	@RequestMapping(value = "/imageList", method = RequestMethod.GET)
	public String imageList(Model model, Criteria cri) throws Exception {

		model.addAttribute("ImgList", imageBoardMapper.imageBoardList(cri));

		int totalPages = (int) (Math.ceil((imageBoardMapper.listCount(cri) * 1.0) / cri.getBoardAmount()));;


		model.addAttribute("pageMaker",
				new PageDTO(cri, totalPages));

		return "imageBoard/imageList";
	}

	//이미지 게시판 게시글 수정시 이미지 파일 리스트
	@RequestMapping(value = "/attachList", method = RequestMethod.GET)
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public ResponseEntity<List<ImageDataDTO>> getAttachList(Integer imageNo) throws Exception {

		return new ResponseEntity<>(imageBoardMapper.getAttachList(imageNo), HttpStatus.OK);
	}

	//이미지 게시판 상세페이지
	@RequestMapping(value = "/imageDetail", method = RequestMethod.GET)
	public String imageDetail(Model model
								, @RequestParam("imageNo") int imageNo
								, Criteria cri) throws Exception {
			

		Comment comment = Comment.builder().imageNo(imageNo).build();
		model.addAttribute("detail", imageBoardMapper.imageBoardDetail(imageNo));
		List<Comment> comment1 = commentMapper.commentList(comment, cri);
		log.info("comment1 : " + comment1);
		model.addAttribute("comment", comment1);
			
		model.addAttribute("pageMaker"
				, new PageDTO(cri, (int) (Math.ceil((commentMapper.cListCount(comment) * 1.0) / cri.getBoardAmount()))));
			
		return "imageBoard/imageDetail";
	}

	//이미지 게시판 게시글 수정 페이지
	@RequestMapping(value = "/imageModify", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public String imageModify(@RequestParam("imageNo") int imageNo , Model model, Principal principal) {

		ImageBoardModifyDTO vo = imageBoardService.getModifyImageData(imageNo, principal);

		if(vo == null)
			return "/accessError";
		else{
			model.addAttribute("list", vo);

			return "imageBoard/imageModify";
		}

	}

	//이미지 게시판 게시글 수정 처리
	@RequestMapping(value = "/imageModifyProc", method = RequestMethod.PATCH)
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public int imageModifyProc(@RequestParam(value = "files", required = false) List<MultipartFile> images
							   , @RequestParam(value = "deleteFiles", required = false) List<String> deleteFiles
							   , @RequestParam(value = "imageNo") int imageNo
							   , @RequestParam(value = "imageTitle") String imageTitle
							   , @RequestParam(value = "imageContent") String imageContent
								, Principal principal) {

		log.info("modifyProc");


		return imageBoardService.modifyCheck(images, deleteFiles, imageNo, imageTitle, imageContent, principal);
	}

	//이미지 게시판 게시글 작성 페이지
	@RequestMapping(value = "/imageInsert", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public String imageInsert() {

		return "imageBoard/imageInsert";
	}

	//이미지 게시판 게시글 작성 처리
	 @RequestMapping(value = "/imageInsertProc", method = RequestMethod.POST)
	 @ResponseBody
	 @PreAuthorize("hasRole('ROLE_MEMBER')")
	 public int imageInsertProc(@RequestParam(value = "imageTitle") String imageTitle
								, @RequestParam(value = "imageContent") String imageContent
									, @RequestParam(value = "files") List<MultipartFile> images
									 , Principal principal) {

		log.info("insert proc");

		return imageBoardService.insertImage(images, imageTitle, imageContent, principal);

	  }

	//이미지 출력 처리
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String image){
		File file = new File("E:\\upload\\boardProject\\" + image);

		ResponseEntity<byte[]> result = null;

		try{
			HttpHeaders header = new HttpHeaders();

			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		}catch (IOException e){
			e.printStackTrace();
		}

		return result;
	}

	//이미지 게시판 게시글 삭제
	@RequestMapping(value = "/imageDelete/{imageNo}", method = RequestMethod.DELETE)
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public int imageDelete(@PathVariable("imageNo") int imageNo
							, Principal principal) {

		return imageBoardService.deleteBoard(imageNo, principal);
	}

}
