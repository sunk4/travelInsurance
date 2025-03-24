package com.roman.Insurance.insuredPerson;

import com.roman.Insurance.ageCategories.request.AgeCategoryRequest;
import com.roman.Insurance.insuredPerson.request.InsuredPersonRequest;
import com.roman.Insurance.riskFactor.response.RiskFactorResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InsuredPersonMapperTest {

    InsuredPersonMapper insuredPersonMapper = Mappers.getMapper(InsuredPersonMapper.class);

    @Test
    void toEntity () {
        InsuredPersonRequest insuredPersonRequest =
                new InsuredPersonRequest(UUID.randomUUID(), "John", "Doe", LocalDate.now(), getAgeCategory(), getRiskFactor(), UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now());

        InsuredPersonEntity insuredPersonEntity = insuredPersonMapper.toEntity(insuredPersonRequest);
        assertNotNull(insuredPersonEntity);
        assertEquals(insuredPersonRequest.firstName(),
                insuredPersonEntity.getFirstName());
    }

    public AgeCategoryRequest getAgeCategory () {
        return new AgeCategoryRequest(UUID.randomUUID());
    }

    public RiskFactorResponse getRiskFactor () {
        return new RiskFactorResponse(UUID.randomUUID(), "Risk", 13, LocalDateTime.now(), LocalDateTime.now());
    }
}