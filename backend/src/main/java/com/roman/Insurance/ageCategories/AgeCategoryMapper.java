package com.roman.Insurance.ageCategories;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgeCategoryMapper {

    AgeCategoryDto toDto (AgeCategoryEntity entity);

    List<AgeCategoryDto> entityListToDto (List<AgeCategoryEntity> ageCategoryEntities);
}
