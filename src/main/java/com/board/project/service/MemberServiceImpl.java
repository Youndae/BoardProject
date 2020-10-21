package com.board.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.project.dto.MemberDTO;
import com.board.project.mapper.MemberMapper;
import com.board.project.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberMapper memberMapper;

	@Override
	public void joinProc(MemberVO memberVO) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MemberVO loginCheck(MemberDTO memberDTO) throws Exception {
		
		/* boolean result = memberVO; */
		
		return memberMapper.loginCheck(memberDTO);
	}
	
	

}
