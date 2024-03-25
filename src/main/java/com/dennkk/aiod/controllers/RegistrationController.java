package com.dennkk.aiod.controllers;

import com.dennkk.aiod.domain.Role;
import com.dennkk.aiod.domain.UserEntity;
import com.dennkk.aiod.domain.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    private final UserRepo userRepo;

    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/register")
    public String registration() {
        return "registration";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute UserEntity user, Model model) {
        UserEntity userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.addAttribute("message", "User already exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
