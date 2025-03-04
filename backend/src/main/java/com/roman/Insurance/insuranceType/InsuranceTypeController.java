package com.roman.Insurance.insuranceType;

import com.roman.Insurance.insuranceType.response.InsuranceTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/insuranceType")
public class InsuranceTypeController {
    private final InsuranceTypeService insuranceTypeService;

    @GetMapping
    public ResponseEntity<List<InsuranceTypeResponse>> getAllInsuranceTypes () {
        List<InsuranceTypeResponse> insuranceTypes = insuranceTypeService.getAllInsuranceTypes();
        return ResponseEntity.ok(insuranceTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceTypeResponse> getInsuranceTypeById (@PathVariable UUID id) {
        InsuranceTypeResponse insuranceType = insuranceTypeService.getInsuranceTypeById(id);
        return ResponseEntity.ok(insuranceType);
    }

}
