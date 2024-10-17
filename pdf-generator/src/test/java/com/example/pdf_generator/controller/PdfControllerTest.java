package com.example.pdf_generator.controller;

import com.example.pdf_generator.tdo.InvoiceRequestDto;
import com.example.pdf_generator.service.PdfService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class PdfControllerTest {

    @InjectMocks
    private PdfController pdfController;

    @Mock
    private PdfService pdfService;

    private InvoiceRequestDto invoiceRequestDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        invoiceRequestDto = new InvoiceRequestDto();
        invoiceRequestDto.setSeller("XYZ Pvt. Ltd");
        invoiceRequestDto.setSellerAddress("Bangalore, India");
        invoiceRequestDto.setBuyer("Vedant Computers");
        invoiceRequestDto.setBuyerAddress("Kurnool, India");
        // Add items if necessary
    }

    @Test
    void testGeneratePdfSuccess() throws IOException {
        // Mocking the behavior of pdfService
        byte[] mockPdf = new byte[0]; // Replace with actual byte array if needed
        when(pdfService.generatePdf(any(InvoiceRequestDto.class))).thenReturn(mockPdf);

        // Making the API call
        ResponseEntity<byte[]> response = pdfController.generatePdf(invoiceRequestDto);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("attachment; filename=invoice.pdf", response.getHeaders().getFirst("Content-Disposition"));
    }

    @Test
    void testGeneratePdfIOException() throws IOException {
        // Mocking the behavior of pdfService to throw IOException
        when(pdfService.generatePdf(any(InvoiceRequestDto.class))).thenThrow(new IOException());

        // Making the API call
        ResponseEntity<byte[]> response = pdfController.generatePdf(invoiceRequestDto);

        // Assertions
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
}
