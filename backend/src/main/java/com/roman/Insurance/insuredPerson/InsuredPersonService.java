package com.roman.Insurance.insuredPerson;

import java.util.List;
import java.util.UUID;

public interface InsuredPersonService {
    List<UUID> createInsuredPerson (
            List<InsuredPersonDTO> insuredPersonDTO,
            UUID insuranceId
    ) throws Exception;

    List<InsuredPersonDTO> getInsuredPersons(List<InsuredPersonDTO> insuredPersonDTO);
}
