package com.durgesh.view;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.durgesh.model.OrderDetails;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class OrderDetailsPDFExporter {
	
	  private List<OrderDetails> orderDetails;
	     
	    public  OrderDetailsPDFExporter(List<OrderDetails> orderDetails) {
	        this.orderDetails = orderDetails;
	       
	    }
	    
	    private void writeTableHeader(PdfPTable table) {
	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(Color.BLUE);
	        cell.setPadding(5);
	         
	        Font font = FontFactory.getFont(FontFactory.HELVETICA);
	        font.setColor(Color.WHITE);
	         
	        cell.setPhrase(new Phrase("order_Id", font));
	         
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("customerName", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("orderName", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("price", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("brand", font));
	        table.addCell(cell); 
	        
	        cell.setPhrase(new Phrase("isActive", font));
	        table.addCell(cell); 
	    }
	    private void writeTableData(PdfPTable table) {
	        for (OrderDetails od : orderDetails) {
	            table.addCell(String.valueOf(od.getOrderId()));
	            table.addCell(od.getCustomerName());
	            table.addCell(od.getOrderName());
	            table.addCell(od.getPrice().toString());
	            table.addCell(od.getBrand());
	            table.addCell(String.valueOf(od.getIsActive()));
	        }
	    }
	    public void export(HttpServletResponse response) throws DocumentException, IOException {
	        Document document = new Document(PageSize.A4);
	        PdfWriter.getInstance(document, response.getOutputStream());
	         
	        document.open();
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(18);
	        font.setColor(Color.BLUE);
	         
	        Paragraph p = new Paragraph("List of Orderdetails", font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	         
	        document.add(p);
	         
	        PdfPTable table = new PdfPTable(6);
	        table.setWidthPercentage(100f);
	        table.setWidths(new float[] {2.5f, 3.5f, 3.0f, 3.0f, 2.5f,3.0f});
	        table.setSpacingBefore(10);
	         
	        writeTableHeader(table);
	        writeTableData(table);
	         
	        document.add(table);
	         
	        document.close();
	         
	    }
	}