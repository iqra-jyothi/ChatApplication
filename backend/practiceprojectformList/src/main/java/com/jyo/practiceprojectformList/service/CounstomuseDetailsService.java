package com.jyo.practiceprojectformList.service;

import com.jyo.practiceprojectformList.Repository.UserRepo;
import com.jyo.practiceprojectformList.entity.Users;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service

public class CounstomuseDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public CounstomuseDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users=userRepo.findByName(username);
        if(users==null)
        {
            throw new UsernameNotFoundException("User not found");
        }
        return new CoustomUserDetails(users);
    }
}
