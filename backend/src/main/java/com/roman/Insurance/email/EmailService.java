package com.roman.Insurance.email;

import com.roman.Insurance.mainCustomer.MainCustomerEntity;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmailWithGeneratedAttachment (
            MainCustomerEntity mainCustomerEntity,
            String paymentLink,
            String templateName,
            byte[] fileData
    ) throws MessagingException;

    void sendEmailWithConfirmationAndAttachment (
            String to,
            String subject,
            String templateName,
            byte[] fileData, String fileName
    ) throws MessagingException;

}
