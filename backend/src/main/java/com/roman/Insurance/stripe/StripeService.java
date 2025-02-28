package com.roman.Insurance.stripe;

import java.util.UUID;

public interface StripeService {
    String createPaymentLink(double amount, String currency,
                             String description, UUID insuranceId, UUID customerId);
}
