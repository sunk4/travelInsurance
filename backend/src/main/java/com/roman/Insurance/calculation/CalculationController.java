package com.roman.Insurance.calculation;

import com.roman.Insurance.customerInsurance.CustomerTravelInsuranceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculation")
@RequiredArgsConstructor
public class CalculationController {
    private final CalculationService calculationService;

    @PostMapping
    public ResponseEntity<CalculationDto> calculatePrice (@RequestBody CustomerTravelInsuranceRequest customerTravelInsuranceRequest) {
        CalculationDto calculationResponseDto =
                calculationService.calculatePrice(customerTravelInsuranceRequest);

        return ResponseEntity.ok(calculationResponseDto);
    }
}
