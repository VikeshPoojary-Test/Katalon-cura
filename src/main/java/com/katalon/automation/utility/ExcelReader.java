package com.katalon.automation.utility;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {

    public static String[][] getDataSheetTCDataXSS(String workBook, String testCaseName) throws IOException {

        String[][] array2 = null;
        String[][] filterValue = null;
        String[][] sheetData = null;


        String fileName=System.getProperty("user.dir")+"/"+workBook;
        FileInputStream fis = new FileInputStream(fileName);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet worksheet = workbook.getSheetAt(0);

        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

        int rowCount = worksheet.getPhysicalNumberOfRows(); //Number of rows in sheet
        XSSFRow rows = worksheet.getRow(0);
        int colCount = rows.getLastCellNum();

        sheetData = new String[rowCount][colCount];

        for (int readRow = 0; readRow < rowCount; readRow++) {
            for (int readCol = 0; readCol < colCount; readCol++) {
                XSSFRow rowNum = worksheet.getRow(readRow);
                XSSFCell currCell = rowNum.getCell((short) readCol);

                switch (formulaEvaluator.evaluate(currCell).getCellType()) {
                    case NUMERIC:
                    case STRING:
                    case BLANK:
                    case BOOLEAN:
                        sheetData[readRow][readCol] = (new DataFormatter().formatCellValue(currCell));
                        break;
                }
            }
        }

        List<String[]> l = new ArrayList<String[]>(Arrays.asList(sheetData));
        l.remove(0);

        array2 = l.toArray(new String[][]{});
        int countFilter = 0;

        for (int row = 0; row < array2.length; row++) {
            if (array2[row][0].equals(testCaseName)) {
                countFilter++;
            }
        }

        filterValue = new String[countFilter][colCount - 1];

        int finalFilterRow = 0;
        boolean filterValueFound = false;

        for (int row = 0; row < array2.length; row++) {
            for (int col = 1; col < array2[row].length; col++) {
                if (array2[row][0].equals(testCaseName)) {
                    filterValue[finalFilterRow][col - 1] = array2[row][col];
                    filterValueFound = true;
                }
            }
            if (filterValueFound) {
                finalFilterRow++;
            }
        }
        workbook.close();
        return filterValue;
    }
}


