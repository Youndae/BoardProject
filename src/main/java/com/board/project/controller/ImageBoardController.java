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

	@RequestMapping("/ImageModifyTest")
	public String imageModifyTest(@RequestParam("ImageNo") int ImageNo , Model model) throws Exception {


		model.addAttribute("list", imageBoardMapper.ModifyTest(ImageNo));
		System.out.println("img : " + imageBoardMapper.ModifyTest(ImageNo));
		return "ImageBoard/ImageModify2";
	}

	@RequestMapping("/AttachList")
	@ResponseBody
	public ResponseEntity<List<ImageDataVO>> getAttachList(Integer ImageNo) throws Exception {
		System.out.println("ImageNo : " + ImageNo);
		System.out.println("result : " + new ResponseEntity<>(imageBoardMapper.getAttachList(ImageNo), HttpStatus.OK));

		return new ResponseEntity<>(imageBoardMapper.getAttachList(ImageNo), HttpStatus.OK);
	}

	@GetMapping(value = "/AttachList2", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<ImageDataVO>> getAttachList2(int ImageNo) throws Exception {
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
	public String imageDetail(Model model, @RequestParam("imageNo") int imageNo, CommentVO commentVO) throws Exception {
			System.out.println("ImageDetail Hi!");
			commentVO.setImageNo(imageNo);
			model.addAttribute("detail", imageBoardMapper.imageDetail(imageNo));
			System.out.println("Detail Complete");
			model.addAttribute("comment", commentMapper.bCommentList(commentVO));
			
		return "ImageBoard/Imagedetail";
	}

	@RequestMapping("/imageModify/{bno}")
	public String imageModify(Model model) throws Exception {

		return "ImageBoard/ImageModify";
	}

	@RequestMapping("/imageModifyProc")
	public String imageModifyProc() throws Exception {

		return "redirect:ImageList";
	}

	@RequestMapping("/ImageInsert")
	public String imageInsert() throws Exception {

		return "ImageBoard/ImageInsert";
	}

	@RequestMapping("/ImageModifyProcTest2")
	@ResponseBody
	public int ImageFileDelete2(@RequestParam("files") List<MultipartFile> images,
			@RequestParam(value = "deletefiles", required = false) List<String> deletefiles, HttpServletRequest request,
			ImageBoardVO imageBoardVO, ImageDataVO imageDataVO) throws Exception {
		System.out.println("안녕?");

		imageBoardVO.setImageNo(Integer.parseInt(request.getParameter("ImageNo")));
		imageBoardVO.setImageTitle(request.getParameter("ImageTitle"));
		imageBoardVO.setImageContent(request.getParameter("ImageContent"));
		imageBoardMapper.ModifyProc(imageBoardVO);
		System.out.println("CImageNo : " + imageBoardVO.getImageNo());
		int step = imageBoardMapper.CountStep(imageBoardVO.getImageNo());
		System.out.println("Step : " + step);

		if (deletefiles != null) {
			System.out.println("지울거 체크 start");
			imageBoardService.deletefiles(deletefiles, request);
			System.out.println("지울거 체크 End");
		} else {
			System.out.println("지울 데이터 없어");
		}

		if (images.size() != 0) {
			System.out.println("저장할거 체크 start");
			result = imageBoardService.imageModify(images, request, step, imageDataVO);

		} else {
			System.out.println("저장할 데이터 없어");
		}

		System.out.println("처리 끝");

		return result;
	}

	/*
	 * @RequestMapping("/ImageModifyProcTest")
	 * 
	 * @ResponseBody public int ImageFileDelete(@RequestParam("files")
	 * List<MultipartFile> images, @RequestParam(value = "deletefiles", required =
	 * false) List<String> deletefiles, HttpServletRequest request, ImageBoardVO
	 * imageBoardVO, ImageDataVO imageDataVO) throws Exception {
	 * System.out.println("안녕?"); System.out.println(deletefiles.size());
	 * System.out.println(images.size()); for (int i = 0; i < deletefiles.size();
	 * i++) { System.out.println("file : " + deletefiles.get(i));
	 * 
	 * imageBoardMapper.deletefiles(deletefiles.get(i)); String path =
	 * request.getSession().getServletContext().getRealPath("IMG/"); File file = new
	 * File(path+deletefiles.get(i)); if(file.exists()) { if(file.delete()) {
	 * System.out.println("삭제 성공"); }else{ System.out.println("삭제 실패"); } }else {
	 * System.out.println("파일 없어"); }
	 * 
	 * }
	 * 
	 * 
	 * imageBoardVO.setImageTitle(request.getParameter("ImageTitle"));
	 * imageBoardVO.setImageContent(request.getParameter("ImageContent"));
	 * imageBoardVO.setImageNo(Integer.parseInt(request.getParameter("ImageNo")));
	 * System.out.println("board Upload Go");
	 * imageBoardMapper.ModifyProc(imageBoardVO);
	 * System.out.println("board Upload Complete");
	 * 
	 * int step = imageBoardMapper.CountStep(imageBoardVO.getImageNo());
	 * 
	 * long sizeSum = 0; String filePath =
	 * request.getSession().getServletContext().getRealPath("IMG/");
	 * System.out.println("filePath : "+filePath); List<String> ImageArray = new
	 * ArrayList<>();
	 * 
	 * for (MultipartFile image : images) { String originalName =
	 * image.getOriginalFilename();
	 * 
	 * System.out.println("originalName: "+originalName); //확장자 검사
	 * if(!isValidExtension(originalName)) { return RESULT_UNACCEPTED_EXTENSION; }
	 * 
	 * //용량검사 sizeSum += image.getSize(); if(sizeSum >= LIMIT_SIZE) { return
	 * RESULT_EXCEED_SIZE; }
	 * 
	 * try { //저장 System.out.println("save Start"); StringBuffer sb = new
	 * StringBuffer();
	 * 
	 * String saveName = sb.append(new
	 * SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()))
	 * .append(UUID.randomUUID().toString())
	 * .append(originalName.substring(originalName.lastIndexOf("."))).toString();
	 * 
	 * String saveFile = filePath + saveName; System.out.println("saveFile : "+
	 * saveFile); ImageArray.add(saveName);
	 * 
	 * 
	 * step++; image.transferTo(new File(saveFile)); System.out.println("save End");
	 * imageDataVO.setImageData(saveName); imageDataVO.setOldName(originalName);
	 * imageDataVO.setImageStep(step); imageBoardMapper.imageInsert(imageDataVO);
	 * 
	 * }catch(Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * return RESULT_SUCCESS;
	 * 
	 * }
	 */

	@RequestMapping("/imageupload")
	@ResponseBody
	public int multiImageUpload(@RequestParam("files") List<MultipartFile> images, HttpServletRequest request,
			ImageBoardVO imageBoardVO, ImageDataVO imageDataVO) throws Exception {
		System.out.println("images : " + images.size());
		/*
		 * int imageNo = 8; imageBoardVO.setBoardNo(imageNo);//seq.nextval로 사용.
		 */
		imageBoardVO.setImageTitle(request.getParameter("ImageTitle"));
		imageBoardVO.setUserId("coco");
		imageBoardVO.setImageContent(request.getParameter("ImageContent"));
		System.out.println("board Upload Go");
		imageBoardMapper.imageInsertProc(imageBoardVO);
		System.out.println("board Upload Complete");

		result = imageBoardService.imageInsert(images, request, imageDataVO);

		/* int step = 1; */
		// step은 service로 넘겨서 같은 그룹중 가장 큰 값을 가져오도록 만들자.
		/*
		 * long sizeSum = 0; String filePath =
		 * request.getSession().getServletContext().getRealPath("IMG/");
		 * System.out.println("filePath : " + filePath); List<String> ImageArray = new
		 * ArrayList<>();
		 * 
		 * for (MultipartFile image : images) { String originalName =
		 * image.getOriginalFilename(); System.out.println("originalName: " +
		 * originalName); // 확장자 검사 if (!isValidExtension(originalName)) { return
		 * RESULT_UNACCEPTED_EXTENSION; }
		 * 
		 * // 용량검사 sizeSum += image.getSize(); if (sizeSum >= LIMIT_SIZE) { return
		 * RESULT_EXCEED_SIZE; } try { // 저장 System.out.println("save Start");
		 * StringBuffer sb = new StringBuffer();
		 * 
		 * String saveName = sb.append(new
		 * SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()))
		 * .append(UUID.randomUUID().toString())
		 * .append(originalName.substring(originalName.lastIndexOf("."))).toString();
		 * 
		 * String saveFile = filePath + saveName; System.out.println("saveFile : " +
		 * saveFile); ImageArray.add(saveName);
		 * 
		 * image.transferTo(new File(saveFile)); System.out.println("save End");
		 * imageDataVO.setImageData(saveName); imageDataVO.setOldName(originalName);
		 * imageDataVO.setImageStep(step); imageBoardMapper.imageInsert(imageDataVO);
		 * step++; } catch (Exception e) { e.printStackTrace(); } }
		 */

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
		return result;
	}
	
	@RequestMapping("/ImageDelete")
	@ResponseBody
	public String ImageDelete(@RequestParam("ImageNo") int ImageNo)throws Exception{
		System.out.println("Delete start!");
		System.out.println("Delete ImageNo : "+ImageNo);
		imageBoardMapper.imageDataDelete(ImageNo);
		System.out.println("Data Delete Complete!");
		imageBoardMapper.imageDelete(ImageNo);
		
		
		commentService.commentDeleteBoard(ImageNo, null);
		
		return "redirect:/board/ImageList";
	}


}
