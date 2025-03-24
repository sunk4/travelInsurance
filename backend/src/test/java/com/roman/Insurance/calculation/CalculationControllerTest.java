package com.roman.Insurance.calculation;

import com.roman.Insurance.ageCategories.request.AgeCategoryRequest;
import com.roman.Insurance.calculation.response.CalculationResponse;
import com.roman.Insurance.customerInsurance.request.CustomerTravelInsuranceRequest;
import com.roman.Insurance.enums.StatusOfPayment;
import com.roman.Insurance.insurance.request.InsuranceRequest;
import com.roman.Insurance.insuredPerson.request.InsuredPersonRequest;
import com.roman.Insurance.mainCustomer.request.MainCustomerRequest;
import com.roman.Insurance.riskFactor.response.RiskFactorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculationControllerTest {
    @Mock
    private CalculationService calculationService;

    @InjectMocks
    private CalculationController calculationController;

    @Test
    void calculatePrice () {
        CustomerTravelInsuranceRequest request = new CustomerTravelInsuranceRequest(
                getMainCustomerRequest(),
                Arrays.asList(getInsuredPersonRequest1(), getInsuredPersonRequest2()),
                getInsuranceRequest()
        );

        CalculationResponse mockResponse = new CalculationResponse(null, null
                , null, 10, null);

        when(calculationService.calculatePrice(any(CustomerTravelInsuranceRequest.class))).thenReturn(mockResponse);

        ResponseEntity<CalculationResponse> response = calculationController.calculatePrice(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        assertEquals(10, response.getBody().totalCalculatedPrice());
    }

    private MainCustomerRequest getMainCustomerRequest () {
        return new MainCustomerRequest("John", "Doe", "john.doe@example.com", "+1234567890", "123 Main St", "New York", "NY", "10001", "123-45-6789");
    }

    private InsuredPersonRequest getInsuredPersonRequest1 () {
        return new InsuredPersonRequest(UUID.randomUUID(), "John", "Doe",
                LocalDate.now(),
                getAgeCategory(), getRiskFactor(), UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now());
    }

    private InsuredPersonRequest getInsuredPersonRequest2 () {
        return new InsuredPersonRequest(UUID.randomUUID(), "John2", "Doe2",
                LocalDate.now(),
                getAgeCategory(), getRiskFactor(), UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now());
    }

    private InsuranceRequest getInsuranceRequest () {
        return new InsuranceRequest(UUID.randomUUID(), UUID.randomUUID(), LocalDate.now(), LocalDate.now(), Arrays.asList(UUID.randomUUID()), StatusOfPayment.PAID, "url", "url", 100.0, UUID.randomUUID());
    }

    private AgeCategoryRequest getAgeCategory () {
        return new AgeCategoryRequest(UUID.randomUUID());
    }

    private RiskFactorResponse getRiskFactor () {
        return new RiskFactorResponse(UUID.randomUUID(), "Risk", 13, LocalDateTime.now(), LocalDateTime.now());
    }
}