package com.roman.Insurance.mainCustomer;

import com.roman.Insurance.encryption.EncryptionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainMainCustomerServiceImpl implements MainCustomerService {
    private final MainCustomerRepository mainCustomerRepository;
    private final MainCustomerMapper customerMapper;
    private final EncryptionService encryptionService;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UUID createMainCustomer (MainCustomerDto mainCustomerDto) throws Exception {
        MainCustomerEntity customerEntity = customerMapper.toEntity(mainCustomerDto);
        MainCustomerEntity encryptedCustomerEntity = encryptionService.encrypt(customerEntity);
        mainCustomerRepository.saveAndFlush(encryptedCustomerEntity);
        entityManager.clear();


        return encryptedCustomerEntity.getId();

    }

    @Override
    public MainCustomerEntity getCustomerByIdEncrypted (UUID customerId) throws Exception {
        MainCustomerEntity mainCustomer =
                mainCustomerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        MainCustomerEntity decryptedMainCustomer =
                encryptionService.decrypt(mainCustomer);
        decryptedMainCustomer.getInsurances().forEach(insuranceEntity ->
                insuranceEntity.setInsuredPersons(
                        insuranceEntity.getInsuredPersons().stream()
                                .map(insuredPerson -> {

                                    try {
                                        return encryptionService.decrypt(insuredPerson);
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }

                                })
                                .collect(Collectors.toList())
                )
        );

        return decryptedMainCustomer;

    }


    @Override
    public MainCustomerEntity getCustomerById (UUID customerId) {
            return mainCustomerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
