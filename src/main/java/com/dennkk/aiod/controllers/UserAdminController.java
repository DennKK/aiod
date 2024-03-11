package com.dennkk.aiod.controllers;

import com.dennkk.aiod.domain.Role;
import com.dennkk.aiod.domain.UserEntity;
import com.dennkk.aiod.repos.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserAdminController {
    private final UserRepo userRepo;

    public UserAdminController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("/{userId}")
    public String userEditForm(@PathVariable Long userId, Model model) {
        UserEntity user = userRepo.findById(userId).orElse(null);
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
            @RequestParam("userId") Long userId
    ) {
        UserEntity user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return "redirect:/admin/users";
        }
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);

        return "redirect:/admin/users";
    }
}