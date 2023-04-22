package com.example.feignclientservice.user;

public record RegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}