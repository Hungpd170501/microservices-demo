package com.example.userservice.service;

import com.example.feignclientservice.email.EmailClient;
import com.example.feignclientservice.email.EmailRequest;
import com.example.feignclientservice.user.RegistrationRequest;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmailClient emailClient;

    public void registerUser(RegistrationRequest request) {
        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        userRepository.saveAndFlush(user);
        EmailRequest emailRequest = EmailRequest.builder()
                .to(user.getEmail())
                .subject("Registration code")
                .template("registration-template")
                .attributes(Map.of(
                        "fullName", user.getFirstName(),
                        "registrationCode", UUID.randomUUID().toString().substring(0, 7)))
                .build();
        emailClient.sendEmail(emailRequest);
    }
}
