package com.roman.Insurance.country;

import com.roman.Insurance.country.response.CountryResponse;
import com.roman.Insurance.coverageRegions.CoverageRegionEntity;
import com.roman.Insurance.coverageRegions.CoverageRegionMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

class CountryMapperTest {
    @Mock
    private CoverageRegionMapper coverageRegionMapper;

    private CountryMapper countryMapper;

    @BeforeEach
    void before() {

        MockitoAnnotations.openMocks(this);

        countryMapper = Mappers.getMapper(CountryMapper.class);

        ReflectionTestUtils.setField(countryMapper, "coverageRegionMapper", coverageRegionMapper);
    }

    @Test
    void countryEntityToCountryDto() {
        CountryEntity entity = new CountryEntity(UUID.randomUUID(), "Romania", getCoverageRegion(), LocalDateTime.now(), LocalDateTime.now());
        CountryResponse response = countryMapper.countryEntityToCountryDto(entity);

        assertNotNull(response);
        assertEquals(entity.getId(), response.id());
        assertEquals(entity.getName(), response.name());
    }

    @Test
    void entityListToDto() {
        CountryEntity entity = new CountryEntity(UUID.randomUUID(), "Romania", getCoverageRegion(), LocalDateTime.now(), LocalDateTime.now());
        List<CountryResponse> responseList = countryMapper.entityListToDto(Collections.singletonList(entity));

        assertNotNull(responseList);
        assertEquals(1, responseList.size());
        assertEquals(entity.getName(), responseList.get(0).name());
    }

    public CoverageRegionEntity getCoverageRegion() {
        return CoverageRegionEntity.builder().id(UUID.randomUUID()).name("Europe").build();
    }
}
