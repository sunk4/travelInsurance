package com.roman.Insurance.encryption;

import com.roman.Insurance.insuredPerson.InsuredPersonEntity;
import com.roman.Insurance.mainCustomer.MainCustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EncryptionServiceImpl implements EncryptionService {

    private final EncryptionUtil encryption;

    @Override
    public MainCustomerEntity encrypt (MainCustomerEntity customerEntity) throws Exception {
        if (customerEntity.getFirstName() != null) {
            customerEntity.setEncryptedFirstName(encryption.encrypt(customerEntity.getFirstName()));
        }
        if (customerEntity.getLastName() != null) {
            customerEntity.setEncryptedLastName(encryption.encrypt(customerEntity.getLastName()));
        }
        if (customerEntity.getEmail() != null) {
            customerEntity.setEncryptedEmail(encryption.encrypt(customerEntity.getEmail()));
        }
        if (customerEntity.getPhoneNumber() != null) {
            customerEntity.setEncryptedPhoneNumber(encryption.encrypt(customerEntity.getPhoneNumber()));
        }
        if (customerEntity.getAddress() != null) {
            customerEntity.setEncryptedAddress(encryption.encrypt(customerEntity.getAddress()));
        }
        if (customerEntity.getCity() != null) {
            customerEntity.setEncryptedCity(encryption.encrypt(customerEntity.getCity()));
        }
        if (customerEntity.getState() != null) {
            customerEntity.setEncryptedState(encryption.encrypt(customerEntity.getState()));
        }
        if (customerEntity.getZipCode() != null) {
            customerEntity.setEncryptedZipCode(encryption.encrypt(customerEntity.getZipCode()));
        }
        if (customerEntity.getPersonalIdentificationNumber() != null) {
            customerEntity.setEncryptedPersonalIdentificationNumber(encryption.encrypt(customerEntity.getPersonalIdentificationNumber()));
        }
        return customerEntity;
    }

    @Override
    public MainCustomerEntity decrypt (MainCustomerEntity customerEntity) throws Exception {
        if (customerEntity.getEncryptedFirstName() != null) {
            customerEntity.setFirstName(encryption.decrypt(customerEntity.getEncryptedFirstName()));
        }
        if (customerEntity.getEncryptedLastName() != null) {
            customerEntity.setLastName(encryption.decrypt(customerEntity.getEncryptedLastName()));
        }
        if (customerEntity.getEncryptedEmail() != null) {
            customerEntity.setEmail(encryption.decrypt(customerEntity.getEncryptedEmail()));
        }
        if (customerEntity.getEncryptedPhoneNumber() != null) {
            customerEntity.setPhoneNumber(encryption.decrypt(customerEntity.getEncryptedPhoneNumber()));
        }
        if (customerEntity.getEncryptedAddress() != null) {
            customerEntity.setAddress(encryption.decrypt(customerEntity.getEncryptedAddress()));
        }
        if (customerEntity.getEncryptedCity() != null) {
            customerEntity.setCity(encryption.decrypt(customerEntity.getEncryptedCity()));
        }
        if (customerEntity.getEncryptedState() != null) {
            customerEntity.setState(encryption.decrypt(customerEntity.getEncryptedState()));
        }
        if (customerEntity.getEncryptedZipCode() != null) {
            customerEntity.setZipCode(encryption.decrypt(customerEntity.getEncryptedZipCode()));
        }
        if (customerEntity.getEncryptedPersonalIdentificationNumber() != null) {
            customerEntity.setPersonalIdentificationNumber(encryption.decrypt(customerEntity.getEncryptedPersonalIdentificationNumber()));
        }
        return customerEntity;
    }

    @Override
    public InsuredPersonEntity encrypt (InsuredPersonEntity insuredPersonEntity) throws Exception {

        if (insuredPersonEntity.getFirstName() != null) {
            insuredPersonEntity.setEncryptedFistName(encryption.encrypt(insuredPersonEntity.getFirstName()));
        }
        if (insuredPersonEntity.getLastName() != null) {
            insuredPersonEntity.setEncryptedLastName(encryption.encrypt(insuredPersonEntity.getLastName()));
        }
        if (insuredPersonEntity.getDateOfBirth() != null) {
            insuredPersonEntity.setEncryptedDateOfBirth(encryption.encrypt(insuredPersonEntity.getDateOfBirth().toString()));
        }
        return insuredPersonEntity;
    }

    @Override
    public InsuredPersonEntity decrypt (InsuredPersonEntity insuredPersonEntity) throws Exception {

        if (insuredPersonEntity.getEncryptedFistName() != null) {
            insuredPersonEntity.setFirstName(encryption.decrypt(insuredPersonEntity.getEncryptedFistName()));
        }
        if (insuredPersonEntity.getEncryptedLastName() != null) {
            insuredPersonEntity.setLastName(encryption.decrypt(insuredPersonEntity.getEncryptedLastName()));
        }
        if (insuredPersonEntity.getEncryptedDateOfBirth() != null) {
            insuredPersonEntity.setDateOfBirth(LocalDate.parse(encryption.decrypt(insuredPersonEntity.getEncryptedDateOfBirth())));
        }
        return insuredPersonEntity;
    }
}
