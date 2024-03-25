package com.dennkk.aiod.controllers;

import com.dennkk.aiod.domain.UserEntity;
import com.dennkk.aiod.domain.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profiles")
public class UserProfileController {
    private final UserRepo userRepo;

    public UserProfileController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/{userId}")
    public String userProfile(@PathVariable Long userId, Model model) {
        UserEntity user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);

        return "userProfile_view";
    }
}
