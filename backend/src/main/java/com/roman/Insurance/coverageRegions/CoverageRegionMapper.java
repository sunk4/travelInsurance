package com.roman.Insurance.coverageRegions;

import com.roman.Insurance.coverageRegions.response.CoverageRegionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoverageRegionMapper {
    CoverageRegionResponse coverageRegionEntityToCoverageRegionDto (CoverageRegionEntity coverageRegionEntity);
}
