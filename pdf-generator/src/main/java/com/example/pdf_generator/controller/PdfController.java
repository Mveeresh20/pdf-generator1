package com.example.pdf_generator.controller;



import com.example.pdf_generator.tdo.*;
import com.example.pdf_generator.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {
    @Autowired
    private PdfService pdfService;

    @PostMapping(value = "/generate", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePdf(@RequestBody InvoiceRequestDto invoiceRequestDto) {
        try {
            byte[] pdfBytes = pdfService.generatePdf(invoiceRequestDto);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=invoice.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


	


