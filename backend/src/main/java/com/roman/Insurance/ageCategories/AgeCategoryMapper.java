package com.roman.Insurance.ageCategories;

import com.roman.Insurance.ageCategories.response.AgeCategoryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgeCategoryMapper {

    AgeCategoryResponse toDto (AgeCategoryEntity entity);

    List<AgeCategoryResponse> entityListToDto (List<AgeCategoryEntity> ageCategoryEntities);
}
