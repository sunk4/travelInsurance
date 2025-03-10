package com.roman.Insurance.stripe;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/stripe")
@RequiredArgsConstructor
public class StripeController {

    private final StripeService stripeService;

    @PostMapping("/webhook")
    public ResponseEntity<String> handleStripeWebhook (
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader
    ) throws Exception {
        stripeService.handleStripeWebhook(payload, sigHeader);
        return ResponseEntity.ok("Webhook processed");
    }
}
