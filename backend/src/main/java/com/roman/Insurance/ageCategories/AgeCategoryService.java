package com.roman.Insurance.ageCategories;

import com.roman.Insurance.ageCategories.response.AgeCategoryResponse;

import java.util.List;
import java.util.UUID;

public interface AgeCategoryService {
    List<AgeCategoryResponse> getAllAgeCategories ();

    AgeCategoryResponse getAgeCategoryById (UUID id);
    AgeCategoryEntity getAgeCategoryEntityById (UUID id);

}
