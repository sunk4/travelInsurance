package com.roman.Insurance.customerInsurance;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
