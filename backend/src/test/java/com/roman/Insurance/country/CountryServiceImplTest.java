package com.roman.Insurance.country;

import com.roman.Insurance.ageCategories.AgeCategoryServiceImpl;
import com.roman.Insurance.country.response.CountryResponse;
import com.roman.Insurance.riskFactor.RiskFactorServiceImpl;
import com.roman.Insurance.utils.DateUtilsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CountryServiceImplTest {

    @Mock
    private CountryRepository countryRepository;
    @Mock
    private CountryMapper countryMapper;

    @Mock
    private DateUtilsServiceImpl dateUtilsService;

    @Mock
    private RiskFactorServiceImpl riskFactorService;

    @Mock
    private AgeCategoryServiceImpl ageCategoryService;

    @InjectMocks
    private CountryServiceImpl countryService;

    @Test
    void findAllCountries () {
        List<CountryEntity> entities = List.of(new CountryEntity(), new CountryEntity());
        List<CountryResponse> responses = List.of(new CountryResponse(null,
                        null, null, 10, null, null, null, null),
                new CountryResponse(null, null, null, 5, null, null, null,
                        null));
        when(countryRepository.findAll()).thenReturn(entities);
        when(countryMapper.entityListToDto(entities)).thenReturn(responses);
        List<CountryResponse> result = countryService.findAllCountries();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(countryRepository, times(1)).findAll();
        verify(countryMapper, times(1)).entityListToDto(entities);

    }

    @Test
    void findCountryById () {
        UUID id = UUID.randomUUID();

        CountryEntity entity = new CountryEntity();
        CountryResponse response = new CountryResponse(id, null, null, 10, null, null, null, null);
        when(countryRepository.findById(id)).thenReturn(java.util.Optional.of(entity));
        when(countryMapper.countryEntityToCountryDto(entity)).thenReturn(response);

        CountryResponse result = countryService.findCountryById(id);

        assertNotNull(result);
        assertEquals(id, result.id());
        verify(countryRepository, times(1)).findById(id);
        verify(countryMapper, times(1)).countryEntityToCountryDto(entity);
    }

    @Test
    void findCountryEntityById () {
        UUID id = UUID.randomUUID();

        CountryEntity entity = new CountryEntity();
        entity.setId(id);
        when(countryRepository.findById(id)).thenReturn(java.util.Optional.of(entity));

        CountryEntity result = countryService.findCountryEntityById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(countryRepository, times(1)).findById(id);
    }

    @Test
    void findCountryByIdAndCalculatedPriceByRiskFactorDateAgeCategory () {
    }
}