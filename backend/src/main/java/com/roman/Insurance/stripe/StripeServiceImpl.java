package com.roman.Insurance.stripe;

import com.roman.Insurance.email.EmailService;
import com.roman.Insurance.insurance.InsuranceService;
import com.roman.Insurance.mainCustomer.MainCustomerEntity;
import com.roman.Insurance.mainCustomer.MainCustomerService;
import com.roman.Insurance.pdfgenerator.PdfGeneratorService;
import com.roman.Insurance.s3Bucket.UploadService;
import com.stripe.Stripe;
import com.stripe.model.Event;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StripeServiceImpl implements StripeService {

    @Value("${stripe.secret-key}")
    private String secretKey;

    @Value("${stripe.webhook-secret}")
    private String webhookSecret;

    private final MainCustomerService mainCustomerService;
    private final InsuranceService insuranceService;
    private final PdfGeneratorService pdfGeneratorService;
    private final UploadService uploadService;
    private final EmailService emailService;

    @Override
    public String createPaymentLink (
            double amount, String currency,
            String description, UUID mainCustomerId,
            UUID insuranceId
    ) {
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

    @Override
    @Transactional
    public void handleStripeWebhook (String payload, String sigHeader) throws Exception {

        Stripe.apiKey = secretKey;
        Event event = Webhook.constructEvent(payload, sigHeader,
                webhookSecret);

        if ("checkout.session.completed".equals(event.getType())) {
            Session session = (Session) event.getDataObjectDeserializer()
                    .getObject()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid data object"));

            UUID mainCustomerId = UUID.fromString(session.getMetadata().get(
                    "mainCustomerId"));
            UUID insuranceId = UUID.fromString(session.getMetadata().get(
                    "insuranceId"));

            MainCustomerEntity customer = mainCustomerService.getCustomerByIdEncrypted(mainCustomerId);

            byte[] pdf = pdfGeneratorService.generatePdf(customer);
            String url = uploadService.uploadFileToS3(pdf,
                    "PaymentConfirmation_" + ".pdf");
            insuranceService.updateStatusOfPaymentAndUrlPayed(insuranceId, url);

            emailService.sendEmailWithConfirmationAndAttachment(
                    customer,
                    "emailTemplateWhenPayed",
                    pdf
            );

        }

    }
}
