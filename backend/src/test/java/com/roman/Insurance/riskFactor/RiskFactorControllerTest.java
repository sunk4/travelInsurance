package com.roman.Insurance.riskFactor;

import com.roman.Insurance.riskFactor.response.RiskFactorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RiskFactorControllerTest {

    @Mock
    private RiskFactorService riskFactorService;

    @InjectMocks
    private RiskFactorController riskFactorController;

    @Test
    void findAllRiskFactors () {
        List<RiskFactorResponse> mockList =
                List.of(new RiskFactorResponse(UUID.randomUUID(),
                        "Risk", 13, LocalDateTime.now(), LocalDateTime.now()));
        when(riskFactorService.findAllRiskFactors()).thenReturn(mockList);

        ResponseEntity<List<RiskFactorResponse>> response =
                riskFactorController.findAllRiskFactors();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void findAllRiskFactors_emptyList () throws Exception {
        when(riskFactorService.findAllRiskFactors()).thenReturn(List.of());

        ResponseEntity<List<RiskFactorResponse>> response =
                riskFactorController.findAllRiskFactors();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }

    @Test
    void findRiskFactorById () {
        UUID id = UUID.randomUUID();
        RiskFactorResponse mockResponse =
                new RiskFactorResponse(UUID.randomUUID(),
                        "Risk", 13, LocalDateTime.now(), LocalDateTime.now());
        when(riskFactorService.getRiskFactorById(id)).thenReturn(mockResponse);

        ResponseEntity<RiskFactorResponse> response =
                riskFactorController.findRiskFactorById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Risk", response.getBody().name());
    }

    @Test
    void findRiskFactorById_invalidId () {
        UUID invalidId = UUID.randomUUID();
        when(riskFactorService.getRiskFactorById(invalidId)).thenThrow(new RuntimeException(
                "Invalid ID"));

        try {
            riskFactorController.findRiskFactorById(invalidId);
        } catch (RuntimeException e) {
            assertEquals("Invalid ID", e.getMessage());
        }
    }
}