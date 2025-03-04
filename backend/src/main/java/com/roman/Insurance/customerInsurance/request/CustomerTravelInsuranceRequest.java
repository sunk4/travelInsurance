package com.roman.Insurance.customerInsurance.request;

import com.roman.Insurance.insurance.request.InsuranceRequest;
import com.roman.Insurance.insuredPerson.request.InsuredPersonRequest;
import com.roman.Insurance.mainCustomer.request.MainCustomerRequest;

import java.util.List;

public record CustomerTravelInsuranceRequest(
        MainCustomerRequest mainCustomerRequest,
        List<InsuredPersonRequest> insuredPersonRequest,
        InsuranceRequest insuranceRequest

) {

}
