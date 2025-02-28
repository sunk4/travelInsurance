package com.roman.Insurance.pdfgenerator;

import com.roman.Insurance.calculation.CalculationDto;
import com.roman.Insurance.calculation.CalculationService;
import com.roman.Insurance.customerInsurance.CustomerTravelInsuranceRequest;
import com.roman.Insurance.insurance.InsuranceDTO;
import com.roman.Insurance.insurance.InsuranceEntity;
import com.roman.Insurance.insurance.InsuranceService;
import com.roman.Insurance.mainCustomer.MainCustomerEntity;
import com.roman.Insurance.mainCustomer.MainCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/testing")
public class TestingController {
    private final PdfGeneratorService pdfGeneratorService;
    private final CalculationService calculationService;
    private final MainCustomerService mainCustomerService;

    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> generatePdf (@PathVariable UUID id) throws Exception {
        MainCustomerEntity mainCustomer = mainCustomerService.getCustomerById(id);
        byte[] pdfBytes = pdfGeneratorService.generatePdf(mainCustomer);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "contract.pdf");
        return new ResponseEntity<>(pdfBytes, headers, 200);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MainCustomerEntity> ahoj (@PathVariable UUID id) throws Exception {
                MainCustomerEntity mainCustomer  =
                        mainCustomerService.getCustomerById(id);

                return  new ResponseEntity<>(mainCustomer,
                        HttpStatus.ACCEPTED);
    }
}
