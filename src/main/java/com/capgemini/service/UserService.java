package com.capgemini.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.capgemini.model.Role;
import com.capgemini.model.User;
import com.capgemini.repository.RoleRepository;
import com.capgemini.repository.UserRepository;

public interface UserService {
    public Optional<User> findUserByUsername(String username);
    public void saveUser(User user);
}
