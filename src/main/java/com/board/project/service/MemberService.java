package com.board.project.service;

import com.board.project.dto.MemberDTO;
import com.board.project.vo.MemberVO;

public interface MemberService {
	
	void joinProc(MemberVO memberVO) throws Exception;
	
	public MemberVO loginCheck(MemberDTO memberDTO) throws Exception;
}
