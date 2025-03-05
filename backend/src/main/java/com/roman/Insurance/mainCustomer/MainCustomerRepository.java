package com.roman.Insurance.mainCustomer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MainCustomerRepository extends JpaRepository<MainCustomerEntity, UUID>,JpaSpecificationExecutor<MainCustomerEntity> {

}
