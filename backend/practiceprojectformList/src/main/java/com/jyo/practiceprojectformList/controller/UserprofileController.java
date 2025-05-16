package com.jyo.practiceprojectformList.controller;

import com.jyo.practiceprojectformList.DTO.ProfileUpdateRequest;
import com.jyo.practiceprojectformList.Repository.UserProfileRepository;
import com.jyo.practiceprojectformList.entity.UserProfile;
import com.jyo.practiceprojectformList.entity.Users;
import com.jyo.practiceprojectformList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserprofileController {
    @Autowired
    private UserProfileRepository userRepository;
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/updateprofile")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileUpdateRequest request) {
//        Optional<UserProfile> userOptional = userRepository.findById(request.getEmail());
//        if (userOptional.isPresent()) {
//            UserProfile user = userOptional.get();
//            user.setName(request.getName());
//            user.setProfilePhotoUrl(request.getProfilePhotoUrl());
//            userRepository.save(user); // Save the updated user profile
//            return ResponseEntity.ok("Profile updated successfully");


        UserProfile user = userRepository.findById(request.getEmail()).orElse(new UserProfile());

        user.setEmail(request.getEmail()); // required if new
        user.setName(request.getName());
        user.setProfilePhotoUrl(request.getProfilePhotoUrl());

        userRepository.save(user);
        return ResponseEntity.ok("Profile updated successfully");
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserProfile>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserProfile>> searchUsers(@RequestParam String name) {
        return ResponseEntity.ok(userRepository.findByNameContainingIgnoreCase(name));
    }


    @Autowired
    private UserService userService;

    @GetMapping("/profile/{email}")
    public Users getUserProfile(@PathVariable String email) {
        return userService.findByName(email);  // Assuming you have a method to fetch user by email
    }
}
