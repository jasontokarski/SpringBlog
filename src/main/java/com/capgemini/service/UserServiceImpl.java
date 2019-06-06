package com.capgemini.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.capgemini.model.Role;
import com.capgemini.model.User;
import com.capgemini.repository.RoleRepository;
import com.capgemini.repository.UserRepository;

//@Service
//public class UserServiceImpl implements UserService {
//	
//	@Autowired
//    private UserRepository userRepository;
//	@Autowired
//    private RoleRepository roleRepository;
//	@Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//    
//    
//	public UserServiceImpl() {
//	}
//
//
//	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
//		this.userRepository = userRepository;
//		this.roleRepository = roleRepository;
//		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//	}
//    
//    public Optional<User> findUserByUsername(String username) {
//    	return userRepository.findByUsername(username);
//    }
//    
//    public Optional<User> findUserByEmail(String email) {
//    	return userRepository.findByEmail(email);
//    }
//    
////    public void saveUser(User user) {
////    	//Get the password input from form
////    	bCryptPasswordEncoder = new BCryptPasswordEncoder();
////    	
////    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
////    	user.setActive(1);
////        Role userRole = new Role(user.getId(), "USER");
////        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
////        userRepository.save(user);
////    }
//}
