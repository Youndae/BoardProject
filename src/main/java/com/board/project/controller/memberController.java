package com.board.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.project.dto.MemberDTO;
import com.board.project.mapper.MemberMapper;
import com.board.project.service.MemberService;
import com.board.project.vo.MemberVO;

@Controller
public class memberController {
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/Join")
	public String join() throws Exception{
		
		return "Member/Join";
	}
	
	@RequestMapping("/JoinProc")
	public String joinProc(MemberVO memberVO, RedirectAttributes redirectAttributes) throws Exception{
		
		String hashedPw = BCrypt.hashpw(memberVO.getUserPw(), BCrypt.gensalt());
		memberVO.setUserPw(hashedPw);
		memberMapper.joinProc(memberVO);
		redirectAttributes.addFlashAttribute("msg", "REGISTERED");
		
		return "redirect:/Login";
		
	}
	
	@RequestMapping("/CheckUserId")
	@ResponseBody
	public int idCheck(@RequestParam("UserId") String UserId) throws Exception{
		
		return memberMapper.idCheck(UserId);
	}
	
	@RequestMapping("/Login")
	public String login() throws Exception{
		
		return "Member/login";
	}
	
	
	  	
	@RequestMapping("/LoginProc")
	public String loginProc(MemberDTO memberDTO, HttpSession session, Model model) throws Exception{
		
		MemberVO memberVO = memberMapper.loginCheck(memberDTO);
		
		if(memberVO == null || !BCrypt.checkpw(memberDTO.getUserPw(), memberVO.getUserPw())) {
			
			String msg = "false";
			model.addAttribute("login", msg);
			 return "Member/login";
		}
		session.setAttribute("userId", memberVO.getUserId()); 
		session.setAttribute("userName", memberVO.getUserName());
		model.addAttribute("member", memberVO);
		
		return "redirect:/BoardList";
	}
	
	@RequestMapping("/Logout")
	public String logout() throws Exception{
		
		return "redirect:/Login";
	}
	
	
}
