package com.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SessionController {

    @RequestMapping(value = "/login")
    public String login()
    {
        return "session/login";
    }

}
