package com.roman.Insurance.mainCustomer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MainCustomerRepository extends JpaRepository<MainCustomerEntity, UUID> {
}
