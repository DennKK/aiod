package com.dennkk.aiod.controllers;

import com.dennkk.aiod.domain.entity.UserEntity;
import com.dennkk.aiod.service.contracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping("/register")
    public String registration() {
        return "registration";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute UserEntity user, Model model) {
        if (!userService.addUser(user)) {
            model.addAttribute("message", "User already exists!");
            return "registration";
        }
        return "redirect:/login";
    }
}
