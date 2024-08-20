package com.sparta.adjustment.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/*
작성자 : 이승현
사용자 비밀번호 찾기를 위해 사용하는 SMTP 서비스
*/
@Service
public class EmailService {
    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public boolean sendUploaderRequestEmail(String to, String code) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Password Reset Request");
            message.setText("To reset your password, please use the following link: " + "https://domain.com/login/find?token=" + code);
            emailSender.send(message); // 이메일 전송
            return true; // 전송 성공
        } catch (Exception e) { // 이메일 전송 실패 시 예외 처리
            e.printStackTrace(); // 또는 로깅 처리
            return false; // 전송 실패
        }
    }
}