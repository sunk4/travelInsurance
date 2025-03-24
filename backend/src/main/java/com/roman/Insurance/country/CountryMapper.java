package com.roman.Insurance.country;

import com.roman.Insurance.country.response.CountryResponse;
import com.roman.Insurance.coverageRegions.CoverageRegionMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = CoverageRegionMapper.class)
public interface CountryMapper {
    @Mapping(target = "coverageRegion", source = "coverageRegion")
    CountryResponse countryEntityToCountryDto (CountryEntity countryEntity);

    List<CountryResponse> entityListToDto (List<CountryEntity> countryEntities);

}
