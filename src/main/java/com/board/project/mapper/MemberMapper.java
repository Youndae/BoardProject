package com.board.project.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.board.project.dto.MemberDTO;
import com.board.project.vo.MemberVO;

@Mapper
public interface MemberMapper {

	void joinProc(MemberVO memberVO) throws Exception;
	
	public MemberVO loginCheck(MemberDTO memberDTO) throws Exception;
	
	int IdCheck(String UserId) throws Exception;
}
