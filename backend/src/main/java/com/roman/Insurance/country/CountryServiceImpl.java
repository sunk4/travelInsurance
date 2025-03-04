package com.roman.Insurance.country;

import com.roman.Insurance.ageCategories.AgeCategoryService;
import com.roman.Insurance.country.response.CountryResponse;
import com.roman.Insurance.coverageRegions.response.CoverageRegionResponse;
import com.roman.Insurance.customerInsurance.request.CustomerTravelInsuranceRequest;
import com.roman.Insurance.riskFactor.RiskFactorService;
import com.roman.Insurance.utils.DateUtilsService;
import jakarta.persistence.EntityNotFoundException;
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
    public List<CountryResponse> findAllCountries () {
        List<CountryEntity> countryEntities = countryRepository.findAll();

        return countryMapper.entityListToDto(countryEntities);
    }

    @Override
    public CountryResponse findCountryById (UUID id) {
        CountryEntity countryEntity =
                countryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                        "Country not found"));
        return countryMapper.countryEntityToCountryDto(countryEntity);
    }

    @Override
    public CountryEntity findCountryEntityById (UUID id) {
        return countryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Country not found"));
    }

    @Override
    public CountryResponse findCountryByIdAndCalculatedPriceByRiskFactorDateAgeCategory (CustomerTravelInsuranceRequest customerTravelInsuranceRequest) {
        CountryEntity countryEntity =
                countryRepository.findById(customerTravelInsuranceRequest.insuranceRequest().countryId()).orElseThrow(() -> new EntityNotFoundException("Country not found"));

        CountryResponse countryResponse = countryMapper.countryEntityToCountryDto(countryEntity);
        double basePricePerDay = countryResponse.coverageRegion().basePricePerDay();
        double totalCalculatedPrice = 0.0;
        for (int i = 0; i < customerTravelInsuranceRequest.insuredPersonRequest().size(); i++) {

            UUID ageCategoryId =
                    customerTravelInsuranceRequest.insuredPersonRequest().get(i).ageCategory().id();
            UUID riskFactorId =
                    customerTravelInsuranceRequest.insuredPersonRequest().get(i).riskFactor().id();

            double priceFactorAgeCategory =
                    ageCategoryService.getAgeCategoryById(ageCategoryId).priceFactor();
            double priceFactoredPrice = basePricePerDay * priceFactorAgeCategory;
            double priceFactorRiskFactor =
                    riskFactorService.getRiskFactorById(riskFactorId).riskFactor();
            priceFactoredPrice *= priceFactorRiskFactor;

            totalCalculatedPrice += priceFactoredPrice;

        }

        long days =
                dateUtilsService.calculateDateDifferenceInDays(customerTravelInsuranceRequest.insuranceRequest().startDate(), customerTravelInsuranceRequest.insuranceRequest().endDate());

        totalCalculatedPrice *= days;

        CoverageRegionResponse countryWithCalculatedPrice =
                countryResponse.coverageRegion().withTotalCalculatedPrice(Math.round(totalCalculatedPrice * 100.0) / 100.0);

        return new CountryResponse(countryResponse.id(), countryResponse.name(),
                countryWithCalculatedPrice, days,
                customerTravelInsuranceRequest.insuranceRequest().startDate(),
                customerTravelInsuranceRequest.insuranceRequest().endDate(),
                countryResponse.createdAt(),
                countryResponse.updatedAt());

    }

}
