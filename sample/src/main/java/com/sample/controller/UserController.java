package com.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
        mav.addObject("formModel", new SignUpFormDto());
        return mav;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("formModel") @Validated SignUpFormDto formDto,
            BindingResult result, ModelAndView mav) {
        mav.setViewName(TEMPLATE_DIR + "/signUp");
        mav.addObject("title", "Sign Up");

        if(result.hasErrors()) {
            mav.addObject("formDto", formDto);
            return mav;
            //return new ModelAndView("redirect:/signup");
        }

        else {
            service.save(formDto.getUsername(), formDto.getEmail(), formDto.getPassword());
            // test
            Iterable<User> users = service.findAll();
            mav.addObject("users", users);
            return mav;
        }

    }

    @RequestMapping(value = "user/{id}")
    public ModelAndView show(@PathVariable("id") int id, ModelAndView mav) {
        mav.setViewName(TEMPLATE_DIR + "/show");
        User user = service.findById(id);
        mav.addObject("user", user);
        return mav;

    }
}
