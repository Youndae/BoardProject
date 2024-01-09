package com.board.project.mapper;

import com.board.project.domain.entity.Auth;
import org.apache.ibatis.annotations.Mapper;

import com.board.project.domain.dto.MemberDTO;
import com.board.project.domain.entity.Member;

@Mapper
public interface MemberMapper {

	void joinProc(Member member);

	void joinAuth(Auth auth);

	int idCheck(String UserId);

	public Member read(String userid);


}
