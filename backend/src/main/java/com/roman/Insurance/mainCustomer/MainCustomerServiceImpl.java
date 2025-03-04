package com.roman.Insurance.mainCustomer;

import com.roman.Insurance.encryption.EncryptionService;
import com.roman.Insurance.mainCustomer.request.MainCustomerRequest;
import com.roman.Insurance.mainCustomer.response.MainCustomerResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainCustomerServiceImpl implements MainCustomerService {
    private final MainCustomerRepository mainCustomerRepository;
    private final MainCustomerMapper customerMapper;
    private final EncryptionService encryptionService;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UUID createMainCustomer (MainCustomerRequest mainCustomerRequest) throws Exception {
        MainCustomerEntity customerEntity = customerMapper.toEntity(mainCustomerRequest);
        MainCustomerEntity encryptedCustomerEntity = encryptionService.encrypt(customerEntity);
        mainCustomerRepository.saveAndFlush(encryptedCustomerEntity);
        entityManager.clear();

        return encryptedCustomerEntity.getId();

    }

    @Override
    public MainCustomerEntity getCustomerByIdEncrypted (UUID customerId) throws Exception {
        MainCustomerEntity mainCustomer =
                mainCustomerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
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
        return mainCustomerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

    @Override
    public List<MainCustomerResponse> getAllCustomers () {
        List<MainCustomerEntity> customerEntities = mainCustomerRepository.findAll();

        List<MainCustomerEntity> decryptedCustomers =
                customerEntities.stream().map((customerEntity) -> {
                    try {
                        return encryptionService.decrypt(customerEntity);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).toList();

        return customerMapper.entityListToDto(decryptedCustomers);
    }
}
