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
            MainCustomerEntity mainCustomerEntity,
            String templateName,
            byte[] fileData
    ) throws MessagingException;

}
