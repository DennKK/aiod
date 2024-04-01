package com.dennkk.aiod.controllers;

import com.dennkk.aiod.domain.entity.UserEntity;
import com.dennkk.aiod.service.contracts.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profiles")
public class UserProfileController {
    private final UserService userService;

    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public String userProfile(@PathVariable Long userId, Model model) {
        UserEntity user = userService.findUserById(userId).orElse(null);
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("user", user);

        return "userProfile_view";
    }
}
