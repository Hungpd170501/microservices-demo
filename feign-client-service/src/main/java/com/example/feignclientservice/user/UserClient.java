package com.example.feignclientservice.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "user-service",
        url = "${clients.user.url}"
)
public interface UserClient {

    @PostMapping("api/v1/user")
    void registerUser(RegistrationRequest registrationRequest);
}
