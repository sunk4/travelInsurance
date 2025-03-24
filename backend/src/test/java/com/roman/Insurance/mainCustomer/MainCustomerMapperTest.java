package com.roman.Insurance.mainCustomer;

import com.roman.Insurance.mainCustomer.request.MainCustomerRequest;
import com.roman.Insurance.mainCustomer.response.MainCustomerResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MainCustomerMapperTest {
    private final MainCustomerMapper mapper = Mappers.getMapper(MainCustomerMapper.class);

    @Test
    void toEntity () {
        MainCustomerRequest request = new MainCustomerRequest("John", "Doe", "john.doe@example.com", "+1234567890", "123 Main St", "New York", "NY", "10001", "123-45-6789");

        MainCustomerEntity entity = mapper.toEntity(request);

        assertNotNull(entity);
        assertEquals(request.firstName(), entity.getFirstName());
        assertEquals(request.lastName(), entity.getLastName());
    }

    @Test
    void toDto () {
        MainCustomerEntity entity = MainCustomerEntity.builder().
                id(UUID.randomUUID()).
                firstName("John").
                lastName("Doe").
                email("john.doe@example.com").
                phoneNumber("+1234567890").
                address("123 Main St").
                city("New York").
                state("NY").
                zipCode("10001").
                createdAt(LocalDateTime.now()).
                updatedAt(LocalDateTime.now()).
                build();
        MainCustomerResponse response = mapper.toDto(entity);

        assertNotNull(response);
        assertEquals(entity.getId(), response.id());
        assertEquals(entity.getFirstName(), response.firstName());
    }

    @Test
    void entityListToDto () {
        MainCustomerEntity entity = MainCustomerEntity.builder().
                id(UUID.randomUUID()).
                firstName("John").
                lastName("Doe").
                email("john.doe@example.com").
                phoneNumber("+1234567890").
                address("123 Main St").
                city("New York").
                state("NY").
                zipCode("10001").
                createdAt(LocalDateTime.now()).
                updatedAt(LocalDateTime.now()).
                build();
        List<MainCustomerResponse> response =
                mapper.entityListToDto(Collections.singletonList(entity));

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(entity.getFirstName(), response.get(0).firstName());
    }
}