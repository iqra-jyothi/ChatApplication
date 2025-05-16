package com.jyo.practiceprojectformList.controller;

import com.jyo.practiceprojectformList.DTO.ForgotPasswordRequest;
import com.jyo.practiceprojectformList.DTO.OTPRequest;
import com.jyo.practiceprojectformList.DTO.ResetPasswordRequest;
import com.jyo.practiceprojectformList.entity.Users;
import com.jyo.practiceprojectformList.service.EmailService;
import com.jyo.practiceprojectformList.service.OTPService;
import com.jyo.practiceprojectformList.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class ForgotPasswordController {
    private final OTPService otpService;
    private final UserService userService;
    private final EmailService emailService;

    public ForgotPasswordController(OTPService otpService, UserService userService, EmailService emailService) {
        this.otpService = otpService;
        this.userService = userService;
        this.emailService = emailService;
    }

    // Step 1: Send OTP to email
    @PostMapping("/forgot-password")
    public ResponseEntity<String> sendOTP(@RequestBody ForgotPasswordRequest request) {
        Users user = userService.findByName(request.getEmail());
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        String otp = otpService.generatAndStoreOtp(request.getEmail());
        emailService.sendOTPEmail(request.getEmail(), otp); // Send OTP via Email

        return ResponseEntity.ok("OTP sent successfully");
    }



    // Step 2: Verify OTP
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@RequestBody OTPRequest request) {
        boolean isValid = otpService. validateOtp(request.getEmail(), request.getOtp());
System.out.println(request.getEmail());
        if (!isValid) {
            return ResponseEntity.status(401).body("Invalid or expired OTP");
        }
//        Users user=userService.findByName(request.getEmail());
//        user.setVerified(true);
//        userService.saveUser(user);
        return ResponseEntity.ok("OTP Verified. Proceed to reset password.");
    }

    // Step 3: Reset Password
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        boolean isValid = otpService.validateOtp(request.getEmail(), request.getOtp());

        if (!isValid) {
            return ResponseEntity.status(401).body("Invalid OTP");
        }

        userService.updatePassword(request.getEmail(), request.getNewPassword());
        return ResponseEntity.ok("Password reset successfully");
    }
}
