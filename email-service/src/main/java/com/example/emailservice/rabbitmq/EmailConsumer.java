package com.example.emailservice.rabbitmq;

import com.example.emailservice.service.EmailService;
import com.example.feignclientservice.email.EmailRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
@AllArgsConstructor
@Slf4j
public class EmailConsumer {
    private final EmailService emailService;

    @RabbitListener(queues = "${rabbitmq.queues.email}")
    public void consumer(EmailRequest emailRequest) throws MessagingException {
        log.info("Consumed {} from queue", emailRequest);
        emailService.sendMessageHtml(emailRequest);
    }
}
