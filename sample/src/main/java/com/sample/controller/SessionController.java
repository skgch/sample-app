package com.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SessionController {

    @RequestMapping(value = "/login")
    public ModelAndView login(ModelAndView mav) {
        mav.setViewName("session/login");
        mav.addObject("title", "Log in");
        return mav;
    }

}
