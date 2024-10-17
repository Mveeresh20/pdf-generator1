package com.example.pdf_generator.service;



import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.example.pdf_generator.tdo.InvoiceRequestDto;
import com.example.pdf_generator.umodel.Item;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {
	public byte[] generatePdf(InvoiceRequestDto invoiceRequestDto) throws IOException {
	    Document document = new Document();
	    ByteArrayOutputStream out = new ByteArrayOutputStream();

	    try {
	        PdfWriter.getInstance(document, out);
	        document.open();

	        // Create PDF table
	        PdfPTable table = new PdfPTable(2);
	        table.setWidthPercentage(100);

	        // Seller Details
	        PdfPCell sellerCell = new PdfPCell();
	        sellerCell.addElement(new Paragraph("Seller: " + invoiceRequestDto.getSeller()));
	        sellerCell.addElement(new Paragraph("GSTIN: " + invoiceRequestDto.getSellerGstin()));
	        sellerCell.addElement(new Paragraph("Address: " + invoiceRequestDto.getSellerAddress()));
	        table.addCell(sellerCell);

	        // Buyer Details
	        PdfPCell buyerCell = new PdfPCell();
	        buyerCell.addElement(new Paragraph("Buyer: " + invoiceRequestDto.getBuyer()));
	        buyerCell.addElement(new Paragraph("GSTIN: " + invoiceRequestDto.getBuyerGstin()));
	        buyerCell.addElement(new Paragraph("Address: " + invoiceRequestDto.getBuyerAddress()));
	        table.addCell(buyerCell);

	        document.add(table);

	        // Create Item Details Table
	        PdfPTable itemTable = new PdfPTable(4);
	        itemTable.setWidthPercentage(100);
	        itemTable.addCell("Item");
	        itemTable.addCell("Rate");
	        itemTable.addCell("Quantity");
	        itemTable.addCell("Amount");

	        for (Item item : invoiceRequestDto.getItems()) {
	            itemTable.addCell(item.getName());
	            itemTable.addCell(String.valueOf(item.getRate()));
	            itemTable.addCell(item.getQuantity()); // quantity is a String now
	            itemTable.addCell(String.valueOf(item.getAmount()));
	        }

	        document.add(itemTable);
	        document.close();

	    } catch (DocumentException e) {
	        e.printStackTrace();
	    }

	    return out.toByteArray();
	}

}


	
	


