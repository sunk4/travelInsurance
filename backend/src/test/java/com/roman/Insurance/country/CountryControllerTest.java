package com.roman.Insurance.country;

import com.roman.Insurance.country.response.CountryResponse;
import com.roman.Insurance.coverageRegions.response.CoverageRegionResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryControllerTest {
    @Mock
    private CountryService countryService;

    @InjectMocks
    private CountryController countryController;

    @Test
    void findAllCountries () {
        List<CountryResponse> mockList =
                List.of(new CountryResponse(UUID.randomUUID(), "Slovakia",
                        new CoverageRegionResponse(UUID.randomUUID(), "Europe", "Europe", 10.0, 10.0, LocalDateTime.now(), LocalDateTime.now()),
                        10, LocalDate.now(), LocalDate.now(),
                        LocalDateTime.now(), LocalDateTime.now()));
        when(countryService.findAllCountries()).thenReturn(mockList);

        ResponseEntity<List<CountryResponse>> response =
                countryController.findAllCountries();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());

    }

    @Test
    void findAllCountries_emptyList () throws Exception {
        when(countryService.findAllCountries()).thenReturn(List.of());

        ResponseEntity<List<CountryResponse>> response =
                countryController.findAllCountries();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }

    @Test
    void findCountryById () {
        UUID id = UUID.randomUUID();
        CountryResponse mockResponse =
                new CountryResponse(UUID.randomUUID(), "Slovakia",
                        new CoverageRegionResponse(UUID.randomUUID(), "Europe", "Europe", 10.0, 10.0, LocalDateTime.now(), LocalDateTime.now()),
                        10, LocalDate.now(), LocalDate.now(),
                        LocalDateTime.now(), LocalDateTime.now());
        when(countryService.findCountryById(id)).thenReturn(mockResponse);

        ResponseEntity<CountryResponse> response = countryController.findCountryById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Slovakia", response.getBody().name());
    }

    @Test
    void findCountryById_invalidId () {
        UUID invalidId = UUID.randomUUID();
        when(countryService.findCountryById(invalidId)).thenThrow(new RuntimeException(
                "Invalid ID"));

        try {
            countryController.findCountryById(invalidId);
        } catch (RuntimeException e) {
            assertEquals("Invalid ID", e.getMessage());
        }
    }
}