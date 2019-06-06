package com.capgemini.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capgemini.exceptions.AppException;
import com.capgemini.model.Role;
import com.capgemini.model.RoleName;
import com.capgemini.model.User;
import com.capgemini.payload.ApiResponse;
import com.capgemini.payload.JwtAuthenticationResponse;
import com.capgemini.payload.LoginRequest;
import com.capgemini.payload.SignUpRequest;
import com.capgemini.repository.RoleRepository;
import com.capgemini.repository.UserRepository;
import com.capgemini.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	    @Autowired
	    AuthenticationManager authenticationManager;

	    @Autowired
	    UserRepository userRepository;

	    @Autowired
	    RoleRepository roleRepository;

	    @Autowired
	    PasswordEncoder passwordEncoder;

	    @Autowired
	    JwtTokenProvider tokenProvider;

	    @PostMapping("/signin")
	    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getUsernameOrEmail(),
	                        loginRequest.getPassword()
	                )
	        );

	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        String jwt = tokenProvider.generateToken(authentication);
	        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	    }

	    
	    @PostMapping("/signup")
	    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
	    	
	        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
	            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Username already exists."), 
	            		HttpStatus.BAD_REQUEST);
	        }

	        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
	            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Email address already exists"),
	                    HttpStatus.BAD_REQUEST);
	        }

	        // Creating user's account
	        User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getUsername(),
	        		passwordEncoder.encode(signUpRequest.getPassword()), signUpRequest.getEmail(), 1);


	        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
	                .orElseThrow(() -> new AppException("User Role not set."));

	        user.setRoles(Collections.singleton(userRole));

	        User result = userRepository.save(user);

	        URI location = ServletUriComponentsBuilder
	                .fromCurrentContextPath().path("/api/users/{username}")
	                .buildAndExpand(result.getUsername()).toUri();

	        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	    }
}
