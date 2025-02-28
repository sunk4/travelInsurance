package com.roman.Insurance.country;

import com.roman.Insurance.coverageRegions.CoverageRegionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryDto>> findAllCountries () {
        List<CountryDto> countries = countryService.findAllCountries();
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> findCountryById(@PathVariable UUID id){
        CountryDto country = countryService.findCountryById(id);

        return ResponseEntity.ok(country);
    }


}
