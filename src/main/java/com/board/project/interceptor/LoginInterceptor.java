package com.board.project.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private static final String LOGIN = "userId";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		HttpSession session = request.getSession();
		System.out.println("pre session : "+session.getAttribute(LOGIN)+", "+session.getAttribute("userName"));
		if(session.getAttribute(LOGIN) != null) {
			logger.info("clear login data before");
			session.removeAttribute(LOGIN);
			session.removeAttribute("userName");
			System.out.println("pre session2 : "+session.getAttribute(LOGIN));
		}
		
		return true;
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
		
		super.postHandle(request, response, handler, modelAndView);
		/*
		 * HttpSession session = request.getSession(); ModelMap modelMap =
		 * modelAndView.getModelMap(); Object memberVO = modelMap.get("member");
		 * 
		 * if(memberVO != null) { logger.info("new login success");
		 * session.setAttribute(LOGIN, memberVO);
		 * System.out.println("post session : "+session.getAttribute(LOGIN));
		 * response.sendRedirect("/board/BoardList"); }
		 */
	}
	
	

}
