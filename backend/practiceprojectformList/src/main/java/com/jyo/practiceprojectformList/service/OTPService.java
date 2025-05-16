package com.jyo.practiceprojectformList.service;

import com.jyo.practiceprojectformList.Repository.OTPRepository;
import com.jyo.practiceprojectformList.Repository.UserRepo;
import com.jyo.practiceprojectformList.entity.OTP;
import com.jyo.practiceprojectformList.entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class OTPService {
    private final OTPRepository otpRepository;
private final UserRepo userRepository;
    public OTPService(OTPRepository otpRepository, UserRepo userRepository) {
        this.otpRepository = otpRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    public String generatAndStoreOtp(String email){
        SecureRandom random=new SecureRandom();
        String otp=String.valueOf(100000+random.nextInt(900000));
        otpRepository.deleteByEmail(email);

        OTP otpentity=new OTP();
        otpentity.setEmail(email);
        otpentity.setOtp(otp);
        otpentity.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otpRepository.save(otpentity);
        return otp;
    }
//    public Users findByEmail(String email) {
//        return userRepository.findByEmail(email).orElse(null);
//    }
    public boolean validateOtp(String email,String otp){
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        System.out.println("the email id"+email);
      return  otpRepository.findByEmail(email)//findByEmail(email)
                .filter(otp1 -> otp1.getOtp().equals(otp) && otp1.getExpiryTime().isAfter(now))
                .isPresent();
    }
}
