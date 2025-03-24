package com.roman.Insurance.riskFactor;

import com.roman.Insurance.riskFactor.response.RiskFactorResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RiskFactorMapperTest {
    private final RiskFactorMapper mapper = Mappers.getMapper(RiskFactorMapper.class);

    @Test
    void toDto () {
        RiskFactorEntity entity = RiskFactorEntity.builder().
                id(UUID.randomUUID()).
                name("Risk").
                riskFactor(13.0).
                createdAt(LocalDateTime.now()).
                updatedAt(LocalDateTime.now()).
                build();
        RiskFactorResponse response = mapper.toDto(entity);

        assertNotNull(response);
    }

    @Test
    void entityListToDto () {
        RiskFactorEntity entity = RiskFactorEntity.builder().
                id(UUID.randomUUID()).
                name("Risk").
                riskFactor(13.0).
                createdAt(LocalDateTime.now()).
                updatedAt(LocalDateTime.now()).
                build();
        List<RiskFactorResponse> response =
                mapper.entityListToDto(Collections.singletonList(entity));

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(entity.getName(), response.get(0).name());

    }
}