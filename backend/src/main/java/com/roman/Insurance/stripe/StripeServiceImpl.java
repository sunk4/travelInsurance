package com.roman.Insurance.stripe;

import com.roman.Insurance.stripe.StripeService;
import com.stripe.Stripe;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StripeServiceImpl implements StripeService {

    @Value("${stripe.secret-key}")
    private String secretKey;

    @Override
    public String createPaymentLink (double amount, String currency,
                                     String description, UUID mainCustomerId,
                                     UUID insuranceId){
        Stripe.apiKey = secretKey;
        try {
            Price price = Price.create(
                    PriceCreateParams.builder()
                            .setUnitAmount((long) (amount * 100))
                            .setCurrency(currency)
                            .setProductData(
                                    PriceCreateParams.ProductData.builder()
                                            .setName(description)
                                            .build()
                            )
                            .build()
            );

            PaymentLinkCreateParams params = PaymentLinkCreateParams.builder()
                    .addLineItem(
                            PaymentLinkCreateParams.LineItem.builder()
                                    .setPrice(price.getId())
                                    .setQuantity(1L)
                                    .build()
                    )
                    .putMetadata("mainCustomerId", String.valueOf(mainCustomerId))
                    .putMetadata("insuranceId", String.valueOf(insuranceId))
                    .build();

            PaymentLink paymentLink = PaymentLink.create(params);
            return paymentLink.getUrl();
        } catch (Exception e) {
            throw new RuntimeException("Error creating payment link", e);
        }
    }
}
