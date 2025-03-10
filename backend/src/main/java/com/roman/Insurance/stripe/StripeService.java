package com.roman.Insurance.stripe;

import java.util.UUID;

public interface StripeService {
    String createPaymentLink (
            double amount, String currency,
            String description, UUID mainCustomerId,
            UUID insuranceId
    );

    void handleStripeWebhook (String payload, String sigHeader) throws Exception;
}
