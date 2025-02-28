package com.roman.Insurance.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Override
    public void sendEmailWithGeneratedAttachment (String to, String paymentLink, String subject, String templateName, byte[] fileData, String fileName) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        Context context = new Context();
        context.setVariable("name", "User");
        context.setVariable("paymentLink", paymentLink);
        String htmlContent = templateEngine.process(templateName, context);

        InputStreamSource attachment = new ByteArrayResource(fileData);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        helper.addAttachment(fileName, attachment);

        mailSender.send(message);

    }

    @Override
    public void sendEmailWithConfirmationAndAttachment (String to, String subject, String templateName, byte[] fileData, String fileName) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        Context context = new Context();
        context.setVariable("name", "User");
        String htmlContent = templateEngine.process(templateName, context);

        InputStreamSource attachment = new ByteArrayResource(fileData);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        helper.addAttachment(fileName, attachment);

        mailSender.send(message);
    }
}
