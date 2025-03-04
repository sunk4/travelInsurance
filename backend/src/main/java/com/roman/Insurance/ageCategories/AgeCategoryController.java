package com.roman.Insurance.ageCategories;

import com.roman.Insurance.ageCategories.response.AgeCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ageCategories")
public class AgeCategoryController {
    private final AgeCategoryService ageCategoryService;

    @GetMapping
    public ResponseEntity<List<AgeCategoryResponse>> getAllAgeCategories () {
        List<AgeCategoryResponse> ageCategories = ageCategoryService.getAllAgeCategories();
        return ResponseEntity.ok(ageCategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgeCategoryResponse> getAgeCategoryById (UUID id) {
        AgeCategoryResponse ageCategory = ageCategoryService.getAgeCategoryById(id);
        return ResponseEntity.ok(ageCategory);
    }
}
