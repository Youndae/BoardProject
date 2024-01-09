package com.board.project.controller;


import lombok.extern.log4j.Log4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Log4j
public class CommonController {

    //error 페이지
    @RequestMapping(value = "/accessError", method = RequestMethod.GET)
    public void accessDenied(Authentication auth, Model model){
        log.info("access Denied : "+auth);
        model.addAttribute("msg", "Access Denied");
    }

}
