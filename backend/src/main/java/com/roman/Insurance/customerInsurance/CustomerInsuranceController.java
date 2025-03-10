package com.roman.Insurance.customerInsurance;

import com.roman.Insurance.customerInsurance.request.CustomerTravelInsuranceRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/customerInsurance")
@RequiredArgsConstructor
public class CustomerInsuranceController {
    private final CustomerInsuranceService customerInsuranceService;

    @PostMapping
    public ResponseEntity<Void> createTravelInsurance (@Valid @RequestBody CustomerTravelInsuranceRequest customerTravelInsuranceRequest) throws Exception {

        customerInsuranceService.createTravelInsurance(customerTravelInsuranceRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
