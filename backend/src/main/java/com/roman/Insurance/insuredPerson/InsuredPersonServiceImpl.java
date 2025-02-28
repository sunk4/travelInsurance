package com.roman.Insurance.insuredPerson;

import com.roman.Insurance.ageCategories.AgeCategoryService;
import com.roman.Insurance.encryption.EncryptionService;
import com.roman.Insurance.insurance.InsuranceService;
import com.roman.Insurance.riskFactor.RiskFactorService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InsuredPersonServiceImpl implements InsuredPersonService {
    private final InsuredPersonRepository insuredPersonRepository;
    private final InsuredPersonMapper insuredPersonMapper;
    private final EncryptionService encryptionService;
    private final AgeCategoryService ageCategoryService;
    private final RiskFactorService riskFactorService;
    private final InsuranceService insuranceService;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createInsuredPerson (List<InsuredPersonDTO> insuredPersonDTOS,
                                  UUID insuranceId) throws Exception {

        List<InsuredPersonEntity> insuredPersonEntities = new ArrayList<>();

        for (InsuredPersonDTO insuredPersonDTO : insuredPersonDTOS) {
            InsuredPersonEntity insuredPersonEntity = insuredPersonMapper.toEntity(insuredPersonDTO);
            insuredPersonEntity = encryptionService.encrypt(insuredPersonEntity);
            insuredPersonEntity.setAgeCategory(ageCategoryService.getAgeCategoryEntityById(insuredPersonDTO.ageCategory().id()));
            insuredPersonEntity.setRiskFactor(riskFactorService.getRiskFactorEntityById(insuredPersonDTO.riskFactor().id()));
            insuredPersonEntity.setInsurance(insuranceService.getInsuranceEntityById(insuranceId));
            insuredPersonEntities.add(insuredPersonEntity);
        }

        List<InsuredPersonEntity> savedInsuredPersonEntities =
                insuredPersonRepository.saveAllAndFlush(insuredPersonEntities);

        entityManager.clear();

    }

    @Override
    public List<InsuredPersonDTO> getInsuredPersons (List<InsuredPersonDTO> insuredPersonsDTO) {
        return insuredPersonsDTO.stream().map(person ->
                new InsuredPersonDTO(
                        person.id(),
                        person.firstName(),
                        person.lastName(),
                        person.dateOfBirth(),
                        ageCategoryService.getAgeCategoryById(person.ageCategory().id()),
                        riskFactorService.getRiskFactorById(person.riskFactor().id()),
                        person.mainCustomerId(),
                        person.createdAt(),
                        person.updatedAt()

                )
        ).toList();
    }

}
