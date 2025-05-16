package com.jyo.practiceprojectformList.service;//package com.jyo.practiceprojectformList.service;
//
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//    private final JavaMailSender mailSender;
//
//    public EmailService(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }
//
//    public void sendOTPEmail(String email,String otp){
//        //send email
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject("Your OTP for Password Reset");
//        message.setText("Your OTP is: " + otp + ". It is valid for 5 minutes.");
//
//        mailSender.send(message);
//
//    }
//}


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class  EmailService{
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOTPEmail(String email, String otp) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
           // ✅ Ensure this matches spring.mail.username
            message.setTo(email);
            message.setSubject("Your OTP for Password Reset");
            message.setText("Your OTP is: " + otp + ". It is valid for 5 minutes.");
            message.setFrom("jyothiiqra2003@gmail.com");
            mailSender.send(message);
            System.out.println("OTP email sent successfully to " + email);
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

