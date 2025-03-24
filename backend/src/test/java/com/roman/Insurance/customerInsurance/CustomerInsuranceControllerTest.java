package com.roman.Insurance.customerInsurance;

import com.roman.Insurance.customerInsurance.request.CustomerTravelInsuranceRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerInsuranceControllerTest {

    @Mock
    private CustomerInsuranceService customerInsuranceService;

    @InjectMocks
    private CustomerInsuranceController customerInsuranceController;

    @Test
    void createTravelInsurance () throws Exception {
        CustomerTravelInsuranceRequest request =
                new CustomerTravelInsuranceRequest(null, null, null);

        doNothing().when(customerInsuranceService).createTravelInsurance(any(CustomerTravelInsuranceRequest.class));

        ResponseEntity<Void> response = customerInsuranceController.createTravelInsurance(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(customerInsuranceService, times(1)).createTravelInsurance(any(CustomerTravelInsuranceRequest.class));
    }
}