package com.board.project.service;

import com.board.project.domain.entity.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.board.project.mapper.MemberMapper;
import com.board.project.domain.entity.Member;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	

	private final MemberMapper memberMapper;


	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void joinProc(Member member) {
		// TODO Auto-generated method stub

		String pw = passwordEncoder.encode(member.getUserPw());

		member.setUserPw(pw);
		memberMapper.joinProc(member);

		Auth auth = Auth.builder()
				.userId(member.getUserId())
				.auth("ROLE_MEMBER")
				.build();

		memberMapper.joinAuth(auth);


	}
	
	

}
