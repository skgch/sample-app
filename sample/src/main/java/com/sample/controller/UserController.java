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
        return mav;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute("formModel") @Validated User user,
            BindingResult result, ModelAndView mav) {
        mav.setViewName(TEMPLATE_DIR + "/signUp");
        mav.addObject("title", "Sign Up");

        if (!result.hasErrors()) {
            service.save(user);
            // test
            Iterable<User> users = service.findAll();
            mav.addObject("users", users);
            return mav;
        }

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "user/{id}")
    public ModelAndView show(@PathVariable("id") int id, ModelAndView mav) {
        mav.setViewName(TEMPLATE_DIR + "/show");
        User user = service.findById(id);
        mav.addObject("user", user);
        return mav;

    }
}
