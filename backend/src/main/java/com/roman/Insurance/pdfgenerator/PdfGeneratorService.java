package com.roman.Insurance.pdfgenerator;

import com.roman.Insurance.calculation.CalculationDto;
import com.roman.Insurance.mainCustomer.MainCustomerEntity;

public interface PdfGeneratorService {
    byte[] generatePdf(MainCustomerEntity mainCustomer);


}
