package com.board.project.service;

import com.board.project.domain.dto.HierarchicalBoardModifyDTO;
import com.board.project.domain.dto.HierarchicalBoardReplyDTO;
import com.board.project.domain.entity.HierarchicalBoard;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

public interface HierarchicalBoardService {
	
	public int boardDelete(int BoardNo, Principal principal) throws Exception;
	
	public HierarchicalBoardModifyDTO getModifyBoardData(int boardNo, Principal principal);

	public HierarchicalBoardReplyDTO getBoardReplyData(int boardNo);

	public int boardModifyProc(HttpServletRequest request, Principal principal);

	public int boardInsertProc(HttpServletRequest request, Principal principal);

	public int boardReplyProc(HttpServletRequest request, Principal principal);
}
