package com.roman.Insurance.country;

import com.roman.Insurance.coverageRegions.CoverageRegionMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = CoverageRegionMapper.class)
public interface CountryMapper {
    @Mapping(target = "coverageRegion", source = "coverageRegion")
    CountryDto countryEntityToCountryDto(CountryEntity countryEntity);

    List<CountryDto> entityListToDto(List<CountryEntity> countryEntities);
}
