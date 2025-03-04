package com.roman.Insurance.mainCustomer;

import com.roman.Insurance.mainCustomer.response.MainCustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main-customer")
public class MainCustomerController {

    private final MainCustomerService mainMainCustomerService;

    @GetMapping
    public ResponseEntity<List<MainCustomerResponse>> getMainCustomer() {
        return ResponseEntity.ok(mainMainCustomerService.getAllCustomers());
    }
}
