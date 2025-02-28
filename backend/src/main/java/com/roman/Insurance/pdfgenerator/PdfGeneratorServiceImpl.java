package com.roman.Insurance.pdfgenerator;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.roman.Insurance.mainCustomer.MainCustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PdfGeneratorServiceImpl implements PdfGeneratorService {
    private final TemplateEngine templateEngine;

    @Override
    public byte[] generatePdf (MainCustomerEntity mainCustomer) {
        Context context = new Context();
        context.setVariable("mainCustomer", mainCustomer);

        String htmlContent = templateEngine.process("contract", context);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();

            String baseUrl = new ClassPathResource("images/").getURL().toExternalForm();
            String imagePath = new ClassPathResource("images/logo.png").getURL().toExternalForm();
            htmlContent = htmlContent.replace("/images/logo.png", imagePath);
            File fontFile = new ClassPathResource("static/fonts/NotoSans-Regular.ttf").getFile();
            builder.useFont(fontFile, "Noto Sans");

            builder.withHtmlContent(htmlContent, baseUrl);
            builder.toStream(outputStream);
            builder.run();

            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
