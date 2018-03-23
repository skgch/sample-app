package com.sample.controller;

import java.util.List;

import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.sample.dto.UserFormDto;
import com.sample.dto.UserFormDto.Edit;
import com.sample.dto.UserFormDto.SignUp;
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
    public String idex(Model model, Pageable pageable) {
        model.addAttribute("title", "All users");
        Page<User> page = service.findAll(pageable);
        List<User> users = page.getContent();
        model.addAttribute("page", page);
        model.addAttribute("users", users);
        return TEMPLATE_DIR + "/index";
    }

    @RequestMapping(value = "/signup")
    public ModelAndView signUp(ModelAndView mav) {
        mav.setViewName(TEMPLATE_DIR + "/signUp");
        mav.addObject("title", "Sign Up");
        mav.addObject("formDto", new UserFormDto());
        mav.addObject("uri", "/signup");
        mav.addObject("buttonText", "Create my account");
        return mav;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String create(@ModelAttribute("formDto")
            @Validated({Default.class, SignUp.class}) UserFormDto formDto,
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

    @PreAuthorize("#id == principal.id")
    @RequestMapping(value = "user/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("title", "Edit");
        model.addAttribute("buttonText", "Save Changes");

        User user = service.findById(id);
        UserFormDto formDto = new UserFormDto();
        formDto.setName(user.getName());
        formDto.setEmail(user.getEmail());
        model.addAttribute("formDto", formDto);

        model.addAttribute("user", user);
        model.addAttribute("uri", "/user/" + user.getId());
        return TEMPLATE_DIR + "/edit";
    }

    @PreAuthorize("#id == principal.id")
    @RequestMapping(value = "user/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id,
                @ModelAttribute("formDto") @Validated({Default.class, Edit.class}) UserFormDto formDto,
                BindingResult result, Model model, RedirectAttributes redirectAttrs) {
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

    @PreAuthorize("hasRole('ADMIN') and #id != principal.id")
    @RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttrs) {
            service.delete(id);
            Flash flash = new Flash(true, "User deleted");
            redirectAttrs.addFlashAttribute("flash", flash);
            return "redirect:/";
    }

}
