package com.board.project.controller;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
		
		return "redirect:/login";
		
	}
	
	@RequestMapping("/Login")
	public String login() throws Exception{
		
		return "Member/login";
	}
	
	/*
	 * @RequestMapping("/LoginCheck") public ModelAndView loginCheck(@ModelAttribute
	 * MemberVO vo, HttpSession session) throws Exception{
	 * 
	 * boolean result = memberMapper.loginCheck(vo); ModelAndView mav = new
	 * ModelAndView();
	 * 
	 * if(result == true) { mav.setViewName("board/BoardList"); mav.addObject("msg",
	 * "success"); }else { mav.setViewName("board/Login"); mav.addObject("msg",
	 * "failure"); }
	 * 
	 * return mav; }
	 */
	
	@RequestMapping("/LoginProc")
	public String loginProc(MemberDTO memberDTO, HttpSession session, Model model) throws Exception{
		
		String uid = memberDTO.getUserId();
		System.out.println("uid : "+uid);
		
		MemberVO memberVO = memberService.loginCheck(memberDTO);
		System.out.println("userPw : "+memberDTO.getUserPw());
		if(memberVO == null || !BCrypt.checkpw(memberDTO.getUserPw(), memberVO.getUserPw())) {
			System.out.println("if false");
			 return "redirect:/Login";
		}
		session.setAttribute("userId", memberVO.getUserId()); 
		session.setAttribute("userName", memberVO.getUserName());
		model.addAttribute("member", memberVO);
		
		String id = (String) session.getAttribute("userId");
		System.out.println("member userId : "+id);
		
		 return "redirect:/BoardList"; 
	}
	
	@RequestMapping("/Logout")
	public String logout() throws Exception{
		
		return "redirect:/Login";
	}
	
}
