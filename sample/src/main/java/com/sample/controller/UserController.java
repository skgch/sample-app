package com.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sample.dto.SignUpFormDto;
import com.sample.entity.User;
import com.sample.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    private static final String TEMPLATE_DIR = "user";

    @RequestMapping(value = "/signup")
    public ModelAndView signUp(ModelAndView mav) {
        mav.setViewName(TEMPLATE_DIR + "/signUp");
        mav.addObject("title", "Sign Up");
        mav.addObject("formDto", new SignUpFormDto());
        return mav;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String create(@ModelAttribute("formDto") @Validated SignUpFormDto formDto,
            BindingResult result, Model model, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			model.addAttribute("formDto", formDto);
			model.addAttribute("title", "Sign Up");
			return TEMPLATE_DIR + "/signUp";
		}

		User user = service.save(formDto.getUsername(), formDto.getEmail(), formDto.getPassword());
		redirectAttrs.addFlashAttribute("flash", "Welcome to the Sample App!");
		// TODO Redirect to user page instead of home page
		return "redirect:/";
    }

    @RequestMapping(value = "user/{id}")
    public ModelAndView show(@PathVariable("id") int id, ModelAndView mav) {
        mav.setViewName(TEMPLATE_DIR + "/show");
        User user = service.findById(id);
        mav.addObject("user", user);
        return mav;

    }
}
