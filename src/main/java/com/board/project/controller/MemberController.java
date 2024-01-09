package com.board.project.controller;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.project.mapper.MemberMapper;
import com.board.project.service.MemberService;
import com.board.project.domain.entity.Member;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

	private final MemberMapper memberMapper;

	private final MemberService memberService;

	//회원가입 페이지
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() throws Exception{
		
		return "member/join";
	}

	//회원가입 처리
	@RequestMapping(value = "/joinProc", method = RequestMethod.POST)
	public String joinProc(Member member, RedirectAttributes redirectAttributes) throws Exception{
		
		log.info("joinProc");

		memberService.joinProc(member);

		redirectAttributes.addFlashAttribute("msg", "REGISTERED");
		
		return "redirect:/member/login";
	}

	//회원가입 아이디중복체크
	@RequestMapping(value = "/checkUserId", method = RequestMethod.GET)
	@ResponseBody
	public int idCheck(@RequestParam(value = "userId") String userId) throws Exception{

		return memberMapper.idCheck(userId);
	}

	//로그인 페이지
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) throws Exception{

		String referrer = request.getHeader("Referer");
		request.getSession().setAttribute("prevPage", referrer);

		return "member/login";
	}
}
