package com.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

import com.sample.dto.Flash;
import com.sample.dto.SignUpFormDto;
import com.sample.entity.User;
import com.sample.service.SessionService;
import com.sample.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private SessionService sessionService;

    private static final String TEMPLATE_DIR = "user";

    @RequestMapping(value = "/user")
    public String idex(Model model) {
        model.addAttribute("title", "All users");
        List<User> users = (List<User>) service.findAll();
        model.addAttribute("users", users);
        return TEMPLATE_DIR + "/index";
    }

    @RequestMapping(value = "/signup")
    public ModelAndView signUp(ModelAndView mav) {
        mav.setViewName(TEMPLATE_DIR + "/signUp");
        mav.addObject("title", "Sign Up");
        mav.addObject("formDto", new SignUpFormDto());
        mav.addObject("uri", "/signup");
        mav.addObject("buttonText", "Create my account");
        return mav;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String create(@ModelAttribute("formDto") @Validated SignUpFormDto formDto,
            BindingResult result, Model model, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            model.addAttribute("formDto", formDto);
            model.addAttribute("title", "Sign Up");
            model.addAttribute("uri", "/signup");
            model.addAttribute("buttonText", "Create my account");
            return TEMPLATE_DIR + "/signUp";
        }

        User user = service.save(formDto.getName(), formDto.getEmail(), formDto.getPassword());
        sessionService.autoLogin(formDto.getEmail().toLowerCase(), formDto.getPassword());

        Flash flash = new Flash(true, "Welcome to the Sample App!");
        redirectAttrs.addFlashAttribute("flash", flash);

        return "redirect:/user/" + user.getId();
    }

    @RequestMapping(value = "user/{id}")
    public ModelAndView show(@PathVariable("id") int id, ModelAndView mav) {
        mav.setViewName(TEMPLATE_DIR + "/show");
        User user = service.findById(id);
        mav.addObject("title", user.getName());
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "user/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        if (!isCorrectUser(id)) {
            return "redirect:/";
        }

        model.addAttribute("title", "Edit");
        model.addAttribute("buttonText", "Save Changes");

        User user = service.findById(id);
        SignUpFormDto formDto = new SignUpFormDto();
        formDto.setName(user.getName());
        formDto.setEmail(user.getEmail());
        model.addAttribute("formDto", formDto);

        model.addAttribute("user", user);
        model.addAttribute("uri", "/user/" + user.getId());
        return TEMPLATE_DIR + "/edit";
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id,
                @ModelAttribute("formDto") @Validated SignUpFormDto formDto,
                BindingResult result, Model model, RedirectAttributes redirectAttrs) {
        if (!isCorrectUser(id)) {
            return "redirect:/";
        }

        if (result.hasErrors()) {
            model.addAttribute("formDto", formDto);
            model.addAttribute("title", "Edit");
            model.addAttribute("uri", "/user/" + id);
            model.addAttribute("buttonText", "Save Changes");
            User user = service.findById(id);
            model.addAttribute("user", user);
            return TEMPLATE_DIR + "/edit";
        }

        User user = service.update(id, formDto.getName(), formDto.getEmail(), formDto.getPassword());

        Flash flash = new Flash(true, "Profile updated");
        redirectAttrs.addFlashAttribute("flash", flash);

        return "redirect:/user/" + user.getId();
    }

    private boolean isCorrectUser(int id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (id == user.getId());
    }
}