package com.jyo.practiceprojectformList.service;

import com.jyo.practiceprojectformList.Repository.TaskRepository;
import com.jyo.practiceprojectformList.Repository.UserRepo;
import com.jyo.practiceprojectformList.entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
private final UserRepo userRepo;
private final GenerateJWTtoken generateJWTtoken;
private final BCryptPasswordEncoder bCryptPasswordEncoder;
private final AuthenticationManager authenticationManager;
    public UserService(UserRepo userRepo, GenerateJWTtoken generateJWTtoken, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.generateJWTtoken = generateJWTtoken;

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
    }
    public Users register(Users users) {

        if (userRepo.existsByName(users.getName())) {
            throw new RuntimeException("Username already exists");
        }

        users.setPassword( bCryptPasswordEncoder
                .encode(users.getPassword()));
        return userRepo.save(users);
    }
    public String verify(Users users) {
        Authentication authenticate= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getName(),users.getPassword()
        ));
//        Users user=userRepo.findByName(users.getName());
//        if(user.getPassword().equals(users.getPassword()))
//        return "success";
//        return "failure";

        if(authenticate.isAuthenticated())
            return generateJWTtoken.generateToken(users);
        return "false";
    }

    //find my name
    public Users findByName(String name)
    {
        return userRepo.findByName(name);
    }

    @Transactional
    public void updatePassword(String name,String password){
        Users user=userRepo.findByName(name);
        if(user==null)
            throw new RuntimeException("User not found");
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepo.save(user);
    }

}
