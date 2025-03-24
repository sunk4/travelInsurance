package com.roman.Insurance.coverageRegions;

import com.roman.Insurance.coverageRegions.response.CoverageRegionResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CoverageRegionMapperTest {
    private final CoverageRegionMapper mapper = Mappers.getMapper(CoverageRegionMapper.class);

    @Test
    void coverageRegionEntityToCoverageRegionDto () {
        CoverageRegionEntity coverageRegionEntity = CoverageRegionEntity.builder()
                .id(UUID.randomUUID())
                .name("name")
                .build();

        CoverageRegionResponse response = mapper.coverageRegionEntityToCoverageRegionDto(coverageRegionEntity);

        assertNotNull(response);
        assertEquals(coverageRegionEntity.getId(), response.id());
        assertEquals(coverageRegionEntity.getName(), response.name());
    }
}