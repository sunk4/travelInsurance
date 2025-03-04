package com.roman.Insurance.customerInsurance;

import com.roman.Insurance.calculation.CalculationService;
import com.roman.Insurance.calculation.response.CalculationResponse;
import com.roman.Insurance.customerInsurance.request.CustomerTravelInsuranceRequest;
import com.roman.Insurance.email.EmailService;
import com.roman.Insurance.insurance.InsuranceService;
import com.roman.Insurance.insuredPerson.InsuredPersonService;
import com.roman.Insurance.mainCustomer.MainCustomerEntity;
import com.roman.Insurance.mainCustomer.MainCustomerService;
import com.roman.Insurance.pdfgenerator.PdfGeneratorService;
import com.roman.Insurance.s3Bucket.UploadService;
import com.roman.Insurance.stripe.StripeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerInsuranceServiceImpl implements CustomerInsuranceService {
    private final MainCustomerService customerService;
    private final InsuredPersonService insurePersonService;
    private final InsuranceService insuranceService;
    private final CalculationService calculationService;
    private final PdfGeneratorService pdfGeneratorService;
    private final UploadService uploadService;
    private final EmailService emailService;
    private final StripeService stripeService;

    @Override
    @Transactional
    public void createTravelInsurance (CustomerTravelInsuranceRequest customerTravelInsuranceRequest) throws Exception {
        UUID mainCustomerId = customerService.createMainCustomer(customerTravelInsuranceRequest.mainCustomerRequest());
        CalculationResponse calculationDto =
                calculationService.calculatePrice(customerTravelInsuranceRequest);
        UUID insuranceId =
                insuranceService.createInsurance(customerTravelInsuranceRequest.insuranceRequest(), mainCustomerId, calculationDto.totalCalculatedPrice());

        insurePersonService.createInsuredPerson(customerTravelInsuranceRequest.insuredPersonRequest(), insuranceId);
        MainCustomerEntity mainCustomer = customerService.getCustomerByIdEncrypted(mainCustomerId);

        byte[] generatedPdf = pdfGeneratorService.generatePdf(mainCustomer);
        String pdfUrl = uploadService.uploadFileToS3(generatedPdf,
                mainCustomerId.toString() +
                        mainCustomer.getLastName() + mainCustomer.getFirstName());

        insuranceService.updateUrlPreview(insuranceId, pdfUrl);

        double totalPrice = mainCustomer.getInsurances().get(0).getTotalPrice();
        String description = mainCustomer.getEmail();

        String paymentLink = stripeService.createPaymentLink(totalPrice, "EUR"
                , description, mainCustomerId, insuranceId);

        emailService.sendEmailWithGeneratedAttachment(mainCustomer.getEmail(),
                paymentLink, "Travel Insurance", "emailTemplate", generatedPdf, mainCustomer.getLastName() + mainCustomer.getFirstName() + ".pdf");

    }
}
