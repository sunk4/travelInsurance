package com.roman.Insurance.stripe;

import com.roman.Insurance.email.EmailService;
import com.roman.Insurance.insurance.InsuranceService;
import com.roman.Insurance.mainCustomer.MainCustomerEntity;
import com.roman.Insurance.mainCustomer.MainCustomerService;
import com.roman.Insurance.pdfgenerator.PdfGeneratorService;
import com.roman.Insurance.s3Bucket.UploadService;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/stripe")
@RequiredArgsConstructor
public class StripeController {
    @Value("${stripe.secret-key}")
    private String secretKey;

    @Value("${stripe.webhook-secret}")
    private String webhookSecret;

    private final InsuranceService insuranceService;
    private final EmailService emailService;
    private final PdfGeneratorService pdfGeneratorService;
    private final UploadService uploadService;
    private final MainCustomerService customerService;

    @PostMapping("/webhook")
    public ResponseEntity<String> handleStripeWebhook (
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader
    ) {
        System.out.println("HAHAHAHA");
        try {
            Stripe.apiKey = secretKey;
            Event event = Webhook.constructEvent(payload, sigHeader,
                    webhookSecret);



            if ("checkout.session.completed".equals(event.getType())) {
                Session session = (Session) event.getDataObjectDeserializer()
                        .getObject()
                        .orElseThrow(() -> new IllegalArgumentException("Invalid data object"));

                UUID mainCustomerId = UUID.fromString(session.getMetadata().get(
                        "mainCustomerId"));
                UUID insuranceId = UUID.fromString(session.getMetadata().get("insuranceId"));

                MainCustomerEntity customer =
                        customerService.getCustomerById(mainCustomerId);

                byte[] pdf = pdfGeneratorService.generatePdf(customer);
                String url = uploadService.uploadFileToS3(pdf,
                        "PaymentConfirmation_" + ".pdf");
                insuranceService.updateStatusOfPaymentAndUrlPayed(insuranceId, url);

                String customerEmail = session.getCustomerDetails().getEmail();
                emailService.sendEmailWithConfirmationAndAttachment(
                        customerEmail,
                        "Payment Confirmation",
                        "emailTemplateWhenPayed",
                        pdf,
                        "PaymentConfirmation_" + ".pdf"

                );

            }

            return ResponseEntity.ok("Webhook processed");
        } catch (SignatureVerificationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Webhook processing error");
        }
    }
}
