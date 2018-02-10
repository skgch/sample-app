package com.sample.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.dto.Flash;
import com.sample.entity.User;

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

    @RequestMapping(value = "/default")
    public String defaultAfterLogin(Principal principal) {
        Authentication auth = (Authentication) principal;
        User user = (User) auth.getPrincipal();
        return "redirect:/user/" + user.getId();
    }

}
