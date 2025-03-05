package com.roman.Insurance.mainCustomer;

import com.roman.Insurance.country.CountryEntity;
import com.roman.Insurance.coverageRegions.CoverageRegionEntity;
import com.roman.Insurance.insurance.InsuranceEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainCustomerSpecification {
    public static Specification<MainCustomerEntity> filterByCriteria (UUID countryId, UUID coverageRegionId, String firstName, String lastName) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (countryId != null) {
                Join<MainCustomerEntity, InsuranceEntity> insuranceJoin = root.join("insurances");
                Join<InsuranceEntity, CountryEntity> countryJoin = insuranceJoin.join("country");
                predicates.add(criteriaBuilder.equal(countryJoin.get("id"), countryId));
            }

            if (coverageRegionId != null) {
                Join<MainCustomerEntity, InsuranceEntity> insuranceJoin = root.join("insurances");
                Join<InsuranceEntity, CountryEntity> countryJoin = insuranceJoin.join("country");
                Join<CountryEntity, CoverageRegionEntity> coverageJoin = countryJoin.join("coverageRegion");
                predicates.add(criteriaBuilder.equal(coverageJoin.get("id"), coverageRegionId));
            }

            if (firstName != null && !firstName.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("encryptedFirstName"), firstName));
            }

            if (lastName != null && !lastName.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("encryptedLastName"), lastName));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}
