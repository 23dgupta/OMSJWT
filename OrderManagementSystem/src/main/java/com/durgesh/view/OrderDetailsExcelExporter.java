package com.durgesh.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.durgesh.model.OrderDetails;

public class OrderDetailsExcelExporter {
	
	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<OrderDetails> orderDetails;
     
    public  OrderDetailsExcelExporter(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
        workbook = new XSSFWorkbook();
    }
    
    private void writeHeaderLine() {
        sheet = workbook.createSheet("OrderDetails");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "orderId", style);      
        createCell(row, 1, "customerName", style);       
        createCell(row, 2, "orderName", style);    
        createCell(row, 3, "price", style);
        createCell(row, 4, "brand", style);
        createCell(row, 5, "isActive", style);
         
    }
    
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
    
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (OrderDetails od : orderDetails) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, od.getOrderId(), style);
            createCell(row, columnCount++, od.getCustomerName(), style);
            createCell(row, columnCount++,od.getOrderName(), style);
            createCell(row, columnCount++,od.getPrice().toString(), style);
            createCell(row, columnCount++,od.getBrand(), style);
           
            createCell(row, columnCount++,od.getIsActive(), style);
             
        }
    }
    
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }

}
