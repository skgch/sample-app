package com.sample.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.dto.Flash;

@Controller
public class SessionController {

    @RequestMapping(value = "/login")
    public String login(@RequestParam Optional<String> error, Model model) {
        if (error.isPresent()) {
            String message = "invalid email/password combination";
            Flash flash = new Flash(false, message);
            model.addAttribute("flash", flash);
        }
        model.addAttribute("title", "Log in");
        return "session/login";
    }

}
