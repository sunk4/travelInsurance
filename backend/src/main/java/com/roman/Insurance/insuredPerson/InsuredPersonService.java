package com.roman.Insurance.insuredPerson;

import com.roman.Insurance.insuredPerson.request.InsuredPersonRequest;
import com.roman.Insurance.insuredPerson.response.InsuredPersonResponse;

import java.util.List;
import java.util.UUID;

public interface InsuredPersonService {
    void createInsuredPerson (
            List<InsuredPersonRequest> insuredPersonRequest,
            UUID insuranceId
    ) throws Exception;

    List<InsuredPersonResponse> getInsuredPersons (List<InsuredPersonRequest> insuredPersonRequest);
}
