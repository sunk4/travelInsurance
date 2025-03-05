package com.roman.Insurance.country;

import com.roman.Insurance.country.response.CountryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/public/country")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryResponse>> findAllCountries () {
        List<CountryResponse> countries = countryService.findAllCountries();
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryResponse> findCountryById (@PathVariable UUID id) {
        CountryResponse country = countryService.findCountryById(id);

        return ResponseEntity.ok(country);
    }

}
