package com.example.emailservice.service;

import com.example.amqp.RabbitMQMessageProducer;
import com.example.feignclientservice.email.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine thymeleafTemplateEngine;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;


    @Value("${spring.mail.username}")
    private String username;

    public void sendMessage(EmailRequest emailRequest) {
        rabbitMQMessageProducer.publish(
                emailRequest,
                "internal.exchange",
                "internal.email.routing-key"
        );
    }

    public void sendMessageHtml(EmailRequest emailRequest) throws MessagingException {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(emailRequest.getAttributes());
        String htmlBody = thymeleafTemplateEngine.process(emailRequest.getTemplate(), thymeleafContext);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(username);
        helper.setTo(emailRequest.getTo());
        helper.setSubject(emailRequest.getSubject());
        helper.setText(htmlBody, true);
        mailSender.send(message);
    }
}