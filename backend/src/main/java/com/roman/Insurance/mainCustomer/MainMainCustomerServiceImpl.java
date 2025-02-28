package com.roman.Insurance.mainCustomer;

import com.roman.Insurance.encryption.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainMainCustomerServiceImpl implements MainCustomerService {
    private final MainCustomerRepository mainCustomerRepository;
    private final MainCustomerMapper customerMapper;
    private final EncryptionService encryptionService;

    @Override
    public UUID createMainCustomer (MainCustomerDto mainCustomerDto) throws Exception {
        MainCustomerEntity customerEntity = customerMapper.toEntity(mainCustomerDto);
        MainCustomerEntity encryptedCustomerEntity = encryptionService.encrypt(customerEntity);
        return mainCustomerRepository.save(encryptedCustomerEntity).getId();

    }

    @Override
    public MainCustomerEntity getCustomerById (UUID customerId) throws Exception {
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
}
