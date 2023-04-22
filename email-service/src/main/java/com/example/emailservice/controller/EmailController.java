package com.example.emailservice.controller;

import com.example.emailservice.service.EmailService;
import com.example.feignclientservice.email.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> sendEmail(@RequestBody EmailRequest emailRequest) throws MessagingException {
        emailService.sendMessage(emailRequest);
        return ResponseEntity.noContent().build();
    }
}
