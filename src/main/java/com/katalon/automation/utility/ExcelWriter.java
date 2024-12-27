package com.katalon.automation.utility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelWriter {

    private static int getRowIndexNumber(String fileName, String text) throws IOException {
        FileInputStream file = new FileInputStream(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        //Identify Testcases coloumn by scanning the entire 1st row
        Iterator<Row> rows = sheet.iterator();

        //sheet is collection of rows
        int k = 1;
        int rowIndex = -1;
        while (rows.hasNext()) {
            Row row = rows.next();
            Iterator<Cell> cells = row.cellIterator();
            while (cells.hasNext()){
                Cell cell = cells.next();
                if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equalsIgnoreCase(text)) {
                    rowIndex = k;
                }
            }
            k++;
        }
        return rowIndex;
    }

    private static int getColumnNumber(String fileName, String columnValue) throws IOException {
        FileInputStream file = new FileInputStream(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        // dentify Testcases coloum by scanning the entire 1st row
        Iterator<Row> rows = sheet.iterator();

        //sheet is collection of rows
        Row firstrow = rows.next();
        Iterator<Cell> ce = firstrow.cellIterator();
        //row is collection of cells
        int k = 1;
        int coloumn = 0;

        while (ce.hasNext()) {
            Cell value = ce.next();

            if (value.getStringCellValue().equalsIgnoreCase(columnValue)) {
                coloumn = k;
            }
            k++;
        }
        return coloumn;
    }

    private static boolean updateCell(String fileName, int row, int col, String updatedValue) throws IOException {

    FileInputStream file = new FileInputStream(fileName);
    XSSFWorkbook workbook = new XSSFWorkbook(file);
    XSSFSheet sheet = workbook.getSheetAt(0);

    Row rowField = sheet.getRow(row - 1);
    Cell cellField = rowField.getCell(col - 1);

    cellField.setCellValue(updatedValue);

    FileOutputStream fos = new FileOutputStream(fileName);
    workbook.write(fos);
    workbook.close();
    file.close();
    return true;
    }
}

