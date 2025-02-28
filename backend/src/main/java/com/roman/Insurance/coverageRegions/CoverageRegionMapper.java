package com.roman.Insurance.coverageRegions;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoverageRegionMapper {
    CoverageRegionDto coverageRegionEntityToCoverageRegionDto (CoverageRegionEntity coverageRegionEntity);
}
