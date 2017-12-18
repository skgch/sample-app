package com.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StaticPagesController {

    private static final String TEMPLATE_DIR = "staticPages";

    @RequestMapping(value="/")
    public ModelAndView home(ModelAndView mav) {
        mav.setViewName(TEMPLATE_DIR + "/home");
        return mav;
    }

    @RequestMapping(value="/help")
    public ModelAndView help(ModelAndView mav) {
        mav.setViewName(TEMPLATE_DIR + "/help");
        mav.addObject("title", "Help");
        return mav;
    }

    @RequestMapping(value="/about")
    public ModelAndView about(ModelAndView mav) {
        mav.setViewName(TEMPLATE_DIR + "/about");
        mav.addObject("title", "About");
        return mav;
    }

    @RequestMapping(value="/contact")
    public ModelAndView contact(ModelAndView mav) {
        mav.setViewName(TEMPLATE_DIR + "/contact");
        mav.addObject("title", "Contact");
        return mav;
    }

}
