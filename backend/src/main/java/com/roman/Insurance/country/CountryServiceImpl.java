package com.roman.Insurance.country;

import com.roman.Insurance.ageCategories.AgeCategoryService;
import com.roman.Insurance.coverageRegions.CoverageRegionDto;
import com.roman.Insurance.customerInsurance.CustomerTravelInsuranceRequest;
import com.roman.Insurance.riskFactor.RiskFactorService;
import com.roman.Insurance.utils.DateUtilsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;
    private final DateUtilsService dateUtilsService;
    private final RiskFactorService riskFactorService;
    private final AgeCategoryService ageCategoryService;

    @Override
    public List<CountryDto> findAllCountries () {
        List<CountryEntity> countryEntities = countryRepository.findAll();

        return countryMapper.entityListToDto(countryEntities);
    }

    @Override
    public CountryDto findCountryById (UUID id) {
        CountryEntity countryEntity =
                countryRepository.findById(id).orElseThrow(() -> new RuntimeException("Country not found"));
        return countryMapper.countryEntityToCountryDto(countryEntity);
    }

    @Override
    public CountryEntity findCountryEntityById (UUID id) {
        return countryRepository.findById(id).orElseThrow(() -> new RuntimeException("Country not found"));
    }

    @Override
    public CountryDto findCountryByIdAndCalculatedPriceByRiskFactorDateAgeCategory (CustomerTravelInsuranceRequest customerTravelInsuranceRequest) {
        CountryEntity countryEntity =
                countryRepository.findById(customerTravelInsuranceRequest.insuranceDTO().countryId()).orElseThrow(() -> new RuntimeException("Country not found"));

        CountryDto countryDto = countryMapper.countryEntityToCountryDto(countryEntity);
        double basePricePerDay = countryDto.coverageRegion().basePricePerDay();
        double totalCalculatedPrice = 0.0;


        for (int i = 0; i < customerTravelInsuranceRequest.insuredPersonDTO().size(); i++) {
            UUID ageCategoryId =
                    customerTravelInsuranceRequest.insuredPersonDTO().get(i).ageCategory().id();
            UUID riskFactorId =
                    customerTravelInsuranceRequest.insuredPersonDTO().get(i).riskFactor().id();

            double priceFactorAgeCategory =
                    ageCategoryService.getAgeCategoryById(ageCategoryId).priceFactor();
            double priceFactoredPrice = basePricePerDay * priceFactorAgeCategory;
            double priceFactorRiskFactor =
                    riskFactorService.getRiskFactorById(riskFactorId).riskFactor();
            priceFactoredPrice *= priceFactorRiskFactor;

            totalCalculatedPrice += priceFactoredPrice;

        }

        long days =
                dateUtilsService.calculateDateDifferenceInDays(customerTravelInsuranceRequest.insuranceDTO().startDate(), customerTravelInsuranceRequest.insuranceDTO().endDate());

        totalCalculatedPrice *= days;

        CoverageRegionDto countryWithCalculatedPrice =
                countryDto.coverageRegion().withTotalCalculatedPrice(Math.round(totalCalculatedPrice * 100.0) / 100.0);

        return new CountryDto(countryDto.id(), countryDto.name(),
                countryWithCalculatedPrice, days,
                customerTravelInsuranceRequest.insuranceDTO().startDate(),
                customerTravelInsuranceRequest.insuranceDTO().endDate() ,
                countryDto.createdAt(),
                countryDto.updatedAt());

    }

}
