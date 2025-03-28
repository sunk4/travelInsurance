package com.roman.Insurance.email;

import com.roman.Insurance.mainCustomer.MainCustomerEntity;
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

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Override
    public void sendEmailWithGeneratedAttachment (MainCustomerEntity mainCustomerEntity, String paymentLink, String templateName, byte[] fileData) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        String fileName =
                mainCustomerEntity.getLastName() + "_" + mainCustomerEntity.getFirstName() + "_" + LocalDate.now() + ".pdf";

        String subject = "Cestovné poistenie " +
                mainCustomerEntity.getLastName() + " " + mainCustomerEntity.getFirstName();

        Context context = new Context();
        context.setVariable("mainCustomer", mainCustomerEntity);
        context.setVariable("paymentLink", paymentLink);
        String htmlContent = templateEngine.process(templateName, context);

        InputStreamSource attachment = new ByteArrayResource(fileData);

        helper.setTo(mainCustomerEntity.getEmail());
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        helper.addAttachment(fileName, attachment);

        mailSender.send(message);

    }

    @Override
    public void sendEmailWithConfirmationAndAttachment (
            MainCustomerEntity mainCustomerEntity,
            String templateName,
            byte[] fileData
    ) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        String fileName =
                mainCustomerEntity.getLastName() + "_" + mainCustomerEntity.getFirstName() + "_" + LocalDate.now() + ".pdf";

        String subject = "Cestovné poistenie " +
                mainCustomerEntity.getLastName() + " " + mainCustomerEntity.getFirstName() + " "  + "Platba prebehla úspešne";

        Context context = new Context();
        context.setVariable("mainCustomer", mainCustomerEntity);
        String htmlContent = templateEngine.process(templateName, context);

        InputStreamSource attachment = new ByteArrayResource(fileData);

        helper.setTo(mainCustomerEntity.getEmail());
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        helper.addAttachment(fileName, attachment);

        mailSender.send(message);
    }
}
