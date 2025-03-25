package com.roman.Insurance.ageCategories;

import com.roman.Insurance.ageCategories.response.AgeCategoryResponse;
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
class AgeCategoryServiceImplTest {
    @Mock
    private AgeCategoryRepository ageCategoryRepository;
    @Mock
    private AgeCategoryMapper ageCategoryMapper;

    @InjectMocks
    private AgeCategoryServiceImpl ageCategoryService;

    @Test
    void getAllAgeCategories () {
        List<AgeCategoryEntity> entities = List.of(new AgeCategoryEntity(), new AgeCategoryEntity());
        List<AgeCategoryResponse> responses =
                List.of(new AgeCategoryResponse(null, null, null, null, 10,
                                null, null),
                        new AgeCategoryResponse(null, null, null, null, 20,
                                null, null));

        when(ageCategoryRepository.findAll()).thenReturn(entities);
        when(ageCategoryMapper.entityListToDto(entities)).thenReturn(responses);

        List<AgeCategoryResponse> result = ageCategoryService.getAllAgeCategories();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(ageCategoryRepository, times(1)).findAll();
        verify(ageCategoryMapper, times(1)).entityListToDto(entities);
    }

    @Test
    void getAgeCategoryById () {
        UUID id = UUID.randomUUID();

        AgeCategoryEntity entity = new AgeCategoryEntity();
        AgeCategoryResponse response = new AgeCategoryResponse(id, null, null, null, 10,
                null, null);
        when(ageCategoryRepository.findById(id)).thenReturn(java.util.Optional.of(entity));
        when(ageCategoryMapper.toDto(entity)).thenReturn(response);

        AgeCategoryResponse result = ageCategoryService.getAgeCategoryById(id);

        assertNotNull(result);
        assertEquals(id, result.id());
        verify(ageCategoryRepository, times(1)).findById(id);
        verify(ageCategoryMapper, times(1)).toDto(entity);

    }

    @Test
    void getAgeCategoryEntityById () {
        UUID id = UUID.randomUUID();

        AgeCategoryEntity entity = new AgeCategoryEntity();
        entity.setId(id);
        when(ageCategoryRepository.findById(id)).thenReturn(java.util.Optional.of(entity));

        AgeCategoryEntity result = ageCategoryService.getAgeCategoryEntityById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(ageCategoryRepository, times(1)).findById(id);
    }
}