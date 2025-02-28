package com.roman.Insurance.insuredPerson;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InsuredPersonRepository extends JpaRepository<InsuredPersonEntity,
        UUID> {
}
