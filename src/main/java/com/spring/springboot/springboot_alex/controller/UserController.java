package com.spring.springboot.springboot_alex.controller;

import com.spring.springboot.springboot_alex.model.User;
import com.spring.springboot.springboot_alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String Userdata(Authentication authentication, Model model){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "userdata";
    }

    @GetMapping("/user/{id}")
    public String UserdataAdmin(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.findUserById(id));
        return "userdata";
    }
}
