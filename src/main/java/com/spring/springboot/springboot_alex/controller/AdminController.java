package com.spring.springboot.springboot_alex.controller;

import com.spring.springboot.springboot_alex.controller.dto.UserDto;
import com.spring.springboot.springboot_alex.model.User;
import com.spring.springboot.springboot_alex.service.RoleService;
import com.spring.springboot.springboot_alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String allUserShow(Model model){
        model.addAttribute("user", new UserDto());
        model.addAttribute("users", userService.listUsers());
        return "users";
    }

    @PostMapping
    public String create(@ModelAttribute("user") UserDto userDto){
        User user = fromDto(userDto);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String removeUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("users", userService.listUsers());
        return "edit";
    }



    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("user") UserDto userDto) {
        User user = fromDto(userDto);
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    private User fromDto(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setRoles(userDto.getRoles() == null ? null : userDto.getRoles().stream()
                .map(roleService::findByRoleName)
                .collect(Collectors.toSet())
        );
        return user;
    }
}
