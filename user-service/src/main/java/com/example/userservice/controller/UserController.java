package com.example.userservice.controller;

import com.example.feignclientservice.user.RegistrationRequest;
import com.example.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public void registerUser(@RequestBody RegistrationRequest registrationRequest) {
        log.info("new customer registration {}", registrationRequest);
        userService.registerUser(registrationRequest);
    }
}
