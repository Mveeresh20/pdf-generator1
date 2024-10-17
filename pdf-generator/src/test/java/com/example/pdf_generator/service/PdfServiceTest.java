package com.example.pdf_generator.service;

import com.example.pdf_generator.tdo.InvoiceRequestDto;
import com.example.pdf_generator.umodel.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PdfServiceTest {
    
    private PdfService pdfService;

    @BeforeEach
    void setUp() {
        pdfService = new PdfService();
    }

    @Test
    void testGeneratePdfSuccess() throws IOException {
        // Arrange
        InvoiceRequestDto invoiceRequestDto = new InvoiceRequestDto();
        invoiceRequestDto.setSeller("XYZ Pvt. Ltd");
        invoiceRequestDto.setSellerAddress("Bangalore, India");
        invoiceRequestDto.setBuyer("Vedant Computers");
        invoiceRequestDto.setBuyerAddress("Kurnool, India");

        List<Item> items = new ArrayList<>();
        Item item = new Item();
        item.setName("Refrigerator");
        item.setQuantity("30");
        item.setRate(200000.00);
        item.setAmount(6000000.00);
        items.add(item);
        invoiceRequestDto.setItems(items);

        // Act
        byte[] pdfBytes = pdfService.generatePdf(invoiceRequestDto);

        // Assert
        assertNotNull(pdfBytes);  // Ensure that the PDF bytes are not null
        assertTrue(pdfBytes.length > 0);  // Ensure that PDF content is generated
    }
}
