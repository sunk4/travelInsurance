package com.roman.Insurance.riskFactor;

import com.roman.Insurance.riskFactor.response.RiskFactorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/risk-factors")
public class RiskFactorController {
    private final RiskFactorService riskFactorService;

    @GetMapping
    public ResponseEntity<List<RiskFactorResponse>> findAllRiskFactors () {
        List<RiskFactorResponse> riskFactors = riskFactorService.findAllRiskFactors();
        return new ResponseEntity<>(riskFactors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiskFactorResponse> findRiskFactorById (@PathVariable UUID id) {
        RiskFactorResponse riskFactor = riskFactorService.getRiskFactorById(id);
        return new ResponseEntity<>(riskFactor, HttpStatus.OK);
    }
}
