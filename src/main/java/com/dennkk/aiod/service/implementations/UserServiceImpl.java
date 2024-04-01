package com.dennkk.aiod.service.implementations;

import com.dennkk.aiod.domain.entity.Role;
import com.dennkk.aiod.domain.entity.UserEntity;
import com.dennkk.aiod.domain.repos.UserRepo;
import com.dennkk.aiod.service.contracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElse(null);
    }

    @Override
    public boolean addUser(UserEntity user) {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return true;
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<UserEntity> findUserById(Long userId) {
        return userRepo.findById(userId);
    }

    @Override
    public void saveUser(UserEntity user, String username, Map<String, String> form) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Enum::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();

        form.keySet().forEach(key -> {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        });

        userRepo.save(user);
    }
}
