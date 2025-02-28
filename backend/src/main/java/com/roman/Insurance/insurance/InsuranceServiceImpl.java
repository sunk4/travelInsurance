package com.roman.Insurance.insurance;

import com.roman.Insurance.country.CountryEntity;
import com.roman.Insurance.country.CountryService;
import com.roman.Insurance.enums.StatusOfPayment;
import com.roman.Insurance.insuranceType.InsuranceTypeEntity;
import com.roman.Insurance.insuranceType.InsuranceTypeService;
import com.roman.Insurance.mainCustomer.MainCustomerEntity;
import com.roman.Insurance.mainCustomer.MainCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {
    private final InsuranceRepo insuranceRepository;
    private final InsuranceMapper insuranceMapper;
    private final MainCustomerService mainCustomerService;
    private final CountryService countryService;
    private final InsuranceTypeService insuranceTypeService;

    @Override
    public UUID createInsurance (
            InsuranceDTO insuranceDTO,
            UUID mainCustomerId, double totalPrice
    ) throws Exception {

        MainCustomerEntity customerEntity = mainCustomerService.getCustomerById(mainCustomerId);
        CountryEntity countryEntity =
                countryService.findCountryEntityById(insuranceDTO.countryId());
        InsuranceEntity insuranceEntity = insuranceMapper.toEntity(insuranceDTO);
        insuranceEntity.setCustomer(customerEntity);
        insuranceEntity.setStatusOfPayment(StatusOfPayment.UNPAID);
        insuranceEntity.setCountry(countryEntity);
        insuranceEntity.setTotalPrice(totalPrice);

        List<InsuranceTypeEntity> insuranceTypes = insuranceTypeService.getAllInsuranceTypesEntitiesByIds(insuranceDTO.insuranceTypeIds());
        insuranceEntity.setInsuranceTypes(insuranceTypes);

        return insuranceRepository.save(insuranceEntity).getId();

    }

    @Override
    public InsuranceEntity getInsuranceEntityById (UUID id) {
        return insuranceRepository.findById(id).orElseThrow(() -> new RuntimeException("Insurance not found"));
    }

    @Override
    public void updateUrlPreview (UUID id, String url) {
        InsuranceEntity insuranceEntity = getInsuranceEntityById(id);
        insuranceEntity.setUrlInsurancePreview(url);
        insuranceRepository.save(insuranceEntity);
    }

    @Override
    public void updateStatusOfPaymentAndUrlPayed (UUID id, String urlPdfPayed) {
        InsuranceEntity insuranceEntity = getInsuranceEntityById(id);
        insuranceEntity.setStatusOfPayment(StatusOfPayment.PAID);
        insuranceEntity.setUrlInsurancePayed(urlPdfPayed);
        insuranceRepository.save(insuranceEntity);
    }
}
