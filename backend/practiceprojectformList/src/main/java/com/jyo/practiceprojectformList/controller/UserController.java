package com.jyo.practiceprojectformList.controller;

import com.jyo.practiceprojectformList.Repository.UserRepo;
import com.jyo.practiceprojectformList.entity.Users;
import com.jyo.practiceprojectformList.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/api/tasks")
public class UserController {
    private  final UserService userService;

private final UserRepo userRepo;
    public UserController(UserService userService, UserRepo userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }
    @PostMapping("/register")
//    public Users register(@RequestBody Users users)
//    {
//
//
//        return  userService.register(users);
//    }
    public ResponseEntity<?> register(@Valid @RequestBody Users users, BindingResult result) {
        if (result.hasErrors()) {
            // Collect validation errors into a readable format
            StringBuilder errors = new StringBuilder();
            result.getFieldErrors().forEach(error ->
                    errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
            );
            return ResponseEntity.badRequest().body(errors.toString());
        }

        // If validation passes, proceed with registration
        Users registeredUser = userService.register(users);
        return ResponseEntity.ok(registeredUser);

    }
    @CrossOrigin(origins = "http://localhost:5173")
        @PostMapping("/login")
    public String login(@RequestBody Users  users )
    {
        return userService.verify(users);
    }





}
