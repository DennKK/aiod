package com.dennkk.aiod.controllers;

import com.dennkk.aiod.domain.entity.Role;
import com.dennkk.aiod.domain.entity.UserEntity;
import com.dennkk.aiod.service.contracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserAdminController {
    private final UserService userService;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "userList";
    }

    @GetMapping("/{userId}")
    public String userEditForm(@PathVariable Long userId, Model model) {
        UserEntity user = userService.findUserById(userId).orElse(null);
        if (user == null) {
            return "redirect:/admin/users";
        }

        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") UserEntity user
    ) {
        userService.saveUser(user, username, form);
        return "redirect:/admin/users";
    }
}
