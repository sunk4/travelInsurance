package com.roman.Insurance.mainCustomer;

import com.roman.Insurance.common.PageResponse;
import com.roman.Insurance.mainCustomer.response.MainCustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/main-customer")
public class MainCustomerController {

    private final MainCustomerService mainCustomerService;

    @GetMapping
    public ResponseEntity<PageResponse<MainCustomerResponse>> getMainCustomer (
            @RequestParam(required = false) UUID countryId,
            @RequestParam(required = false) UUID coverageRegionId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize
    ) throws Exception {
        return ResponseEntity.ok(mainCustomerService.getAllCustomers(countryId, coverageRegionId, firstName, lastName, pageNum, pageSize));

    }

    @GetMapping("/{id}")
    public ResponseEntity<MainCustomerResponse> getMainCustomerById (@PathVariable UUID id) throws Exception {
        return ResponseEntity.ok(mainCustomerService.getCustomerByIdDto(id));
    }
}
