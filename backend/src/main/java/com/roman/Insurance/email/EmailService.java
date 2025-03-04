package com.roman.Insurance.email;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmailWithGeneratedAttachment (
            String to, String paymentLink,
            String subject,
            String templateName,
            byte[] fileData, String fileName
    ) throws MessagingException;

    void sendEmailWithConfirmationAndAttachment (
            String to,
            String subject,
            String templateName,
            byte[] fileData, String fileName
    ) throws MessagingException;

}
