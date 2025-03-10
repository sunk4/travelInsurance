package com.roman.Insurance.ageCategories;

import com.roman.Insurance.ageCategories.response.AgeCategoryResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgeCategoryControllerTest {

    @Mock
    private AgeCategoryService ageCategoryService;

    @InjectMocks
    private AgeCategoryController ageCategoryController;

    @Test
    void getAllAgeCategories () throws Exception {
        List<AgeCategoryResponse> mockList =
                List.of(new AgeCategoryResponse(UUID.randomUUID(), "Teen", 13, 17, 0.8, LocalDateTime.now(), LocalDateTime.now()));
        when(ageCategoryService.getAllAgeCategories()).thenReturn(mockList);

        ResponseEntity<List<AgeCategoryResponse>> response = ageCategoryController.getAllAgeCategories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getAllAgeCategories_emptyList () throws Exception {
        when(ageCategoryService.getAllAgeCategories()).thenReturn(List.of());

        ResponseEntity<List<AgeCategoryResponse>> response = ageCategoryController.getAllAgeCategories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }

    @Test
    void getAgeCategoryById () {
        UUID id = UUID.randomUUID();
        AgeCategoryResponse mockResponse =
                new AgeCategoryResponse(UUID.randomUUID(), "Teen", 13, 17, 0.8, LocalDateTime.now(), LocalDateTime.now());
        when(ageCategoryService.getAgeCategoryById(id)).thenReturn(mockResponse);

        ResponseEntity<AgeCategoryResponse> response = ageCategoryController.getAgeCategoryById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Teen", response.getBody().name());
    }

    @Test
    void getAgeCategoryById_invalidId () {
        UUID invalidId = UUID.randomUUID();
        when(ageCategoryService.getAgeCategoryById(invalidId)).thenThrow(new RuntimeException("Invalid ID"));

        try {
            ageCategoryController.getAgeCategoryById(invalidId);
        } catch (RuntimeException e) {
            assertEquals("Invalid ID", e.getMessage());
        }
    }
}