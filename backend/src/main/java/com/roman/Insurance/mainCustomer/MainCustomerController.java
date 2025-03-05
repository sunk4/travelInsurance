package com.roman.Insurance.mainCustomer;

import com.roman.Insurance.common.PageResponse;
import com.roman.Insurance.mainCustomer.response.MainCustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main-customer")
public class MainCustomerController {

    private final MainCustomerService mainMainCustomerService;

    @GetMapping
    public ResponseEntity<PageResponse<MainCustomerResponse>> getMainCustomer(
            @RequestParam(required = false) UUID countryId,
            @RequestParam(required = false) UUID coverageRegionId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize
    ) throws Exception {
        return ResponseEntity.ok(mainMainCustomerService.getAllCustomers(countryId,coverageRegionId,firstName,lastName,pageNum,pageSize));
    }
}
