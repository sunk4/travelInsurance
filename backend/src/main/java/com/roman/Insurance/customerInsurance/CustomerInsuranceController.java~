package com.roman.Insurance.customerInsurance;

import com.roman.Insurance.calculation.CalculationService;
import com.roman.Insurance.customer.MainCustomerService;
import com.roman.Insurance.email.EmailService;
import com.roman.Insurance.insurance.InsuranceService;
import com.roman.Insurance.insuredPerson.InsuredPersonService;
import com.roman.Insurance.pdfgenerator.PdfGeneratorService;
import com.roman.Insurance.s3Bucket.UploadService;
import com.roman.Insurance.stripe.StripeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customerInsurance")
@RequiredArgsConstructor
public class CustomerInsuranceController {
    private final CustomerInsuranceService customerInsuranceService;

    @PostMapping
    public ResponseEntity<Void> createTravelInsurance (@RequestBody CustomerTravelInsuranceRequest customerTravelInsuranceRequest) throws Exception {

        customerInsuranceService.createTravelInsurance(customerTravelInsuranceRequest);

        return null;

    }
}
