package com.roman.Insurance.ageCategories;

import java.util.List;
import java.util.UUID;

public interface AgeCategoryService {
    List<AgeCategoryDto> getAllAgeCategories ();

    AgeCategoryDto getAgeCategoryById (UUID id);
    AgeCategoryEntity getAgeCategoryEntityById (UUID id);

}
