package com.board.project.service;

import com.board.project.domain.ResultProperties;
import com.board.project.domain.dto.HierarchicalBoardModifyDTO;
import com.board.project.domain.dto.HierarchicalBoardReplyDTO;
import com.board.project.domain.dto.HierarchicalBoardDeleteDTO;
import com.board.project.domain.entity.HierarchicalBoard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.board.project.mapper.HierarchicalBoardMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HierarchicalBoardServiceImpl implements HierarchicalBoardService{


	private final HierarchicalBoardMapper boardMapper;

	private final PrincipalService principalService;


	@Override
	@Transactional(rollbackFor = Exception.class)
	public int boardDelete(int boardNo, Principal principal) {


		if(!principalService.checkWriter(boardNo, "board", principal))
			return ResultProperties.FAIL;

		HierarchicalBoardDeleteDTO hierarchicalBoardDeleteDTO = boardMapper.checkDeleteNo(boardNo);

		//upperNo == 0 인 경우 최상위 글이기 때문에 하위글까지 전체 삭제.
		// 답글이 없는 경우이더라도 GroupNo는 자기 자신의 글번호를 갖고 있으니 괜찮.
		if(hierarchicalBoardDeleteDTO.getBoardUpperNo() == 0){
			boardMapper.deleteBoardGroup(hierarchicalBoardDeleteDTO.getBoardGroupNo());
		}else{
			//같은 groupNo를 갖고 있는 게시글 list
			List<HierarchicalBoardDeleteDTO> dto = boardMapper.groupData(hierarchicalBoardDeleteDTO.getBoardGroupNo());

			//삭제할 boardNo 리스트
			List<Integer> deleteNoList = new ArrayList<>();

			//삭제 요청이 들어온 boardNo를 먼저 담아줌.
			deleteNoList.add(boardNo);

			//삭제 요청이 들어온 게시글의 하위 글들을 찾아 list에 담아줌.
			deleteData(dto, boardNo, 0, deleteNoList);

			boardMapper.deleteHierarchicalList(deleteNoList);
		}

		return ResultProperties.SUCCESS;


		/*
			//게시글 삭제시에도 하위글을 남겨놓으려고 한다면

			//삭제요청이 들어온 게시글의 upperNo를 조회
			HierarchicalBoardDeleteDTO dto = boardMapper.checkDeleteNo(boardNo);
			int upperNo = dto.getBoardUpperNo();

			//해당 게시글의 바로 하위 게시글의 upperNo를 요청이 들어온 게시글의 upperNo로 수정.
			//mapper 삭제로 미구현.
			boardMapper.patchUpperNo(boardNo, upperNo)
			//UPDATE hierarchicalBoard SET boardUpperNo = #{boardUpperNo} WHERE boardUpperNo = #{boardNo}

			// 요청이 들어온 게시글 삭제
			boardMapper.deleteBoard(boardNo)

			//하위글을 삭제하더라도 indent는 건들지 않는 이유.
			//indent까지 수정하게 되면 같은 그룹내의 계층 구조에서 한단계 올라간다는 의미가 되고
			//그럼 삭제한것과 같은 계층에 있던 데이터와 동일한 계층으로 올라온다는 것인데
			//그렇게 되면 계층구조가 무너질 수 있기 때문에 indent는 건들지 않는다.

		 */

	}

	/**
	 * @param dto
	 * @param delNo
	 * @param idx
	 * @param deleteNoList
	 *
	 * 재귀호출을 통해 요청이 들어온 게시글의 하위 글들을 찾아 deleteNoList에 담아준다.
	 * 또한 계층형 게시판의 특성상 하위글이 상위글보다 글번호가 클 수 없기 때문에 리스트의 특정위치 이후로만 검색하도록 한다.
	 * 그리고 이 조건을 만족하기 위해 그룹 데이터를 가져올 때 boardNo 순서대로 가져오도록 쿼리를 작성한다.
	 */
	public void deleteData(List<HierarchicalBoardDeleteDTO> dto, int delNo, int idx, List<Integer> deleteNoList){

		for(int i = idx; i < dto.size(); i++){
			if(dto.get(i).getBoardUpperNo() == delNo){
				int no = dto.get(i).getBoardNo();
				deleteNoList.add(no);
				deleteData(dto, no, i, deleteNoList);
			}
		}
	}


	@Override
	public HierarchicalBoardModifyDTO getModifyBoardData(int boardNo, Principal principal) {

		try{
			if(!principalService.checkWriter(boardNo, "board", principal))
				return null;

			return boardMapper.getBoardModifyData(boardNo);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int boardModifyProc(HttpServletRequest request, Principal principal) {

		HierarchicalBoard hierarchicalBoard = HierarchicalBoard.builder()
												.boardTitle(request.getParameter("boardTitle"))
												.boardNo(Integer.parseInt(request.getParameter("boardNo")))
												.boardContent(request.getParameter("boardContent"))
												.build();


		if(!principalService.checkWriter(hierarchicalBoard.getBoardNo(), "board", principal))
			return ResultProperties.FAIL;

		boardMapper.boardModifyProc(hierarchicalBoard);
		return hierarchicalBoard.getBoardNo();


	}

	@Override
	public HierarchicalBoardReplyDTO getBoardReplyData(int boardNo) {

		try{
			return boardMapper.getBoardReplyData(boardNo);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int boardInsertProc(HttpServletRequest request, Principal principal) {


		HierarchicalBoard hierarchicalBoard = HierarchicalBoard.builder()
						.userId(principal.getName())
						.boardTitle(request.getParameter("boardTitle"))
						.boardContent(request.getParameter("boardContent"))
						.build();

		boardMapper.boardInsertProc(hierarchicalBoard);

		return ResultProperties.SUCCESS;

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int boardReplyProc(HttpServletRequest request, Principal principal) {

		HierarchicalBoard hierarchicalBoard = HierarchicalBoard.builder()
						.userId(principal.getName())
						.boardTitle(request.getParameter("boardTitle"))
						.boardContent(request.getParameter("boardContent"))
						.boardUpperNo(Integer.parseInt(request.getParameter("boardNo")))
						.boardGroupNo(Integer.parseInt(request.getParameter("boardGroupNo")))
						.boardIndent(Integer.parseInt(request.getParameter("boardIndent"))+1)
						.build();

		boardMapper.boardReplyProc(hierarchicalBoard);

		return ResultProperties.SUCCESS;

	}


}
