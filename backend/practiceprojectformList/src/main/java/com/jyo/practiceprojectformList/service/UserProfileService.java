package com.jyo.practiceprojectformList.service;

import com.jyo.practiceprojectformList.Repository.UserProfileRepository;
import com.jyo.practiceprojectformList.entity.UserProfile;

public class UserProfileService {
    private final UserProfileRepository userRepository;

    public UserProfileService(UserProfileRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserProfile getUserByEmail(String email) {
        return userRepository.findByEmail(email); // Find user by email
    }
}
