package com.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticPagesController {

    private static final String TEMPLATE_DIR = "staticPages";

    @RequestMapping(value="/")
    public String home() {
        return TEMPLATE_DIR + "/" +"home";
    }

    @RequestMapping(value="/help")
    public String help() {
        return TEMPLATE_DIR + "/" + "help";
    }

    @RequestMapping(value="/about")
    public String about() {
        return TEMPLATE_DIR + "/" + "about";
    }

    @RequestMapping(value="/contact")
	public String contact() {
        return TEMPLATE_DIR + "/" + "contact";
    }

}
