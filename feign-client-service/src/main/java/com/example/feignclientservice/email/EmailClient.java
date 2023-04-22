package com.example.feignclientservice.email;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "email-service",
        url = "${clients.email.url}"
)
public interface EmailClient {

    @PostMapping("api/v1/email")
    void sendEmail(EmailRequest emailRequest);
}
