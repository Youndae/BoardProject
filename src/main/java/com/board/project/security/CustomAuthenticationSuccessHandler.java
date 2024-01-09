package com.board.project.security;


import lombok.extern.log4j.Log4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.warn("Login Success");
        List<String> roleNames = new ArrayList<>();

        authentication.getAuthorities().forEach(authority->{
            roleNames.add(authority.getAuthority());
        });

        HttpSession session = request.getSession();

        if(session != null){
            String redirectUrl = (String) session.getAttribute("prevPage");

            log.info("RedirectUrl : " + redirectUrl);
            if(redirectUrl != null && !redirectUrl.contains("/member/login")){
                session.removeAttribute("prevPage");
                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
            }else{
                super.onAuthenticationSuccess(request, response, authentication);
            }

        }else{
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

}
