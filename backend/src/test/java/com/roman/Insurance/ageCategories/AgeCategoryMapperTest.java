package com.roman.Insurance.ageCategories;

import com.roman.Insurance.ageCategories.response.AgeCategoryResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AgeCategoryMapperTest {
    private final AgeCategoryMapper mapper = Mappers.getMapper(AgeCategoryMapper.class);

    @Test
    void toDto () {
        AgeCategoryEntity entity = new AgeCategoryEntity(UUID.randomUUID(), "Teen", 13, 17, 0.8, LocalDateTime.now(), LocalDateTime.now());
        AgeCategoryResponse response = mapper.toDto(entity);

        assertNotNull(response);
        assertEquals(entity.getId(), response.id());
        assertEquals(entity.getName(), response.name());
    }

    @Test
    void entityListToDto () {
        AgeCategoryEntity entity = new AgeCategoryEntity(UUID.randomUUID(), "Teen", 13, 17, 0.8, LocalDateTime.now(), LocalDateTime.now());
        List<AgeCategoryResponse> responseList = mapper.entityListToDto(Collections.singletonList(entity));

        assertNotNull(responseList);
        assertEquals(1, responseList.size());
        assertEquals(entity.getName(), responseList.get(0).name());
    }
}