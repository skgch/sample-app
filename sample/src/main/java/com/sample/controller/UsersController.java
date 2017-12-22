package com.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsersController {

    private static final String TEMPLATE_DIR = "users";

    @RequestMapping(value="/signup")
    public ModelAndView signUp(ModelAndView mav) {
        mav.setViewName(TEMPLATE_DIR + "/signUp");
        mav.addObject("title", "Sign Up");
        return mav;
    }
}
